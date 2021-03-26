package view

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.mFab
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.themeContext
import kotlinx.css.*
import react.RBuilder
import react.RProps
import react.RState
import styled.*
import util.AppUtils
import util.ComponentStyles.buttonMargin

class ContentDetailsComponent(props: RProps): AppBaseComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        themeContext.Consumer { theme ->
            val themeStyle = object : StyleSheet("ComponentStyles", isStatic = true) {
                val detailRoot by css {
                    width = LinearDimension.auto
                    display = Display.flex
                    flexDirection = FlexDirection.row
                }

                val image by css {
                    width = LinearDimension("40%")
                    height = LinearDimension.initial
                    marginRight = LinearDimension("3%")
                }

            }
            val content = AppUtils.getEntry(AppUtils.getParamFromUrl("id"))
            styledDiv {
                styledDiv{
                    css{+themeStyle.detailRoot}
                    styledImg {
                        css{+themeStyle.image}

                        attrs{
                            src = content.thumbnail
                        }
                    }

                    styledDiv {
                        styledH1{+content.name
                            css {
                                marginBottom = LinearDimension("30px")
                            }}
                        mTypography(content.description)
                    }
                }
                mFab("edit", "  Edit  ", color = MColor.secondary, onClick = {
                    AppUtils.go("ContentEdit", mapOf("id" to content.id))
                }) { css(buttonMargin) }
            }

        }
    }
}