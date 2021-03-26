package dbplayground


class PersonDao(private val database: Database) {

    suspend fun insertAsync(entity: PersonEntity){
        val connection = database.openConnection()
        val statement = connection.prepareStatement("INSERT INTO PersonEntity VALUES (?,?)")
        statement.setString(1, entity.username)
        statement.setString(2, entity.password)
        statement.executeUpdateAsync()
        connection.commit()
        connection.close()
    }

    suspend fun insertListAsync(entities: List<PersonEntity>){
        val connection = database.openConnection()
        val statement = connection.prepareStatement("INSERT INTO PersonEntity VALUES ${makeQueryValues(entities)}")
        makeQueryParams(entities).forEachIndexed { index, param ->
            statement.setString(index + 1,param.toString())
        }
        statement.executeUpdateAsync()
        connection.commit()
        connection.close()
    }

    private fun makeQueryValues(entities: List<PersonEntity>): String{
        val params = mutableListOf<String>()
        entities.map { params.add("(?,?)") }
        return params.joinToString(",")
    }

    private fun makeQueryParams(entities: List<PersonEntity>): Array<Any>{
        val params = mutableListOf<Any>()
        entities.forEach {
            params.add(it.username)
            it.password?.let { it1 -> params.add(it1) }
        }
        return params.toTypedArray()
    }

    suspend fun findByUsername(mUsername: String): PersonEntity?{
        val connection = database.openConnection()
        val statement = connection.prepareStatement("SELECT * FROM PersonEntity WHERE username = ?")
        statement.setString(1, mUsername)
        val resultSet = statement.executeQueryAsyncInt()
        if(resultSet.next()) {
            return PersonEntity().apply {
                username = resultSet.getString("username")?:""
                password = resultSet.getString("password")
            }
        }
        statement.close()
        resultSet.close()
        return null
    }

    suspend fun findAll(): List<PersonEntity> {
        val connection = database.openConnection()
        val statement = connection.prepareStatement("SELECT * FROM PersonEntity")
        val resultSet = statement.executeQueryAsyncInt()
        val result = mutableListOf<PersonEntity>()
        while(resultSet.next()) {
            result.add(PersonEntity().apply {
                username = resultSet.getString("username")?:""
                password = resultSet.getString("password")
            })
        }
        statement.close()
        resultSet.close()
        return result
    }
}