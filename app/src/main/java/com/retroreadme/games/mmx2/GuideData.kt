package com.retroreadme.games.mmx2

import com.retroreadme.games.mmx2.ItemType.ARMOR
import com.retroreadme.games.mmx2.ItemType.HEART
import com.retroreadme.games.mmx2.ItemType.SECRET
import com.retroreadme.games.mmx2.ItemType.SUB_TANK
import com.retroreadme.games.mmx2.ItemType.ZERO_PART
import com.retroreadme.core.Section
import com.retroreadme.core.Tone

/**
 * All walkthrough content lives here as plain Kotlin so it's easy to edit.
 * Change a string, rebuild, done.
 */
object GuideData {

    // ---------------------------------------------------------------- Stages & power-ups

    val stages: List<Stage> = listOf(
        Stage(
            id = "sponge", maverick = "Wire Sponge", area = "Weather Control",
            weakness = "Sonic Slicer", weapon = "Strike Chain",
            powerUps = listOf(
                PowerUp(
                    "sponge_heart", HEART, "Heart Tank", emptyList(),
                    listOf(
                        "As soon as the stage starts, wall-climb the wall on the far left.",
                        "Near the top is a small hidden nook, roughly level with your health bar. The heart is inside.",
                    ),
                ),
                PowerUp(
                    "sponge_sub", SUB_TANK, "Sub Tank", listOf("Legs (recommended)"),
                    listOf(
                        "Early on you reach thin platforms rising and falling over a bed of spikes.",
                        "Stand on the first one, then jump left onto the big trunk-like wall.",
                        "Climb up to the large platform overhead and follow it right to the Sub Tank.",
                    ),
                ),
            ),
            xHunterDoor = "In the elevator shaft, let the lifts go past and carefully wall-slide down beneath them. The door is at the bottom right.",
        ),
        Stage(
            id = "gator", maverick = "Wheel Gator", area = "Dinosaur Tank",
            weakness = "Strike Chain", weapon = "Spin Wheel",
            powerUps = listOf(
                PowerUp(
                    "gator_arm", ARMOR, "Arm parts", listOf("Legs"),
                    listOf(
                        "Near the start, look for an opening in the ceiling.",
                        "Wall-jump up the wall just to the right of it, then air-dash over to the small jutting piece of wall.",
                        "Climb up through the opening to the capsule.",
                        "It's technically possible without Legs using Strike Chain and a pixel-perfect jump. Not worth the frustration.",
                    ),
                    effect = "Special weapons can be charged, and the buster fires a stronger double charge shot.",
                ),
                PowerUp(
                    "gator_heart", HEART, "Heart Tank", listOf("Speed Burner", "Legs", "Arm parts"),
                    listOf(
                        "After the Ride Armor section and the trip underneath the tank, you climb a ladder back up onto it. The heart sits on top of a spike-covered wall to the left.",
                        "Stand on the raised ledge to its right and fully charge Speed Burner.",
                        "Jump as high as you can toward the heart, air-dash, then release the charged Speed Burner to fly across.",
                        "If you come up short, aim to catch the ledge edge. Speedrunner option: take a hit and use the invincibility frames to climb past the spikes.",
                    ),
                ),
            ),
            xHunterDoor = "Ride the second elevator all the way to the top. The door is on the right at the very top, just under the ceiling spikes, so step off quickly.",
        ),
        Stage(
            id = "crab", maverick = "Bubble Crab", area = "Deep-Sea Base",
            weakness = "Spin Wheel", weapon = "Bubble Splash",
            powerUps = listOf(
                PowerUp(
                    "crab_heart", HEART, "Heart Tank", emptyList(),
                    listOf(
                        "A little after you first see the fish submarine (Sea Canthller), it opens a large trapdoor in the floor.",
                        "Climb the wall on the right side of that trapdoor to near the top.",
                        "Dash-jump off to the left onto the orange rocket platforms and ride them up to the heart. It can take a few tries.",
                    ),
                ),
                PowerUp(
                    "crab_sub", SUB_TANK, "Sub Tank", listOf("Bubble Splash", "Arm parts"),
                    listOf(
                        "Go to the long flat rocky stretch where the giant fish passes, clear the jellyfish, and fully charge Bubble Splash.",
                        "Jump straight up. The bubbles only just reach the surface, which isn't far enough on its own.",
                        "As you break the surface, tap jump again. X can jump off the top of the water, repeatedly, so hop left along the surface and up onto the ledge.",
                        "Without weapons: X jumps higher walking (not dashing) down a slope. From the slope at the left end, jump to the lower left wall, climb to the waterline, then hop right along the surface.",
                    ),
                ),
            ),
            miniBosses = listOf("Sea Canthller"),
            xHunterDoor = "Above the entrance to the underwater base. You must destroy Sea Canthller before it docks or the door stays shut. Then climb the wall up to the door.",
        ),
        Stage(
            id = "stag", maverick = "Flame Stag", area = "Volcanic Zone",
            weakness = "Bubble Splash", weapon = "Speed Burner",
            powerUps = listOf(
                PowerUp(
                    "stag_sub", SUB_TANK, "Sub Tank", emptyList(),
                    listOf(
                        "Near the start you meet a big beetle robot that rams walls and smashes them.",
                        "Stand on the flat part of its back and let it carry you up toward the top of the screen.",
                        "Jump left onto the high platform for the Sub Tank.",
                    ),
                ),
                PowerUp(
                    "stag_heart", HEART, "Heart Tank", emptyList(),
                    listOf(
                        "In the section where lava rises and you wall-climb to outrun it, watch the left side on the way up. The heart is sealed behind a wall there.",
                        "Charge your buster while climbing and blast the wall open.",
                        "Dash in, grab the heart, and get back on the wall fast before the lava catches you.",
                    ),
                ),
            ),
            xHunterDoor = "Let the second wall-ramming beetle break the highest breakable wall, then climb up the volcano to the door.",
        ),
        Stage(
            id = "moth", maverick = "Morph Moth", area = "Robot Junkyard",
            weakness = "Speed Burner", weapon = "Silk Shot",
            powerUps = listOf(
                PowerUp(
                    "moth_body", ARMOR, "Body parts", listOf("Spin Wheel"),
                    listOf(
                        "Just after the first room with the magnetic ceiling, you come to a small raised ledge.",
                        "Stand on the ground in front of that ledge. The floor patch there is a flat solid color, unlike the textured junk floor everywhere else.",
                        "Fire Spin Wheel into it and the saw digs down, opening a shaft.",
                        "Drop in and follow it to the capsule.",
                    ),
                    effect = "You take less damage, and damage you absorb fills the Giga Crush gauge.",
                ),
                PowerUp(
                    "moth_heart", HEART, "Heart Tank", listOf("Crystal Hunter"),
                    listOf(
                        "At the very start, outside the large building, there's an enemy carrying a shield.",
                        "Shoot the shield so it flies off, then freeze the enemy with Crystal Hunter.",
                        "Stand on the frozen enemy, jump right onto the building's wall, and climb up to the heart.",
                    ),
                ),
            ),
            miniBosses = listOf("Pararoid S-38 (twice)"),
            xHunterDoor = "After the first Old Robot mini-boss, go down the ladder. A ledge off to the right leads to the door. Air dash makes this easy.",
        ),
        Stage(
            id = "centipede", maverick = "Magna Centipede", area = "Central Computer",
            weakness = "Silk Shot", weapon = "Magnet Mine",
            powerUps = listOf(
                PowerUp(
                    "centipede_heart", HEART, "Heart Tank", listOf("Legs", "Speed Burner (helps)"),
                    listOf(
                        "In the opening area with the two yellow spotlights, stay out of the lights so the ceiling turrets don't drop.",
                        "The second turret hangs beneath an opening in the roof.",
                        "Get onto the turret's right side (air dash, or a charged Speed Burner for extra reach) and wall-climb up through the opening to the heart.",
                    ),
                ),
                PowerUp(
                    "centipede_sub", SUB_TANK, "Sub Tank", listOf("Speed Burner", "Legs", "Arm parts"),
                    listOf(
                        "In the moving-block room, go to the final part where three rows of two blocks drop and stack up.",
                        "Dash past them before they seal the way, and charge Speed Burner.",
                        "Ride the last block as it slides right. Before it falls into the pit, jump right and release the charged Speed Burner to reach the wall.",
                        "Climb up into the ceiling opening for the Sub Tank. A charged Crystal Hunter slows the blocks down if you're struggling.",
                    ),
                ),
            ),
            miniBosses = listOf("Chop Register", "Raider Killer"),
            xHunterDoor = "Race through the falling-block room. The door is at the bottom right, and the blocks will wall it off if you're slow.",
        ),
        Stage(
            id = "snail", maverick = "Crystal Snail", area = "Energen Crystal",
            weakness = "Magnet Mine", weapon = "Crystal Hunter",
            powerUps = listOf(
                PowerUp(
                    "snail_head", ARMOR, "Head parts", emptyList(),
                    listOf(
                        "Work through the area of slanted crystal platforms full of bat enemies.",
                        "Slide down the big shaft. Just before the bottom there's an opening in the left wall.",
                        "Follow that tunnel to the capsule.",
                    ),
                    effect = "Adds the Item Tracer to your weapon menu. It scans for hidden passages.",
                ),
                PowerUp(
                    "snail_heart", HEART, "Heart Tank", listOf("Legs", "Strike Chain (helps)"),
                    listOf(
                        "Early on, get into the Ride Armor and walk it back left to the large hole.",
                        "Drop down hugging the left wall and land on the lower platform.",
                        "Jump off the left side, hover as long as the armor allows, then jump out of it as it starts to fall and keep going left to the heart's ledge.",
                        "If you're short, fire Strike Chain at the wall to pull yourself over. A jet platform can carry you back afterwards.",
                    ),
                ),
            ),
            miniBosses = listOf("Magna Quartz"),
            xHunterDoor = "Use the Ride Armor to smash the crystal blocks in the spike pit, then make the giant crystal slide down to clear the hall so the armor can continue. Break the crystal blocks on a ledge to the left, then climb up using the floating probe enemies.",
        ),
        Stage(
            id = "ostrich", maverick = "Overdrive Ostrich", area = "Desert Base",
            weakness = "Crystal Hunter", weapon = "Sonic Slicer",
            powerUps = listOf(
                PowerUp(
                    "ostrich_legs", ARMOR, "Leg parts", listOf("Spin Wheel"),
                    listOf(
                        "Just before the end of the stage, look above the final door. There's a dark wall on a ledge to the upper right.",
                        "Break it with Spin Wheel to reach the capsule.",
                    ),
                    effect = "Air dash: jump, then press dash in midair.",
                ),
                PowerUp(
                    "ostrich_heart", HEART, "Heart Tank", emptyList(),
                    listOf(
                        "Near the end, the heart sits on a ledge surrounded by spikes.",
                        "Keep the Ride Chaser bike alive through the desert (spare bikes wait at checkpoints). Inside the base, dash up the incline to launch onto the ledge.",
                        "Alternative: a charged Speed Burner from the left reaches it. You'll touch spikes and lose a life, but the heart stays collected.",
                    ),
                ),
            ),
            xHunterDoor = "Near the start, past the scorpion enemy, break the sand wall with Spin Wheel to open a tunnel to the door.",
            xHunterDoorRequires = listOf("Spin Wheel"),
        ),
        Stage(
            id = "hunter3", maverick = "X-Hunter Stage 3", area = "Final stages",
            weakness = null, weapon = null,
            powerUps = listOf(
                PowerUp(
                    "shoryuken", SECRET, "Shoryuken", listOf("All 8 Heart Tanks", "All 4 Sub Tanks", "All 4 armor parts"),
                    listOf(
                        "After the elevator section you reach two ladders, one leading up and one down. Use the elevator to get to the upper ladder.",
                        "The next room is lined with spikes. Cross it with air dashes and charged Speed Burner.",
                        "At the long drop that follows, slide down the left wall. You'll slip into a hidden room with the capsule.",
                    ),
                    effect = "At full health with the X-Buster selected, press forward, down, down-forward + fire. It destroys most bosses in one hit (Morph Moth takes two).",
                ),
            ),
        ),
        Stage(
            id = "zero", maverick = "Zero's parts", area = "Carried by the X-Hunters",
            weakness = null, weapon = null,
            powerUps = listOf(
                PowerUp(
                    "zero_head", ZERO_PART, "Head: from Serges", listOf("Sonic Slicer"),
                    listOf(
                        "Beat Serges in whichever Maverick stage he's hiding in.",
                        "He leaves first as the map fills up, so take him early if you get the chance.",
                    ),
                ),
                PowerUp(
                    "zero_body", ZERO_PART, "Body: from Violen", listOf("Bubble Splash"),
                    listOf("Beat Violen in whichever Maverick stage he's hiding in."),
                ),
                PowerUp(
                    "zero_legs", ZERO_PART, "Legs: from Agile", listOf("Magnet Mine"),
                    listOf("Beat Agile in whichever Maverick stage he's hiding in."),
                ),
            ),
            notes = listOf(
                "All three revive Zero, and you skip the Zero fight before Sigma.",
                "Never beat a Maverick whose stage shows a Sigma icon without clearing the hidden door first. That X-Hunter is then gone for good.",
            ),
        ),
    )

