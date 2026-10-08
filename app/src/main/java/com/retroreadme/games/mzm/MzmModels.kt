package com.retroreadme.games.mzm

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

enum class Area(val label: String) {
    BRINSTAR("Brinstar"),
    KRAID("Kraid's Lair"),
    NORFAIR("Norfair"),
    RIDLEY("Ridley's Lair"),
    TOURIAN("Tourian"),
    CRATERIA("Crateria"),
    CHOZODIA("Chozodia"),
}

enum class Kind(val label: String, val short: String, val color: Color) {
    MAJOR("Upgrade", "Upgrade", MzmColors.Major),
    ENERGY("Energy Tank", "Energy", MzmColors.Energy),
    MISSILE("Missile Tank", "Missile", MzmColors.Missile),
    SUPER("Super Missile Tank", "Super", MzmColors.SuperMissile),
    POWER_BOMB("Power Bomb Tank", "Power Bomb", MzmColors.PowerBomb),
}

data class Item(
    /** Stable checklist id, e.g. "br_m3". Never rename once released. */
    val id: String,
    val area: Area,
    val kind: Kind,
    /** Display name: the upgrade's name, or "Missile Tank 3" etc. (numbered within the area). */
    val name: String,
    val needs: List<String>,
    val steps: List<String>,
    /** Needs gear you only get in Chozodia (Power Bombs, Gravity Suit, Space Jump...). */
    val late: Boolean = false,
)

data class Boss(
    val id: String,
    val name: String,
    val area: Area,
    /** Where it is and what beating it gets you. */
    val lines: List<String>,
    val weakPoint: String,
    val strategy: List<String>,
)

data class MzmPage(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
