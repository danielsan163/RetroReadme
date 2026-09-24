package com.retroreadme.games.mmx2

import com.retroreadme.core.Section

enum class ItemType(val label: String, val symbol: String) {
    HEART("Heart Tank", "♥"),
    SUB_TANK("Sub Tank", "S"),
    ARMOR("Armor part", "A"),
    SECRET("Secret upgrade", "★"),
    ZERO_PART("Zero part", "Z"),
}

data class PowerUp(
    val id: String,
    val type: ItemType,
    val name: String,
    /** Weapons/upgrades you need first. Empty = nothing needed. */
    val requires: List<String>,
    val steps: List<String>,
    val effect: String? = null,
)

data class Stage(
    val id: String,
    val maverick: String,
    val area: String,
    val weakness: String?,
    val weapon: String?,
    val powerUps: List<PowerUp>,
    val miniBosses: List<String> = emptyList(),
    val xHunterDoor: String? = null,
    val xHunterDoorRequires: List<String> = emptyList(),
    val notes: List<String> = emptyList(),
)

enum class BossCategory(val label: String) {
    OPENING("Opening stage"),
    MAVERICK("Mavericks"),
    MINI("Mini-bosses"),
    X_HUNTER("X-Hunters in the stages"),
    FINAL("Final stages"),
}

data class Boss(
    val id: String,
    val name: String,
    val category: BossCategory,
    val location: String,
    val weakness: String,
    val reward: String? = null,
    val weaponNote: String? = null,
    val attacks: List<String>,
    val strategy: List<String>,
)

data class RouteStep(
    val number: Int,
    val maverick: String,
    val area: String,
    val useWeapon: String,
    val collect: List<String>,
    val later: String? = null,
    val thenDo: List<String> = emptyList(),
    val warning: String? = null,
)

data class InfoPage(
    val id: String,
    val title: String,
    val subtitle: String,
    val sections: List<Section> = emptyList(),
    val route: List<RouteStep> = emptyList(),
)
