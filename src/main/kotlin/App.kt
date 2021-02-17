import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mThemeProvider
import react.RBuilder
import react.RProps
import styled.styledDiv


class App(props: RProps): AppBaseComponent(props) {

    override fun RBuilder.render() {
        mCssBaseline()
        mThemeProvider(AppUtils.createTheme(state)) {
           styledDiv {
               +"App running on app"
           }
        }
    }
}

fun RBuilder.app() = child(App::class) {}