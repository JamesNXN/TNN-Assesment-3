package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.ConversationManager;
import me.lihq.game.people.Npc;
import me.lihq.game.people.Player;

/**
 * window for getting interaction option from the player. The options are question, accuse and ignore.
 */
public class InteractionSelectionBubble extends SpeechBubble{

    public InteractionSelectionBubble(Player player, Skin skin, ConversationManager conversationManager, Gui gui) {
        super(player, skin);

        TextButton questionButton = new TextButton("Question", skin, "buttonBubble");
        questionButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //interaction halts when there is no clue in the inventory
                if (player.getInventory().getCollectedClues().size == 0){
                    gui.displayInfo("You don't have any clues!");
                    conversationManager.setFinished(true);
                }

                //if the npc is falsely accused, he/she will refuse to be questioned
                else if (conversationManager.getInteractingCharacter().isFalseAccused()){
                    Npc interactingCharacter = conversationManager.getInteractingCharacter();

                    //load fail response speech to conversation manager
                    SpeechBubble speechBubble = new ConversationSpeechBubble(interactingCharacter,
                            interactingCharacter.getDialogue().getFailResponseArray().random(), skin);
                    conversationManager.addSpeechBubble(speechBubble);
                    conversationManager.nextSpeechBubble();
                }

                //otherwise open question window
                else {
                    conversationManager.addSpeechBubble(conversationManager.getInteractingCharacter(), "What do you want to know?");
                    conversationManager.addAction(() -> gui.getClueSelectionWindow().show(gui.getGuiStage()));
                    conversationManager.nextSpeechBubble();
                }
            }
        });
        addButton(questionButton);

        TextButton accuseButton = new TextButton("Accuse", skin, "buttonBubble");
        accuseButton.addListener(new ChangeListener() {
            /*
            checks to see if the player has collected 6 clues or more and that 1 of those clues is the weapon clue and 1 is the motive clue
            if this is true the players accusation becomes possible
            */
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //interaction halts when there is no clue in the inventory
                if (player.getInventory().getCollectedClues().size == 0){
                    gui.displayInfo("You don't have any clues!");
                    conversationManager.setFinished(true);
                }

                //player cannot initiate accuse unless they found more than 6 clues which include weapon and motive clues.
                if (player.getInventory().getCollectedClues().size >= 6) {
                    //initiate accuse when both weapon and motive clue is found
                    if (player.getInventory().isWeaponFound() && player.getInventory().isMotiveFound()) {
                        conversationManager.addSpeechBubble(player, "You're the murderer!");
                        conversationManager.addSpeechBubble(conversationManager.getInteractingCharacter(), "Nonsense! What proof do you have?");
                        conversationManager.addAction(() -> gui.getAccuseWindow().show(gui.getGuiStage()));
                        conversationManager.nextSpeechBubble();
                    }
                    //when the player had found only weapon clue
                    else if (player.getInventory().isWeaponFound() && !player.getInventory().isMotiveFound()){
                        conversationManager.addSpeechBubble(player, "I need to know the motive behind the murder before I can accuse someone");
                        conversationManager.nextSpeechBubble();
                    }
                    //when the player had found only motive clue
                    else if (!player.getInventory().isWeaponFound() && player.getInventory().isMotiveFound()){
                        conversationManager.addSpeechBubble(player, "I need to know what the murder weapon is before I can accuse someone");
                        conversationManager.nextSpeechBubble();
                    }
                    //when the player had not found both weapon and motive clues
                    else{
                        conversationManager.addSpeechBubble(player, "I need to know about both the weapon and motive");
                        conversationManager.nextSpeechBubble();
                    }
                }
                else{
                    conversationManager.addSpeechBubble(player, "I need more clues to accuse someone.");
                    conversationManager.nextSpeechBubble();
                }
            }
        });
        addButton(accuseButton);

        TextButton ignoreButton = new TextButton("Ignore", skin, "buttonBubble");
        ignoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 conversationManager.setFinished(true);
            }
        });
        addButton(ignoreButton);

    }

    private void addButton(TextButton button){
        getContentTable().add(button).row();
    }
}
