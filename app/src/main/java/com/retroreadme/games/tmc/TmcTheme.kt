package com.retroreadme.games.tmc

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** Link's tunic green and Ezlo's gold on dark forest green. */
object TmcColors {
    val Background = Color(0xFF0B1A12)
    val Bar = Color(0xFF15301F)
    val Panel = Color(0xFF112419)
    val Selected = Color(0xFF2E5A2A)
    val Line = Color(0xFF274433)
    val LinkGreen = Color(0xFF5BD06A)
    val Text = Color(0xFFEDF5E8)
    val Muted = Color(0xFF94AE98)
    val Warning = Color(0xFFF08A4B)
    val EzloGold = Color(0xFFE8C04A)

    // Checklist meter colors.
    val Heart = Color(0xFFF0607A)
    val KinGreen = Color(0xFF6BD86B)
    val KinBlue = Color(0xFF5AA8F0)
    val KinRed = Color(0xFFE85A4F)
    val KinGold = EzloGold
    val Upgrade = Color(0xFFC79BF2)
    val Step = Color(0xFF8FD3C0)
}

val TmcPalette = GuidePalette(
    background = TmcColors.Background,
    bar = TmcColors.Bar,
    panel = TmcColors.Panel,
    selected = TmcColors.Selected,
    line = TmcColors.Line,
    accent = TmcColors.LinkGreen,
    text = TmcColors.Text,
    muted = TmcColors.Muted,
    warning = TmcColors.Warning,
    secret = TmcColors.EzloGold,
)
