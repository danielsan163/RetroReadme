package com.retroreadme.games.aos

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object AosPages {

    /** The castle's areas, roughly in the order you reach them. */
    val areas = listOf(
        "Castle Corridor", "Chapel", "Study", "Dance Hall", "Inner Quarters", "Floating Garden",
        "Clock Tower", "Underground Reservoir", "Underground Cemetery", "The Arena",
        "Forbidden Area", "Top Floor", "Chaotic Realm",
    )

    /** The souls that open up the castle, in the usual order, with what each opens. */
    val route: List<Pair<String, String>> = listOf(
        "s_grave_keeper" to "Backdash. On the only path from the start of the Castle Corridor.",
        "s_flying_armor" to "Longer jumps. At the end of the Corridor's 2nd long water passage.",
        "s_malphas" to "Double jump, which opens most of the early castle. In the Study, after Great Armor.",
        "s_skeleton_blaze" to "Slide through low gaps. In the Dance Hall, after Big Golem.",
        "s_undine" to "Walk on water. In the Inner Quarters, above and right of the Headhunter.",
        "s_skula" to "Sink and walk underwater. In the Clock Tower, after Death.",
        "s_galamoth" to "Get past the Chronomage in the Inner Quarters. In the Underground Cemetery, past Legion.",
        "s_giant_bat" to "Fly. In the Arena, after Balore. Also one of the three souls for the good ending.",
        "s_hippogryph" to "Super-high jump, which makes Giant Bat unnecessary for getting around. On the Top Floor, behind the wall right of the Iron Golem.",
    )

    val endings: List<AosPage> = listOf(
        AosPage("e_bad", "Bad ending", "Beat Graham as normal", listOf(
            Section(null, listOf(
                "Beat Graham on the Top Floor without the three souls below equipped, and the game ends there.",
            )),
        )),
        AosPage("e_true", "Toward the best ending", "Three souls against Graham", listOf(
            Section("Equip all three for the whole Graham fight", listOf(
                "Bullet: Flame Demon (Underground Cemetery, Forbidden Area).",
                "Guardian: Giant Bat (the Arena, after Balore).",
                "Enchanted: Succubus (the Arena, Top Floor, Chaotic Realm).",
            ), tone = Tone.SECRET),
            Section(null, listOf(
                "They're the answers to the riddles in the three ancient books. Have them on before the fight starts and keep them on until it's over; you can change afterwards.",
                "The story then continues into the Chaotic Realm and the fight with Chaos.",
                "With all three equipped you can also fire Flame Demon's fireballs while flying as a bat.",
            )),
        )),
        AosPage("e_best", "Best ending extras", "Chaos and a full soul collection", listOf(
            Section(null, listOf(
                "Defeat Chaos for the best ending.",
                "If you've collected every soul before beating Chaos, the best ending has an extra line of dialogue.",
                "Collect 100% of the souls and the Chaos Ring (infinite MP) appears in one of the small side rooms of the Chaotic Realm where you can see space in the background.",
            )),
        )),
        AosPage("e_after", "After the credits", "New Game+ and Julius", listOf(
            Section(null, listOf(
                "After the full ending, a bat symbol above your save file starts New Game+: you keep your items and souls, but not the Ability souls.",
                "New Game+ also gives you another shot at Legion's soul.",
                "With a good ending cleared, start a new game named JULIUS to play as Julius Belmont.",
            )),
        )),
    )

    val hints: List<AosPage> = listOf(
        AosPage("h_basics", "How souls work", "Random drops and soul holders", listOf(
            Section(null, listOf(
                "Most enemies have a random chance to drop their soul when defeated. The important story souls come from soul holders, which always give the same soul.",
                "Bosses you only fight once always drop their soul, except Legion. Creaking Skull, Manticore, Great Armor and Big Golem show up again later as regular enemies.",
                "You can hold up to 9 of each soul, mainly for trading over a link cable.",
                "In the pause menu's enemy list, a red check mark shows which souls you already have.",
            )),
        )),
        AosPage("h_farm", "Farming rare souls", "Pick the right room", listOf(
            Section(null, listOf(
                "Best rooms: where the enemy is alone, where lots of them appear, or where it's right by an exit. Kill it, step out, step back in, repeat.",
                "Raise your LCK: the Ghost Dancer (+4) and Gremlin (+8) souls, and luck-boosting gear.",
                "The Soul Eater Ring from Hammer's shop raises soul drop rates a lot, but costs 300,000 gold (240,000 with the Tsuchinoko soul equipped).",
                "Check an enemy's weaknesses in the enemy list first; the faster the kill, the faster the farm.",
            )),
        )),
        AosPage("h_money", "Making money", "For the Soul Eater Ring", listOf(
            Section(null, listOf(
                "With the Rare Ring on, kill the two Lubicants just inside the first Arena challenge (by the Arena save point) for Muramasa swords, which sell for about 30,000 each.",
                "Or equip the Mimic soul (gold for damage taken) and stand in the spike pit by the 1st Clock Tower save point, healing at the save point when low.",
            )),
        )),
        AosPage("h_headhunter", "Powering up Headhunter", "More souls, more stats", listOf(
            Section(null, listOf(
                "Headhunter raises every stat but LCK based on how many souls you've absorbed, duplicates included, stepping up every 16, to a maximum of +33.",
                "It doesn't need to be equipped while you collect. A quick way to rack them up: dash in and out of the small Merman room above the first long water hallway, right of the first save point.",
            )),
        )),
        AosPage("h_waterfall", "The Reservoir waterfall", "Getting to the Forbidden Area", listOf(
            Section(null, listOf(
                "Equip Undine to stand on the water, go to the far left of the screen, and rush through the waterfall with Curly, Devil or Manticore.",
                "That reaches the Forbidden Area; you'll hit its locked door from the other side much earlier.",
            )),
        )),
    )
}
