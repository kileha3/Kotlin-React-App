package util

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

    val listRoot by css {
        //display = Display.inlineFlex
        padding(1.spacingUnits)
    }

    val inline by css {
        display = Display.inlineBlock
    }
}