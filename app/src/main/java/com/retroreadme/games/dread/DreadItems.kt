package com.retroreadme.games.dread

import com.retroreadme.games.dread.Area.ARTARIA
import com.retroreadme.games.dread.Area.BURENIA
import com.retroreadme.games.dread.Area.CATARIS
import com.retroreadme.games.dread.Area.DAIRON
import com.retroreadme.games.dread.Area.ELUN
import com.retroreadme.games.dread.Area.FERENIA
import com.retroreadme.games.dread.Area.GHAVORAN
import com.retroreadme.games.dread.Area.HANUBIA
import com.retroreadme.games.dread.Kind.ENERGY
import com.retroreadme.games.dread.Kind.MAJOR
import com.retroreadme.games.dread.Kind.MISSILE
import com.retroreadme.games.dread.Kind.MISSILE_PLUS
import com.retroreadme.games.dread.Kind.PART
import com.retroreadme.games.dread.Kind.POWER_BOMB

/**
 * All 146 items: 23 upgrades, 8 Energy Tanks, 16 Energy Parts, 75 Missile Tanks, 11 Missile+
 * Tanks and 13 Power Bomb Tanks. Positions come from MapGenie's map; the directions from
 * Gameranx's area guides, matched to those positions. Numbering within each area runs top to
 * bottom on the map. Items marked confirm are ones where the match was a judgement call.
 */
object DreadItems {

    private val code = mapOf(
        ARTARIA to "ar", CATARIS to "ca", DAIRON to "da", BURENIA to "bu",
        FERENIA to "fe", GHAVORAN to "gh", ELUN to "el", HANUBIA to "ha",
    )
    private val letter = mapOf(ENERGY to "e", PART to "q", MISSILE to "m", MISSILE_PLUS to "x", POWER_BOMB to "p")

    private fun up(id: String, area: Area, name: String, needs: List<String>, vararg steps: String) =
        Item("u_$id", area, MAJOR, name, needs, steps.toList())

    private fun t(area: Area, kind: Kind, n: Int, needs: List<String>, vararg steps: String, confirm: Boolean = false) =
        Item("${code.getValue(area)}_${letter.getValue(kind)}$n", area, kind, "${kind.label} $n", needs, steps.toList(), confirm)

    private const val SHINE = SHINESPARK
    private const val MORPH = "Morph Ball"
    private const val BOMB = "Bomb"
    private const val CROSS = "Cross Bomb"
    private const val PB = "Power Bomb"
    private const val SPEED = "Speed Booster"
    private const val SPACE = "Space Jump"
    private const val SCREW = "Screw Attack"
    private const val GRAPPLE = "Grapple Beam"
    private const val MAGNET = "Spider Magnet"
    private const val SHIFT = "Flash Shift"
    private const val GRAVITY = "Gravity Suit"
    private const val VARIA = "Varia Suit"
    private const val WAVE = "Wave Beam"
    private const val DIFFUSION = "Diffusion Beam"

