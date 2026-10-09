package com.retroreadme.games.mmz

import androidx.compose.ui.graphics.Color
import com.retroreadme.ui.GuidePalette

/** Zero's red armor and cyan saber on Neo Arcadia's dark navy. */
object MmzColors {
    val Background = Color(0xFF0A0F1F)
    val Bar = Color(0xFF16213D)
    val Panel = Color(0xFF111A31)
    val Selected = Color(0xFF6A1E26)
    val Line = Color(0xFF26324F)
    val ZeroRed = Color(0xFFE8433A)
    val Text = Color(0xFFEAF0FA)
    val Muted = Color(0xFF8E9BB8)
    val Warning = Color(0xFFF2A13B)
    val Saber = Color(0xFF5BD8F0)

    // Checklist meter colors per Cyber-elf family.
    val Nurse = Color(0xFF6EE7A0)
    val Animal = Color(0xFFF2C94C)
    val Hacker = Color(0xFFB58CFF)
    val Rare = Color(0xFFFF8FB8)
}

val MmzPalette = GuidePalette(
    background = MmzColors.Background,
    bar = MmzColors.Bar,
    panel = MmzColors.Panel,
    selected = MmzColors.Selected,
    line = MmzColors.Line,
    accent = MmzColors.ZeroRed,
    text = MmzColors.Text,
    muted = MmzColors.Muted,
    warning = MmzColors.Warning,
    secret = MmzColors.Saber,
)
