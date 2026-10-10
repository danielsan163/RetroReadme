package com.retroreadme.games.dread

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** Samus's Dread suit, red-orange with a blue visor, on ZDR's dark blue-black. */
object DreadColors {
    val Background = Color(0xFF070B14)
    val Bar = Color(0xFF131C2E)
    val Panel = Color(0xFF0E1524)
    val Selected = Color(0xFF5C2414)
    val Line = Color(0xFF23304A)
    val Suit = Color(0xFFF26A32)
    val Text = Color(0xFFEAF0F8)
    val Muted = Color(0xFF8E9AB2)
    val Warning = Color(0xFFE8434A)
    val Visor = Color(0xFF4FC8F0)

    // Checklist meter colors per item type (as in the other Metroid guides).
    val Major = Color(0xFFF28C28)
    val Energy = Color(0xFF7CFC6A)
    val Part = Color(0xFFB8F0A8)
    val Missile = Color(0xFFE8604C)
    val MissilePlus = Color(0xFFFF9E80)
    val PowerBomb = Color(0xFFF2C94C)
}

val DreadPalette = GuidePalette(
    background = DreadColors.Background,
    bar = DreadColors.Bar,
    panel = DreadColors.Panel,
    selected = DreadColors.Selected,
    line = DreadColors.Line,
    accent = DreadColors.Suit,
    text = DreadColors.Text,
    muted = DreadColors.Muted,
    warning = DreadColors.Warning,
    secret = DreadColors.Visor,
)
