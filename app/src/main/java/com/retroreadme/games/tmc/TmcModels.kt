package com.retroreadme.games.tmc

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

/** A "where next" pointer. Checkable, but not part of the 100% checklist. */
data class Step(val id: String, val chapter: String, val text: String)

data class Heart(
    /** Stable checklist id, e.g. "h07". Never rename once released. */
    val id: String,
    val area: String,
    val needs: List<String>,
    val how: String,
)

enum class KinColor(val label: String, val color: Color) {
    GREEN("Green", TmcColors.KinGreen),
    BLUE("Blue", TmcColors.KinBlue),
    RED("Red", TmcColors.KinRed),
    GOLD("Gold", TmcColors.KinGold),
}

data class Fusion(
    /** Stable checklist id, e.g. "f042". Never rename once released. */
    val id: String,
    val number: Int,
    val color: KinColor,
    /** Story stage (1-6) from which it's available. */
    val stage: Int,
    /** Who to fuse with; "Random" for the 18 that turn up on whoever the game picks. */
    val fuser: String,
    val location: String,
    val result: String,
) {
    val random: Boolean get() = fuser == "Random"
}

enum class UpgradeKind(val label: String) { BOTTLE("Bottles"), WALLET("Wallets"), BOMBS("Bomb Bags"), QUIVER("Quivers"), ITEM("Item upgrades") }

data class Upgrade(
    /** Stable checklist id, e.g. "u_bottle2". Never rename once released. */
    val id: String,
    val kind: UpgradeKind,
    val name: String,
    val area: String,
    val how: String,
    /** Only one of the two guides covers this one. */
    val confirm: Boolean = false,
    /** A way to lose it for good. */
    val missable: String? = null,
)

data class Boss(val id: String, val name: String, val dungeon: String, val reward: String, val strategy: List<String>)

data class TmcPage(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
