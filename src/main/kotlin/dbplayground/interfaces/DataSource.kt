package dbplayground.interfaces

interface DataSource {

   fun getConnection(): Connection

}