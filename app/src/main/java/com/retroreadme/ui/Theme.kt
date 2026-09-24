package com.retroreadme.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import android.graphics.Typeface as AndroidTypeface

/**
 * The colors every shared screen draws with. The launcher has its own palette and each game
 * supplies one, so the shell stays the same while each screen gets its own look.
 */
@Immutable
data class GuidePalette(
    /** Detail pane and screen background. */
    val background: Color,
    /** Tab bar / header strip and the left-hand list. */
    val bar: Color,
    /** Panel fill in the detail pane. */
    val panel: Color,
    /** Selected row in the list. */
    val selected: Color,
    /** Neutral panel stripes and dividers. */
    val line: Color,
    /** Headings, focus outlines, current tab. */
    val accent: Color,
    val text: Color,
    val muted: Color,
    /** Warnings and "watch out" callouts. */
    val warning: Color,
    /** Secrets and hidden things. */
    val secret: Color,
)

/**
 * Launcher palette, after the SNES: the console's greys for the body,
 * the purple of the A/B buttons for selection, the lavender of X/Y for accents.
 */
val LauncherPalette = GuidePalette(
    background = Color(0xFF111015),
    bar = Color(0xFF2A2831),
    panel = Color(0xFF22212A),
    selected = Color(0xFF4B3F8F),
    line = Color(0xFF45424F),
    accent = Color(0xFFB8AEEB),
    text = Color(0xFFE9E7EE),
    muted = Color(0xFF9C99A8),
    warning = Color(0xFFE08A8A),
    secret = Color(0xFF8E7CE0),
)

private val LocalPalette = staticCompositionLocalOf { LauncherPalette }

/** The palette of whatever screen is showing. */
val Palette: GuidePalette
    @Composable @ReadOnlyComposable
    get() = LocalPalette.current

/** Condensed bold for headings, echoing the slanted, tight lettering of retro game logos. */
val Condensed = FontFamily(AndroidTypeface.create("sans-serif-condensed", AndroidTypeface.BOLD))

@Composable
fun GuideTheme(palette: GuidePalette, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalPalette provides palette) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = palette.accent,
                background = palette.background,
                surface = palette.panel,
                onSurface = palette.text,
                onBackground = palette.text,
            ),
            content = content,
        )
    }
}
