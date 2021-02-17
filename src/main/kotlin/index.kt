import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render
import styled.styledDiv

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
           styledDiv {
               +"App running"
           }
        }
    }
}
