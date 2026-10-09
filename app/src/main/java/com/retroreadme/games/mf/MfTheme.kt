package com.retroreadme.games.mf

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** The Fusion Suit: cyan and X-parasite yellow on dark station teal. */
object MfColors {
    val Background = Color(0xFF071417)
    val Bar = Color(0xFF12313A)
    val Panel = Color(0xFF0E2228)
    val Selected = Color(0xFF1F5A63)
    val Line = Color(0xFF24464F)
    val Cyan = Color(0xFF4FD8E8)
    val Text = Color(0xFFE6F4F5)
    val Muted = Color(0xFF8FB0B5)
    val Warning = Color(0xFFF06A4A)
    val XYellow = Color(0xFFF2D24B)

    // Checklist meter colors per item type (same as Zero Mission's).
    val Missile = Color(0xFFE8604C)
    val Energy = Color(0xFF7CFC6A)
    val PowerBomb = Color(0xFFF2C94C)
}

val MfPalette = GuidePalette(
    background = MfColors.Background,
    bar = MfColors.Bar,
    panel = MfColors.Panel,
    selected = MfColors.Selected,
    line = MfColors.Line,
    accent = MfColors.Cyan,
    text = MfColors.Text,
    muted = MfColors.Muted,
    warning = MfColors.Warning,
    secret = MfColors.XYellow,
)
