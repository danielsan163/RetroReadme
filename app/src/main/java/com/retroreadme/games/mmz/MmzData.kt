package com.retroreadme.games.mmz

import com.retroreadme.games.mmz.Group.BEE
import com.retroreadme.games.mmz.Group.BIR
import com.retroreadme.games.mmz.Group.BOM
import com.retroreadme.games.mmz.Group.BUFFER
import com.retroreadme.games.mmz.Group.CLOC
import com.retroreadme.games.mmz.Group.EFF
import com.retroreadme.games.mmz.Group.GIBBER
import com.retroreadme.games.mmz.Group.HAFMAR
import com.retroreadme.games.mmz.Group.ICK
import com.retroreadme.games.mmz.Group.ITE
import com.retroreadme.games.mmz.Group.JACKSON
import com.retroreadme.games.mmz.Group.LAR
import com.retroreadme.games.mmz.Group.MOR
import com.retroreadme.games.mmz.Group.NITE
import com.retroreadme.games.mmz.Group.PIE
import com.retroreadme.games.mmz.Group.RIBBID
import com.retroreadme.games.mmz.Group.SHELTER
import com.retroreadme.games.mmz.Group.STICK
import com.retroreadme.games.mmz.Group.STOC
import com.retroreadme.games.mmz.Group.TAN
import com.retroreadme.games.mmz.Group.TOTTEN
import com.retroreadme.games.mmz.Group.TURBO
import com.retroreadme.games.mmz.Group.WINKIE
import com.retroreadme.games.mmz.Source.BASE
import com.retroreadme.games.mmz.Source.BOSS
import com.retroreadme.games.mmz.Source.BOX
import com.retroreadme.games.mmz.Source.ENEMIES
import com.retroreadme.games.mmz.Source.MISSION
import com.retroreadme.games.mmz.Source.SECRET

/**
 * The missions in a good order, and all 78 Cyber-elves, filed under the mission where you get
 * them. Checked against StrategyWiki's Mega Man Zero guide and the Mega Man Knowledge Base.
 */
object MmzData {

