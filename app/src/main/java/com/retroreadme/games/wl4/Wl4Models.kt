package com.retroreadme.games.wl4

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

/** Normal and Hard move the jewel pieces (and sometimes the CD), so each gets its own guide. */
enum class Mode(val label: String) { NORMAL("Normal"), HARD("Hard") }

enum class Passage(val label: String, val color: Color) {
    ENTRY("Entry Passage", Wl4Colors.Entry),
    EMERALD("Emerald Passage", Wl4Colors.Emerald),
    RUBY("Ruby Passage", Wl4Colors.Ruby),
    TOPAZ("Topaz Passage", Wl4Colors.Topaz),
    SAPPHIRE("Sapphire Passage", Wl4Colors.Sapphire),
    GOLDEN("Golden Pyramid", Wl4Colors.Golden),
}

/** Where things are in one level on one difficulty. */
data class ModeData(
    /** Exactly four entries, in the order you'll usually find them. */
    val jewels: List<String>,
    /** Null for the two levels without a CD (Hall of Hieroglyphs, Golden Passage). */
    val cd: String?,
    val keyzer: String,
    /** Countdown after hitting the frog switch, as the game shows it. */
    val escapeTime: String,
    val escapeTip: String? = null,
)

data class Wl4Level(
    /** Stable id used in checklist keys, e.g. "ptp_j1". Never rename once released. */
    val id: String,
    val name: String,
    val passage: Passage,
    val normal: ModeData,
    val hard: ModeData,
    val notes: List<String> = emptyList(),
) {
    fun data(mode: Mode) = if (mode == Mode.NORMAL) normal else hard
}

enum class ItemKind(val label: String) { JEWEL("Jewel piece"), CD("CD"), KEYZER("Keyzer") }

/** One checkbox: a jewel piece, CD or Keyzer in a level. */
data class Wl4Item(val id: String, val kind: ItemKind, val number: Int, val where: String, val color: Color)

fun Wl4Level.items(mode: Mode): List<Wl4Item> {
    val d = data(mode)
    return d.jewels.mapIndexed { i, w -> Wl4Item("${id}_j${i + 1}", ItemKind.JEWEL, i + 1, w, passage.color) } +
        listOfNotNull(d.cd?.let { Wl4Item("${id}_cd", ItemKind.CD, 0, it, Wl4Colors.Cd) }) +
        Wl4Item("${id}_key", ItemKind.KEYZER, 0, d.keyzer, Wl4Colors.Keyzer)
}

data class Boss(
    val id: String,
    val name: String,
    val passage: Passage,
    val weakTo: String?,
    val treasure: String?,
    val timeNormal: String,
    val timeHard: String,
    val strategy: List<String>,
)

data class Wl4Page(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
