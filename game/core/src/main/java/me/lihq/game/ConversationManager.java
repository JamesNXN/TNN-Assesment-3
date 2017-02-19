package me.lihq.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;

import me.lihq.game.gui.Gui;
import me.lihq.game.gui.SpeechBubble;
import me.lihq.game.people.AbstractPerson;

public class ConversationManager extends InputAdapter{
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

    public void addSpeech(AbstractPerson speakingPerson, String speechLine){
        speechBubbleQueue.addLast(new SpeechBubble(speakingPerson, speechLine, skin));
        if (!interactingCharacterArray.contains(speakingPerson, false)) {
            interactingCharacterArray.add(speakingPerson);
        }
    }

    public void clearConversation(){
        speechBubbleQueue.clear();
        interactingCharacterArray.clear();
        stage = null;
    }

    public void startConversation(Stage stage){
        this.stage = stage;

        currentSpeech = speechBubbleQueue.removeFirst();
        currentSpeech.show(stage);
    }

    private void nextSpeech(){
        currentSpeech.hide();
        if (speechBubbleQueue.size != 0) {
            startConversation(stage);
        }
        else{
            isFinished = true;
        }
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        nextSpeech();
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE){
            nextSpeech();
        }
        return true;
    }
}
