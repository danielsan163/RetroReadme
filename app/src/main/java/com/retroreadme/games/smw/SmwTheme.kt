package com.retroreadme.games.smw

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/**
 * Colors from the World 1 (Yoshi's Island) overworld: grass-green hills, sandy paths,
 * yellow level dots, and the red dots that mark levels with a secret exit.
 * Kept dark for the Nova's AMOLED screen.
 */
object SmwColors {
    val Background = Color(0xFF07120A)
    val Grass = Color(0xFF1B4D1F)
    val Panel = Color(0xFF10261A)
    val Selected = Color(0xFF2F7A34)
    val Line = Color(0xFF2C5233)
    val Sand = Color(0xFFF6F1DC)
    val Muted = Color(0xFFA3B89C)
    val Amber = Color(0xFFF89838)

    /** Level-dot yellow: normal exits. */
    val NormalExit = Color(0xFFF8D830)

    /** Red-dot red: secret exits. */
    val SecretExit = Color(0xFFE84830)

    val PalaceYellow = Color(0xFFF8D830)
    val PalaceGreen = Color(0xFF38B048)
    val PalaceRed = Color(0xFFE84830)
    val PalaceBlue = Color(0xFF3878F0)
}

val SmwPalette = GuidePalette(
    background = SmwColors.Background,
    bar = SmwColors.Grass,
    panel = SmwColors.Panel,
    selected = SmwColors.Selected,
    line = SmwColors.Line,
    accent = SmwColors.NormalExit,
    text = SmwColors.Sand,
    muted = SmwColors.Muted,
    warning = SmwColors.Amber,
    secret = SmwColors.SecretExit,
)

fun ExitKind.color(): Color = when (this) {
    ExitKind.NORMAL -> SmwColors.NormalExit
    ExitKind.SECRET -> SmwColors.SecretExit
}
