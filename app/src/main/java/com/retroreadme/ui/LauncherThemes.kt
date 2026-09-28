package com.retroreadme.ui

import androidx.compose.ui.graphics.Color

/**
 * Launcher color themes, one per console, in the order the dropdown lists them.
 * The name is what gets saved, so don't rename entries once people have picked one.
 * Game guides keep their own palettes; these only color the launcher.
 */
enum class LauncherTheme(val label: String, val palette: GuidePalette) {
    NES(
        "NES",
        GuidePalette(
            background = Color(0xFF121214), bar = Color(0xFF3A3A3E), panel = Color(0xFF1E1E21),
            selected = Color(0xFF5C5C62), line = Color(0xFF48484D), accent = Color(0xFFE03C31),
            text = Color(0xFFE8E6E3), muted = Color(0xFF9A9AA0),
            warning = Color(0xFFF2A33A), secret = Color(0xFFB8B8BE),
        ),
    ),
    FC(
        "FC",
        GuidePalette(
            background = Color(0xFF140B0B), bar = Color(0xFF7A1C22), panel = Color(0xFF221314),
            selected = Color(0xFF9A2E34), line = Color(0xFF4E2C2C), accent = Color(0xFFE6C15A),
            text = Color(0xFFF3EAD8), muted = Color(0xFFB89E96),
            warning = Color(0xFFF08A3A), secret = Color(0xFFD9CBB0),
        ),
    ),
    SNES("SNES", LauncherPalette),
    SFC(
        "SFC",
        GuidePalette(
            background = Color(0xFF111114), bar = Color(0xFF34343B), panel = Color(0xFF1F1F24),
            selected = Color(0xFF2F5FAF), line = Color(0xFF44444C), accent = Color(0xFFF2C12E),
            text = Color(0xFFECEBEF), muted = Color(0xFF9C9CA6),
            warning = Color(0xFFE0463C), secret = Color(0xFF3DA35D),
        ),
    ),
    GENESIS(
        "Genesis",
        // Sega blue: black body, the Sega logo's blue, silver trim.
        GuidePalette(
            background = Color(0xFF07090F), bar = Color(0xFF16213A), panel = Color(0xFF111827),
            selected = Color(0xFF1E4C9A), line = Color(0xFF2A3550), accent = Color(0xFF5B9BFF),
            text = Color(0xFFE8ECF2), muted = Color(0xFF8A96AB),
            warning = Color(0xFFF2A33A), secret = Color(0xFFC0C6D0),
        ),
    ),
    GBA(
        "GBA",
        GuidePalette(
            background = Color(0xFF0D0B1C), bar = Color(0xFF2E2873), panel = Color(0xFF17143A),
            selected = Color(0xFF4E46B8), line = Color(0xFF34306A), accent = Color(0xFF9FC8FF),
            text = Color(0xFFECEAFB), muted = Color(0xFF9D98C8),
            warning = Color(0xFFFFB347), secret = Color(0xFFFF6FA8),
        ),
    ),
    GB_DMG(
        "GB DMG",
        GuidePalette(
            background = Color(0xFF0F380F), bar = Color(0xFF306230), panel = Color(0xFF1B441B),
            selected = Color(0xFF4E7A26), line = Color(0xFF3E6E2A), accent = Color(0xFF9BBC0F),
            text = Color(0xFFC9DE8A), muted = Color(0xFF8BAC0F),
            warning = Color(0xFFE8B04A), secret = Color(0xFFC0507A),
        ),
    ),
    PS1(
        "PS1",
        GuidePalette(
            background = Color(0xFF101012), bar = Color(0xFF2E2F33), panel = Color(0xFF1C1D20),
            selected = Color(0xFF2F5E4E), line = Color(0xFF44464B), accent = Color(0xFF7C9BE6),
            text = Color(0xFFE6E6E8), muted = Color(0xFF9A9BA0),
            warning = Color(0xFFE23D4A), secret = Color(0xFFE88FCB),
        ),
    );

    companion object {
        fun byId(id: String?): LauncherTheme = entries.firstOrNull { it.name == id } ?: SNES
    }
}
