package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;

import me.lihq.game.gui.ConversationSpeechBubble;
import me.lihq.game.gui.SpeechBubble;
import me.lihq.game.people.AbstractPerson;

public class ConversationManager{
    private Stage stage;
    private Skin skin;
    private Queue<SpeechBubble> speechBubbleQueue;
    private Array<AbstractPerson> interactingCharacterArray;

    private SpeechBubble currentSpeech;
    private boolean isFinished = false;

    public ConversationManager(Skin skin){
        this.skin = skin;
        speechBubbleQueue = new Queue<>();
        interactingCharacterArray = new Array<>();
    }

    public void addSpeechBubble(SpeechBubble speechBubble){
        speechBubbleQueue.addLast(speechBubble);

        if (speechBubble instanceof ConversationSpeechBubble) {
            AbstractPerson speakingPerson = speechBubble.getSpeakingPerson();
            if (!interactingCharacterArray.contains(speakingPerson, false)) {
                interactingCharacterArray.add(speakingPerson);
            }
        }
    }

    public void addSpeechBubble(AbstractPerson speakingPerson, String dialogueLine) {
        speechBubbleQueue.addLast(new ConversationSpeechBubble(speakingPerson, dialogueLine, skin));

        if (!interactingCharacterArray.contains(speakingPerson, false)) {
            interactingCharacterArray.add(speakingPerson);
        }
    }

    public void clear(){
        if (currentSpeech != null) {
            currentSpeech.hide();
        }
        speechBubbleQueue.clear();
        interactingCharacterArray.clear();
        stage = null;
    }

    public void startConversation(Stage stage){
        this.stage = stage;

        currentSpeech = speechBubbleQueue.removeFirst();
        currentSpeech.show(stage);
    }

    public void nextSpeech(){
        //wait for button click from the player if the current speech bubble is a button speech bubble
        if (currentSpeech instanceof ConversationSpeechBubble) {
            currentSpeech.hide();
            if (speechBubbleQueue.size != 0) {
                currentSpeech = speechBubbleQueue.removeFirst();
                currentSpeech.show(stage);
            } else {
                isFinished = true;
            }
        }
    }

    public void question(){

    }

    public boolean isFinished(){
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Array<AbstractPerson> getInteractingCharacterArray() {
        return interactingCharacterArray;
    }
}
