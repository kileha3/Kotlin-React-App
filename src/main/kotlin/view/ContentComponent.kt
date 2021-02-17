package view

import react.RBuilder
import react.RProps
import react.RState
import styled.styledDiv

class ContentComponent(props: RProps): AppBaseComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        styledDiv {
            +"Showing Content data"
        }
    }
}