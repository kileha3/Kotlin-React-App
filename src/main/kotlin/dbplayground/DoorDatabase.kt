package dbplayground

import dbplayground.interfaces.Connection
import dbplayground.wrappers.IndexedDb.checkIfExists
import dbplayground.wrappers.SQLiteDatasourceJs
import org.w3c.dom.Worker

class Database {

    internal lateinit var dataSource: SQLiteDatasourceJs

    var exists: Boolean = false

    suspend fun init(dbName: String,workerPath: String){
        dataSource = SQLiteDatasourceJs(dbName, Worker(workerPath))
        exists = checkIfExists(dbName)
        if(exists){
            dataSource.loadDbFromIndexedDb()
        }else{
            createAllTables()
            preloadData()
        }
    }

    fun openConnection() : Connection {
        return dataSource.getConnection()
    }

    val personDao = PersonDao(this)
}