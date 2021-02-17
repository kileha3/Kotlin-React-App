package view

import AppBaseState
import util.ComponentStyles.rootDiv
import modal.Person
import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItemWithIcon
import com.ccfraser.muirwik.components.menu.mMenuItem
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import controller.MainComponentPresenter
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.css.*
import react.*
import react.dom.link
import react.router.dom.*
import styled.StyleSheet
import styled.css
import styled.styledDiv

interface MainComponentState: AppBaseState {
    var currentView: String
    var responsiveDrawerOpen: Boolean
    var personInfo: Person?
}

interface MainComponentProps: RProps{
    var initialView: String
    var onThemeChange: () -> Unit
    var onLocaleChange: (locale: String) -> Unit
}

class MainComponent(props: MainComponentProps): AppBaseComponent<MainComponentProps, MainComponentState>(props),
    MainComponentView {

    private var mPresenter: MainComponentPresenter = MainComponentPresenter(mapOf(), this)

    private val drawerWidth = 240.px


    init {
        GlobalScope.launch {
            withTimeout(3000){
                mPresenter.onCreate()
            }
        }
    }

    override fun MainComponentState.init(props: MainComponentProps) {
        currentView = props.initialView
        responsiveDrawerOpen = false
    }

    override fun RBuilder.render() {
        mCssBaseline()

        themeContext.Consumer { theme ->
            styledDiv {
                css {
                    flexGrow = 1.0
                    width = 100.pct
                    zIndex = 1
                    overflow = Overflow.hidden
                    position = Position.relative
                    display = Display.flex
                }

                styledDiv {

                    css { +rootDiv }

                    mAppBar(position = MAppBarPosition.absolute) {
                        css {
                            position = Position.absolute
                            marginLeft = drawerWidth
                            media(theme.breakpoints.up(Breakpoint.md)) {
                                width = 100.pct - drawerWidth
                            }
                        }

                        mToolbar {
                            mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                                mIconButton("menu", color = MColor.inherit, onClick = { mPresenter.handleUpdateDrawerState()})
                            }

                            mToolbarTitle(state.currentView)

                            mAvatar {
                                attrs{
                                    src = state.personInfo?.profile?:""
                                    sizes = "large"
                                    onClick = {mPresenter.handleThemeChange()}
                                }
                                +"${state.personInfo?.name?.subSequence(0,1)}"
                            }
                        }
                    }

                    val p: MPaperProps = jsObject { }
                    p.asDynamic().style = kotlinext.js.js {
                        position = "relative"; width = drawerWidth.value; display = "block"; height =
                        "100%"; minHeight = "100vh"
                    }
                    mHidden(mdUp = true) {
                        mDrawer(state.responsiveDrawerOpen, MDrawerAnchor.left, MDrawerVariant.temporary, paperProps = p,
                            onClose = { setState { responsiveDrawerOpen = !responsiveDrawerOpen }}) {
                            appBarSpacer()
                            drawerItems()
                        }
                    }
                    mHidden(smDown = true, implementation = MHiddenImplementation.css) {
                        mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = p) {
                            appBarSpacer()
                            drawerItems()
                        }
                    }

                    // Main content area
                    styledDiv {
                        css {
                            height = 100.pct
                            flexGrow = 1.0; minWidth = 0.px
                            backgroundColor = Color(theme.palette.background.default)
                        }
                        appBarSpacer()
                        styledDiv {
                            css {
                                media(theme.breakpoints.down(Breakpoint.sm)) {
                                    height = 100.vh - 57.px
                                }
                                media(theme.breakpoints.up(Breakpoint.sm)) {
                                    height = 100.vh - 65.px
                                }

                                overflowY = Overflow.auto
                                padding(2.spacingUnits)
                                backgroundColor = Color(theme.palette.background.default)
                            }
                            hashRouter {
                               switch{
                                   route("/Schools", SchoolsComponent::class, exact = true)
                                   route("/Content", ContentComponent::class, exact = true)
                               }
                           }
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.drawerItems(fullWidth: Boolean = false) {
        themeContext.Consumer { theme ->
            mList {
                css {
                    backgroundColor = Color(theme.palette.background.paper)
                    width = if (fullWidth) LinearDimension.auto else drawerWidth
                }

                mListItemWithIcon("library_books", "Content", divider = false, onClick = {
                    window.location.assign("#/Content")
                })
                mListItemWithIcon("school", "Schools", divider = false, onClick = {
                    window.location.assign("#/Schools")
                })
                mListItemWithIcon("group", "Classes", divider = false)
                mListItemWithIcon("pie_chart", "Report", divider = true)
                mListItemWithIcon("person", "People", divider = false)
                mListItemWithIcon("settings", "Settings", divider = false)
            }
        }
    }

    private fun RBuilder.appBarSpacer() {
        themeContext.Consumer { theme ->
            val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
                val toolbar by css {
                    toolbarJsCssToPartialCss(theme.mixins.toolbar)
                }
            }

            styledDiv {
                css(themeStyles.toolbar)
            }
            mDivider {  }
        }
    }

    override fun updateDrawerState() {
        setState {
            responsiveDrawerOpen = !responsiveDrawerOpen
        }
    }

    override fun updatePerson(person: Person) {
        setState{
            personInfo = person
        }
    }

    override fun onThemeChange() {
        props.onThemeChange()
    }

    override fun onLocaleChange(locale: String) {
        props.onLocaleChange(locale)
    }
}

fun RBuilder.initMainComponent(initialView: String, onThemeChange: () -> Unit,
                               onLocaleChange: (locale:String) -> Unit) = child(MainComponent::class){
    attrs.initialView = initialView
    attrs.onThemeChange = onThemeChange
    attrs.onLocaleChange = onLocaleChange
}