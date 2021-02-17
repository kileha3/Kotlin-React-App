package view

import react.*

open class AppBaseComponent<P:RProps,S:RState>(props: P): RComponent<P, S>(props), AppBaseView {
    override fun RBuilder.render() {}
}