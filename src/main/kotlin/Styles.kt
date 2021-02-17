import com.ccfraser.muirwik.components.spacingUnits
import kotlinx.css.*
import styled.StyleSheet

object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
    val rootDiv by css {
        overflow = Overflow.hidden
        position = Position.relative
        display = Display.flex
        width = 100.pct
    }

    val profileImage by css {
        display = Display.inlineBlock
    }
}