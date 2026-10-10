package com.retroreadme.games.dread

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

/** ZDR's areas, in the order you first reach them. Itorash has no items. */
enum class Area(val label: String) {
    ARTARIA("Artaria"),
    CATARIS("Cataris"),
    DAIRON("Dairon"),
    BURENIA("Burenia"),
    FERENIA("Ferenia"),
    GHAVORAN("Ghavoran"),
    ELUN("Elun"),
    HANUBIA("Hanubia"),
}

enum class Kind(val label: String, val short: String, val color: Color) {
    MAJOR("Upgrade", "Upgrade", DreadColors.Major),
    ENERGY("Energy Tank", "Energy", DreadColors.Energy),
    PART("Energy Part", "Part", DreadColors.Part),
    MISSILE("Missile Tank", "Missile", DreadColors.Missile),
    MISSILE_PLUS("Missile+ Tank", "Missile+", DreadColors.MissilePlus),
    POWER_BOMB("Power Bomb Tank", "Power Bomb", DreadColors.PowerBomb),
}

data class Item(
    /** Stable checklist id, e.g. "ar_m3" or "u_chargebeam". Never rename once released. */
    val id: String,
    val area: Area,
    val kind: Kind,
    /** Display name: the upgrade's name, or "Missile Tank 3" etc. (numbered within the area). */
    val name: String,
    val needs: List<String>,
    val steps: List<String>,
    /** Matching the item to the sources' descriptions was a judgement call; worth confirming. */
    val confirm: Boolean = false,
) {
    /** Shinespark items read badly mid-game; their pages suggest a video. */
    val needsShinespark: Boolean get() = SHINESPARK in needs

    /** A YouTube search that finds a video of this item. */
    val videoSearch: String get() = "Metroid Dread ${area.label} $name"
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

data class DreadPage(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
