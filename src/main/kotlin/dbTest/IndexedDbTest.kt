package dbTest

import react.RBuilder
import react.RProps
import react.RState
import view.AppBaseComponent

class IndexedDbTest(props: RProps): AppBaseComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        val indexedDb = js("window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB")
        val request = indexedDb.open("UmDatabase", 1)
        console.log(request)
    }
}

fun RBuilder.indexedDb() = child(IndexedDbTest::class) {}