package com.retroreadme.games.mzm

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** Samus's Varia Suit: suit orange, suit red, visor green, on dark gunmetal. */
object MzmColors {
    val Background = Color(0xFF0B0E14)
    val Gunmetal = Color(0xFF2A2F3A)
    val Panel = Color(0xFF161B24)
    val Selected = Color(0xFF7A3A14)
    val Line = Color(0xFF3A4150)
    val Orange = Color(0xFFF28C28)
    val Text = Color(0xFFE8ECF2)
    val Muted = Color(0xFF8E97A8)
    val Red = Color(0xFFD8342C)
    val Visor = Color(0xFF7CFC6A)

    // Checklist meter colors per item type
    val Major = Orange
    val Energy = Visor
    val Missile = Color(0xFFE8604C)
    val SuperMissile = Color(0xFF4FC3F7)
    val PowerBomb = Color(0xFFF2C94C)
}

val MzmPalette = GuidePalette(
    background = MzmColors.Background,
    bar = MzmColors.Gunmetal,
    panel = MzmColors.Panel,
    selected = MzmColors.Selected,
    line = MzmColors.Line,
    accent = MzmColors.Orange,
    text = MzmColors.Text,
    muted = MzmColors.Muted,
    warning = MzmColors.Red,
    secret = MzmColors.Visor,
)
