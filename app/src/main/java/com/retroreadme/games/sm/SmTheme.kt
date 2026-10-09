package com.retroreadme.games.sm

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** The Varia Suit's orange-red on the deep purple of Zebes' caverns. */
object SmColors {
    val Background = Color(0xFF120A1A)
    val Bar = Color(0xFF2A1838)
    val Panel = Color(0xFF1B1026)
    val Selected = Color(0xFF6E2A1E)
    val Line = Color(0xFF3B2A4A)
    val Varia = Color(0xFFFF7A3C)
    val Text = Color(0xFFF0E8F4)
    val Muted = Color(0xFFA192AE)
    val Red = Color(0xFFE0443A)
    val Visor = Color(0xFF7CFC6A)

    // Checklist meter colors per item type (as in the other Metroid guides).
    val Major = Color(0xFFF28C28)
    val Energy = Visor
    val Reserve = Color(0xFFC9B8F0)
    val Missile = Color(0xFFE8604C)
    val SuperMissile = Color(0xFF4FC3F7)
    val PowerBomb = Color(0xFFF2C94C)
}

val SmPalette = GuidePalette(
    background = SmColors.Background,
    bar = SmColors.Bar,
    panel = SmColors.Panel,
    selected = SmColors.Selected,
    line = SmColors.Line,
    accent = SmColors.Varia,
    text = SmColors.Text,
    muted = SmColors.Muted,
    warning = SmColors.Red,
    secret = SmColors.Visor,
)
