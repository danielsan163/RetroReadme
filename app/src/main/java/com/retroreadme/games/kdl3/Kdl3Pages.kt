package com.retroreadme.games.kdl3

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object Kdl3Pages {

    val bosses: List<Boss> = listOf(
        Boss(
            "b_whispy", "Whispy Woods", "Grass Land",
            listOf(
                "Inhale the fruit it spits and fire it back (or jump over it if you have an ability). Its air puffs curve toward you, and don't touch its nose.",
                "At half health it gets angry and chases you, spitting rotten fruit that flies further. Keep spitting it back.",
            ),
        ),
        Boss(
            "b_acro", "Acro", "Ripple Field",
            listOf(
                "Jump over its tackles. When it hits the wall, rocks fall: inhale them and spit them back.",
                "Underwater, send the things it spits out back at it.",
            ),
        ),
        Boss(
            "b_poncon", "Pon & Con", "Sand Canyon",
            listOf(
                "Only hits on Pon or Con count; their minions just get in the way. Inhale a line of minions to shoot straight through them.",
                "Stay clear of the bombs that drop from above. Easy fight, but they have about double a normal boss's health.",
            ),
        ),
        Boss(
            "b_ado", "Ado", "Cloudy Park",
            listOf(
                "Ado paints bosses from Kirby's Dream Land 2 that come to life. Beat each in turn; Kracko is the last.",
                "After that, Ado runs at you herself.",
            ),
        ),
        Boss(
            "b_dedede", "King Dedede", "Iceberg",
            listOf(
                "The first phase is a familiar Dedede fight.",
                "Then Dark Matter shows itself and he floats around with a mouth in his belly. Its eye fires dark balls you can inhale and spit back at him.",
                "Have all 30 Heart Stars before this fight to reach the Hyper Zone afterwards.",
            ),
        ),
        Boss(
            "b_darkmatter", "Dark Matter", "Hyper Zone",
            listOf(
                "You fight with the Love-Love Stick, which fires Heart Stars.",
                "Its orange orbs home in on you, but the stick can destroy them. It also shoots lightning in four directions, one after another.",
            ),
        ),
        Boss(
            "b_zero", "Zero", "Hyper Zone",
            listOf(
                "Straight after Dark Matter. Aim your shots at its eye.",
                "Focus on dodging when it releases small Dark Matters and fires projectiles; they track you.",
            ),
        ),
    )

    val subGames: List<Kdl3Page> = listOf(
        Kdl3Page("sg_gordo", "Which Gordo was thrown?", "Grass Land 3 · Pitcherman", listOf(
            Section(null, listOf("A Gordo flies between two screens and shows a face for a split second. Pick the face you saw. Three rounds, each faster.")),
        )),
        Kdl3Page("sg_eel", "Where's the eel?", "Ripple Field 3 · Elieel", listOf(
            Section(null, listOf("Elieel and four Gordos pop in and out of five pots, faster each time. Pick the pot Elieel was last in. Three rounds.")),
        )),
        Kdl3Page("sg_face", "How many of the same face?", "Sand Canyon 3 · Caramelo", listOf(
            Section(null, listOf("A crowd of Gordos briefly show faces. Count the ones with the face it asks for. Three rounds; the last is very fast.")),
        )),
        Kdl3Page("sg_color", "How many of the same color?", "Cloudy Park 3 · Tama-san", listOf(
            Section(null, listOf("Gordos in four colors pop up from behind a screen. Count the color it asks for. Three rounds.")),
        )),
        Kdl3Page("sg_sound", "What was that sound?", "Iceberg 3 · Chef Kawasaki", listOf(
            Section(null, listOf("Five Gordos each make a sound, then a sixth is struck. Pick the Gordo with the same sound. Three rounds.")),
        )),
        Kdl3Page("sg_rules", "Retrying sub-games", "One mistake ends the attempt", listOf(
            Section(null, listOf(
                "A wrong answer in a stage's sub-game means replaying the whole stage to try again.",
                "MG-5 and Jumping are on the Worlds tab's Hyper Zone & extras list, with checkboxes.",
            ), tone = Tone.WARNING),
        )),
    )

    val hints: List<Kdl3Page> = listOf(
        Kdl3Page("pattern", "Stage pattern", "The same six kinds of task in every world", listOf(
            Section(null, listOf(
                "Stage 1: flowers or mushrooms. Stage 2: a specific ability or friend. Stage 3: a sub-game. Stage 4: a rescue behind a mid-boss. Stage 5: bring the right friend to the end. Stage 6: collect items (Grass Land, Sand Canyon, Iceberg) or carve Star Blocks into a shape (Ripple Field, Cloudy Park).",
                "Most tasks play a tone when you've done the part that counts. The character at the end hands over the Heart Star.",
            )),
        )),
        Kdl3Page("percent", "What 100% takes", "How the file percentage adds up", listOf(
            Section(null, listOf(
                "Clearing stages: 30%. Heart Stars: 30%. Bosses: 15%. That's 75% from the stages and bosses alone.",
                "The rest comes from Dark Matter and Zero, then MG-5, Boss Butch, and a score of 10 in Jumping (1% each).",
            )),
            Section("This guide's checklist", listOf(
                "34 items: the 30 Heart Stars, Zero, MG-5, Boss Butch and Jumping.",
            ), tone = Tone.SECRET),
        )),
        Kdl3Page("hyper", "Reaching the Hyper Zone", "Don't beat Dedede too early", listOf(
            Section(null, listOf(
                "You need all 30 Heart Stars before beating King Dedede. Otherwise you get the normal ending.",
                "Once a world's boss is beaten with all six of that world's Heart Stars, the dark clouds clear and the boss is at peace; a Warp Star waits in its room instead.",
            )),
        )),
        Kdl3Page("retries", "Replaying stages", "No limit on retries", listOf(
            Section(null, listOf(
                "Replay any stage as often as you like. Finishing a task you've already done just gives a 1-Up.",
                "Friends travel with you between stages. That's how you bring Nago into Iceberg 5.",
            )),
        )),
        Kdl3Page("combos", "Useful friend + ability combos", "The ones Heart Stars rely on", listOf(
            Section(null, listOf(
                "Pitch + Clean: throws water balls (Ripple Field 1's sprouts).",
                "Coo or Rick + Clean: a feather duster (Cloudy Park 1's flowers).",
                "Kine + Spark: a light bulb that reveals hidden doors and the right door in Sand Canyon 6's maze.",
                "Kine + Parasol: the pose that makes Bakasa laugh (Ripple Field 2).",
                "Coo or ChuChu + Burning: cuts through ice (Iceberg 4).",
                "Coo + Stone: drops fast to break blocks below. Cancel it quickly over pits.",
            )),
        )),
    )
}
