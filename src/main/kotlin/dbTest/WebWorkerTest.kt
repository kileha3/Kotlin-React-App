package dbTest

import org.w3c.dom.Worker
import react.RBuilder
import react.RProps
import react.RState
import view.AppBaseComponent

class WebWorkerTest(props: RProps): AppBaseComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        val worker = Worker("worker.sql-wasm.js")
        worker.onmessage =  {
            val data = it.data.asDynamic(); val id = data["id"].toString().toInt()
            when (id) {
                3 -> {
                    console.log(id,data["results"][0].values)
                }
                4 -> {
                    console.log("Export Query", data)
                } else -> {
                console.log("Success Query", if(data["results"]) data["results"] else data)
            }
            }
            it
        }

        worker.onerror = {
            console.log("Error", it)
        }

        worker.postMessage(JSON.parse<Any>("{\"id\":\"1\",\"action\":\"open\"}"))
        worker.postMessage(JSON.parse<Any>("{ \"id\": \"2\", \"action\": \"exec\", \"sql\": \"CREATE TABLE Person (username, password);\" }"))
        worker.postMessage(JSON.parse<Any>("{ \"id\": \"2\", \"action\": \"exec\", \"sql\": \"INSERT INTO Person (username, password) VALUES ('John','1234'), ('Doe','2345')\" }"))
        worker.postMessage(JSON.parse<Any>("{ \"id\": \"3\", \"action\": \"exec\", \"sql\": \"SELECT * FROM Person WHERE username=:name\", \"params\":{\":name\":\"John\"}}"))
        worker.postMessage(JSON.parse<Any>("{\"id\":\"4\",\"action\":\"export\"}"))
    }
}

fun RBuilder.webWorker() = child(WebWorkerTest::class) {}