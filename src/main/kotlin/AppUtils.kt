import com.ccfraser.muirwik.components.Colors
import com.ccfraser.muirwik.components.styles.Theme
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createMuiTheme

object AppUtils {


    fun createTheme(state: AppBaseState): Theme {
        @Suppress("UnsafeCastFromDynamic")
        val themeOptions: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
        themeOptions.palette?.type = state.themeColor
        themeOptions.palette?.primary.main = Colors.Teal.shade600.toString()
        return createMuiTheme(themeOptions)
    }

    fun go(destination: String, args: Map<String,String>){
    }

}