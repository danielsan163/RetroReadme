package com.retroreadme.games.mmx2

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/**
 * Palette pulled from the game's own UI: the royal-blue pause menu, X's cyan highlights,
 * heart-tank red. Detail pane is true black because the Nova's screen is AMOLED.
 */
object Mmx2Colors {
    val Void = Color(0xFF000000)
    val MenuNavy = Color(0xFF0B1A4A)
    val Panel = Color(0xFF0E1D3F)
    val Selected = Color(0xFF1E3F9E)
    val Line = Color(0xFF243A72)
    val Cyan = Color(0xFF6FD8FF)
    val Heart = Color(0xFFFF4D6D)
    val SubTank = Color(0xFFFFC93C)
    val Armor = Color(0xFF9C8CFF)
    val Fire = Color(0xFFFF8A2A)
    val Sigma = Color(0xFFC45CFF)
    val Zero = Color(0xFFE23B3B)
    val Text = Color(0xFFEAF2FF)
    val Muted = Color(0xFF8EA2C8)
}

val Mmx2Palette = GuidePalette(
    background = Mmx2Colors.Void,
    bar = Mmx2Colors.MenuNavy,
    panel = Mmx2Colors.Panel,
    selected = Mmx2Colors.Selected,
    line = Mmx2Colors.Line,
    accent = Mmx2Colors.Cyan,
    text = Mmx2Colors.Text,
    muted = Mmx2Colors.Muted,
    warning = Mmx2Colors.Fire,
    secret = Mmx2Colors.Sigma,
)

fun ItemType.color(): Color = when (this) {
    ItemType.HEART -> Mmx2Colors.Heart
    ItemType.SUB_TANK -> Mmx2Colors.SubTank
    ItemType.ARMOR -> Mmx2Colors.Armor
    ItemType.SECRET -> Mmx2Colors.Fire
    ItemType.ZERO_PART -> Mmx2Colors.Zero
}
