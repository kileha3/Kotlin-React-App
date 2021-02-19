import com.ccfraser.muirwik.components.Colors
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mThemeProvider
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createMuiTheme
import view.initMainComponent
import react.RBuilder
import react.RProps
import react.RState
import react.setState
import view.AppBaseComponent

interface AppBaseState: RState {
    var themeColor: String
}

class App(props: RProps): AppBaseComponent<RProps, AppBaseState>(props) {

    override fun RBuilder.render() {
        mCssBaseline()

        @Suppress("UnsafeCastFromDynamic")
        val themeOptions: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
        themeOptions.palette?.type = state.themeColor
        themeOptions.palette?.primary.main = Colors.Blue.shade500.toString()
        mThemeProvider(createMuiTheme(themeOptions)) {
           initMainComponent("Content", {
               setState { themeColor = if (themeColor == "dark") "light" else "dark" }
           })
        }
    }
}

fun RBuilder.app() = child(App::class) {}