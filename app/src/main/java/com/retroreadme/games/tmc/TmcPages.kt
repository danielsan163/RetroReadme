package com.retroreadme.games.tmc

import com.retroreadme.core.Section
import com.retroreadme.core.Tone

object TmcPages {

    val bosses: List<Boss> = listOf(
        Boss("b_chuchu", "Big Green Chuchu", "Deepwood Shrine", "Earth Element", listOf(
            "You're Minish-sized, so an ordinary Chuchu is huge.",
            "Use the Gust Jar on its base until it wobbles and topples. Don't be under it when it falls.",
            "Slash it while it's down. It jumps more as it weakens; keep away from where it lands.",
        )),
        Boss("b_gleerok", "Gleerok", "Cave of Flames", "Fire Element", listOf(
            "Its head can't be hurt. Hit its shell on the side or back with the Cane of Pacci between fireballs.",
            "When it collapses, run up its neck and slash the yellow spike on its back.",
            "It sinks, rocks fall, and it comes back spitting fire faster. Repeat.",
        )),
        Boss("b_mazaal", "Mazaal", "Fortress of Winds", "Ocarina of Wind", listOf(
            "Shoot the eye on each hand with an arrow while that hand is still, then slash it to finish it off. Don't get caught by the grabbing hand.",
            "With both hands down, its head drops. Shrink at a Minish Portal at the top of the room and get in through its mouth.",
            "Inside, slash the glowing statue. In later rounds, dig with the Mole Mitts to find it.",
            "Its beam can shrink you; get back to a portal and grow again.",
        )),
        Boss("b_octorok", "Big Octorok", "Temple of Droplets", "Water Element", listOf(
            "You're Minish-sized again. Deflect the rocks it spits back at it with your shield.",
            "Then the floor ices over: chase it (the Pegasus Boots help) and burn the flower on its back with the Lantern.",
            "Keep clear of its mouth when it starts sucking, and mash buttons to break out of its icy breath.",
        )),
        Boss("b_gyorg", "Gyorg Pair", "Palace of Winds", "Wind Element", listOf(
            "Fought in the sky on the backs of the two Gyorgs; Roc's Cape gets you from one to the other.",
            "On the red one, charge your sword to split and hit its eyes. The blue one fires at you meanwhile.",
        )),
        Boss("b_vaati", "Vaati", "Dark Hyrule Castle", "The end", listOf(
            "Fill your bottles first. Once inside, three bells mark how long you have to reach Zelda; don't stop to fight more than you must.",
            "Vaati changes form as you go. In his Wind Mage form, shoot the orbs around him with arrows.",
            "The Four Sword's splits are your strongest attack throughout.",
        )),
    )

    val hints: List<TmcPage> = listOf(
        TmcPage("h_gregal", "Don't lose the Light Arrows", "Missable", listOf(
            Section(null, listOf(
                "Fuse with Strato (the stranger in Hyrule Town) early: it opens a portal in South Hyrule Field up to the Home of the Wind Tribe.",
                "Up there Gregal is ill. Suck the ghost off him with the Gust Jar.",
                "If you reach the Cloud Tops through Veil Falls (the story route) before doing this, he dies and the Bow of Light is gone.",
            ), tone = Tone.WARNING),
        )),
        TmcPage("h_kinstones", "Kinstone fusions", "How they work", listOf(
            Section(null, listOf(
                "When someone has a thought bubble, press L next to them and pick the half that matches theirs.",
                "Each fusion makes something happen somewhere: a chest, a beanstalk, an open tree. The map marks the spot.",
                "Fusions unlock in six story stages, so not everyone has one to offer yet. The Kinstones tab is grouped by stage.",
                "Gold fusions are part of the story (Castor Wilds, Veil Falls and the Cloud Tops).",
            )),
        )),
        TmcPage("h_random", "Random fusions", "18 of the 100", listOf(
            Section(null, listOf(
                "Eighteen fusions aren't tied to anyone. They turn up on whoever the game picks from a long list of people, mostly in Hyrule Town, and any of the Minish.",
                "If someone with one leaves during the story, it moves to someone else, so you can't miss them.",
                "Leaving and coming back into an area can make new ones appear.",
            )),
        )),
        TmcPage("h_finish", "After all 100", "Two last rewards", listOf(
            Section(null, listOf(
                "A late fusion with a Forest Minish in the Minish Village opens a crack by Lake Hylia's Wind Crest down to Librari, who gives you a Heart Container.",
                "Tingle's brothers keep count of your fusions; with all 100 done, the Kinstone Bag becomes the Tingle Trophy.",
            ), tone = Tone.SECRET),
        )),
        TmcPage("h_sword", "Splitting and the Sanctuary", "The sword's elements", listOf(
            Section(null, listOf(
                "Each element you take to the Elemental Sanctuary in Hyrule Castle powers up your sword: hold the button on a glowing pad to split into two, then three, then four Links.",
                "Many puzzles, and several Heart Pieces, need a split to push a heavy block.",
            )),
        )),
        TmcPage("h_crests", "Wind Crests and the Ocarina", "Getting around", listOf(
            Section(null, listOf(
                "Wind Crests are the stone markers you uncover around Hyrule. Once you have the Ocarina of Wind, it flies you to any crest you've found.",
                "Swiftblade and the other swordmasters teach sword techniques (Tiger Scrolls). They aren't on this checklist, but several dojos hold Heart Pieces.",
            )),
        )),
    )
}