    val allPowerUps: List<PowerUp> = stages.flatMap { it.powerUps }

    // ---------------------------------------------------------------- Bosses

    val bosses: List<Boss> = listOf(
        Boss(
            "cf0", "Gigantic Mechaniloid CF-0", BossCategory.OPENING, "Maverick Factory",
            weakness = "X-Buster",
            attacks = listOf(
                "Walks and leaps around in the background; it's several screens tall.",
                "Swings its spiked-ball fists in spins and rapid punches.",
                "Its feet hurt if you drop to the bottom of the room.",
            ),
            strategy = listOf(
                "Only the head takes damage. Everything else just blocks shots.",
                "Stay high on the walls, keep a charge ready, and release it whenever the head lines up.",
            ),
        ),

        // Mavericks
        Boss(
            "b_sponge", "Wire Sponge", BossCategory.MAVERICK, "Weather Control",
            weakness = "Sonic Slicer", reward = "Strike Chain",
            weaponNote = "Sonic Slicer does 2 damage, 5 when charged. Buster-only is very doable.",
            attacks = listOf(
                "Spins his vine chain as a shield, then throws it straight across the room.",
                "Jumps at you along the floor.",
                "Hangs from the ceiling and drops seeds that sprout into spiky plants.",
                "When damaged enough he flashes red, becomes invincible, and calls lightning down near himself.",
            ),
            strategy = listOf(
                "The chain only travels horizontally, so climb a wall to dodge it and shoot as he reels it back.",
                "Shoot the seeds and sprouts down before they box you in; they're the real danger.",
                "When he turns red, get as far from him as you can until the lightning stops.",
                "A good first Maverick if you're starting with nothing.",
            ),
        ),
        Boss(
            "b_gator", "Wheel Gator", BossCategory.MAVERICK, "Dinosaur Tank",
            weakness = "Strike Chain", reward = "Spin Wheel",
            weaponNote = "Strike Chain does 3 damage, 5 charged.",
            attacks = listOf(
                "Dives into the oil and sends saw wheels rolling up the walls and across the ceiling.",
                "Surfaces under you for a heavy bite. Wiggle left and right to break free.",
                "Leaps into a wall; the mark he leaves hurts on contact.",
            ),
            strategy = listOf(
                "Enter with a charged shot and open with it, then switch to Strike Chain.",
                "Stay high on the left wall. Hop off briefly to let each saw wheel pass, then get straight back on.",
                "When the oil goes calm, a bite is coming. Hit him as he surfaces, before he can jump at the wall.",
            ),
        ),
        Boss(
            "b_crab", "Bubble Crab", BossCategory.MAVERICK, "Deep-Sea Base",
            weakness = "Spin Wheel", reward = "Bubble Splash",
            weaponNote = "Spin Wheel pops his bubble shield and does 3 damage, 4 charged. The plain buster can't get through the shield.",
            attacks = listOf(
                "Surrounds himself with a bubble shield.",
                "Hops around the room and charges across the floor with his claws.",
                "Releases bubbles with small crab robots inside.",
                "Jolts of electricity change the water level. The ceiling is spiked, so don't jump too high.",
            ),
            strategy = listOf(
                "Mostly stay on the walls. When he hops close, drop down and dash underneath rather than jumping over.",
                "Changing water levels change how high he hops, so give yourself extra room when the water is up.",
            ),
        ),
        Boss(
            "b_stag", "Flame Stag", BossCategory.MAVERICK, "Volcanic Zone",
            weakness = "Bubble Splash", reward = "Speed Burner",
            weaponNote = "Bubble Splash does 2 damage. Sonic Slicer also does 2 if you have it instead.",
            attacks = listOf(
                "Bounds up and down the walls of his very tall arena.",
                "Dashes at you; if he grabs you it's a throw, uppercut and slam combo.",
                "Throws pairs of fireballs.",
                "Below half health his flames turn blue and linger longer.",
            ),
            strategy = listOf(
                "Hug the far left or right corner; he rarely lands right on top of you there.",
                "Each Bubble Splash hit tends to make him throw fireballs. Learn that one dodge and you can win without getting touched.",
                "When he jumps up a wall, go over him and immediately reverse, because he'll try to cut you off.",
            ),
        ),
        Boss(
            "b_moth", "Morph Moth", BossCategory.MAVERICK, "Robot Junkyard",
            weakness = "Speed Burner", reward = "Silk Shot",
            weaponNote = "Speed Burner does 3 damage, 6 charged, in both forms.",
            attacks = listOf(
                "Cocoon form swings from the ceiling, gathers scrap, and spins across the floor flinging junk.",
                "At about half health he breaks out and flies around in moth form.",
                "The moth scatters damaging powder and fires a beam.",
            ),
            strategy = listOf(
                "Contact damage is heavy, so keep your distance and use the walls to get over him.",
                "Hit with Speed Burner, wait for his flashing to end, hit again. Charged Speed Burner makes it quick.",
            ),
        ),
        Boss(
            "b_centipede", "Magna Centipede", BossCategory.MAVERICK, "Central Computer",
            weakness = "Silk Shot", reward = "Magnet Mine",
            weaponNote = "Silk Shot does 2 damage, 4 charged, stuns him, and knocks off his tail. Strike Chain is a decent backup at 2 damage.",
            attacks = listOf(
                "Jumps between corners and teleports between four fixed spots.",
                "Breaks his tail into pieces that circle you, stop, then snap back together where you were.",
                "Throws three curving shurikens.",
                "Raises an arm and magnetically pulls you in. Each catch strips an ability: first charging, then multi-shot, then dash, then jump height.",
            ),
            strategy = listOf(
                "The moment he raises his arm, dash away and keep holding dash. If you're caught, mash the D-pad to escape.",
                "Your first Silk Shot hit removes his tail, which also ends the power drain for the rest of the fight.",
                "When he's on the ceiling, fire Silk Shot into the floor on the opposite side; the diagonal shrapnel flies up into him.",
                "His hitbox is smaller than it looks, so you can often dash under his corner jumps.",
            ),
        ),
        Boss(
            "b_snail", "Crystal Snail", BossCategory.MAVERICK, "Energen Crystal",
            weakness = "Magnet Mine", reward = "Crystal Hunter",
            weaponNote = "Magnet Mine does 3 damage, 4 charged, and pulls him out of his shell.",
            attacks = listOf(
                "Hides in his shell to block shots.",
                "Fires crystal bubbles that freeze you in place.",
                "Flies around inside his shell, ramming you.",
                "Makes the screen ripple and slows your movement down.",
            ),
            strategy = listOf(
                "Magnet Mine yanks him out of the shell and sends him into the wall for extra punishment.",
                "He often flies toward you after being hit, so jump away right after each shot.",
                "Hit him while he's exposed and out of his shell.",
            ),
        ),
        Boss(
            "b_ostrich", "Overdrive Ostrich", BossCategory.MAVERICK, "Desert Base",
            weakness = "Crystal Hunter", reward = "Sonic Slicer",
            weaponNote = "Crystal Hunter does 3 damage and freezes him briefly. With the buster alone he's still a reasonable first pick.",
            attacks = listOf(
                "Sprints at you; a hit sends you flying across the arena.",
                "Hopping kicks.",
                "Runs off into the background, then drops out of the sky onto you.",
                "Throws Sonic Slicer blades straight ahead and down from above.",
            ),
            strategy = listOf(
                "Freeze him with Crystal Hunter, then refreeze him as he jumps to throw blades.",
                "Use the valley-shaped dips: stand on the opposite slope, dash-jump over him as he runs down, turn and shoot.",
                "When he goes into the background, keep dashing; he homes in on where you're standing.",
            ),
        ),

        // Mini-bosses
        Boss(
            "m_canthller", "Sea Canthller", BossCategory.MINI, "Deep-Sea Base",
            weakness = "Charged shots",
            attacks = listOf(
                "A huge coelacanth submarine with several destructible parts that chases you through the water.",
            ),
            strategy = listOf(
                "Take the parts down with charged shots as it follows you.",
                "If an X-Hunter is in this stage, you must destroy it before it docks, or the X-Hunter door won't open.",
            ),
        ),
        Boss(
            "m_pararoid", "Pararoid S-38", BossCategory.MINI, "Robot Junkyard",
            weakness = "Destroy the host first",
            attacks = listOf(
                "A pink parasite bug that takes over an Old Robot body and fights with it.",
                "You face two of them in this stage.",
                "Smaller wingless Pararoids try to latch onto you and force you to shoot, jump or dash on their own.",
            ),
            strategy = listOf(
                "Wreck the Old Robot body to expose the parasite, then finish it off.",
                "If a small Pararoid grabs you, mash the D-pad to shake it loose.",
            ),
        ),
        Boss(
            "m_chop", "Chop Register", BossCategory.MINI, "Central Computer (1st)",
            weakness = "Silk Shot / Strike Chain",
            weaponNote = "No invincibility frames, so a Giga Crush or a well-placed charged Sonic Slicer destroys it outright. Spin Wheel slows it down.",
            attacks = listOf(
                "A floating wireframe sword that slashes, thrusts, and sweeps in circles when you get close.",
                "The green blade deflects your shots and hurts on contact.",
            ),
            strategy = listOf(
                "Only the blue hilt can be damaged. Aim everything at it.",
                "Keep out of its circular sweep range and pick your shots when the hilt is exposed.",
            ),
        ),
        Boss(
            "m_raider", "Raider Killer", BossCategory.MINI, "Central Computer (2nd)",
            weakness = "Speed Burner",
            attacks = listOf(
                "Green (never scanned): shoots straight ahead or jumps and fires three shots down.",
                "Blue (scanned once): aims its shots at you.",
                "Red: adds jumps that try to land on you.",
                "Purple: adds a shield projectile from its mouth. Every level also raises its health.",
            ),
            strategy = listOf(
                "The scanner drones in the hallway before this fight power it up each time they lock onto you. Dodge them and it stays easy.",
                "Hit it with Speed Burner, jump its return fire, repeat.",
            ),
        ),
        Boss(
            "m_quartz", "Magna Quartz", BossCategory.MINI, "Energen Crystal",
            weakness = "Giga Crush",
            weaponNote = "No invincibility frames, so Giga Crush destroys it in one go.",
            attacks = listOf(
                "A large crystal that spawns drones.",
                "The drones fire lasers that bounce off the walls.",
            ),
            strategy = listOf(
                "Keep moving so the bouncing lasers don't pin you, and hit the crystal between volleys.",
            ),
        ),

        // X-Hunters (in Maverick stages)
        Boss(
            "h_serges", "Serges", BossCategory.X_HUNTER, "Random stage (hidden door)",
            weakness = "Sonic Slicer", reward = "Zero part 1: head",
            weaponNote = "If you meet him in Energen Crystal, Silk Shot turns into a big crystal there and does 3 damage, 5 charged. Stand close so it starts inside his barrier.",
            attacks = listOf(
                "Floats on a platform and drops mines that explode on touch.",
                "Raises a barrier that blocks shots.",
                "After taking a hit, he spins and sprays beams around the room.",
            ),
            strategy = listOf(
                "His barrier goes up when you press fire, not when you release it. So hold a charge, wait for the barrier to drop, then release.",
                "Bounce Sonic Slicer off the wall so it arrives after the barrier has gone down.",
                "Air dash helps a lot. He leaves once you've beaten 5 Mavericks.",
            ),
        ),
        Boss(
            "h_violen", "Violen", BossCategory.X_HUNTER, "Random stage (hidden door)",
            weakness = "Bubble Splash", reward = "Zero part 2: body",
            weaponNote = "Sonic Slicer also does 2. In Weather Control, Silk Shot becomes leaves and works well on him.",
            attacks = listOf(
                "Fires energy balls straight at you.",
                "Leaps around the room.",
                "Swings a huge spiked ball on a chain that ricochets at high speed.",
            ),
            strategy = listOf(
                "Stand in front of him and keep firing Bubble Splash; the ball often doesn't swing directly in front.",
                "Every few swings he jumps toward you twice; step aside or dash under.",
                "He leaves once you've beaten 6 Mavericks.",
            ),
        ),
        Boss(
            "h_agile", "Agile", BossCategory.X_HUNTER, "Random stage (hidden door)",
            weakness = "Magnet Mine", reward = "Zero part 3: legs",
            weaponNote = "Magnet Mine does 3 damage, 4 charged. In Deep-Sea Base or Volcanic Zone, Silk Shot becomes rocks and is strong against him.",
            attacks = listOf(
                "Charges across the room sword-first.",
                "Jumps and fires a large energy beam aimed at your position.",
            ),
            strategy = listOf(
                "Shoot him when he stops, then go up a wall so you can drop past his beam and fire again.",
                "He's the last to leave, after your 7th Maverick.",
            ),
        ),

        // Final stages
        Boss(
            "f_violen", "Neo Violen", BossCategory.FINAL, "X-Hunter Stage 1",
            weakness = "Bubble Splash",
            weaponNote = "Only Bubble Splash, the buster, and Giga Crush hurt him here.",
            attacks = listOf(
                "Same spiked ball and energy balls as before.",
                "Now summons blocks around the room in a predictable pattern.",
            ),
            strategy = listOf(
                "After each hit, back off until he stops flashing, then go again.",
                "Stay near the edges or beside a block to avoid the ball, and use the blocks to reach him when he jumps high.",
            ),
        ),
        Boss(
            "f_serges", "Serges Tank", BossCategory.FINAL, "X-Hunter Stage 2",
            weakness = "Sonic Slicer",
            weaponNote = "For the four cannons: Giga Crush wrecks them all at once, Silk Shot takes one direct hit each, Spin Wheel about three. Avoid the plain buster on the cannons; it's slow.",
            attacks = listOf(
                "A big war machine over a spike pit, with four moving platforms for you.",
                "Four cannons each fire a different shot.",
                "Each destroyed cannon makes the tank roll forward and crush a platform.",
            ),
            strategy = listOf(
                "Destroy all four cannons first; the front then blows open and exposes Serges.",
                "Stand on the machine underneath him, right at the edge of the platform, and fire charged Sonic Slicers straight at him. Facing him from the edge lands them far more reliably than firing away and letting them arc back.",
            ),
        ),
        Boss(
            "f_agile", "Agile Flyer", BossCategory.FINAL, "X-Hunter Stage 3",
            weakness = "Magnet Mine",
            weaponNote = "The Shoryuken capsule is in this same stage. At full health, one Shoryuken square to his face ends the fight.",
            attacks = listOf(
                "Floats near the top of the screen.",
                "Glows red and drops spiked platforms on both sides, which release energy balls that run along the walls and floor.",
                "Fires small missiles followed by a big bomb.",
            ),
            strategy = listOf(
                "Stay under him when the spiked platforms drop.",
                "Climb a wall to his height and release a charged Magnet Mine; it drifts through him while he keeps flying into it.",
            ),
        ),
        Boss(
            "f_rematch", "Maverick rematches", BossCategory.FINAL, "X-Hunter Stage 4",
            weakness = "Same as before",
            weaponNote = "Weapons refill between X-Hunter stages, and four small capsules respawn on the centre platforms after every fight.",
            attacks = listOf("All eight Mavericks again, in any order you pick."),
            strategy = listOf(
                "Teleporter layout, which is always the same:",
                "Upper left is Morph Moth, upper right is Wheel Gator.",
                "Middle left is Wire Sponge, middle right is Overdrive Ostrich.",
                "Bottom row, left to right: Bubble Crab, Flame Stag, Magna Centipede, Crystal Snail.",
                "Switch to the right weapon before you step in, since the fight starts immediately.",
                "With the Shoryuken: Overdrive Ostrich is the easiest, hit him as he jumps. Wheel Gator works as he rises to spin, Magna Centipede while he's on the ceiling, and Crystal Snail once Magnet Mine knocks him out of his shell.",
                "Morph Moth needs two Shoryukens, one per form. Flame Stag moves too much for it to be reliable.",
                "Remember the Shoryuken only works at full health, so top up from the capsules between fights.",
            ),
        ),
        Boss(
            "f_zero", "Zero", BossCategory.FINAL, "Central Computer",
            weakness = "Speed Burner",
            weaponNote = "Only fought if you're missing any Zero part. He blocks almost every weapon; uncharged Speed Burner is your best option.",
            attacks = listOf(
                "Charged and normal buster shots.",
                "Saber slashes and a sword dash.",
                "Punches the ground to throw debris upward.",
            ),
            strategy = listOf(
                "Wait for a gap in his shots before firing; your shot is wasted if it hits one of his.",
                "Stay clear of the debris after his ground punch.",
                "Collect all three Zero parts beforehand and you skip this fight entirely.",
            ),
        ),
        Boss(
            "f_sigma", "Neo Sigma", BossCategory.FINAL, "Central Computer",
            weakness = "Sonic Slicer",
            weaponNote = "Sonic Slicer does 2 damage, 4 charged. Fire extra blades around the room; they bounce into him.",
            attacks = listOf(
                "Five electric orbs that home in one at a time.",
                "A claw dash that knocks you across the room.",
                "Vanishes up the wall and dives down on you.",
                "At low health, sends an electric wall across the floor.",
            ),
            strategy = listOf(
                "Hug a wall for the dash and the electric wall; dash a little to dodge the dive and orbs.",
                "Save your Sub Tanks for the next form.",
            ),
        ),
        Boss(
            "f_virus", "Sigma Virus", BossCategory.FINAL, "Central Computer",
            weakness = "Strike Chain",
            weaponNote = "Only Strike Chain, the buster (charged), and Giga Crush hurt him. There's no health bar; the background flashes on each hit.",
            attacks = listOf(
                "Sweeps across the screen firing a beam downward.",
                "Spits blue orbs that become small enemies.",
                "When he's glowing red, he latches onto you and drains your health.",
            ),
            strategy = listOf(
                "The small enemies drop health and weapon energy, so use them to refuel.",
                "Keep firing Strike Chain the whole time, even while he's stuck to you; it still connects.",
                "Use Sub Tanks here, not on Neo Sigma.",
            ),
        ),
    )

