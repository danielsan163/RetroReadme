package com.retroreadme.games.aos

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** A moonlit castle: gold highlights, dark blood-red bars, violet-grey stone. */
object AosColors {
    val Night = Color(0xFF0C0A12)
    val Blood = Color(0xFF4A0E1A)
    val Stone = Color(0xFF1A1724)
    val Violet = Color(0xFF3A2A4E)
    val Line = Color(0xFF3A3148)
    val Moon = Color(0xFFE8C872)
    val Text = Color(0xFFECE6F2)
    val Muted = Color(0xFF9A93AC)
    val Crimson = Color(0xFFC23A4A)
    val Lilac = Color(0xFF8E7CC3)

    // The in-game soul colors, for the checklist meters
    val Bullet = Color(0xFFE0443C)
    val Guardian = Color(0xFF4A8FE0)
    val Enchanted = Color(0xFFE8D24A)
    val Ability = Color(0xFFA8A4B4)
}

val AosPalette = GuidePalette(
    background = AosColors.Night,
    bar = AosColors.Blood,
    panel = AosColors.Stone,
    selected = AosColors.Violet,
    line = AosColors.Line,
    accent = AosColors.Moon,
    text = AosColors.Text,
    muted = AosColors.Muted,
    warning = AosColors.Crimson,
    secret = AosColors.Lilac,
)
