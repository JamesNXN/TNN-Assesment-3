package me.lihq.game;

import com.badlogic.gdx.utils.Array;

import java.util.Objects;

import me.lihq.game.models.Clue;
import me.lihq.game.models.Hint;
import me.lihq.game.models.Score;
import me.lihq.game.people.Npc;
import me.lihq.game.people.Personality;
import me.lihq.game.people.Player;
import me.lihq.game.people.QuestionStyle;

/**
 * The interaction class holds all the logic for questioning and accuse
 */
public class Interaction {

    private GameWorld gameWorld;
    private Player player;
    private Npc interactingNpc;
    private Score score;

    /**
     * Constructor for interaction object
     */
    public Interaction(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.player = gameWorld.getPlayer();
        score = Score.getInstance();
    }

    public void setInteractingNpc(Npc interactingNpc){
        this.interactingNpc = interactingNpc;
    }

    /**
     * Questioning takes a clue and a questioning style and questions the npc that is being interacted with
     *
     * Then if the player has found a clue the questioning logic starts
     *
     * Success or failure in questioning is determined by the questioning style the player chooses
     * if the questioning style is equal to the npcs personality and the player has not already
     * asked the npc about that clue successfully then the player will be given a hint as to who the clue
     * could point to (more info about hints in hint)
     *
     * if the player questions the npc successfully they gain 50 points and if they question them unsuccessfully
     * then they lose 25 points
     *
     * @param clue - clue to question the npc about
     * @param questioningStyle - questioning style selected by the player
     */
    public void question(Clue clue, QuestionStyle questioningStyle) {
        player.getPersonalityMeter().setMeter(questioningStyle);

        // reject if it was asked before
        if (interactingNpc.getQuestionedClueArray().contains(clue, false)){
            score.subPoints(10);

            gameWorld.getConversationManager().addSpeechBubble(interactingNpc, "I already told you about that");
            gameWorld.getConversationManager().nextSpeechBubble();
            return;
        }

        if (Objects.equals(interactingNpc.getPersonality().toString(), questioningStyle.toString())) {

            score.addPoints(50);     // Successful questioning

            Hint hint = new Hint(clue);
            player.getInventory().addHint(hint);
            interactingNpc.addQuestionedClue(clue);

            gameWorld.getConversationManager().addSpeechBubble(interactingNpc, "I think it's related to " + hint.getRelatedNpcNames() + '.');
            gameWorld.getConversationManager().nextSpeechBubble();

        }
        else {
            score.subPoints(25);      // Failed questioning
            gameWorld.getConversationManager().addSpeechBubble(interactingNpc, interactingNpc.getDialogue().getFailResponseArray().random());
            gameWorld.getConversationManager().nextSpeechBubble();
        }
    }

    /**
     * The accuse method first checks to see if the player has collected 6 clues or more and that 1 of those clues is the weapon clue and 1 is the motive clue
     * if this is true the players accusation becomes plausible and the logic to calculate whether it is a success or not starts
     * if this is false the player will receive a notification telling them that they need to find more evidence
     *
     * first a checking value is initialised to 0 then for every clue in the provided array of clues the player is trying to use
     * to accuse the npc if the clue points to the npc then the checking value is incremented by 1
     *
     * if the checking value is greater than or equal to 4 then it is considered a successful accusation if not
     * it is considered a failure upon a successful accusation the game is considered cleared and the player is awarded 500 points
     *
     * if the accusation was a failure 200 points are lost and it will no longer be possible to question the npc
     *
     * @param clues - an array of clues being used in the accusation
     */
    public void accuse(Array<Clue> clues) {
        if (player.getInventory().getCollectedClues().size >= 6 && player.getInventory().isWeaponFound() && player.getInventory().isMotiveFound()){
            int checkingValue = 0;
            for (Clue cluesCheck : clues) {
                if (cluesCheck.getRelatedNpcIdArray().contains(interactingNpc.getId(), true)) {
                    checkingValue += 1;
                }
            }
            if (checkingValue >= 4) {
                score.addPoints(500);      // Game clear
                gameWorld.getConversationManager().addSpeechBubble(interactingNpc, "Oh no! You got me!");
                gameWorld.getConversationManager().nextSpeechBubble();

                gameWorld.getGui().displayInfo("GAME CLEAR!");
            }
            else if (checkingValue < 4) {
                score.failedAccusation();
                score.subPoints(200);      // Failed accusation
                interactingNpc.setFalseAccused(true);

                gameWorld.getConversationManager().addSpeechBubble(interactingNpc, "They don't prove anything!");
                gameWorld.getConversationManager().nextSpeechBubble();

                gameWorld.getGui().displayInfo("Accuse fail.\nYou cannot question this character anymore.");
            }
        }
        else {
            score.failedAccusation();
            score.subPoints(200);      // Failed accusation
            interactingNpc.setFalseAccused(true);

            gameWorld.getConversationManager().addSpeechBubble(interactingNpc, "They're not enough to prove I'm the murderer!");
            gameWorld.getConversationManager().addAction(() -> {
                gameWorld.getConversationManager().setFinished(true);
                gameWorld.getGui().displayInfo("Accuse fail." +
                        "\nYou cannot question this character anymore.\nFind more clues");
            });
            gameWorld.getConversationManager().nextSpeechBubble();


        }
    }
}
