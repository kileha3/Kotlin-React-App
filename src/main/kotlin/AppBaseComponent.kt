import react.*

interface AppBaseState: RState {
    var themeColor: String
    var currentLocale: String
}


open class AppBaseComponent(props: RProps): RComponent<RProps, AppBaseState>(props), AppBaseView {

    override fun AppBaseState.init(props: RProps) {
        themeColor = "light"
        currentLocale = "enUS"
    }
    override fun RBuilder.render() {}

    override fun changeTheme() {
        setState { themeColor = if (themeColor == "dark") "light" else "dark" }
    }

    override fun changeLocale(locale: String) {
        setState {
            currentLocale = locale
        }
    }
}