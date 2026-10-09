package com.retroreadme.games.mmz

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object MmzPages {

    val bosses: List<Boss> = listOf(
        Boss("b_golem", "Golem", "m_ciel", null, "Z-Saber", listOf(
            "Shoot its head, and keep your shots off Ciel.",
            "After a few hits a mysterious figure hands you the Z-Saber. Equip it from the menu: one slash finishes the Golem.",
        )),
        Boss("b_falcon", "Aztec Falcon", "m_disposal", null, "Thunder Chip", listOf(
            "You have a little over a minute before the prisoners are crushed.",
            "His arms block Buster shots, so use the Z-Saber.",
            "Cling to the wall opposite him while he fires arrows, then slash him when he dashes past.",
            "When he opens an arm and starts pulling you in, dash away from it.",
        )),
        Boss("b_core", "Pantheon Core", "m_train", "Thunder", "Totten", listOf(
            "Keep your distance and fire charged Buster shots (with the Thunder Chip).",
            "Its floor pistons rise one by one to push you into the ceiling spikes. Step onto the next piston before yours lifts.",
            "Jump its flames, and climb the wall if it gets close.",
        )),
        Boss("b_orotic", "Guard Orotic", "m_factory", null, "Flame Chip", listOf(
            "Hit the blue core while its casing is open; a charged saber slash does the most damage.",
            "The heads take turns: punches (jump them), flames and homing lightning (hit the core, then dash away), and ice that freezes you (mash every button to break out).",
            "A destroyed head grows back once its slot reaches the top. If you break heads, break the ones on the right: they take longest to come back round.",
        )),
        Boss("b_anubis", "Anubis Necromancess", "m_shuttle", "Flame", "M-oria", listOf(
            "His cane blocks Buster shots. Dodge it when he throws it, then hit him with a charged Flame slash while it's away: that's when he's open.",
            "Cut down the zombies he raises with saber combos, watching for the returning cane.",
            "When he sinks into the sand, sand walls close in: jump clear of them and wall-jump out of the last one.",
        )),
        Boss("b_blizzack", "Blizzack Staggroff", "m_hidden", "Flame", "Ice Chip", listOf(
            "Equip the Flame Chip: charged Buster shots while he's on the ground, charged slashes when he's close.",
            "His blizzard can't be dodged on the ground. Wall-jump up to the ceiling.",
            "Watch his stomps, his snowballs (they turn into spikes on the floor) and his horn missiles.",
        )),
        Boss("b_hittite", "Hittite Hottide", "m_mech", null, "Stoctto", listOf(
            "A giant drill machine on its way to the base. Break its parts one at a time.",
            "Shoot its mines and floating bombs before they land, and keep the Gli-Eyes it launches down.",
            "With its parts gone, Pantheons come out of the hatch: finish them.",
            "Want Hapitan? Let it drill into the yellow building first.",
        )),
        Boss("b_harpuia", "Harpuia", "m_colbor", "Ice", "Beedle", listOf(
            "Equip the Ice Chip.",
            "He fights mostly from above, firing energy waves from his blades and calling wind and lightning.",
            "Stay moving, and hit him with charged Ice shots or slashes whenever he comes within reach.",
        )),
        Boss("b_phantom", "Phantom", "m_protect", null, "Hafmarda", listOf(
            "He dashes in to slash, then splits into copies. The real one flickers differently: hit that one. Hit a fake and he strikes back.",
            "His big shuriken breaks into four pieces when it lands.",
            "Sometimes he rides the shuriken and throws daggers down; keep dodging until he drops.",
        )),
        Boss("b_fefnir", "Fefnir", "m_duel", "Thunder", "Turbo", listOf(
            "Equip the Thunder Chip.",
            "Dash under his fireballs. When he jumps and punches the ground, the eruption spreads: be under him or away from it.",
            "Charged, his attacks get bigger. In his last phase fireballs rain down: keep dashing.",
        )),
        Boss("b_leviathan", "Leviathan", "m_hacking", "Flame", "Bomgu", listOf(
            "Underwater, so you jump higher. Equip the Flame Chip.",
            "She swims along the top dropping ice that sinks onto you, fires rings of ice, and throws homing spearheads.",
            "Hit her with charged Flame attacks as she passes, and shoot the ice down before it lands.",
        )),
        Boss("b_maha", "Maha Ganeshariff", "m_data", "Thunder", "Itecle", listOf(
            "Equip the Thunder Chip.",
            "Up close his hand slaps block most attacks; a well-timed Buster shot can go over the hand to his head.",
            "When he curls up and rolls at you dropping bombs, jump over him.",
            "When he swings from the ceiling by his trunk, slash the trunk to bring him down.",
        )),
        Boss("b_hanu", "Hanumachine", "m_hanu", "Thunder", "Eenite", listOf(
            "Equip the Thunder Chip.",
            "Destroy the little Hanumachines before they cling to you and explode. Sticken comes from them: you only get it in this fight.",
            "When he turns into a fireball and bounces around the room, stay clear and wait for him to land.",
        )),
        Boss("b_herc", "Herculious Anchortus", "m_shrine", "Ice", "Beehoney", listOf(
            "Equip the Ice Chip: charged Ice attacks knock him out of most of his moves.",
            "He charges at you, and fires orbs from his horn.",
            "When he throws out his arms to fence you in with electricity, get out before he charges.",
        )),
        Boss("b_devil", "Rainbow Devil", "m_tower", null, "M-orolli", listOf(
            "A slime that creeps toward you. If it grabs you, mash the buttons to break free.",
            "It throws droplets that fly back to it, and splits into pieces that bounce around the room.",
            "Hit the core between attacks.",
        )),
        Boss("b_copyx", "Copy-X", "m_core", "Ice (second form)", null, listOf(
            "After a rematch with earlier bosses.",
            "First form has no weakness. He switches between plain, ice, lightning and fire shots, slides, and dashes at you wrapped in golden light. Stay airborne and slash between attacks.",
            "His second form is weak to Ice. Watch for the rings that trap you, the beam that sweeps the floor, and the spiked platforms that slam down.",
        )),
    )

    val weapons: List<MmzPage> = listOf(
        MmzPage("w_buster", "Buster Shot", "From Milan, at the very start", listOf(
            Section(null, listOf(
                "A handheld gun. Weapons level up by hitting enemies with them.",
                "Higher levels add more shots on screen, a second charge level (the one that carries a chip's element) and a faster charge.",
            )),
        )),
        MmzPage("w_saber", "Z-Saber", "During the Golem fight", listOf(
            Section(null, listOf(
                "Zero's sword, and the strongest weapon for most of the game.",
                "Levels add a double then triple slash combo, a charged slash (which carries a chip's element) and a faster charge.",
                "Two more levels come from jump attacks (a spinning slash in the air) and dash attacks (a spinning slash while dashing).",
            )),
        )),
        MmzPage("w_rod", "Triple Rod", "From Cerveau, after Retrieve Data", listOf(
            Section(null, listOf(
                "A spear that stabs in eight directions. Levels extend it to a double and triple stab, then add a charged spin.",
                "Stabbing down onto an enemy bounces you up, which reaches a few hidden boxes.",
            )),
        )),
        MmzPage("w_shield", "Shield Boomerang", "From Cerveau, one mission after the Triple Rod", listOf(
            Section(null, listOf(
                "Hold it up to reflect shots back. Charge it to throw it as a boomerang; levels make it fly further.",
                "It's the only weapon that still charges in Hard mode.",
            )),
        )),
        MmzPage("w_chips", "Element Chips", "Thunder, Flame and Ice", listOf(
            Section(null, listOf(
                "Thunder Chip from Aztec Falcon, Flame Chip from Guard Orotic, Ice Chip from Blizzack Staggroff.",
                "Equip one in the menu and your fully charged attacks take its element: Thunder stuns, Fire burns, Ice freezes.",
                "Fire beats Ice, Ice beats Thunder, Thunder beats Fire. Each boss page lists its weakness.",
            )),
        )),
    )

    val hints: List<MmzPage> = listOf(
        MmzPage("h_missions", "Missions close for good", "Read this first", listOf(
            Section(null, listOf(
                "Missions can't be replayed. Clearing or failing one (including leaving with the Escape Unit) both close it, and elves dropped by enemies there are lost.",
                "The base attack (Hanumachine) starts once every mission is done, or early if you fail Duel in Desert, the Giant Mechaniloid, or four missions in a row. Whatever's still open then is gone.",
                "Boxes stay put, though: you can go back to an area later for any box you skipped.",
            ), tone = Tone.WARNING),
        )),
        MmzPage("h_order", "Mission order", "What the Missions tab follows", listOf(
            Section(null, listOf(
                "Each mission is listed with the chip its boss is weak to, so the order follows the chips: Thunder first, then Flame, then Ice.",
                "Retrieve Data comes last of the ten: it shuts off the upper lab (two elves) and the spider nests that make easy E-Crystals.",
                "Missions in the same area share a map but the enemies change; the second visit sometimes runs backwards.",
            )),
        )),
        MmzPage("h_elves", "Using elves", "And what it costs", listOf(
            Section(null, listOf(
                "Elves with an E-Crystal cost must be fed (raised) before you can use them; the rest work as soon as you find them.",
                "Every elf is used up when you use it, and each one used lowers your mission score for the rest of the game. Lasting ones (life, Sub Tanks, Turbo and the like) keep working, and keep costing.",
                "So for good ranks, use few or none. For Jackson, use none at all until he's unlocked.",
            )),
        )),
        MmzPage("h_rank", "Ranks and EX Skills", "Scores out of 100", listOf(
            Section(null, listOf(
                "Each mission is scored on clear time, enemies destroyed, damage taken, retries, and elves used, and ranked F up to S.",
                "Bosses fought while your rank is A or S use an extra attack (their EX Skill).",
                "The door behind Ciel at the base (Beevoize) only opens at A or S rank.",
            )),
        )),
        MmzPage("h_jackson", "Jackson, Hard and Ultimate", "After the credits", listOf(
            Section(null, listOf(
                "Beating the game unlocks Hard mode (hold L when starting a new game). Elves are off and weapons don't level up there.",
                "Jackson: collect every elf and raise every one fully (about 23,000 E-Crystals), use none, beat the game, then load that clear save.",
                "Ultimate mode: beat the game having used every elf, Jackson included. Hold R when starting a new game.",
                "Elves you've found carry over when you load a clear save (New Game+), so missed ones can be picked up on a second run.",
            ), tone = Tone.SECRET),
        )),
        MmzPage("h_crystals", "E-Crystals", "Raising elves takes a lot", listOf(
            Section(null, listOf(
                "Raising everything needs about 23,000 E-Crystals.",
                "The spider nests in the Underground Laboratory keep making enemies to farm, until Retrieve Data closes that part of the lab.",
            )),
        )),
    )
}
