package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;

import me.lihq.game.gui.ConversationSpeechBubble;
import me.lihq.game.gui.SpeechBubble;
import me.lihq.game.models.Clue;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Npc;

public class ConversationManager{
    private Stage stage;
    private Skin skin;
    private Queue<SpeechBubble> speechBubbleQueue;
    private Npc interactingCharacter;

    private SpeechBubble currentSpeech;
    private boolean isFinished = false;

    public ConversationManager(Skin skin){
        this.skin = skin;
        speechBubbleQueue = new Queue<>();
    }

    public void addSpeechBubble(SpeechBubble speechBubble){
        speechBubbleQueue.addLast(speechBubble);

        if (speechBubble instanceof ConversationSpeechBubble) {
            AbstractPerson speakingPerson = speechBubble.getSpeakingPerson();
            if (speakingPerson instanceof Npc) {
                interactingCharacter = (Npc) speakingPerson;
            }
        }
    }

    public void addSpeechBubble(AbstractPerson speakingPerson, String dialogueLine) {
        speechBubbleQueue.addLast(new ConversationSpeechBubble(speakingPerson, dialogueLine, skin));

        if (speakingPerson instanceof Npc) {
            interactingCharacter = (Npc) speakingPerson;
        }
    }

    public void clear(){
        if (currentSpeech != null) {
            currentSpeech.hide();
        }
        speechBubbleQueue.clear();
        interactingCharacter = null;
        stage = null;
    }

    public void startConversation(Stage stage){
        this.stage = stage;

        currentSpeech = speechBubbleQueue.removeFirst();
        currentSpeech.show(stage);
    }

    public void nextSpeechBubble(){
        currentSpeech.hide();
        if (speechBubbleQueue.size != 0) {
            currentSpeech = speechBubbleQueue.removeFirst();
            currentSpeech.show(stage);
        } else {
            isFinished = true;
        }
    }

    public void question(Clue clue){

    }

    public boolean isFinished(){
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Npc getInteractingCharacter() {
        return interactingCharacter;
    }

    public SpeechBubble getCurrentSpeech() {
        return currentSpeech;
    }
}
