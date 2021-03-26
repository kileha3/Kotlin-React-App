package dbplayground

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.RBuilder
import react.RProps
import react.RState
import view.AppBaseComponent

class DbPlayground(props: RProps): AppBaseComponent<RProps, RState>(props) {

    private var database = Database()

    override fun RBuilder.render() {
        GlobalScope.launch(Dispatchers.Main) {
            database.init(DB_NAME, "worker.sql-wasm.js")
            val prefix = "Db => ${if(database.exists) "indexedDb" else "in-Memory"}"

            console.log("$prefix:findAll", JSON.parse(JSON.stringify(database.personDao.findAll())))
            console.log("$prefix:findByUsername", database.personDao.findByUsername("sarah"))
        }
    }

    companion object {
        val DB_NAME = "UmDbTest2"
    }
}

fun RBuilder.dbPlayground() = child(DbPlayground::class) {}