package view

import com.ccfraser.muirwik.components.list.*
import com.ccfraser.muirwik.components.mDivider
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.themeContext
import kotlinx.css.*
import react.RBuilder
import react.RProps
import react.RState
import react.dom.span
import styled.StyleSheet
import styled.css
import styled.styledDiv
import styled.styledImg
import util.AppUtils
import util.ComponentStyles.listRoot

class ContentComponent(props: RProps): AppBaseComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        val builder = RBuilder()
        themeContext.Consumer { theme ->
            val themeStyle = object : StyleSheet("ComponentStyles", isStatic = true) {
                val list by css {
                    width = LinearDimension.auto
                    backgroundColor = Color(theme.palette.background.paper)
                }
            }


            styledDiv {
                css(listRoot)
                mList {
                    css(themeStyle.list)
                    AppUtils.entryList().forEach { entry ->
                        mListItem( alignItems = MListItemAlignItems.flexStart, button = true,divider = true, onClick = {
                            AppUtils.go("ContentDetails", mapOf("id" to entry.id))
                        }) {
                            styledImg {
                                css {
                                    marginRight = 20.px
                                }
                                attrs{
                                    src = entry.thumbnail
                                    width = "100px"
                                }
                            }
                            mListItemText( builder.span {+entry.name }, builder.span {
                                mTypography(entry.description)
                            })
                        }
                    }
                }
            }
        }
    }
}