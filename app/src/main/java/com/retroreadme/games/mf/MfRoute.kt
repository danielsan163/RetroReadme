package com.retroreadme.games.mf

/** One trip in the 100% route: a stretch of the game and the items picked up along the way. */
data class Leg(val id: String, val title: String, val note: String, val itemIds: List<String>)

/**
 * All 100 items in the order a completionist playthrough picks them up, split into trips.
 * Follows Thonky's 100% item order (checked against Metroid Recon's walkthrough). The game sends
 * you sector to sector, so this is mostly the story order, with a cleanup at the end once the
 * Screw Attack opens everything up.
 */
object MfRoute {

    val legs: List<Leg> = listOf(
        Leg(
            "start", "Main Deck: first steps",
            "Missiles from the first Data Room, then Arachnus-X for the Morph Ball.",
            listOf("md_m1", "md_m2", "md_e1", "md_e2"),
        ),
        Leg(
            "srx", "Sector 1 (SRX): Charge Beam",
            "Clear the atmospheric stabilizers and beat Charge Core-X.",
            listOf("md_m3", "s1_e1", "s1_m4", "s1_m1", "s1_m2", "s1_m3"),
        ),
        Leg(
            "tro", "Sector 2 (TRO): Bombs and Hi-Jump",
            "Bombs from the Data Room past the Level 1 doors, then Zazabi for the Hi-Jump and Jumpball.",
            listOf("s2_m1", "s2_m2", "s2_m3", "s2_e1", "s2_m4", "s2_m5", "s2_m6", "s2_m7", "s2_m8"),
        ),
        Leg(
            "aqa", "Sector 4 (AQA): Speed Booster",
            "Serris for the Speed Booster, then lower the water at Pump Control.",
            listOf("s4_m2", "s4_m3", "s4_e1", "s4_m1", "s4_m4", "s4_m5"),
        ),
        Leg(
            "pyr", "Sector 3 (PYR): Super Missiles",
            "Open the Level 2 doors, beat B.O.X. and download the Super Missiles.",
            listOf("s3_m1", "s3_m2", "s3_m3"),
        ),
        Leg(
            "noc", "Sector 6 (NOC): Varia Suit",
            "Dark rooms and an SA-X close call, then Mega Core-X for the Varia Suit.",
            listOf("s6_m1", "s6_e1", "s6_e2", "s6_m2", "s6_m3", "s6_m4"),
        ),
        Leg(
            "arc", "Sector 5 (ARC): Ice Missiles",
            "The Ice Missile Data Room behind the Level 3 doors.",
            listOf("s5_e1", "s5_m1", "s5_m2"),
        ),
        Leg(
            "survivors", "Main Deck: survivors",
            "After the Sector 3 boiler emergency (Wide Core-X, for the Wide Beam), check on the Habitation Deck.",
            listOf("md_m4", "md_m5"),
        ),
        Leg(
            "pb", "Sector 5 (ARC): Power Bombs",
            "Power Bombs from the Data Room, and an SA-X chase on the way out.",
            listOf("s5_p1", "s5_p2", "s5_p3"),
        ),
        Leg(
            "blackout", "Main Deck: blackout and Space Jump",
            "The elevator stalls: find the Auxiliary Power Station and beat Yakuza for the Space Jump.",
            listOf("md_p1", "md_m6", "md_e3", "md_m7"),
        ),
        Leg(
            "plasma", "Sector 2 (TRO): Plasma Beam",
            "Clear the overgrowth around the reactor: Nettori for the Plasma Beam.",
            listOf("s2_p1", "s2_e2"),
        ),
        Leg(
            "nightmare", "Sector 5 (ARC): Nightmare",
            "Nightmare has broken loose. Beat it for the Gravity Suit.",
            listOf("s5_m3", "s5_p4", "s5_e3", "s5_p6", "s5_p7", "s5_e2", "s5_p8"),
        ),
        Leg(
            "diffusion", "Sector 4 (AQA): Diffusion Missiles",
            "Speed Boost into Sector 4, open the Level 4 doors and download the Diffusion Missiles.",
            listOf("s4_m6", "s4_p1", "s4_p2", "s4_e2", "s4_m7", "s4_m8", "s4_p3", "s4_m9", "s4_p4"),
        ),
        Leg(
            "wave", "Sector 6 (NOC): Wave Beam",
            "Through the Restricted Zone to B.O.X. II for the Wave Beam.",
            listOf("s6_m6"),
        ),
        Leg(
            "ridley", "Sector 1 (SRX): Ridley-X",
            "After the Restricted Lab is jettisoned, Ridley-X for the Screw Attack.",
            listOf("s1_e2", "s1_p1"),
        ),
        Leg(
            "cleanup1", "Cleanup: Sectors 1 and 3",
            "With the Screw Attack everything is open. Sweep Sector 1, then cross into Sector 3.",
            listOf(
                "s1_p2", "s1_e3", "s1_m5", "s1_m6", "s1_p3", "s3_m4", "s3_e1", "s3_p1", "s3_p2",
                "s3_m5", "s3_p4", "s3_p5", "s3_e2", "s3_m6", "s3_p3", "s3_m7", "s3_e3", "s3_p6",
            ),
        ),
        Leg(
            "cleanup2", "Cleanup: Sectors 5, 6, 2 and the Main Deck",
            "Through the tunnels to Sectors 5 and 6, the Restricted Zone, Sector 2, and a last stop on the Main Deck.",
            listOf(
                "s5_p5", "s5_m4", "s6_p1", "s6_p2", "md_p2", "s6_p3", "s6_e3", "s6_m5", "s2_e3",
                "s2_p4", "s2_p2", "s2_p3", "s2_p5", "s2_m9", "md_p3",
            ),
        ),
    )

    /** Which leg each item belongs to. */
    val legOf: Map<String, Leg> = legs.flatMap { leg -> leg.itemIds.map { it to leg } }.toMap()

    /** Every item in route order. */
    val items: List<Item> = legs.flatMap { leg -> leg.itemIds.map { MfItems.byId.getValue(it) } }

    init {
        val ids = items.map { it.id }
        require(ids.size == MfItems.all.size && ids.toSet() == MfItems.byId.keys) {
            "MfRoute must list every item exactly once"
        }
    }
}
