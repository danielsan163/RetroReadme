package com.retroreadme.games.sm

/** One trip in the 100% route: a stretch of the game and the items picked up along the way. */
data class Leg(val id: String, val title: String, val note: String, val itemIds: List<String>)

/**
 * All 100 items in the order a completionist playthrough picks them up, split into trips.
 * Follows Budwin's 100% walkthrough (checked against Metroid Recon), except that the Wave Beam
 * waits for the Grappling Beam instead of a wall-jump. No sequence breaks.
 */
object SmRoute {

    val legs: List<Leg> = listOf(
        Leg(
            "start", "Crateria and the Morphing Ball",
            "Down to Brinstar for the Morphing Ball and first Missiles, then back up to Crateria for the Bombs.",
            listOf("u_morph", "br_m1", "u_bomb", "cr_m1", "cr_e1"),
        ),
        Leg(
            "green", "Green and pink Brinstar",
            "The second elevator leads into green Brinstar. Beat Spore Spawn for Super Missiles, then open the Spazer's green door.",
            listOf("br_m3", "br_m5", "u_charge", "br_s1", "br_m6", "u_spazer"),
        ),
        Leg(
            "kraid", "Hi-Jump Boots and Kraid",
            "Down into Norfair for the Hi-Jump Boots, then back to Brinstar's Kraid's Lair for the Varia Suit.",
            listOf("no_e1", "u_hijump", "no_m1", "u_varia", "br_e1"),
        ),
        Leg(
            "norfair", "Upper Norfair",
            "With the Varia Suit, the hot rooms are open: the Speed Booster to the east, the Ice Beam to the west.",
            listOf("no_m10", "no_m2", "no_m9", "no_m3", "u_speed", "u_ice", "no_m4", "no_r1", "no_m5"),
        ),
        Leg(
            "croc", "Crocomire and the Grappling Beam",
            "Beat Crocomire, take the Power Bombs and Speed Boost across the cavern to the Grappling Beam. Then back east for the Wave Beam.",
            listOf("no_p1", "no_m7", "no_m11", "u_grapple", "no_e2", "no_m8", "u_wave"),
        ),
        Leg(
            "red", "Red Brinstar",
            "Up through red Brinstar for the X-Ray Scope and two Power Bomb Tanks.",
            listOf("u_xray", "br_p1", "br_m11", "br_p2"),
        ),
        Leg(
            "ship", "The Wrecked Ship",
            "Grapple across the flooded cavern, beat Phantoon to restore power, and find the Gravity Suit.",
            listOf(
                "cr_m2", "ws_m1", "ws_s1", "ws_s2", "ws_m2", "cr_m4", "cr_m5", "ws_m3", "ws_r1",
                "u_gravity", "cr_m6", "ws_e1",
            ),
        ),
        Leg(
            "sparks", "Crateria: Shinesparks",
            "Three Shinesparks from the landing site lead to four Crateria items.",
            listOf("cr_p1", "cr_e2", "cr_m7", "cr_m8"),
        ),
        Leg(
            "sweep", "Brinstar sweep",
            "With Power Bombs, the Speed Booster and the X-Ray Scope, sweep Brinstar from green to pink to blue.",
            listOf(
                "br_r1", "br_m7", "br_m8", "br_s2", "br_e2", "br_s3", "br_p4", "br_e4", "br_m4", "br_p5",
                "br_e5", "br_p3", "br_e3", "br_m2", "br_m9", "br_m10",
            ),
        ),
        Leg(
            "tourian", "Old Tourian",
            "Mother Brain's old room and the shaft beside it, back in Crateria.",
            listOf("cr_m3", "cr_s1"),
        ),
        Leg(
            "maridia", "Maridia and Draygon",
            "Through the glass tube into Maridia: Botwoon, then Draygon for the Space Jump.",
            listOf("ma_m1", "ma_s1", "ma_e2", "ma_m8", "ma_m4", "ma_m5", "ma_s3", "ma_m2", "ma_s2", "ma_e1", "ma_m3", "u_space"),
        ),
        Leg(
            "maridia2", "Maridia: Spring Ball and Plasma",
            "The sand pits under the glass tube, the Spring Ball past Shaktool, and the Plasma Beam behind Draygon's sealed door.",
            listOf("ma_m6", "ma_p1", "u_spring", "u_plasma", "ma_r1", "ma_m7"),
        ),
        Leg(
            "back", "Spring Ball errands",
            "Two tanks back in Kraid's Lair and Norfair.",
            listOf("br_m12", "no_m6"),
        ),
        Leg(
            "ridley", "Ridley's Lair",
            "Golden Torizo for the Screw Attack, then Ridley. Tourian and Mother Brain are next.",
            listOf("no_m12", "no_s1", "u_screw", "no_m13", "no_p2", "no_e4", "no_e3", "no_m14", "no_p3", "no_m15"),
        ),
    )

    /** Which leg each item belongs to. */
    val legOf: Map<String, Leg> = legs.flatMap { leg -> leg.itemIds.map { it to leg } }.toMap()

    /** Every item in route order. */
    val items: List<Item> = legs.flatMap { leg -> leg.itemIds.map { SmItems.byId.getValue(it) } }

    init {
        val ids = items.map { it.id }
        require(ids.size == SmItems.all.size && ids.toSet() == SmItems.byId.keys) {
            "SmRoute must list every item exactly once"
        }
    }
}
