package com.retroreadme.games.kdl3

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** Kirby pink and shoe red on deep berry, with Heart Star gold for the checklist. */
object Kdl3Colors {
    val Background = Color(0xFF1A0A12)
    val Berry = Color(0xFF6A1E3E)
    val Panel = Color(0xFF2A1220)
    val Selected = Color(0xFFB03A5E)
    val Line = Color(0xFF4E2236)
    val Pink = Color(0xFFFF9FC8)
    val Text = Color(0xFFFFEFF5)
    val Muted = Color(0xFFC99AAE)
    val ShoeRed = Color(0xFFE0303C)
    val HeartGold = Color(0xFFFFD24A)
}

val Kdl3Palette = GuidePalette(
    background = Kdl3Colors.Background,
    bar = Kdl3Colors.Berry,
    panel = Kdl3Colors.Panel,
    selected = Kdl3Colors.Selected,
    line = Kdl3Colors.Line,
    accent = Kdl3Colors.Pink,
    text = Kdl3Colors.Text,
    muted = Kdl3Colors.Muted,
    warning = Kdl3Colors.ShoeRed,
    secret = Kdl3Colors.HeartGold,
)
