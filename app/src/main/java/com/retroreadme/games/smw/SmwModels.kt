package com.retroreadme.games.smw

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

/** The map regions, in the order the Worlds tab lists them. Grouping follows the game's own exit list. */
enum class World(val label: String) {
    YOSHIS_ISLAND("Yoshi's Island"),
    DONUT_PLAINS("Donut Plains"),
    VANILLA_DOME("Vanilla Dome"),
    TWIN_BRIDGES("Twin Bridges"),
    FOREST("Forest of Illusion"),
    CHOCOLATE("Chocolate Island"),
    VALLEY("Valley of Bowser"),
    STAR_WORLD("Star World"),
    SPECIAL("Special Zone"),
}

enum class LevelType(val label: String) {
    LEVEL("Level"),
    GHOST_HOUSE("Ghost House"),
    CASTLE("Castle"),
    FORTRESS("Fortress"),
    SWITCH_PALACE("Switch Palace"),
}

enum class ExitKind(val label: String) { NORMAL("Normal exit"), SECRET("Secret exit") }

/** What an exit needs and how to reach it, before it has an id. */
data class ExitInfo(
    val leadsTo: String,
    val steps: List<String>,
    /** Power-ups, Yoshi or switch blocks you need. Empty = nothing special. */
    val needs: List<String> = emptyList(),
    /** Only one detailed source described this; worth checking in-game. */
    val confirm: Boolean = false,
)

data class Exit(
    /** Stable checklist id, e.g. "dp1_s". Never rename once released. */
    val id: String,
    val kind: ExitKind,
    val info: ExitInfo,
)

data class Level(
    val id: String,
    val name: String,
    val world: World,
    val type: LevelType,
    /** 0, 1 or 2 exits. Bowser's Castle has none: it doesn't count toward the 96. */
    val exits: List<Exit>,
    val boss: List<String> = emptyList(),
    val notes: List<String> = emptyList(),
)

fun level(
    id: String,
    name: String,
    world: World,
    type: LevelType,
    normal: ExitInfo?,
    secret: ExitInfo? = null,
    boss: List<String> = emptyList(),
    notes: List<String> = emptyList(),
) = Level(
    id, name, world, type,
    exits = listOfNotNull(
        normal?.let { Exit("${id}_n", ExitKind.NORMAL, it) },
        secret?.let { Exit("${id}_s", ExitKind.SECRET, it) },
    ),
    boss = boss,
    notes = notes,
)

/** A plain page of sections (Yoshi & Capes, Hints). */
data class Page(
    val id: String,
    val title: String,
    val subtitle: String,
    val sections: List<Section>,
)

data class Palace(
    val id: String,
    val name: String,
    val color: Color,
    /** The palace's own level, whose exit is on the checklist. */
    val levelId: String,
    val openedBy: String,
    val sections: List<Section>,
)
