package com.retroreadme.games.wl4

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** The Golden Pyramid: gold highlights on dusk purple. */
object Wl4Colors {
    val Background = Color(0xFF0E0714)
    val Bar = Color(0xFF2A1440)
    val Panel = Color(0xFF1C0F2A)
    val Selected = Color(0xFF5A2E86)
    val Line = Color(0xFF3A2452)
    val Gold = Color(0xFFF2C23A)
    val Text = Color(0xFFF6EEDC)
    val Muted = Color(0xFFB3A2C4)
    val Orange = Color(0xFFFF7A3D)
    val Pink = Color(0xFFE05AB8)

    // Jewel piece colors per passage
    val Entry = Color(0xFFA064E0)
    val Emerald = Color(0xFF2FC06A)
    val Ruby = Color(0xFFE23A4A)
    val Topaz = Color(0xFFF2A83A)
    val Sapphire = Color(0xFF3A7CF0)
    val Golden = Color(0xFFF2C23A)

    val Cd = Color(0xFFC8D0DC)
    val Keyzer = Color(0xFFD9A45A)
}

val Wl4Palette = GuidePalette(
    background = Wl4Colors.Background,
    bar = Wl4Colors.Bar,
    panel = Wl4Colors.Panel,
    selected = Wl4Colors.Selected,
    line = Wl4Colors.Line,
    accent = Wl4Colors.Gold,
    text = Wl4Colors.Text,
    muted = Wl4Colors.Muted,
    warning = Wl4Colors.Orange,
    secret = Wl4Colors.Pink,
)
