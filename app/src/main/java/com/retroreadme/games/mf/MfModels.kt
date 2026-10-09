package com.retroreadme.games.mf

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

enum class Sector(val label: String, val short: String) {
    MAIN_DECK("Main Deck", "Main Deck"),
    SRX("Sector 1 (SRX)", "SRX"),
    TRO("Sector 2 (TRO)", "TRO"),
    PYR("Sector 3 (PYR)", "PYR"),
    AQA("Sector 4 (AQA)", "AQA"),
    ARC("Sector 5 (ARC)", "ARC"),
    NOC("Sector 6 (NOC)", "NOC"),
}

enum class Kind(val label: String, val short: String, val color: Color) {
    MISSILE("Missile Tank", "Missile", MfColors.Missile),
    ENERGY("Energy Tank", "Energy", MfColors.Energy),
    POWER_BOMB("Power Bomb Tank", "Power Bomb", MfColors.PowerBomb),
}

data class Item(
    /** Stable checklist id, e.g. "s2_m4". Never rename once released. */
    val id: String,
    val sector: Sector,
    val kind: Kind,
    /** "Missile Tank 4" etc., numbered within the sector (Metroid Recon's numbering). */
    val name: String,
    val needs: List<String>,
    val steps: List<String>,
    /** Only one of the two guides backs a detail here. */
    val confirm: Boolean = false,
) {
    /** Shinespark items read badly mid-game; their pages suggest a video. */
    val needsShinespark: Boolean get() = SHINESPARK in needs

    /** A YouTube search that finds a video of this item. */
    val videoSearch: String get() = "Metroid Fusion ${sector.label} $name"
}

const val SHINESPARK = "Shinespark"

/** An ability Samus gets back: from a Data Room download or a Core-X. */
data class Ability(
    val id: String,
    val name: String,
    val sector: Sector,
    /** Where it comes from, e.g. "Data Room" or the boss. */
    val from: String,
    val where: String,
    val opens: String,
)

data class Boss(
    val id: String,
    val name: String,
    val sector: Sector,
    val reward: String?,
    val weakPoint: String,
    val strategy: List<String>,
)

data class MfPage(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
