package dbplayground

import kotlin.js.json

suspend fun Database.createAllTables() {
    dataSource.sendMessage(json("action" to "exec", "sql" to "CREATE TABLE IF NOT EXISTS PersonEntity(username, password)"))
}

suspend fun Database.preloadData() {
    personDao.insertListAsync(listOf(
        PersonEntity().apply {
            username = "john"
            password = "12345"},
        PersonEntity().apply {
            username = "sarah"
            password = "45679"},
    ))
    dataSource.importDbToIndexedDb()
}