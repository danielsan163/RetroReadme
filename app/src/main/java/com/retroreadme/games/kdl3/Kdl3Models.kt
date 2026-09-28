package com.retroreadme.games.kdl3

import com.retroreadme.core.Section

enum class World(val label: String) {
    GRASS_LAND("Grass Land"),
    RIPPLE_FIELD("Ripple Field"),
    SAND_CANYON("Sand Canyon"),
    CLOUDY_PARK("Cloudy Park"),
    ICEBERG("Iceberg"),
}

enum class Friend(val label: String, val trait: String) {
    RICK("Rick", "Hamster. Wall-kicks up sheer walls if you don't quite make a jump."),
    KINE("Kine", "Sunfish. Swims against currents that push Kirby back."),
    COO("Coo", "Owl. Flies against strong wind."),
    NAGO("Nago", "Cat. Double and triple jumps."),
    CHUCHU("ChuChu", "Octopus. Clings to and crawls along ceilings."),
    PITCH("Pitch", "Bird. Flies like Kirby floats, but can't beat strong wind."),
}

enum class Ability(val label: String, val from: String) {
    BURNING("Burning", "Fire-breathing dragon enemies; the mid-boss Boboo."),
    STONE("Stone", "Rocky; the mid-boss Blocky."),
    ICE("Ice", "Chilly (snowman); the mid-boss Yuki."),
    NEEDLE("Needle", "Togezo; the mid-boss Captain Stitch."),
    PARASOL("Parasol", "Sasuke; the mid-boss Jumper Shoot."),
    SPARK("Spark", "Sparky."),
    CLEAN("Clean", "Broom Hatter, Keke (inhale the broom); the mid-boss Haboki."),
    CUTTER("Cutter", "Sir Kibble."),
}

data class Stage(
    /** Stable id; the checklist key is "<id>_hs". Never rename once released. */
    val id: String,
    val world: World,
    val number: Int,
    /** Who gives the Heart Star. */
    val character: String,
    /** One-line summary of the task. */
    val task: String,
    val friends: List<Friend> = emptyList(),
    val abilities: List<Ability> = emptyList(),
    val steps: List<String>,
    val notes: List<String> = emptyList(),
    /** The two guides disagree on a detail; worth confirming in-game. */
    val confirm: Boolean = false,
) {
    val name get() = "${world.label} $number"
    val heartId get() = "${id}_hs"
}

/** A 100% item outside the stages (Zero, MG-5, Boss Butch, Jumping). */
data class Extra(val id: String, val title: String, val subtitle: String, val steps: List<String>)

data class Boss(val id: String, val name: String, val where: String, val strategy: List<String>)

data class Kdl3Page(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
