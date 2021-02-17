import AppUtils.PRIMARY_COLOR
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mThemeProvider
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createMuiTheme
import react.RBuilder
import react.RProps
import react.RState
import react.setState

interface AppBaseState: RState {
    var themeColor: String
    var currentLocale: String
}

class App(props: RProps): AppBaseComponent<RProps,AppBaseState>(props) {

    override fun RBuilder.render() {
        mCssBaseline()

        @Suppress("UnsafeCastFromDynamic")
        val themeOptions: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
        themeOptions.palette?.type = state.themeColor
        themeOptions.palette?.primary.main = PRIMARY_COLOR.toString()
        mThemeProvider(createMuiTheme(themeOptions)) {
           mainComponent("Content", {
               setState { themeColor = if (themeColor == "dark") "light" else "dark" }
           },{
               setState { currentLocale = it}
           })
        }
    }
}

fun RBuilder.app() = child(App::class) {}