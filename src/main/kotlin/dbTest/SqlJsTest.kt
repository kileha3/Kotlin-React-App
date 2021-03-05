package dbTest

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import react.RBuilder
import react.RProps
import react.RState
import view.AppBaseComponent

class SqlJsTest(props: RProps): AppBaseComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        GlobalScope.launch {
            val SQL = initSqlJs().await()
            val db = js("new SQL.Database()")
            db.run("CREATE TABLE Person (username, password);")
            db.run("INSERT INTO Person VALUES (?,?), (?,?)", arrayOf("John","12345","James","12346"))
            val stmt = db.prepare("SELECT * FROM Person WHERE username=?")
            stmt.bind(arrayOf("John"))
            do{
                val row = stmt.getAsObject()
                console.log("Person: " + JSON.stringify(row))
            }while (stmt.step())
        }
    }
}

fun RBuilder.sqlJs() = child(SqlJsTest::class) {}