    val all: List<Item> = listOf(

        // ---------------------------------------------------------------- Artaria

        up("chargebeam", ARTARIA, "Charge Beam", emptyList(),
            "A Chozo Statue in the bottom-left corner of Artaria, once you've slipped past the first working E.M.M.I. It's in AR-03."),
        up("spidermagnet", ARTARIA, "Spider Magnet", listOf("Charge Beam"),
            "Destroy the grey E.M.M.I.: open the Thermal doors with the Charge Beam, take the Omega Beam from the Central Unit and blast its core. It's in AR-05."),
        up("phantomcloak", ARTARIA, "Phantom Cloak", listOf(MAGNET),
            "Corpius's reward (see Bosses), past the alien door at the top of the tall E.M.M.I. Zone shaft. It's in AR-10."),
        up("variasuit", ARTARIA, "Varia Suit", listOf(MORPH),
            "From the Red Teleportal (from Cataris), go left past the Save Station through the Morph Ball gap. Throw the Thermal Flow switch and run up through the exploding rooms to the Chozo Statue. It's in AR-11."),
        up("grapplebeam", ARTARIA, "Grapple Beam", listOf(SPEED, SHINE),
            "From the Yellow Teleportal (from Dairon), break the Speed Booster blocks in the upper left and Shinespark across the gap, then follow the path round. It's in AR-14."),
        up("screwattack", ARTARIA, "Screw Attack", listOf(GRAVITY),
            "Far left of Artaria once it's thawed (the Blue Teleportal from Cataris drops you nearby). It's in AR-16."),

        t(ARTARIA, MISSILE, 1, listOf("Super Missile"),
            "From the Red Teleportal go up, above the Save Station, to the Super Missile and Charge Beam doors. The tank is in AR-17."),
        t(ARTARIA, MISSILE, 2, listOf(VARIA),
            "The big superheated room by the Cataris elevator, on the far right. The tank is in AR-19."),
        t(ARTARIA, MISSILE, 3, listOf(MORPH),
            "Through the upper-left door of the Network Station with four doors: shoot the red pustules above to open the walls, then roll in. The tank is in AR-12."),
        t(ARTARIA, MISSILE, 4, emptyList(),
            "Just inside the E.M.M.I. Zone door by the Dairon elevator and Save Station, on the floor behind a Beam block. The tank is in AR-12."),
        t(ARTARIA, MISSILE, 5, listOf("Charge Beam"),
            "Right of the four-door Network Station: through the upper-right Charge door, then slide under to it. The tank is in AR-20."),
        t(ARTARIA, MISSILE, 6, listOf(VARIA),
            "The superheated room above the far-right Save Station, the one with a blue magnetic ceiling. The tank is in AR-21."),
        t(ARTARIA, MISSILE, 7, emptyList(),
            "After escaping the second E.M.M.I., climb the shaft out of the cavern: it's by a Sensor door near the second Network Station. The tank is in AR-08.",
            confirm = true),
        t(ARTARIA, MISSILE, 8, emptyList(),
            "Next to the fuel switch above Corpius's room: shoot the Missile block. The tank is in AR-22."),
        t(ARTARIA, MISSILE, 9, listOf(MORPH, SPEED, SHINE, SPACE),
            "The huge E.M.M.I. Zone room with Spider Magnet walls has a Morph Ball hole in its left wall, ending at Speed Booster blocks.",
            "Charge up in the passage at the far upper right, drop to the bottom-left corner, store a Shinespark, Space Jump up, roll into the hole and launch left. The tank is in AR-11."),
        t(ARTARIA, MISSILE, 10, listOf(MORPH),
            "Right below the Red Teleportal, where the one in Cataris brings you. The tank is in AR-11."),
        t(ARTARIA, MISSILE, 11, emptyList(),
            "From the Map Station, go up to the giant pink crab-like enemy and get to the far side of the slide. The tank is in AR-11."),
        t(ARTARIA, MISSILE, 12, listOf(MAGNET),
            "The tall E.M.M.I. Zone room covered in blue walls: at the top, shoot through the wall. You pass it right after the first E.M.M.I. falls. The tank is in AR-06."),
        t(ARTARIA, MISSILE, 13, emptyList(),
            "Next to the funnel-shaped room where you first meet an E.M.M.I. The tank is in AR-23.",
            confirm = true),
        t(ARTARIA, MISSILE, 14, listOf(SCREW),
            "Just left of the Burenia elevator: break the Beam blocks and Screw Attack blocks. The tank is in AR-25."),
        t(ARTARIA, MISSILE, 15, listOf(WAVE),
            "Under the Total Recharge Station by the Burenia elevator, once the area's thawed: a red pustule on the bottom level. Shoot it through the wall with the Wave Beam. The tank is in AR-16.",
            confirm = true),
        t(ARTARIA, MISSILE, 16, listOf(SPACE, BOMB),
            "Right of the central Save Station, the room with a funnel: Space Jump to the upper-right corner and bomb the alcove. The tank is in AR-26.",
            confirm = true),
        t(ARTARIA, MISSILE, 17, listOf(GRAPPLE),
            "The middle of the E.M.M.I. Zone, behind a Grapple point, right of a Wide Beam box. The tank is in AR-04."),
        t(ARTARIA, MISSILE, 18, listOf(SPACE),
            "Under the central Save Station, at the top of a tall path only Space Jump reaches. The tank is in AR-26."),
        t(ARTARIA, MISSILE, 19, listOf(GRAVITY),
            "Top of the big freezing room at the far left, left of the Chozo Soldier's arena. The tank is in AR-27."),
        t(ARTARIA, MISSILE, 20, listOf(GRAVITY),
            "In the water under the Screw Attack and Total Recharge rooms at the far left, once the area's thawed. You pass it on the way. The tank is in AR-16.",
            confirm = true),
        t(ARTARIA, MISSILE, 21, listOf(MAGNET),
            "The lower-left Energy Recharge Station room: climb the blue wall on the left and drop into the water. The tank is in AR-04."),
        t(ARTARIA, MISSILE, 22, listOf(PB),
            "Just outside the Charge Beam's Chozo Statue room: Power Bomb to open a Morph Ball path. The tank is in AR-04."),
        t(ARTARIA, MISSILE_PLUS, 1, listOf(GRAVITY),
            "Through the door right of the Varia Suit's statue, in plain view in the lava. The tank is in AR-11."),
        t(ARTARIA, MISSILE_PLUS, 2, listOf(SPEED),
            "The long cave right of the Chozo Soldier's arena: clear it, break the Speed Booster blocks on the left, then run, jump to the ledge and slide through the small blocks while still boosting. The tank is in AR-04."),
        t(ARTARIA, ENERGY, 1, listOf(SPEED, SHINE, SHIFT),
            "Far upper-left corner, down the hallway from the Varia Suit, behind a Shutter and Speed Booster blocks.",
            "Left of the shutter, boost and store a Shinespark running right over the shutter; then run back left, Flash Shift over it and Shinespark through. The tank is in AR-11."),
        t(ARTARIA, ENERGY, 2, listOf("Charge Beam"),
            "Right of the starting room, under the first Network Station: slide under once you have the Charge Beam. The tank is in AR-01."),
        t(ARTARIA, PART, 1, listOf(MORPH),
            "Left of the upper-left Save Station by the Red Teleportal, through a Morph Ball gap. On the way to the Varia Suit. It's in AR-11."),
        t(ARTARIA, PART, 2, emptyList(),
            "From the lower Energy Recharge Station go left into the cold room and run through it. It's in AR-04."),
        t(ARTARIA, POWER_BOMB, 1, listOf(SPACE),
            "Left side of the E.M.M.I. Zone, the huge room with Spider Magnet walls: Space Jump to the top. The tank is in AR-04."),

        // ---------------------------------------------------------------- Cataris

        up("morphball", CATARIS, "Morph Ball", listOf("Wide Beam"),
            "Destroy the green E.M.M.I.: break the Wide Beam cover above the Save Station to reach the Central Unit. It's in CA-04."),
        up("diffusionbeam", CATARIS, "Diffusion Beam", listOf(VARIA),
            "Kraid's reward (see Bosses). With the Varia Suit, go through the left door by the Ammo Recharge Station and follow the cave. It's in CA-03."),

        t(CATARIS, MISSILE, 1, listOf(SPEED, SHINE, SPACE),
            "The big room next to the Dairon tram: Speed Booster blocks in the top-right ceiling.",
            "Run from the tram, slide through the small Beam block, Space Jump and Shinespark across the gap, then Shinespark up into the blocks. The tank is in CA-06."),
        t(CATARIS, MISSILE, 2, listOf("Wide Beam"),
            "Upper right, the tall shaft room: break the Beam block wall on the left into a secret room. At its bottom push the Wide Beam crate, drop down and reveal the hidden spot on the left. The tank is in CA-08."),
        t(CATARIS, MISSILE, 3, listOf(SPACE),
            "The alcove above the Orange Teleportal; Space Jump up to it. The tank is in CA-04.",
            confirm = true),
        t(CATARIS, MISSILE, 4, listOf(VARIA, SPACE, PB),
            "Above the Blue Teleportal: jump to the very top of the room and bomb the alcove to drop down to it. The tank is in CA-10."),
        t(CATARIS, MISSILE, 5, listOf(MORPH),
            "At the left-center E.M.M.I. Zone exit. The tank is in CA-11."),
        t(CATARIS, MISSILE, 6, listOf(MORPH, MAGNET),
            "At the far-right Save Station by the Energy Recharge Station: a Morph Ball passage leads to a hidden room with a Spider Magnet ceiling. The tank is in CA-11."),
        t(CATARIS, MISSILE, 7, emptyList(),
            "Just above the Artaria elevator: take the right door from the elevator, then go up and right. The tank is in CA-10."),
        t(CATARIS, MISSILE, 8, listOf(GRAVITY, SPEED),
            "From the Red Teleportal into the superheated room with the Orange Teleportal: Speed Boost through the wall in the lower right, shoot the Beam blocks and slide under the low wall without losing speed. The tank is in CA-04.",
            confirm = true),
        t(CATARIS, MISSILE, 9, listOf(DIFFUSION),
            "Right above the Purple Teleportal past Kraid's room. Instead of crossing the lava, break a path up with the Diffusion Beam. The tank is in CA-03."),
        t(CATARIS, MISSILE, 10, listOf(VARIA, WAVE),
            "From the Purple Teleportal go right into the cave and up into the superheated room: a red pustule in its upper-right corner (Wave or Diffusion Beam). The tank is in CA-03."),
        t(CATARIS, MISSILE, 11, listOf(VARIA),
            "The superheated room above the lower-right Network Station. The tank is in CA-03."),
        t(CATARIS, MISSILE, 12, listOf(DIFFUSION, BOMB),
            "From the Purple Teleportal go left, break the red pustule and roll through the narrow tunnel into the lava room. Bomb the end. The tank is in CA-03."),
        t(CATARIS, MISSILE, 13, listOf(SCREW),
            "Above Experiment Z-57's arena: on the upper of the two thermal trapdoors, a passage you break with the Screw Attack. The tank is in CA-03."),
        t(CATARIS, MISSILE, 14, listOf(GRAVITY, DIFFUSION),
            "Back from Artaria with the Gravity Suit, break the wall in your way with the Diffusion Beam; it's just past it. The tank is in CA-02.",
            confirm = true),
        t(CATARIS, MISSILE_PLUS, 1, listOf(MORPH),
            "In the vents right outside the Save Station before Kraid. The tank is in CA-04."),
        t(CATARIS, ENERGY, 1, listOf(MAGNET),
            "Upper right: once the third Thermal Flow switch has lowered the lava, hang on the Spider Magnet wall in the shaft to lower a platform, then reach it from the first Thermal Flow switch. The tank is in CA-06."),
        t(CATARIS, PART, 1, listOf(MAGNET),
            "The long hallway above the upper-right Save Station, once the lava's down: wall-jump up and go left past the Spider Magnet platform. It's in CA-06."),
        t(CATARIS, PART, 2, listOf(GRAVITY, PB, SPEED, SHINE),
            "From the Purple Teleportal go down a level through Beam blocks into a lava passage. Power Bomb through to the Speed Booster blocks, break them and Shinespark up. It's in CA-03."),
        t(CATARIS, POWER_BOMB, 1, listOf(GRAPPLE, PB, MAGNET),
            "Upper right, the tall shaft with two Spider Magnet walls: take the top-right Grapple door. Power Bomb and Missile the block, then hang from the Spider Magnet ceiling to lower it. The tank is in CA-09."),
        t(CATARIS, POWER_BOMB, 2, listOf(PB, SPEED, SHINE),
            "Middle of the E.M.M.I. Zone: go left from the Save Station door into the vents and Power Bomb the block.",
            "Above the vents, Speed Boost, slide through the narrow gap and store a Shinespark, then drop down and spark right as a Morph Ball. The tank is in CA-11."),
        t(CATARIS, POWER_BOMB, 3, listOf(GRAVITY, PB, GRAPPLE),
            "Kraid's arena: through the lava at the bottom and roll through the vents; at the top, pull away the Grapple block. The tank is in CA-03."),

        // ---------------------------------------------------------------- Dairon

        up("widebeam", DAIRON, "Wide Beam", emptyList(),
            "A Chozo Statue in Dairon's dark section, once you've restored the power at its generator. It's in DA-05."),
        up("morphballbomb", DAIRON, "Bomb", listOf(DIFFUSION),
            "Restore the second generator past the E.M.M.I. Zone, then go into the room left of the upper Save Station. It's in DA-07."),
        up("speedbooster", DAIRON, "Speed Booster", listOf(SHIFT),
            "Destroy the yellow E.M.M.I. in the big lower-left E.M.M.I. Zone (reached from Burenia's lower tram). It's in DA-08."),

        t(DAIRON, MISSILE, 1, listOf(SPEED, SHINE),
            "The room next to the Cataris tram: Shinespark up through the Speed Booster blocks to the top. The tank is in DA-02."),
        t(DAIRON, MISSILE, 2, listOf(BOMB),
            "Under the Bomb's Chozo Statue: bomb-jump to a hidden block. The tank is in DA-07."),
        t(DAIRON, MISSILE, 3, listOf(GRAVITY, WAVE),
            "Upper-left freezing rooms, above the wall of Missile Tank 6: Wave Beam the red pustule in the wall left of the Spider Magnet wall. It's at the start of the hidden path. The tank is in DA-09."),
        t(DAIRON, MISSILE, 4, listOf(CROSS),
            "From the upper-left Total Recharge Station take the lower-right door: a Morph Ball path of Bomb, Missile and Pitfall blocks. Bomb, fire four Missiles, and Cross Bomb over the pitfalls. The tank is in DA-06."),
        t(DAIRON, MISSILE, 5, listOf(GRAPPLE),
            "The very large room near the Artaria elevator, in its upper part: swing across the four Grapple points, or Space Jump later. The tank is in DA-04."),
        t(DAIRON, MISSILE, 6, listOf(GRAVITY, SPEED, SHINE, SCREW),
            "Upper-left freezing rooms: a big wall of Speed Booster blocks. Charge a Shinespark in the Map Station room, drop to the platform beside the wall and launch in.",
            "Below, Screw Attack the enemies and Speed Boost through the last block. The tank is in DA-09."),
        t(DAIRON, MISSILE, 7, listOf(SPEED),
            "Above the Central Unit's room: the room with one sliding blue magnetic ceiling panel. The Speed Booster wall is just right of it. The tank is in DA-08."),
        t(DAIRON, MISSILE, 8, listOf(GRAVITY, GRAPPLE),
            "The hidden lava room (see Missile Tank 11): pull away the Grapple block and roll through the narrow passage at the top. The tank is in DA-10."),
        t(DAIRON, MISSILE, 9, listOf(BOMB),
            "Far left, by the giant monster's corpse: just outside the Save Station, bomb to find a secret compartment. The tank is in DA-11."),
        t(DAIRON, MISSILE, 10, emptyList(),
            "From the far-left Save Station, take the lower vent and shoot up through hidden Beam blocks in the ceiling. It's next to a Grapple block. The tank is in DA-08."),
        t(DAIRON, MISSILE, 11, listOf(DIFFUSION, GRAVITY, SPEED),
            "Next to Energy Part 3: shoot the red pustule to open a hidden door into a big lava room. This one's in the lower-right corner: into the lava with the Speed Booster. The tank is in DA-10."),
        t(DAIRON, MISSILE_PLUS, 1, listOf(SPEED),
            "Above the Ammo Recharge Station: charge the Speed Booster in the E.M.M.I. Zone, then jump, wall-jump, slide and wall-jump again into the Speed Booster blocks. No Shinespark needed. The tank is in DA-08."),
        t(DAIRON, ENERGY, 1, listOf(SPEED, SHINE),
            "Leaving the E.M.M.I. Zone after the Speed Booster, you Shinespark up a tall shaft of Speed Booster blocks. Drop back down to it. The tank is in DA-08."),
        t(DAIRON, PART, 1, listOf(GRAVITY, SPEED, SHINE, SPACE),
            "Upper-left corner of the freezing rooms: from the frozen pool, store a Shinespark, Space Jump up to the wall of Speed Booster blocks and smash through. The timing is tight. It's in DA-09."),
        t(DAIRON, PART, 2, listOf(BOMB),
            "Down the hallway right of Power Bomb Tank 1, past a pressure pad: a hidden block in the ceiling. It's in DA-06."),
        t(DAIRON, PART, 3, listOf(VARIA),
            "The superheated room under the Total Recharge Station. It's in DA-10."),
        t(DAIRON, PART, 4, listOf(SPEED),
            "With the Speed Booster, smash the Speed Booster blocks in front of the door, then come back into the room for it. It's in DA-08."),
        t(DAIRON, POWER_BOMB, 1, listOf(PB),
            "The E.M.M.I. Zone room by the Ferenia elevator, in its upper part. Power Bomb your way to it. The tank is in DA-06."),
        t(DAIRON, POWER_BOMB, 2, listOf(GRAVITY, GRAPPLE),
            "Out of the hidden lava room's right door, grapple to the blue magnetic ceiling and shoot the Missile blocks. It's next to the Artaria elevator. The tank is in DA-10."),
        t(DAIRON, POWER_BOMB, 3, listOf(CROSS, GRAPPLE),
            "The Ammo Recharge Station room (lower left): a secret path to a small water room. Cross Bomb the Bomb blocks, pull away the Grapple block, then bomb the spot. The tank is in DA-08."),

        // ---------------------------------------------------------------- Burenia

        up("flashshift", BURENIA, "Flash Shift", listOf(BOMB),
            "Deeper into Burenia past the Network Station (shoot through the floor by the fans to find the way on). It's in BU-03."),
        up("gravitysuit", BURENIA, "Gravity Suit", listOf(SPACE, GRAPPLE, DIFFUSION),
            "Deep in Burenia's water, from the Green Teleportal. In the big underwater room, break all the red pustules and grapple the loose mechanical pod down through the floor. It's in BU-05."),

        t(BURENIA, MISSILE, 1, listOf(GRAVITY, SPACE),
            "The room under Drogyga's arena: bottom-right corner, in the water. The tank is in BU-06."),
        t(BURENIA, MISSILE, 2, listOf("Pulse Radar"),
            "The tall room right of the Map Station: a single hidden block in the upper-right corner. This room rises when you come back later. The tank is in BU-08."),
        t(BURENIA, MISSILE, 3, listOf(MAGNET, DIFFUSION),
            "From the Network Station, go down and right along the Spider Magnet walls to the top of a water cave. Right corner: break the blocks with the Diffusion Beam. The tank is in BU-03."),
        t(BURENIA, MISSILE, 4, listOf(GRAPPLE),
            "Bottom-left corner of the room joining the two Dairon trams: a pool with a metal Grapple point above it. The tank is in BU-02."),
        t(BURENIA, MISSILE, 5, listOf(SHIFT),
            "The Twin Robot Chozo Soldiers' room: through the upper-left shutter (not the door) to a breakable block in the corner. The tank is in BU-09."),
        t(BURENIA, MISSILE, 6, listOf(SPACE, GRAVITY),
            "The room left of the big chamber with Missile+ Tank 2, in plain view on the path. The tank is in BU-03."),
        t(BURENIA, MISSILE, 7, listOf(GRAVITY, SPEED, SHINE),
            "The big room with Missile+ Tank 2 on a platform: Shinespark into the Speed Booster blocks in the middle. The tank is in BU-11."),
        t(BURENIA, MISSILE, 8, listOf(SCREW),
            "Just outside the Artaria elevator; Screw Attack up to it. The tank is in BU-13."),
        t(BURENIA, MISSILE_PLUS, 1, listOf(SHIFT, MORPH),
            "Left of the Map Station, the room with the magnetic and Grapple wall: at the top, Missile the wall behind the Shutter platform, Flash Shift to the ledge and roll in. The tank is in BU-07."),
        t(BURENIA, MISSILE_PLUS, 2, emptyList(),
            "The big open underwater room under the Flash Shift room: drop off to the left from the top onto its platform. The tank is in BU-03."),
        t(BURENIA, MISSILE_PLUS, 3, listOf(SCREW),
            "On the ceiling of the room left of the Network Station by the Artaria elevator. Screw Attack up. The tank is in BU-09."),
        t(BURENIA, MISSILE_PLUS, 4, listOf(MORPH, SPEED, SHINE),
            "The hardest here. In the Morph Ball area right of the Green Teleportal, break the Speed Booster blocks at the bottom, then keep a Shinespark along a long twisting path.",
            "Store one, shoot through the floor as you fall and launch into the slope; press Down at the top of each slope to store it again. The tank is in BU-10."),
        t(BURENIA, ENERGY, 1, listOf(SHIFT, MAGNET),
            "Right of the center Save Station, in view above Pitfall blocks: Flash Shift to the Spider Magnet wall. The tank is in BU-03."),
        t(BURENIA, ENERGY, 2, listOf("Storm Missile", SPACE),
            "From the Green Teleportal (from Ghavoran), drop to the lower underwater levels, break the Storm Missile crate and Space Jump up. The tank is in BU-04."),
        t(BURENIA, PART, 1, listOf(SPEED),
            "By the fans after the Network Station: shoot through the floor to find the hidden path. It's in BU-02.",
            confirm = true),
        t(BURENIA, PART, 2, listOf(BOMB, "Ice Missile"),
            "The Green Teleportal room: bomb the corner right of the portal, then Ice Missile the Enki blocking the hidden path. It's in BU-10."),
        t(BURENIA, POWER_BOMB, 1, listOf(MORPH, SPEED, SHINE),
            "Bottom of Burenia, right of the squid's room: a narrow Morph Ball path with Speed Booster blocks.",
            "Store a Shinespark in the room before, slide twice into the narrow path, jump as a Morph Ball and launch right. The tank is in BU-09."),

        // ---------------------------------------------------------------- Ferenia

        up("stormmissiles", FERENIA, "Storm Missile", listOf("Pulse Radar", "Ice Missile"),
            "Escue's reward (see Bosses). From the Save Station past the second E.M.M.I. Zone, go down and right to the spire on the far right; Pulse Radar shows the way down to the boss door. It's in FE-05."),
        up("spacejump", FERENIA, "Space Jump", listOf("Storm Missile"),
            "Behind the Storm Missile crate above the Network Station in the big central room, past the Twin Robot Chozo Soldiers. It's in FE-01."),
        up("wavebeam", FERENIA, "Wave Beam", listOf(GRAVITY, SCREW),
            "Destroy the purple E.M.M.I.: its Central Unit is up the E.M.M.I. Zone in the upper right (blast the red pustules with the Diffusion Beam). It's in FE-03."),

        t(FERENIA, MISSILE, 1, listOf(GRAVITY, "Pulse Radar", SPEED, SHINE),
            "The freezing rooms under the Hanubia elevator: Pulse Radar shows Speed Booster blocks in the lower-left of the middle room. Slide in while boosting.",
            "Below, Shinespark as a Morph Ball through the next block. The tank is in FE-06."),
        t(FERENIA, MISSILE, 2, listOf(PB, SPACE),
            "The upper-left Network Station: in the tall passage through its left door, Power Bomb the blocks in the upper-left corner and Space Jump up. The tank is in FE-01."),
        t(FERENIA, MISSILE, 3, listOf(WAVE, "Storm Missile"),
            "By the Cyan Teleportal from Burenia (down and left from Burenia's Network Station, past a Storm Missile crate). The tank is in FE-01.",
            confirm = true),
        t(FERENIA, MISSILE, 4, listOf(PB, WAVE, SPACE),
            "A hidden room under the Ghavoran tram: at the water line, Power Bomb the Bomb block, Wave Beam the red pustule and Space Jump up. The tank is in FE-01."),
        t(FERENIA, MISSILE, 5, listOf(PB, SPACE),
            "From Missile Tank 4, a Beam block path in the lower right leads down to a hidden room of Pitfall and Bomb blocks. The tank is in FE-01."),
        t(FERENIA, MISSILE, 6, emptyList(),
            "Right of Energy Part 3, a door to a spire with a view: break the blocks above you, then the one in the top-right corner of the hidden room. The tank is in FE-04."),
        t(FERENIA, MISSILE_PLUS, 1, listOf(CROSS, SPEED, SHINE),
            "The room by the Ghavoran tram: a narrow Morph Ball hallway of Beam, Pitfall, Bomb and Speed Booster blocks.",
            "Store a Shinespark from the room to the right, shoot the Beam blocks, Cross Bomb over the pitfalls, lay a bomb and launch before it goes off. The tank is in FE-01."),
        t(FERENIA, MISSILE_PLUS, 2, listOf(SPEED, SHINE),
            "A small room outside the left Dairon elevator, hidden by Beam and Pitfall blocks: run through the elevator room for a Shinespark, jump, shoot the blocks, drop and spark in mid-fall (D-Pad neutral, A). The tank is in FE-01."),
        t(FERENIA, PART, 1, listOf(SCREW, CROSS, GRAPPLE),
            "Next to the Energy Recharge Station in the middle of the map, behind Screw Attack blocks: a Bomb and Grapple block puzzle. A Cross Bomb laid while falling clears four blocks; stand on the Bomb block platform and grapple. It's in FE-08."),
        t(FERENIA, PART, 2, listOf(WAVE, GRAVITY, SPEED, SHINE, SPACE),
            "The freezing room above the left Dairon elevator: top-left Wave Beam door, then down and shoot the red pustule.",
            "Speed Boost in the elevator room, slide and run up the ramp, store a Shinespark at the top and Space Jump to the blocks. It's in FE-01."),
        t(FERENIA, PART, 3, listOf(BOMB),
            "The room to the lower right of the far-right Save Station: bomb a Bomb block. It's in FE-04."),
        t(FERENIA, PART, 4, listOf(SPACE, BOMB),
            "Above the right-hand Dairon elevator: Space Jump up and bomb through the Morph Ball passage. It's in FE-09."),
        t(FERENIA, POWER_BOMB, 1, listOf(PB),
            "The very large room right of the upper-left Network Station (spire stairs in the background): Power Bomb at the top. The tank is in FE-07."),
        t(FERENIA, POWER_BOMB, 2, listOf(PB),
            "The lower E.M.M.I. Zone, through the door from the Dairon elevator: Power Bomb in the large chamber. It's at the very top. The tank is in FE-01."),

        // ---------------------------------------------------------------- Ghavoran

        up("supermissile", GHAVORAN, "Super Missile", listOf(BOMB),
            "Past the whale room, left of the elevator: bomb a hidden block, drop to the lower level and through the Missile door. It's in GH-02."),
        up("spinboost", GHAVORAN, "Spin Boost", listOf("Plasma Beam"),
            "Through the Plasma door in the E.M.M.I. Zone and past the giant creature you slide under (shoot its back). It's in GH-05."),
        up("icemissile", GHAVORAN, "Ice Missile", listOf("Spin Boost"),
            "Destroy the blue E.M.M.I.: its Central Unit is up the tall room at the far left of the E.M.M.I. Zone. It's in GH-06."),
        up("pulseradar", GHAVORAN, "Pulse Radar", listOf("Ice Missile"),
            "Ice Missile the Enki at the top right of the tall room by the E.M.M.I. Zone's right exit; in the next room, roll into the bottom-left corner. It's in GH-08."),
        up("crossbomb", GHAVORAN, "Cross Bomb", listOf(SCREW),
            "Golzuna's reward (see Bosses), up the long Morph Ball passage by the Green Teleportal. It's in GH-10."),

        t(GHAVORAN, MISSILE, 1, emptyList(),
            "Up and right of the Pulse Radar room, in the twisting Morph Ball vents: break the hidden block at the top. The tank is in GH-08."),
        t(GHAVORAN, MISSILE, 2, listOf("Pulse Radar"),
            "The room right of Golzuna: Pulse Radar shows hidden blocks in the bottom-right corner. The tank is in GH-11."),
        t(GHAVORAN, MISSILE, 3, listOf(SCREW, MORPH),
            "Left of the easternmost Save Station, a tall shaft only the Screw Attack opens. A Morph Ball chute on its left wall slides down to it. The tank is in GH-08."),
        t(GHAVORAN, MISSILE, 4, listOf(CROSS),
            "Right above the Green Teleportal, past Pitfall blocks: Cross Bomb across them. The tank is in GH-08."),
        t(GHAVORAN, MISSILE, 5, listOf("Storm Missile"),
            "Just below the Green Teleportal: from the room right of the Power Bomb block, Storm Missile the crate beside the portal. The tank is in GH-08."),
        t(GHAVORAN, MISSILE, 6, listOf(CROSS),
            "Below the Map Station: take its left door down to the bottom and bomb the small alcove into a hidden room. Cross Bomb past the pitfalls. The tank is in GH-03."),
        t(GHAVORAN, MISSILE, 7, listOf(GRAPPLE, SPEED, SPACE),
            "The whale room left of the Elun tram, high on its upper-left wall behind Speed Booster blocks. Charge from as far as your grapple reaches in the Elun tram room, then Speed Boost and Space Jump into them. The tank is in GH-04.",
            confirm = true),
        t(GHAVORAN, MISSILE, 8, listOf("Plasma Beam", GRAPPLE),
            "The room left of the Burenia elevator, through the Plasma door: grapple to it, or Space Jump later. The tank is in GH-13."),
        t(GHAVORAN, MISSILE, 9, listOf(GRAPPLE),
            "The big whale room southeast of the Network Station, at the bottom: grapple to it. The tank is in GH-15.",
            confirm = true),
        t(GHAVORAN, MISSILE, 10, listOf(SPACE),
            "The room right of the Burenia elevator: in the water in the left-wall corner. Missile the block. The tank is in GH-02."),
        t(GHAVORAN, MISSILE_PLUS, 1, emptyList(),
            "In the vents under the Network Station: go back to where you lowered the elevator-shaped room and climb up. Easy from below with Space Jump. The tank is in GH-12."),
        t(GHAVORAN, ENERGY, 1, listOf("Ice Missile"),
            "Left of the upper-right E.M.M.I. Zone exit: Ice Missile the Enki. The tank is in GH-07."),
        t(GHAVORAN, PART, 1, listOf(SPEED, SHINE),
            "The tall room near the right-most Save Station: Shinespark up into the middle of the ceiling. It's in GH-09."),
        t(GHAVORAN, PART, 2, listOf(SPACE, GRAPPLE),
            "The tall central chamber left of the center Save Station: from the platform above, drop and grab the Grapple block as you fall, then Missile the block in the alcove from the crumbling mushroom. It's in GH-04."),
        t(GHAVORAN, POWER_BOMB, 1, listOf(SPACE),
            "Top of the huge central shaft by the center Save Station: Space Jump up past the diagonal walls. The tank is in GH-07."),

        // ---------------------------------------------------------------- Elun

        up("plasmabeam", ELUN, "Plasma Beam", listOf(BOMB),
            "Bomb through the floor at the dead end past Elun's first Save Station and follow the path through the X. It's in EL-05."),

        t(ELUN, MISSILE, 1, listOf(PB, SPACE),
            "Top right, in the winding vents: Power Bomb the block in the far upper right and Space Jump to the top. The tank is in EL-04."),
        t(ELUN, MISSILE, 2, listOf(SPEED, SHINE),
            "The bottom-right chamber with two X-infected Chozo and a wind fan above: store a Shinespark running with the wind, slide down the narrow passages and spark through the blocks. The tank is in EL-05."),
        t(ELUN, ENERGY, 1, listOf(BOMB),
            "Left of the Chozo Soldier's room: shoot the hidden blocks beside it and bomb up to it. The tank is in EL-04."),
        t(ELUN, POWER_BOMB, 1, emptyList(),
            "In the vent maze near the exit with the Grapple point. The tank is in EL-04."),

        // ---------------------------------------------------------------- Hanubia

        up("powerbomb", HANUBIA, "Power Bomb", listOf(WAVE),
            "Through the Wave Beam door at the top of Hanubia: beat the Chozo Soldier in the next room. It's in HA-05."),

        t(HANUBIA, MISSILE, 1, listOf(MORPH),
            "Under the Total Recharge Station on the way to the upper-right exit: shoot a Missile down the Morph Ball path. The tank is in HA-06."),
        t(HANUBIA, MISSILE, 2, listOf(MORPH),
            "Under the Network Station with three doors, along a Morph Ball path. Hard to miss on your way through. The tank is in HA-03."),
        t(HANUBIA, POWER_BOMB, 1, listOf(SPEED, SHINE, BOMB),
            "Through the Network Station's upper-right door, the room to the right: from the ledge, Speed Boost, store a Shinespark and bomb through. As a Morph Ball, jump and spark down through the Speed Booster blocks. The tank is in HA-07."),
    )

    val byId: Map<String, Item> = all.associateBy { it.id }

    init {
        require(byId.size == all.size) { "Duplicate item id" }
        require(all.size == 146) { "Metroid Dread has 146 items here, not ${all.size}" }
    }
}
