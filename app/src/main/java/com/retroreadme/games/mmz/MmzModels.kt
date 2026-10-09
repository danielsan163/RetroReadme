package com.retroreadme.games.mmz

import androidx.compose.ui.graphics.Color
import com.retroreadme.core.Section

enum class Family(val label: String, val color: Color, val about: String) {
    NURSE("Nurse", MmzColors.Nurse, "Health: refills, Sub Tanks and a longer life bar."),
    ANIMAL("Animal", MmzColors.Animal, "Help in a fight, or a permanent boost to Zero's moves."),
    HACKER("Hacker", MmzColors.Hacker, "Change the mission: stun enemies, add time, weaken the boss."),
    RARE("Rare", MmzColors.Rare, "One of a kind."),
}

/**
 * Elves with the same name ending (or a unique name) do the same thing and cost the same to
 * raise. [ec] is the total E-Crystals to raise one fully before it can be used (0: usable as
 * found). [lasting] elves change something for good, not just for one mission.
 */
enum class Group(val family: Family, val effect: String, val ec: Int, val lasting: Boolean = false) {
    EFF(Family.NURSE, "Restores some health.", 0),
    ICK(Family.NURSE, "Restores all your health, or fills a Sub Tank if you're already full.", 0),
    LAR(Family.NURSE, "A shield that soaks up enemy shots and turns them into health.", 0),
    PIE(Family.NURSE, "Raises your maximum health by 4.", 750, lasting = true),
    TAN(Family.NURSE, "Becomes a Sub Tank.", 1200, lasting = true),
    WINKIE(Family.NURSE, "Doubles your maximum health.", 3000, lasting = true),
    BEE(Family.ANIMAL, "Follows you and shoots at enemies for the rest of the mission.", 0),
    BIR(Family.ANIMAL, "Catches you once if you fall into a pit.", 0),
    BOM(Family.ANIMAL, "A shield that blocks shots, then explodes.", 0),
    STICK(Family.ANIMAL, "Stuns enemies near you for the rest of the mission.", 0),
    GIBBER(Family.ANIMAL, "Climb ladders faster.", 200, lasting = true),
    RIBBID(Family.ANIMAL, "Slide down walls more slowly.", 400, lasting = true),
    BUFFER(Family.ANIMAL, "No more knockback when you're hit.", 750, lasting = true),
    TURBO(Family.ANIMAL, "Run faster.", 1000, lasting = true),
    SHELTER(Family.ANIMAL, "Halves the damage you take.", 2000, lasting = true),
    STOC(Family.HACKER, "Stuns every enemy on screen for a while.", 0),
    CLOC(Family.HACKER, "Adds time when a mission has a time limit.", 0),
    ITE(Family.HACKER, "Enemies always drop an item for the rest of the mission.", 0),
    MOR(Family.HACKER, "Turns the enemies on screen into Mettaurs.", 0),
    HAFMAR(Family.HACKER, "Halves this mission's boss's health.", 1000),
    NITE(Family.HACKER, "Removes every ordinary enemy for the rest of the mission.", 1250),
    TOTTEN(Family.HACKER, "Covers every spike in the game: they stop killing you.", 3500, lasting = true),
    JACKSON(Family.RARE, "Hold jump to become invincible (pits and crushers still kill).", 4500, lasting = true),
}

/** How an elf is found, which decides when you can still get it. */
enum class Source(val label: String, val color: Color) {
    ENEMIES("From enemies: this mission only", MmzColors.Warning),
    MISSION("During the mission only", MmzColors.Warning),
    BOSS("Boss reward", MmzColors.ZeroRed),
    BOX("In a box: also after the mission", MmzColors.Saber),
    BASE("At the Resistance Base", MmzColors.Saber),
    SECRET("Secret", MmzColors.Rare),
}

data class Elf(
    /** Stable checklist id, e.g. "e_winkie". Never rename once released. */
    val id: String,
    val name: String,
    val group: Group,
    val missionId: String,
    val source: Source,
    val how: String,
    /** A way to lose the chance at this elf, if there is one. */
    val missable: String? = null,
) {
    val family: Family get() = group.family
}

data class Mission(
    val id: String,
    val title: String,
    val area: String,
    val boss: String,
    /** The chip that hurts the boss most, or null. */
    val weakness: String?,
    /** What opens this mission and what it opens, in a line or two. */
    val unlock: String,
    val tips: List<String>,
)

data class Boss(
    val id: String,
    val name: String,
    val missionId: String,
    val weakness: String?,
    val reward: String?,
    val strategy: List<String>,
)

data class MmzPage(val id: String, val title: String, val subtitle: String, val sections: List<Section>)
