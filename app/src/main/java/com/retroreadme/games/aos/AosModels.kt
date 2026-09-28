package com.retroreadme.games.aos

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

enum class SoulType(val label: String, val color: String, val tint: Color, val howToUse: String) {
    BULLET("Bullet souls", "red", AosColors.Bullet, "Up + B. Costs MP per use."),
    GUARDIAN("Guardian souls", "blue", AosColors.Guardian, "R, toggled or held. Drains MP while active."),
    ENCHANTED("Enchanted souls", "yellow", AosColors.Enchanted, "Always on while equipped. No MP cost."),
    ABILITY("Ability souls", "grey", AosColors.Ability, "Permanent once collected; switch them on or off under ABILITY in the pause menu."),
}

data class Soul(
    /** Stable checklist id, e.g. "s_flame_demon". Never rename once released. */
    val id: String,
    /** Number in the in-game enemy list, or null for soul-holder souls. */
    val number: Int?,
    val name: String,
    val type: SoulType,
    val effect: String,
    /** MP per use (Bullet) or per second (Guardian); null if it costs none. */
    val mp: String?,
    val areas: String,
    val best: String,
    val note: String?,
) {
    val fromHolder get() = number == null
}

data class AosPage(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
