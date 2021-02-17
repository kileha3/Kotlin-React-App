package view

import react.RBuilder
import react.RProps
import react.RState
import styled.styledDiv

class SchoolsComponent(props: RProps): AppBaseComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        styledDiv {
            +"Showing Schools Content"
        }
    }
}