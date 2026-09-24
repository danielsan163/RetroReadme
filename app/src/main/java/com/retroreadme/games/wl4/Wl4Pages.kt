package com.retroreadme.games.wl4

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object Wl4Pages {

    // ---------------------------------------------------------------- Bosses

    val bosses: List<Boss> = listOf(
        Boss(
            "b_spoiled", "Spoiled Rotten", Passage.ENTRY, weakTo = null, treasure = null,
            timeNormal = "1:00", timeHard = "1:00",
            strategy = listOf(
                "Keep hitting it with regular attacks.",
                "When it opens its mouth, jump-attack over it and hit it from behind. Watch out for the Spikeheads.",
            ),
        ),
        Boss(
            "b_cractus", "Cractus", Passage.EMERALD, weakTo = "Black Dragon", treasure = "Crown",
            timeNormal = "4:00", timeHard = "3:00",
            strategy = listOf(
                "Break its vase to wake it up, then wait on the ladders.",
                "When it swipes with its leaves, slam it on the head. Stay behind it after a hit so it backs off.",
                "Its drool turns you into Zombie Wario; touch the firefly that appears to change back. Standing under or on the left ladder avoids the drool attack.",
            ),
        ),
        Boss(
            "b_condor", "Cuckoo Condor", Passage.RUBY, weakTo = "Big Fist", treasure = "Earrings",
            timeNormal = "4:00", timeHard = "3:00",
            strategy = listOf(
                "Walk under the clock so its crane reaches for you. Dodge it, then hit the crane while it flashes to spin it into the boss.",
                "Later it rolls cogs along the floor; jump them (the left one is faster).",
                "Once the condor appears, catch its eggs before they land and throw them at the chick on its head.",
            ),
        ),
        Boss(
            "b_aerodent", "Aerodent", Passage.TOPAZ, weakTo = "Large Lips", treasure = "Necklace",
            timeNormal = "4:00", timeHard = "3:00",
            strategy = listOf(
                "Stun the parachuting spiky enemies, pick them up and power-throw them at the boss.",
                "When it comes down, hit its glowing feet to flip it, then hit the mouse on its head as many times as you can.",
                "Fireballs start falling once it's weak. On Hard, an item from the shop helps beat the clock.",
            ),
        ),
        Boss(
            "b_catbat", "Catbat", Passage.SAPPHIRE, weakTo = "Black Dog", treasure = "Bracelets",
            timeNormal = "4:00", timeHard = "3:00",
            strategy = listOf(
                "Ride the waves it sends and use them to reach the cat on its head. Mind the drill in the water.",
                "After a hit it releases spiky eyes that make you Puffy Wario.",
                "Once the cat is gone, the red spiky eyes hurt and all waves bob up and down; slam its head to finish it.",
            ),
        ),
        Boss(
            "b_diva", "Golden Diva", Passage.GOLDEN, weakTo = null, treasure = null,
            timeNormal = "6:00", timeHard = "5:00",
            strategy = listOf(
                "Stun each of the four masks and throw it at her fan.",
                "Then: throw the green bug at her face, charge the blue ball back at her before it explodes, catch a black egg and throw it, and let a thrown hammer land on your own head to become Bouncy Wario and spring into her face.",
                "She repeats these faster. With four hits left she pounds the floor: jump as she lands, hit her face as she comes down. Finish by hitting her lips.",
            ),
        ),
    )

    // ---------------------------------------------------------------- Shop & Items

    val shop: List<Wl4Page> = listOf(
        Wl4Page(
            "minigames", "Mini-Game Shop", "Win Medals before each boss",
            listOf(
                Section(null, listOf(
                    "Each passage ends with a Mini-Game Shop before the boss room. Games cost coins: 2,000 the first time, 5,000 after that.",
                    "Medals from the games buy one item from the Item Shop before each boss.",
                )),
                Section("The three games", listOf(
                    "Wario's Homerun Derby: one Medal per 3 home runs. The pitcher's head moves tell you the pitch.",
                    "The Wario Hop: one Medal per 15 jumps. Jump in time with the music; it speeds up.",
                    "Wario Roulette: match Wario's face; one Medal per 5 correct.",
                )),
            ),
        ),
        Wl4Page(
            "items", "Item Shop", "One item per boss fight",
            listOf(
                Section("Best item for each boss", listOf(
                    "Cractus: Black Dragon. Cuckoo Condor: Big Fist. Aerodent: Large Lips. Catbat: Black Dog.",
                )),
                Section("Prices", listOf(
                    "Before Spoiled Rotten only the cheap items are on sale: Apple Bomb, Blast Cannon, Vizorman, Bugle (2, 2, 4 and 6 Medals on Normal; 3, 3, 6 and 9 on Hard).",
                    "After that the big four appear: Black Dog, Large Lips, Big Fist, Black Dragon (10 Medals each on Normal, 12 on Hard).",
                )),
            ),
        ),
    )

    // ---------------------------------------------------------------- Hints

    val hints: List<Wl4Page> = listOf(
        Wl4Page(
            "switch", "Frog switch and escape", "Get everything before you press it",
            listOf(
                Section(null, listOf(
                    "The vortex closes behind you. Hitting the frog switch reopens it and starts a countdown; each level page shows the time for this difficulty.",
                    "When the countdown runs out, your coins drain 10 at a time, and running out of coins loses the level.",
                    "The switch also swaps the frog blocks: solid ones vanish and see-through ones turn solid. Some items can only be reached after the switch.",
                    "A Keyzer only counts if you leave through the vortex holding it. Without it the next level stays locked.",
                )),
            ),
        ),
        Wl4Page(
            "transform", "Wario's transformations", "What each one is for",
            listOf(
                Section(null, listOf(
                    "Fat (monkey's apple): breaks blue blocks and hard floors by landing on them.",
                    "Puffy (Beezley sting or arrow): floats straight up to high places.",
                    "Bat (bat bite): flap with A for precise flying; light turns you back.",
                    "Flat (crushed): floats like paper and slips through narrow gaps.",
                    "Bouncy (Hammer guy): huge springs that break columns of blocks overhead.",
                    "Zombie (ghost goo, drool): drops through thin platforms; light or water turns you back.",
                    "Snowman (snowfall or snowballs): rolls down slopes and breaks snowball blocks.",
                    "Flaming (torches): once fully ablaze, burns through flame blocks. Water puts it out.",
                    "Frozen (Yeti breath): slides until you hit a wall, safely past spikes.",
                    "Bubble (underwater): carries you up through currents you can't swim against.",
                )),
            ),
        ),
        Wl4Page(
            "chests", "Treasure chests and the ending", "Beat bosses fast",
            listOf(
                Section(null, listOf(
                    "Each of the four main bosses guards three treasure chests plus a treasure: 12 chests in all.",
                    "Chests start vanishing when the boss timer reaches 1:00. Every chest you keep adds to the ending.",
                    "Hard mode gives you a minute less for each of those bosses.",
                ), tone = Tone.SECRET),
            ),
        ),
        Wl4Page(
            "modes", "Difficulty modes", "Chosen when you start a save file",
            listOf(
                Section(null, listOf(
                    "Normal and Hard use the same level layouts, but Hard moves almost every jewel piece, often to where diamonds sat on Normal, and sometimes the CD.",
                    "Hard also has more enemies, shorter escape timers, less boss time, and pricier items.",
                    "S-Hard unlocks after you clear Hard. Some players report you also need all 12 chests.",
                )),
                Section("About this guide's Hard pages", listOf(
                    "The Hard locations here come from a single detailed walkthrough. Tell me anything that doesn't match and I'll fix it.",
                ), tone = Tone.WARNING),
            ),
        ),
        Wl4Page(
            "crowns", "Crowns and Karaoke", "Score in each level",
            listOf(
                Section(null, listOf(
                    "Finish a level with 10,000+ coins for a Gold Crown, 8,000+ for Silver, 6,000+ for Bronze. Each heart left adds 100.",
                    "Enemies drop more coins when your heart meter is full.",
                    "A Gold Crown in every level unlocks Karaoke in the Sound Room.",
                )),
            ),
        ),
        Wl4Page(
            "sound", "Sound Room", "Where the CDs play",
            listOf(
                Section("Emerald", listOf("About That Shepherd, Things That Never Change, Tomorrow's Blood Pressure, Beyond the Headrush.")),
                Section("Ruby", listOf("Driftwood & The Island Dog, The Judge's Feet, The Moon's Lamppost, Soft Shell.")),
                Section("Topaz", listOf("So Sleepy, The Short Futon, Avocado Song, Mr. Fly.")),
                Section("Sapphire", listOf("Yesterday's Words, The Errand, You and Your Shoes, Mr. Ether & Planaria.")),
            ),
        ),
        Wl4Page(
            "bonus", "Bonus rooms", "Purple pipes and diamonds",
            listOf(
                Section(null, listOf(
                    "Every level except the Entry Passage and the Golden Passage has two bonus rooms behind purple pipes, each with a diamond (1,000 coins).",
                    "They're optional and hold no jewel pieces, CDs or Keyzers, but they're the easiest route to Gold Crowns.",
                )),
            ),
        ),
    )
}