    val missions: List<Mission> = listOf(
        Mission(
            "m_ciel", "Rescue Ciel", "Underground Laboratory", "Golem", null,
            "The first mission. It can't be aborted.",
            listOf(
                "Ciel is with you through the stage; points come off if she's hurt, including by your own shots in the boss fight.",
                "The Golem can't really be hurt until the Z-Saber shows up mid-fight. Equip it and slash.",
            ),
        ),
        Mission(
            "m_disposal", "Rescue Reploids from Disposal", "Disposal Center", "Aztec Falcon", null,
            "Can't be aborted. Afterwards Ciel offers three missions: Destroy Train, Find Shuttle and Retrieve Data.",
            listOf(
                "You have a little over a minute in the boss room before the prisoners are crushed.",
                "The boss drops the Thunder Chip, and back at base Ciel gives you the Escape Unit for leaving missions early (which counts as failing them).",
            ),
        ),
        Mission(
            "m_base", "Resistance Base visit", "Resistance Base", "", null,
            "Not a mission: the base between missions. Four elves turn up here after the Disposal Center.",
            listOf("Talk to everyone after each mission; some elves and weapons are handed over in conversation."),
        ),
        Mission(
            "m_train", "Destroy Train", "Subway", "Pantheon Core", "Thunder",
            "Opens after the Disposal Center. Completing or failing it opens Occupy Factory and Rescue Colbor.",
            listOf("Once you're on the train there's a short time limit to reach and beat the boss."),
        ),
        Mission(
            "m_factory", "Occupy Factory", "Abandoned Factory", "Guard Orotic", null,
            "Opens after Destroy Train. Completing it opens Protect Factory.",
            listOf(
                "If a security sensor spots you, get under the shutter before it closes or it's game over.",
                "The boss drops the Flame Chip.",
            ),
        ),
        Mission(
            "m_shuttle", "Find Shuttle", "Desert", "Anubis Necromancess", "Flame",
            "Opens after the Disposal Center. Completing or failing it opens Find Hidden Base and Duel in Desert.",
            listOf("After the boss, walk the shuttle's survivor to safety; points come off if they're hurt."),
        ),
        Mission(
            "m_hidden", "Find Hidden Base", "Secret Base", "Blizzack Staggroff", "Flame",
            "Opens after Find Shuttle. Completing it opens Stop the Hacking.",
            listOf(
                "Free every captive Reploid before the boss door opens. Some cages hold enemies instead: leave those shut.",
                "Shoot the green guards before their search beams find you, or the base locks down for a while.",
                "The boss drops the Ice Chip.",
            ),
        ),
        Mission(
            "m_mech", "Giant Mechaniloid Invasion", "Disposal Center", "Hittite Hottide", null,
            "Starts on its own once four missions after the Disposal Center are done (completed or failed). Failing it brings on the base attack early.",
            listOf(
                "If it reaches the base gate you have about ten seconds left.",
                "For Hapitan, let it drill into the yellow building before you destroy it.",
            ),
        ),
        Mission(
            "m_colbor", "Rescue Colbor", "Subway", "Harpuia", "Ice",
            "Opens after Destroy Train.",
            listOf("Harpuia attacks mostly from above with wind and lightning."),
        ),
        Mission(
            "m_protect", "Protect Factory", "Abandoned Factory", "Phantom", null,
            "Opens after Occupy Factory.",
            listOf("After Phantom, find and disarm the bombs he planted around the factory before time runs out."),
        ),
        Mission(
            "m_duel", "Duel in Desert", "Desert", "Fefnir", "Thunder",
            "Opens after Find Shuttle. Don't fail it: failing starts the base attack early and closes every unfinished mission.",
            listOf("No hidden areas; just keep moving left and pick off the new enemies for their elves."),
        ),
        Mission(
            "m_hacking", "Stop the Hacking", "Secret Base", "Leviathan", "Flame",
            "Opens after Find Hidden Base.",
            listOf(
                "Destroy the hacked computers in the base. Two of them hold elves, and four more are in the prison cells.",
                "Leviathan is fought underwater.",
            ),
        ),
        Mission(
            "m_data", "Retrieve Data", "Underground Laboratory", "Maha Ganeshariff", "Thunder",
            "Opens after the Disposal Center, but leave it till last: it closes off most of the upper lab, including two elves, and the spider nests that make easy E-Crystals.",
            listOf(
                "Afterwards, Cerveau gives you the Triple Rod; the Shield Boomerang follows one mission later.",
                "After the boss, run back to the starting shaft as the ceiling falls, breaking the emergency doors on the way.",
            ),
        ),
        Mission(
            "m_hanu", "Resistance Base Perseverance", "Resistance Base", "Hanumachine", "Thunder",
            "Starts once every mission above is done, or early if you fail Duel in Desert, the Giant Mechaniloid, or four missions in a row. Any mission still open is gone after it. Can't be aborted.",
            listOf("Sticken only comes from the little Hanumachines he throws during this fight."),
        ),
        Mission(
            "m_shrine", "Neo Arcadia Shrine", "Neo Arcadia Shrine", "Herculious Anchortus", "Ice",
            "After the base attack.",
            listOf(
                "Two mid-bosses on the way (the Pantheon Aces, then Asura Basura), each with an elf.",
                "Six boxes hide on the shrine's roofs; several need tricky jumps or the Triple Rod.",
            ),
        ),
        Mission(
            "m_tower", "Neo Arcadia Tower", "Neo Arcadia Tower", "Rainbow Devil", null,
            "After the Shrine. Can't be aborted.",
            listOf("Shoot the Gyro Cannons on the way up for Birrair."),
        ),
        Mission(
            "m_core", "Neo Arcadia Core", "Neo Arcadia Core", "Copy-X", "Ice (second form)",
            "The last mission.",
            listOf("A boss rematch first, then Copy-X. No enemy here drops an elf."),
        ),
    )

    private fun elf(name: String, group: Group, mission: String, source: Source, how: String, missable: String? = null) =
        Elf("e_" + name.lowercase().replace('-', '_'), name, group, mission, source, how, missable)

    private const val ONLY = "Only during the mission: if you miss it, it's gone until a New Game+."

