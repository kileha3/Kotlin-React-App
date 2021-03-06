package dbplayground.wrappers
import dbplayground.interfaces.Connection
import dbplayground.interfaces.DataSource
import dbplayground.wrappers.IndexedDb.DATABASE_VERSION
import dbplayground.wrappers.IndexedDb.DB_STORE_KEY
import dbplayground.wrappers.IndexedDb.DB_STORE_NAME
import dbplayground.wrappers.IndexedDb.indexedDb
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Worker
import kotlin.js.Json
import kotlin.js.json

/**
 * Class responsible to manage all SQLite worker tasks
 */
class SQLiteDatasourceJs(private val dbName: String, private val worker: Worker) : DataSource {

    private val pendingMessages = mutableMapOf<Int, CompletableDeferred<WorkerResult>>()

    init {
        worker.onmessage = { it: dynamic ->
            val pendingCompletable = pendingMessages.remove(it.data["id"].toString().toInt())
            if(pendingCompletable != null){
                pendingCompletable.complete(
                    WorkerResult(it.data["id"], it.data["results"], it.data["ready"], it.data["buffer"])
                )
            }
        }
    }

    /**
     * Execute SQL task by sending a message via Worker
     * @param message message to be sent for SQLJs to execute
     */
    suspend fun sendMessage(message: Json): WorkerResult {
        val completable = CompletableDeferred<WorkerResult>()
        val actionId = ++idCounter
        pendingMessages[actionId] = completable
        message["id"] = actionId
        worker.postMessage(message)
        return completable.await()
    }

    /**
     * Load a stored database from indexed database to SQLJs database
     */
    suspend fun loadDbFromIndexedDb(): Boolean {
        val exportCompletable = CompletableDeferred<Boolean>()
        val request = indexedDb.open(dbName, DATABASE_VERSION)
        request.onsuccess =  { event: dynamic ->
            val db = event.target.result
            val store = db.transaction(DB_STORE_NAME, "readwrite").objectStore(DB_STORE_NAME).get(DB_STORE_KEY)
            store.onsuccess = { data: dynamic ->
                GlobalScope.launch {
                    val result = sendMessage(json("action" to "open", "buffer" to data.target.result))
                    exportCompletable.complete(result.ready)
                }
            }
            store.onerror = {
                exportCompletable.completeExceptionally(
                    Throwable("Error when executing store data fetch"))
            }
        }
        request.onerror = {
            exportCompletable.completeExceptionally(
                Throwable("Error when importing database from IndexedDb to SQLite DB"))
        }
        return exportCompletable.await()
    }

    /**
     * Import SQLJs database to the indexed Database
     */
    suspend fun importDbToIndexedDb(): Boolean {
        val exportCompletable = CompletableDeferred<Boolean>()
        val result = sendMessage(json("action" to "export"))
        val request = indexedDb.open(dbName, DATABASE_VERSION)
        request.onsuccess = { event: dynamic ->
            val db = event.target.result
            val transaction = db.transaction(DB_STORE_NAME, "readwrite")
            transaction.oncomplete = {
                exportCompletable.complete(true)
            }
            transaction.onerror = {
                exportCompletable.completeExceptionally(
                    Throwable("Error when importing SQLJs database to IndexedDb")
                )
            }
            val store = transaction.objectStore(DB_STORE_NAME)
            store.put(result.buffer, DB_STORE_KEY)
        }
        return exportCompletable.await()
    }


    override fun getConnection(): Connection {
        return SQLiteConnectionJs(this)
    }


    companion object {
        var idCounter = 0
    }
}