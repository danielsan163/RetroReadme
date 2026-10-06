package com.retroreadme.games.mzm

/** One trip in the 100% route: a stretch of the game and the items picked up along the way. */
data class Leg(val id: String, val title: String, val note: String, val itemIds: List<String>)

/**
 * All 100 items in the order a completionist playthrough picks them up, split into trips.
 * Follows Metroid Recon's 100% walkthrough; each item comes after everything in its needs.
 * Uses no sequence breaks; leftovers that need Chozodia gear are swept up near the end.
 */
object MzmRoute {

    val legs: List<Leg> = listOf(
        Leg(
            "start", "Brinstar: first steps",
            "Morph Ball, Long Beam, first Missiles. Beat Deorem for the Charge Beam (it comes back later if it escapes), then find the Bombs.",
            listOf("u_morph", "u_long", "br_m1", "u_charge", "br_m2", "br_m3", "br_e2", "u_bomb", "br_m4"),
        ),
        Leg(
            "crateria", "Crateria: Power Grip",
            "Take the elevator to Norfair and head up into Crateria for Unknown Item 1 and the Power Grip.",
            listOf("cr_m1", "u_plasma", "cr_m2", "u_grip"),
        ),
        Leg(
            "ice", "Norfair: Ice Beam",
            "Back down to Norfair for the Ice Beam. The Chozo Statue after it points to Kraid's Lair.",
            listOf("no_m1", "no_m2", "u_ice", "no_m4", "no_m3"),
        ),
        Leg(
            "to_kraid", "Brinstar: on to Kraid",
            "With the Ice Beam and Bombs, pick up three Brinstar items on the way to Kraid's Lair.",
            listOf("br_m5", "br_m7", "br_e1"),
        ),
        Leg(
            "kraid", "Kraid's Lair",
            "Power the Zip Lines, beat the Acid Worm, find Unknown Item 2, then defeat Kraid for the Speed Booster.",
            listOf(
                "kr_m1", "kr_m2", "kr_m3", "kr_e1", "kr_m4", "kr_m5", "kr_m6", "u_spacejump",
                "u_speed", "kr_m7", "kr_m8", "kr_e2",
            ),
        ),
        Leg(
            "varia", "Hi-Jump Boots and Varia Suit",
            "Speed Boost down Norfair to the Hi-Jump Boots, then head up to Brinstar's north for the Varia Suit.",
            listOf("br_m6", "u_hijump", "no_m6", "br_s1", "br_m8", "u_varia", "br_e3"),
        ),
        Leg(
            "wave", "Norfair: Wave Beam",
            "The Varia Suit opens Norfair's super-heated rooms. Beat the two larvae on the way to Ridley's Lair.",
            listOf("br_m9", "br_m10", "no_m5", "no_m7", "no_s1", "no_m9", "no_m10", "u_wave", "no_e1", "no_m11"),
        ),
        Leg(
            "ridley", "Ridley's Lair",
            "Beat Imago for your first Super Missiles, sweep the lair counter-clockwise, grab Unknown Item 3 and defeat Ridley.",
            listOf(
                "ri_s1", "ri_e1", "ri_m1", "ri_m2", "ri_m3", "ri_m4", "ri_m5", "ri_m6", "ri_s2",
                "ri_m7", "ri_m8", "ri_m9", "ri_m10", "ri_s3", "u_gravity", "ri_e2", "ri_e3", "ri_m11",
            ),
        ),
        Leg(
            "tourian", "Screw Attack and Tourian",
            "Riding back up to Norfair opens the way to the Screw Attack. Then on to Tourian and Mother Brain.",
            listOf("u_screw", "no_m12"),
        ),
        Leg(
            "suit", "Chozodia: suit restored",
            "After the Ruins Test your suit is back with Plasma Beam, Space Jump and Gravity Suit working.",
            listOf("cz_s1", "cz_e3", "cz_m1", "cz_s2", "cz_s3", "cz_s4"),
        ),
        Leg(
            "pb", "Chozodia: Power Bombs",
            "Take back your Power Bombs on the ship, then use them to open the ship and the cliffs below the ruins.",
            listOf("cz_p1", "cz_p2", "cz_s5", "cz_s6", "cz_p3", "cz_p4", "cz_e2", "cz_s7", "cz_s8", "cz_p5", "cz_p6"),
        ),
        Leg(
            "cleanup", "Cleanup across Zebes",
            "Crateria, then Ridley's Lair, Norfair, Kraid's Lair (via the hidden tunnel by Norfair's Map Room) and Mother Brain's empty lair.",
            listOf("cr_s1", "cr_m3", "ri_m12", "ri_m13", "no_m8", "no_m13", "no_s2", "no_p1", "kr_m9", "to_p1", "to_m1"),
        ),
        Leg(
            "final", "Final run",
            "Grab the landing site's Power Bomb Tank on the way back, and the last Energy Tank next to Mecha Ridley's room.",
            listOf("cr_p1", "cz_e1"),
        ),
    )

    /** Which leg each item belongs to. */
    val legOf: Map<String, Leg> = legs.flatMap { leg -> leg.itemIds.map { it to leg } }.toMap()

    /** Every item in route order. */
    val items: List<Item> = legs.flatMap { leg -> leg.itemIds.map { MzmItems.byId.getValue(it) } }

    init {
        val ids = items.map { it.id }
        require(ids.size == MzmItems.all.size && ids.toSet() == MzmItems.byId.keys) {
            "MzmRoute must list every item exactly once"
        }
    }
}