    val elves: List<Elf> = listOf(
        // Rescue Ciel
        elf("Stoccue", STOC, "m_ciel", ENEMIES, "Destroy 5 Spider Nests. There are only four, so back off after the first one to make it come back.", ONLY),
        elf("Stocchu", STOC, "m_ciel", ENEMIES, "Destroy 5 of the blue Pantheons near the end of the stage.", ONLY),
        elf("Nuppie", PIE, "m_ciel", BOX, "Go back to the lab: a box in a small room up in the ceiling.", "Get it before you finish Retrieve Data."),
        elf("Clocpah", CLOC, "m_ciel", BOX, "Go back to the lab: a box at the far left of the room where Zero slept.", "Get it before you start Retrieve Data."),

        // Disposal Center
        elf("Birsky", BIR, "m_disposal", ENEMIES, "Destroy 5 Spikings (the spiked balls the Carryarms drop).", ONLY),
        elf("Gireff", EFF, "m_disposal", ENEMIES, "Destroy the Pantheon Hunters.", ONLY),
        elf("Gibber", GIBBER, "m_disposal", ENEMIES, "Destroy the Totem Cannons.", ONLY),
        elf("Mippie", PIE, "m_disposal", BOX, "A box hanging from a crane hook. Jump off the tallest building and slash it."),
        elf("Clocpooh", CLOC, "m_disposal", BOX, "A box on a wall. Stand on the Totem Cannon below and slash it from underneath."),

        // Resistance Base
        elf("Beevoize", BEE, "m_base", BASE, "The door behind Ciel opens once you have an A or S rank."),
        elf("Fureff", EFF, "m_base", BASE, "Give 250 E-Crystals to the hungry Reploid (Hibou) near the engine room, then go into the room past him."),
        elf("Clocka", CLOC, "m_base", BASE, "After the Disposal Center, listen to old Andrew's story."),
        elf("Lippie", PIE, "m_base", BOX, "After the Disposal Center, the desert gate's guards leave: climb onto the gate west of the base for a hidden box."),

        // Destroy Train
        elf("Itemon", ITE, "m_train", ENEMIES, "Destroy 5 Kerberos (the orange bikes).", ONLY),
        elf("Buffer", BUFFER, "m_train", ENEMIES, "Destroy 5 Pantheon Warriors.", ONLY),
        elf("Hafmargo", HAFMAR, "m_train", ENEMIES, "Destroy 3 Crush Rollers (the spiked rollers) on the train.", ONLY),
        elf("Birdian", BIR, "m_train", ENEMIES, "Destroy 5 Ravens on the train.", ONLY),
        elf("Beesus", BEE, "m_train", BOSS, "Beat the mid-boss (Metarook)."),
        elf("Totten", TOTTEN, "m_train", BOSS, "Clear the mission."),
        elf("Bireff", EFF, "m_train", BOX, "Up a shaft in the ceiling before the mid-boss's door."),

        // Occupy Factory
        elf("Beenet", BEE, "m_factory", ENEMIES, "Destroy 5 Shield Attackers.", ONLY),
        elf("Ribbid", RIBBID, "m_factory", ENEMIES, "Destroy 5 Cannon Hoppers.", ONLY),
        elf("Stickle", STICK, "m_factory", ENEMIES, "Destroy 5 Securipiders.", ONLY),
        elf("Greff", EFF, "m_factory", BOX, "In an air vent."),
        elf("Winkie", WINKIE, "m_factory", BOX, "In the area with the big pit, above the Carryarms."),
        elf("Motolar", LAR, "m_factory", BOX, "Top right of the big pit area, above a ladder."),
        elf("Nutan", TAN, "m_factory", BOX, "Behind a false wall left of the ladder up to the boss room."),

        // Find Shuttle
        elf("Itettle", ITE, "m_shuttle", ENEMIES, "Destroy the Sand Snakes.", ONLY),
        elf("Birfly", BIR, "m_shuttle", ENEMIES, "Destroy 5 Condoroids.", ONLY),
        elf("Stocpie", STOC, "m_shuttle", ENEMIES, "Destroy 5 Sand Jaws (the traps in the sand).", ONLY),
        elf("M-oria", MOR, "m_shuttle", BOSS, "Beat Anubis Necromancess."),
        elf("Lubtan", TAN, "m_shuttle", BOX, "On top of the crumbling stone platforms. Also reachable in Duel in Desert."),

        // Find Hidden Base
        elf("Shelter", SHELTER, "m_hidden", ENEMIES, "Destroy 3 Battle Turtle brothers.", ONLY),
        elf("Nebitan", TAN, "m_hidden", BOX, "Hidden in the left wall near the bottom of the trench, by the base entrance."),
        elf("M-orell", MOR, "m_hidden", BOX, "A hidden passage in the base: jump above the third pipe before the computer room's door."),

        // Giant Mechaniloid Invasion
        elf("Birtack", BIR, "m_mech", ENEMIES, "Destroy the Gli-Eyes the mechaniloid sends out.", ONLY),
        elf("Stoctto", STOC, "m_mech", BOSS, "Destroy Hittite Hottide."),
        elf("Hapitan", TAN, "m_mech", BOX, "Let the mechaniloid drill into the yellow building before you finish it. Afterwards a pit leads down to a box in a nook in the wall.", "If it doesn't dig, you can still reach the nook later by jumping off a Gli-Eye with the Triple Rod."),

        // Rescue Colbor
        elf("Birtross", BIR, "m_colbor", ENEMIES, "Destroy 3 Pantheon Fliers.", ONLY),
        elf("Beedle", BEE, "m_colbor", BOSS, "Beat Harpuia."),
        elf("Dereff", EFF, "m_colbor", BOX, "Left of the starting point."),
        elf("Sireff", EFF, "m_colbor", BOX, "Under a platform near the start of the falling platforms."),
        elf("Morick", ICK, "m_colbor", BOX, "Under a platform a little past Sireff, below a ladder."),

        // Protect Factory
        elf("Stickon", STICK, "m_protect", ENEMIES, "Destroy 5 Garms (the dogs).", ONLY),
        elf("Hafmarda", HAFMAR, "m_protect", BOSS, "Beat Phantom."),

        // Duel in Desert
        elf("Kenite", NITE, "m_duel", ENEMIES, "Destroy the Cameloids.", ONLY),
        elf("M-orque", MOR, "m_duel", ENEMIES, "Destroy 5 Shellcrawlers (the small tanks).", ONLY),
        elf("Turbo", TURBO, "m_duel", BOSS, "Beat Fefnir."),

        // Stop the Hacking
        elf("Bompa", BOM, "m_hacking", ENEMIES, "Destroy 5 Screwdriggers.", ONLY),
        elf("Bompu", BOM, "m_hacking", ENEMIES, "Destroy the Sharkseals.", ONLY),
        elf("Bomgu", BOM, "m_hacking", BOSS, "Beat Leviathan."),
        elf("Reppie", PIE, "m_hacking", MISSION, "Inside one of the hacked computers you destroy.", "Get it while the mission is on."),
        elf("Keick", ICK, "m_hacking", MISSION, "Inside another of the hacked computers.", "Get it while the mission is on."),
        elf("Areff", EFF, "m_hacking", BOX, "Bottom left of the flooded area, by an underwater cliff."),
        elf("Rohealar", LAR, "m_hacking", BOX, "In the ceiling of the flooded area: jump from the left submarine."),
        elf("M-orekka", MOR, "m_hacking", BOX, "In prison cell 1-02."),
        elf("Tielar", LAR, "m_hacking", BOX, "In prison cell 2-01."),
        elf("Clocta", CLOC, "m_hacking", BOX, "In prison cell 3-02."),
        elf("Itepon", ITE, "m_hacking", BOX, "In prison cell 3-05."),

        // Retrieve Data
        elf("Bomga", BOM, "m_data", ENEMIES, "Destroy 5 Floppers (the floating bombs).", ONLY),
        elf("Sticker", STICK, "m_data", ENEMIES, "Destroy 5 Seal Cannons (the green tanks).", ONLY),
        elf("Itecle", ITE, "m_data", BOSS, "Beat Maha Ganeshariff."),
        elf("Somack", ICK, "m_data", BOX, "After the mission: a new area at the top of the lab's shaft."),

        // Resistance Base Perseverance
        elf("Sticken", STICK, "m_hanu", ENEMIES, "Destroy 5 of the little Hanumachines he sends out during the fight.", ONLY),
        elf("Eenite", NITE, "m_hanu", BOSS, "Beat Hanumachine."),

        // Neo Arcadia Shrine
        elf("Beefive", BEE, "m_shrine", BOSS, "Beat the first mid-boss, the Pantheon Aces."),
        elf("Cloctch", CLOC, "m_shrine", BOSS, "Beat the second mid-boss, Asura Basura."),
        elf("Beehoney", BEE, "m_shrine", BOSS, "Beat Herculious Anchortus."),
        elf("Iteron", ITE, "m_shrine", BOX, "Ride the first lifts up above the room of Ray Blades: two boxes on the roof."),
        elf("Ireff", EFF, "m_shrine", BOX, "The other box on that first roof."),
        elf("Stocpoh", STOC, "m_shrine", BOX, "Above the Pantheon Aces' room: get up there with the Triple Rod from above the Gyro Cannons. Two boxes."),
        elf("Ereff", EFF, "m_shrine", BOX, "The other box above the Pantheon Aces' room."),
        elf("Coswick", ICK, "m_shrine", BOX, "After the Pantheon Aces' room, drop into the left pit; the box is on a platform to the right."),
        elf("Muelar", LAR, "m_shrine", BOX, "Before the boss door, jump left from the highest vanishing block to a lift; ride it and dash-jump left above Asura Basura's room."),

        // Neo Arcadia Tower
        elf("Birrair", BIR, "m_tower", ENEMIES, "Destroy the Gyro Cannons.", ONLY),
        elf("M-orolli", MOR, "m_tower", BOSS, "Beat the Rainbow Devil."),
        elf("Hareff", EFF, "m_tower", BOX, "In plain view on the way up."),

        // Neo Arcadia Core
        elf("Jackson", JACKSON, "m_core", SECRET, "Collect and fully raise every other elf without using any, then beat the game and load that clear save. See Hints."),
    )

    val missionById: Map<String, Mission> = missions.associateBy { it.id }
    val elfById: Map<String, Elf> = elves.associateBy { it.id }
    fun elvesIn(mission: Mission): List<Elf> = elves.filter { it.missionId == mission.id }

    init {
        require(elves.size == 78) { "Mega Man Zero has 78 Cyber-elves, not ${elves.size}" }
        require(elfById.size == elves.size) { "Duplicate elf id" }
        require(elves.all { it.missionId in missionById }) { "Elf filed under an unknown mission" }
    }
}
