package com.retroreadme.games.mmx2

data class Weapon(
    val id: String,
    val name: String,
    /** Who you get it from, or how it's unlocked. */
    val source: String,
    val beats: String? = null,
    val uncharged: List<String>,
    val charged: List<String>,
    val uses: List<String> = emptyList(),
    /** True for the eight Maverick weapons, which need the Arm parts to charge. */
    val needsArmToCharge: Boolean = true,
)

object WeaponData {
    val all: List<Weapon> = listOf(
        Weapon(
            "w_buster", "X-Buster", "Always equipped", beats = null,
            uncharged = listOf(
                "1 damage a shot. Three can be on screen at once.",
                "An uncharged shot fired during a dash does double damage, including off a wall.",
            ),
            charged = listOf(
                "Half charge does 2 damage, full charge does 4.",
                "With the Arm parts you can charge both cannons, firing two full shots back to back.",
            ),
            needsArmToCharge = false,
        ),
        Weapon(
            "w_sonic", "Sonic Slicer", "Overdrive Ostrich", beats = "Wire Sponge",
            uncharged = listOf(
                "Throws two energy blades that ricochet off walls, ceilings and obstacles.",
                "The bounce angle shifts with every hit, and they vanish on contact with an enemy.",
                "Two pairs can be in the air at once.",
            ),
            charged = listOf(
                "Fires five blades up into the air, which then rain back down across the screen.",
                "Good on Serges and Neo Sigma, and one well-placed hit destroys Chop Register.",
            ),
            uses = listOf("The most useful all-round stand-in for the buster."),
        ),
        Weapon(
            "w_chain", "Strike Chain", "Wire Sponge", beats = "Wheel Gator",
            uncharged = listOf(
                "A short-range chain punch. Hold the button to extend the reach.",
                "3 damage on Wheel Gator, 5 if he's already flashing.",
            ),
            charged = listOf("Longer, mid-range version of the same attack."),
            uses = listOf(
                "Grabs capsules dropped by enemies and pulls them to you.",
                "Latches onto walls and pulls X across to them, which is how you reach Crystal Snail's Heart Tank.",
                "One of the only weapons that hurts the Sigma Virus.",
            ),
        ),
        Weapon(
            "w_wheel", "Spin Wheel", "Wheel Gator", beats = "Bubble Crab",
            uncharged = listOf("Drops a saw that grinds along the floor ahead of you."),
            charged = listOf("Bursts into eight projectiles firing out in eight directions."),
            uses = listOf(
                "Cuts through breakable floors and walls.",
                "Needed for three items: the Leg parts, the Body parts, and Overdrive Ostrich's X-Hunter door.",
            ),
        ),
        Weapon(
            "w_bubble", "Bubble Splash", "Bubble Crab", beats = "Flame Stag",
            uncharged = listOf(
                "Releases a stream of bubbles that arc upward and pop on contact.",
                "Hold the button for near-rapid fire.",
            ),
            charged = listOf(
                "Wraps a barrier of bubbles around X that damages what it touches.",
                "It drains weapon energy the whole time it's up.",
            ),
            uses = listOf(
                "Underwater, the barrier raises your jump height. That's how you reach Bubble Crab's Sub Tank.",
                "The only thing that hurts Neo Violen besides the buster and Giga Crush.",
            ),
        ),
        Weapon(
            "w_burner", "Speed Burner", "Flame Stag", beats = "Morph Moth",
            uncharged = listOf("Fires a burst of flame straight ahead."),
            charged = listOf(
                "X turns into a flaming dash that carries him forward through the air.",
                "It counts as its own dash, so it stacks: jump, air dash, then release the Burner for two dashes' worth of distance.",
            ),
            uses = listOf(
                "The main way to cross long gaps. Several Heart Tanks and a Sub Tank need it.",
                "The only weapon Zero doesn't block, if you end up fighting him.",
            ),
        ),
        Weapon(
            "w_silk", "Silk Shot", "Morph Moth", beats = "Magna Centipede",
            uncharged = listOf(
                "Lobs a ball that arcs down and breaks into four fragments flying out diagonally.",
                "Its form changes with the stage: leaves in Weather Control, crystals in Energen Crystal, rocks in Deep-Sea Base and Volcanic Zone.",
            ),
            charged = listOf("Same arc, but splits into eight fragments."),
            uses = listOf(
                "Fire it at the floor to hit enemies on the ceiling with the diagonal fragments.",
                "In some secret rooms it pulls in health and weapon capsules.",
                "Strong against whichever X-Hunter you meet in the stages listed above.",
            ),
        ),
        Weapon(
            "w_magnet", "Magnet Mine", "Magna Centipede", beats = "Crystal Snail",
            uncharged = listOf(
                "Sends out a mine at a steady speed that you steer up and down with the D-pad.",
                "It detonates on an enemy, or sticks to a wall and goes off shortly after.",
            ),
            charged = listOf(
                "Becomes a small black hole you steer the same way, but it passes through walls.",
                "It grows as it absorbs things, and drags enemy fire into itself.",
            ),
            uses = listOf("Hitting Crystal Snail with it slams him into a wall for 2 extra damage."),
        ),
        Weapon(
            "w_crystal", "Crystal Hunter", "Crystal Snail", beats = "Overdrive Ostrich",
            uncharged = listOf(
                "Fires a crystal drop that encases whatever it hits.",
                "Does no damage to any boss except Overdrive Ostrich, who it freezes.",
            ),
            charged = listOf(
                "Sends the screen into slow motion for a short while.",
                "Handy for timing wall jumps through closing walls or moving blocks.",
            ),
            uses = listOf(
                "A crystallised enemy works as a platform. That's how you reach Morph Moth's Heart Tank.",
                "Break it by dashing through it or jumping off it, and it drops a weapon capsule.",
            ),
        ),
        Weapon(
            "w_giga", "Giga Crush", "Body parts, in Morph Moth's stage", beats = null,
            uncharged = listOf(
                "A screen-wide blast, chosen from the weapon menu like any weapon.",
                "Its gauge fills from damage you take while wearing the Body parts.",
            ),
            charged = listOf(
                "No charged version.",
                "Always 2 damage to a boss, but it one-shots Chop Register and Magna Quartz, who have no invincibility frames.",
                "It also wipes out all four of the Serges Tank's cannons at once.",
            ),
            needsArmToCharge = false,
        ),
        Weapon(
            "w_shoryuken", "Shoryuken", "Hidden capsule, X-Hunter Stage 3", beats = null,
            uncharged = listOf(
                "Needs all 8 Heart Tanks, 4 Sub Tanks and 4 armor parts to appear.",
                "Only works at full health with the X-Buster selected.",
                "Input forward, down, down-forward, then fire.",
            ),
            charged = listOf(
                "Destroys almost any boss in one hit. Morph Moth takes two, one per form.",
                "Taking any damage drops you below full health and disables it until you heal.",
            ),
            needsArmToCharge = false,
        ),
    )
}
