package me.lihq.game;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Hint;
import me.lihq.game.models.Score;
import me.lihq.game.people.Npc;
import me.lihq.game.people.Personality;
import me.lihq.game.people.Player;

public class Interaction {

    private Player player;
    private Npc npc;
    private Score score;

    public Interaction(Player player, Npc npc) {
        this.player = player;
        this.npc = npc;
        score = Score.getInstance();
    }

    public void question(Clue clue, Personality questioningStyle) {
        if (!npc.isFalseAccused()) {
            if (player.getInventory().getCollectedClues().size != 0) {
                if (npc.getPersonality() == questioningStyle && !npc.getExhaustedClues().contains(clue, true)) {
                    //// TODO: 08/02/2017 some graphical success text
                    score.addPoints(50);     // Successful questioning
                    player.getInventory().addHint(new Hint(clue));
                    npc.addExhaustedClue(clue);
                } else {
                    score.subPoints(25);      // Failed questioning
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
        if (clues.size < 4 && player.getInventory().isWeaponFound()){
            int checkingValue = 0;
            for (Clue cluesCheck : clues) {
                if (cluesCheck.getRelatedNpcIdArray().contains(npc.getId(), true)) {
                    checkingValue += 1;
                }
            }
            if (checkingValue >= 4) {
                score.addPoints(500);      // Game clear
                //todo accuse success
            }
            else if (checkingValue < 4) {
                score.failedAccusation();
                score.subPoints(200);      // Failed accusation
                npc.setFalseAccused(true);
                //todo some accuse fail stuff
            }
        }
        else {
            //todo add some graphical stuff telling player they cant accuse until have met minimum clue requirements
        }
    }
}
