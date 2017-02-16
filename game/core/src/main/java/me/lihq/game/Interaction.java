package me.lihq.game;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.models.Clue;
import me.lihq.game.models.ClueType;
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
        if (npc.getFalselyAccused() == false) {
            if (player.inventory.getCollectedClues().size != 0) {
                if (npc.getPersonality() == questioningStyle && !npc.getExhaustedClues().contains(clue, true)) {
                    //// TODO: 08/02/2017 some graphical success text
                    player.score.addPoints(50);     // Successful questioning
                    player.inventory.addNewHint(new Hint(clue));
                    npc.addExhaustedClue(clue);
                } else {
                    player.score.subPoints(25);      // Failed questioning
                    //// TODO: 08/02/2017 some graphical fail text
                }
            } else {
                //// TODO: 08/02/2017 some graphical text regarding a lack of clues
            }
        } else {
            //todo some graphical text telling player to go away
        }
    }

    public void accuse(Array<Clue> clues) {
        if (clues.size < 4 || clues.first().getClueType() == ClueType.MOTIVE){
            int checkingValue = 0;
            for (Clue cluesCheck : clues) {
                if (cluesCheck.getRelatedNpcIdArray().contains(npc.getId(), true)) {
                    checkingValue += 1;
                }
            }
            if (checkingValue >= 4) {
                player.score.addPoints(500);      // Game clear
                //todo accuse success
            }
            else if (checkingValue < 4) {
                player.score.failedAccusation();
                player.score.subPoints(200);      // Failed accusation
                npc.setFalselyAccused();
                //todo some accuse fail stuff
            }
        }
        else {
            //todo add some graphical stuff telling player they cant accuse until have met minimum clue requirements
        }
    }
}
