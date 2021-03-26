package dbplayground.interfaces

interface Connection {

    fun setAutoCommit(commit: Boolean)

    fun prepareStatement(param: String?): PreparedStatement

    fun commit()

    fun close()
}