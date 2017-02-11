package me.lihq.game;

import me.lihq.game.models.Clue;
import me.lihq.game.models.Hint;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Personality;
import me.lihq.game.people.Player;

/**
 * Created by User on 08/02/2017.
 */
public class Interaction {

    Player player;

    NPC npc;

    public Interaction(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public void question(Clue clue, Personality questioningStyle) {
        if (player.inventory.getCollectedClues().size != 0) {
            if (npc.getPersonality() == questioningStyle && !npc.getExhaustedClues().contains(clue, true)) {
                //// TODO: 08/02/2017 some graphical success text
                player.addToTime(5);                          // Increases the time penalty by 5.
                player.inventory.addNewHint(new Hint(clue));
                npc.addExhaustedClue(clue);
            }
            else {
                //// TODO: 08/02/2017 some graphical fail text
            }
        }
        else {
            //// TODO: 08/02/2017 some graphical text regarding a lack of clues
        }
    }
}