    // ---------------------------------------------------------------- Boss order tab

    val orderPages: List<InfoPage> = listOf(
        InfoPage(
            id = "route", title = "Recommended route", subtitle = "Weakness chain with every item",
            route = listOf(
                RouteStep(
                    1, "Overdrive Ostrich", "Desert Base", "X-Buster",
                    collect = listOf("Heart Tank (ride the bike up to it)"),
                    later = "Leg parts need Spin Wheel.",
                ),
                RouteStep(
                    2, "Wire Sponge", "Weather Control", "Sonic Slicer",
                    collect = listOf("Heart Tank", "Sub Tank"),
                    warning = "The X-Hunters appear after this fight. Watch for Sigma icons on the map, and never beat a Maverick whose stage shows one without clearing the hidden door first. See the X-Hunter timing page for how they come and go.",
                ),
                RouteStep(
                    3, "Wheel Gator", "Dinosaur Tank", "Strike Chain",
                    collect = emptyList(),
                    later = "Arm parts need Legs. The Heart Tank needs Speed Burner, Legs and Arms.",
                ),
                RouteStep(
                    4, "Bubble Crab", "Deep-Sea Base", "Spin Wheel",
                    collect = listOf("Heart Tank"),
                    later = "Sub Tank needs Bubble Splash and Arms.",
                    thenDo = listOf(
                        "Revisit Desert Base for the Leg parts (Spin Wheel).",
                        "Revisit Dinosaur Tank for the Arm parts (Legs).",
                    ),
                ),
                RouteStep(
                    5, "Flame Stag", "Volcanic Zone", "Bubble Splash",
                    collect = listOf("Heart Tank", "Sub Tank"),
                    thenDo = listOf("Revisit Deep-Sea Base for the Sub Tank."),
                ),
                RouteStep(
                    6, "Morph Moth", "Robot Junkyard", "Speed Burner",
                    collect = listOf("Body parts"),
                    later = "Heart Tank needs Crystal Hunter.",
                    thenDo = listOf("Revisit Dinosaur Tank for the Heart Tank."),
                ),
                RouteStep(
                    7, "Magna Centipede", "Central Computer", "Silk Shot",
                    collect = listOf("Heart Tank", "Sub Tank"),
                ),
                RouteStep(
                    8, "Crystal Snail", "Energen Crystal", "Magnet Mine",
                    collect = listOf("Head parts", "Heart Tank"),
                    thenDo = listOf(
                        "Revisit Robot Junkyard for the last Heart Tank (Crystal Hunter).",
                        "With all 16 items, grab the Shoryuken in X-Hunter Stage 3.",
                    ),
                ),
            ),
        ),
        InfoPage(
            id = "chain", title = "Weakness chain", subtitle = "Who beats whom",
            sections = listOf(
                Section(
                    "Each weapon beats the next Maverick",
                    listOf(
                        "Wire Sponge's Strike Chain beats Wheel Gator",
                        "Wheel Gator's Spin Wheel beats Bubble Crab",
                        "Bubble Crab's Bubble Splash beats Flame Stag",
                        "Flame Stag's Speed Burner beats Morph Moth",
                        "Morph Moth's Silk Shot beats Magna Centipede",
                        "Magna Centipede's Magnet Mine beats Crystal Snail",
                        "Crystal Snail's Crystal Hunter beats Overdrive Ostrich",
                        "Overdrive Ostrich's Sonic Slicer beats Wire Sponge",
                    ),
                ),
                Section(
                    "Damage for reference",
                    listOf(
                        "X-Buster: 1 uncharged, 2 half charge, 4 full charge.",
                        "Weakness hits usually do 2 to 3, and 4 to 6 when charged with the Arm parts.",
                        "Giga Crush always does 2 to any boss.",
                        "Crystal Hunter does no damage to most bosses; it only hurts Overdrive Ostrich.",
                    ),
                ),
                Section(
                    "Where to start",
                    listOf(
                        "Overdrive Ostrich and Wire Sponge are both manageable with just the buster.",
                        "Starting at Ostrich lets you follow the chain all the way around.",
                    ),
                ),
            ),
        ),
        InfoPage(
            id = "hunters", title = "X-Hunter timing", subtitle = "Don't lose Zero's parts",
            sections = listOf(
                Section(
                    "How they work",
                    listOf(
                        "After you beat any two Mavericks, Serges, Violen and Agile move into stages whose Maverick is still alive.",
                        "Occupied stages show a Sigma icon on the stage select map.",
                        "Each stage has a hidden door that only opens while an X-Hunter is there. Door locations are in the Power-Ups tab.",
                        "Each one you beat returns a piece of Zero.",
                    ),
                ),
                Section(
                    "Two ways to lose one, both permanent",
                    listOf(
                        "Beating a Maverick whose stage shows a Sigma icon, without going through that stage's hidden door first, kills off that X-Hunter for good.",
                        "The map always keeps at least one stage that has a Maverick and no X-Hunter. So every Maverick you beat shrinks the room available: with 5 beaten only 2 can remain, with 6 only 1, and with 7 none at all.",
                    ),
                    tone = Tone.WARNING,
                ),
                Section(
                    "Before you beat your next Maverick",
                    listOf(
                        "Stages left minus one is the most X-Hunters that can still be on the map. Check that against how many you've beaten.",
                        "Clear the ones you can see before that number drops again.",
                        "Enter and exit a cleared stage to reshuffle their positions. Press Start and choose Exit on the weapon menu; the option only appears in stages you've already finished. There's no penalty for doing it as often as you like.",
                    ),
                ),
                Section(
                    "If you lose one",
                    listOf(
                        "You'll fight Zero in the Central Computer before Sigma. Uncharged Speed Burner is the weapon for him.",
                        "Nothing else changes. The Shoryuken and every item are still available.",
                    ),
                ),
            ),
        ),
        InfoPage(
            id = "finale", title = "Final stages", subtitle = "What happens after the 8 Mavericks",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "X-Hunter Stage 1 ends with Neo Violen. Use Bubble Splash.",
                        "X-Hunter Stage 2 ends with the Serges Tank. Break the cannons, then Sonic Slicer.",
                        "X-Hunter Stage 3 ends with Agile Flyer. Use Magnet Mine. The Shoryuken capsule is hidden in this stage.",
                        "X-Hunter Stage 4 is the rematch of all eight Mavericks.",
                        "Central Computer: Zero if you're missing parts, then Neo Sigma (Sonic Slicer) and Sigma Virus (Strike Chain).",
                    ),
                    numbered = true,
                ),
                Section(
                    "If you have all three Zero parts",
                    listOf("A fake Zero shows up instead. The real Zero arrives, destroys it, and you go straight to Sigma."),
                    tone = Tone.SECRET,
                ),
            ),
        ),
    )

    // ---------------------------------------------------------------- Secrets tab

    val secrets: List<InfoPage> = listOf(
        InfoPage(
            "s_shoryuken", "Shoryuken", "The Street Fighter uppercut",
            sections = listOf(
                Section(
                    "Unlock",
                    listOf(
                        "Collect all 8 Heart Tanks, 4 Sub Tanks and 4 armor parts.",
                        "The capsule then appears in a hidden room in X-Hunter Stage 3. Full directions are in the Power-Ups tab.",
                    ),
                ),
                Section(
                    "Use",
                    listOf(
                        "You need full health and the X-Buster selected.",
                        "Input forward, down, down-forward, then fire.",
                        "It destroys nearly any boss in one hit. Morph Moth needs two, one per form.",
                    ),
                    tone = Tone.SECRET,
                ),
            ),
        ),
        InfoPage(
            "s_zero", "Zero's parts", "Changes the ending fight",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "Serges holds the head, Violen the body, Agile the legs.",
                        "Get all three and Dr. Cain rebuilds Zero. In the Central Computer, the real Zero destroys a fake and you skip that fight.",
                        "Miss any and the X-Hunters raid the base and steal whatever parts you had. Then you must fight Zero before Sigma.",
                    ),
                ),
            ),
        ),
        InfoPage(
            "s_silk", "Silk Shot changes by stage", "It picks up whatever's lying around",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "Silk Shot takes a different form depending on the stage's debris.",
                        "Weather Control: leaves. Especially good against Violen there.",
                        "Energen Crystal: crystals. Especially good against Serges there.",
                        "Deep-Sea Base or Volcanic Zone: rocks. Especially good against Agile there.",
                    ),
                ),
            ),
        ),
        InfoPage(
            "s_serges", "Serges' barrier trick", "Beat his shield",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "His barrier reacts when you press the fire button, not when you release it.",
                        "Hold fire to charge, let the barrier drop, then release the charged shot.",
                    ),
                ),
            ),
        ),
        InfoPage(
            "s_giga", "Giga Crush", "The Body parts' screen-clearing attack",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "Its gauge fills from damage you absorb while wearing the Body parts.",
                        "Select it from the weapon menu like any weapon.",
                        "It always does 2 damage to bosses.",
                        "Chop Register and Magna Quartz have no invincibility frames, so one Giga Crush destroys them outright.",
                        "It also blows up all four of the Serges Tank's cannons at once.",
                    ),
                ),
            ),
        ),
        InfoPage(
            "s_raider", "Keep Raider Killer weak", "Central Computer",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "The scanners in the hallway before Raider Killer upgrade it every time they lock onto you.",
                        "Unscanned it's green and simple. Each scan moves it to blue, red, then purple, with new attacks and more health.",
                        "Dash through and avoid the scanners.",
                    ),
                ),
            ),
        ),
        InfoPage(
            "s_drain", "Magna Centipede's power drain", "Don't get grabbed",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "Each time he pulls you in, you lose an ability for the fight: charging, then multi-shot, then dash, then full jump height.",
                        "Dash away the moment he raises his arm. If caught, mash the D-pad.",
                        "Hitting him with Silk Shot knocks his tail off and stops the drain for good.",
                    ),
                    tone = Tone.WARNING,
                ),
            ),
        ),
        InfoPage(
            "s_mobility", "Weapons as movement tools", "Reach places early",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "Charged Speed Burner launches you horizontally through the air. It's how several hearts and a Sub Tank are reached.",
                        "Crystal Hunter freezes enemies into platforms you can stand on. Dashing into a frozen enemy destroys it.",
                        "Strike Chain grabs walls and pulls you to them.",
                        "Charged Bubble Splash boosts your jump height underwater.",
                        "The Head parts' Item Tracer scans the screen for hidden passages.",
                    ),
                ),
            ),
        ),
        InfoPage(
            "s_misc", "Handy tricks", "Smaller things worth knowing",
            sections = listOf(
                Section(
                    null,
                    listOf(
                        "Items stay collected even if you die right after grabbing them, which makes the spike-risk hearts safe to attempt.",
                        "Sub Tanks fill from health pickups you collect while at full health.",
                        "Beat Wire Sponge with Sonic Slicer for a funny death animation: his upper half pops off.",
                        "With careful jumping you can take the Ride Armor into Crystal Snail's mini-boss room.",
                        "You can drive the Ride Chaser right up to Overdrive Ostrich's door.",
                    ),
                ),
            ),
        ),
    )
}
