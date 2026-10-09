package com.retroreadme.games.sm

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

/** The in-game map areas. Kraid's Lair is on Brinstar's map and Ridley's Lair on Norfair's. */
enum class Area(val label: String) {
    CRATERIA("Crateria"),
    BRINSTAR("Brinstar"),
    NORFAIR("Norfair"),
    WRECKED_SHIP("Wrecked Ship"),
    MARIDIA("Maridia"),
}

enum class Kind(val label: String, val short: String, val color: Color) {
    MAJOR("Upgrade", "Upgrade", SmColors.Major),
    ENERGY("Energy Tank", "Energy", SmColors.Energy),
    RESERVE("Reserve Tank", "Reserve", SmColors.Reserve),
    MISSILE("Missile Tank", "Missile", SmColors.Missile),
    SUPER("Super Missile Tank", "Super", SmColors.SuperMissile),
    POWER_BOMB("Power Bomb Tank", "Power Bomb", SmColors.PowerBomb),
}

data class Item(
    /** Stable checklist id, e.g. "br_m3" or "u_morph". Never rename once released. */
    val id: String,
    val area: Area,
    val kind: Kind,
    /** Display name: the upgrade's name, or "Missile Tank 3" etc. (numbered within the area). */
    val name: String,
    val needs: List<String>,
    val steps: List<String>,
    /** The two guides disagree on a detail here. */
    val confirm: Boolean = false,
) {
    /** Shinespark items read badly mid-game; their pages suggest a video. */
    val needsShinespark: Boolean get() = SHINESPARK in needs

    /** A YouTube search that finds a video of this item. */
    val videoSearch: String get() = "Super Metroid ${area.label} $name"
}

const val SHINESPARK = "Shinespark"

data class Boss(
    val id: String,
    val name: String,
    /** The area, as shown under its name. */
    val place: String,
    /** Where it is and what beating it gets you. */
    val lines: List<String>,
    val weakPoint: String,
    val strategy: List<String>,
)

data class SmPage(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
