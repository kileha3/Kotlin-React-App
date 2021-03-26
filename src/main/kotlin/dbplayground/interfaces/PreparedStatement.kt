package dbplayground.interfaces

import dbplayground.types.BigDecimal
import dbplayground.types.Date
import dbplayground.types.Time
import dbplayground.types.TimeStamp

interface PreparedStatement {

    fun setBoolean(index: Int, value: Boolean)

    fun setByte(index: Int, value: Byte)

    fun setShort(index: Int, value: Short)

    fun setInt(index: Int, value: Int)

    fun setLong(index: Int, value: Long)

    fun setFloat(index: Int, value: Float)

    fun setDouble(index: Int, value: Double)

    fun setBigDecimal(index: Int, value: BigDecimal)

    fun setString(index: Int, value: String?)

    fun setBytes(index: Int, value: ByteArray)

    fun setDate(index: Int, value: Date)

    fun setTime(index: Int, value: Time)

    fun setTimestamp(index: Int, value: TimeStamp)

    fun setObject(index: Int, value: Any)

    fun setArray(index: Int, value: Array<Any>)

    fun executeUpdate(): Int

    suspend fun executeUpdateAsync(): Int

    suspend fun executeQueryAsyncInt(): ResultSet

    fun executeQuery(): ResultSet

    fun close()

}