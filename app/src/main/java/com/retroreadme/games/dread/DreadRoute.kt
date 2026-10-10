package com.retroreadme.games.dread

/** One trip in the 100% route: a stretch of the game and the items picked up along the way. */
data class Leg(val id: String, val title: String, val note: String, val itemIds: List<String>)

/**
 * All 146 items in a 100% order. The story trips follow Gameranx's walkthrough and pick up the
 * upgrades and the items right on the way; once the Power Bomb opens everything, a cleanup trip
 * through each area collects the rest. Raven Beak (Itorash) is the point of no return, so finish
 * the cleanup before taking the capsule from Hanubia.
 */
object DreadRoute {

    val legs: List<Leg> = listOf(
        Leg(
            "artaria", "Artaria: the first E.M.M.I.",
            "The Charge Beam, then the grey E.M.M.I. for the Spider Magnet, and Corpius for the Phantom Cloak.",
            listOf(
                "u_chargebeam", "ar_e2", "u_spidermagnet", "ar_m12", "u_phantomcloak"
            ),
        ),
        Leg(
            "cataris", "Cataris and Dairon: Morph Ball and Varia",
            "Thermal Flow switches through Cataris, the Wide Beam in Dairon, the green E.M.M.I. for the Morph Ball, the Varia Suit back in Artaria, then Kraid.",
            listOf(
                "u_widebeam", "u_morphball", "ar_m10", "ar_q1", "u_variasuit", "ca_x1", "u_diffusionbeam"
            ),
        ),
        Leg(
            "speed", "Dairon and Burenia: Bomb to Grapple",
            "The Bomb in Dairon, the Flash Shift in Burenia, the yellow E.M.M.I. for the Speed Booster, and the Grapple Beam back in Artaria.",
            listOf(
                "u_morphballbomb", "u_flashshift", "bu_q1", "u_speedbooster", "da_e1", "da_q4",
                "u_grapplebeam"
            ),
        ),
        Leg(
            "ghavoran", "Ghavoran and Elun",
            "After Drogyga: Super Missiles, the Plasma Beam in Elun, the Spin Boost, the blue E.M.M.I. for Ice Missiles, and the Pulse Radar.",
            listOf(
                "u_supermissile", "u_plasmabeam", "el_e1", "u_spinboost", "u_icemissile", "gh_e1",
                "u_pulseradar"
            ),
        ),
        Leg(
            "ferenia", "Ferenia: Escue and Space Jump",
            "Escue for the Storm Missile, then the Space Jump past the Twin Robot Chozo Soldiers.",
            listOf(
                "u_stormmissiles", "u_spacejump"
            ),
        ),
        Leg(
            "gravity", "Gravity Suit and Screw Attack",
            "Into Burenia's depths for the Gravity Suit, Experiment Z-57 in Cataris, then the Screw Attack in thawed Artaria.",
            listOf(
                "bu_e2", "u_gravitysuit", "u_screwattack"
            ),
        ),
        Leg(
            "bombs", "Cross Bomb, Wave Beam and Power Bomb",
            "Golzuna, the purple E.M.M.I. and the Chozo Soldier in Hanubia. With the Power Bomb, everything is open.",
            listOf(
                "u_crossbomb", "u_wavebeam", "u_powerbomb"
            ),
        ),
        Leg(
            "clean_hanubia", "Cleanup: Hanubia",
            "Everything left in Hanubia, top to bottom on the map.",
            listOf(
                "ha_m1", "ha_p1", "ha_m2"
            ),
        ),
        Leg(
            "clean_ferenia", "Cleanup: Ferenia",
            "Everything left in Ferenia, top to bottom on the map.",
            listOf(
                "fe_x1", "fe_m1", "fe_p1", "fe_m2", "fe_m3", "fe_q1", "fe_m4", "fe_m5", "fe_m6", "fe_q2",
                "fe_q3", "fe_p2", "fe_x2", "fe_q4"
            ),
        ),
        Leg(
            "clean_ghavoran", "Cleanup: Ghavoran",
            "Everything left in Ghavoran, top to bottom on the map.",
            listOf(
                "gh_q1", "gh_p1", "gh_m1", "gh_m2", "gh_m3", "gh_m4", "gh_m5", "gh_q2", "gh_m6", "gh_m7",
                "gh_x1", "gh_m8", "gh_m9", "gh_m10"
            ),
        ),
        Leg(
            "clean_elun", "Cleanup: Elun",
            "Everything left in Elun, top to bottom on the map.",
            listOf(
                "el_m1", "el_p1", "el_m2"
            ),
        ),
        Leg(
            "clean_burenia", "Cleanup: Burenia",
            "Everything left in Burenia, top to bottom on the map.",
            listOf(
                "bu_m1", "bu_x1", "bu_m2", "bu_m3", "bu_e1", "bu_m4", "bu_m5", "bu_m6", "bu_q2", "bu_m7",
                "bu_x2", "bu_m8", "bu_x3", "bu_x4", "bu_p1"
            ),
        ),
        Leg(
            "clean_dairon", "Cleanup: Dairon",
            "Everything left in Dairon, top to bottom on the map.",
            listOf(
                "da_m1", "da_q1", "da_m2", "da_p1", "da_q2", "da_m3", "da_m4", "da_m5", "da_m6", "da_m7",
                "da_m8", "da_m9", "da_m10", "da_q3", "da_p2", "da_m11", "da_x1", "da_p3"
            ),
        ),
        Leg(
            "clean_cataris", "Cleanup: Cataris",
            "Everything left in Cataris, top to bottom on the map.",
            listOf(
                "ca_m1", "ca_p1", "ca_m2", "ca_e1", "ca_q1", "ca_m3", "ca_m4", "ca_m5", "ca_m6", "ca_m7",
                "ca_p2", "ca_m8", "ca_m9", "ca_m10", "ca_m11", "ca_m12", "ca_q2", "ca_m13", "ca_p3",
                "ca_m14"
            ),
        ),
        Leg(
            "clean_artaria", "Cleanup: Artaria",
            "Everything left in Artaria, top to bottom on the map.",
            listOf(
                "ar_e1", "ar_x1", "ar_m1", "ar_m2", "ar_m3", "ar_m4", "ar_m5", "ar_m6", "ar_m7", "ar_p1",
                "ar_m8", "ar_m9", "ar_m11", "ar_m13", "ar_m14", "ar_m15", "ar_m16", "ar_m17", "ar_m18",
                "ar_m19", "ar_m20", "ar_m21", "ar_x2", "ar_q2", "ar_m22"
            ),
        ),
    )

    /** Which leg each item belongs to. */
    val legOf: Map<String, Leg> = legs.flatMap { leg -> leg.itemIds.map { it to leg } }.toMap()

    /** Every item in route order. */
    val items: List<Item> = legs.flatMap { leg -> leg.itemIds.map { DreadItems.byId.getValue(it) } }

    init {
        val ids = items.map { it.id }
        require(ids.size == DreadItems.all.size && ids.toSet() == DreadItems.byId.keys) {
            "DreadRoute must list every item exactly once"
        }
    }
}
