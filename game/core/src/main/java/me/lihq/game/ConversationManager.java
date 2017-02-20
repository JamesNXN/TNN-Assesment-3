package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Queue;

import me.lihq.game.gui.ConversationSpeechBubble;
import me.lihq.game.gui.SpeechBubble;
import me.lihq.game.models.Clue;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Npc;

public class ConversationManager{
    private Stage stage;
    private Skin skin;
    private Queue<Object> conversationQueue;
    private Npc interactingCharacter;

    private Object currentConversation;
    private boolean isFinished = false;

    public ConversationManager(Skin skin){
        this.skin = skin;
        conversationQueue = new Queue<>();
    }

    public void addSpeechBubble(SpeechBubble speechBubble){
        conversationQueue.addLast(speechBubble);

        if (speechBubble instanceof ConversationSpeechBubble) {
            AbstractPerson speakingPerson = speechBubble.getSpeakingPerson();
            if (speakingPerson instanceof Npc) {
                interactingCharacter = (Npc) speakingPerson;
            }
        }
    }

    public void addSpeechBubble(AbstractPerson speakingPerson, String dialogueLine) {
        conversationQueue.addLast(new ConversationSpeechBubble(speakingPerson, dialogueLine, skin));

        if (speakingPerson instanceof Npc) {
            interactingCharacter = (Npc) speakingPerson;
        }
    }

    public void addAction(Runnable action){
        conversationQueue.addLast(action);
    }

    public void clear(){
        if (currentConversation != null && currentConversation instanceof SpeechBubble) {
            ((SpeechBubble)currentConversation).hide();
        }
        conversationQueue.clear();
        interactingCharacter = null;
        stage = null;
    }

    public void startConversation(Stage stage){
        this.stage = stage;

        currentConversation = conversationQueue.removeFirst();
        if (currentConversation instanceof SpeechBubble) {
            ((SpeechBubble) currentConversation).show(stage);
        }
        else{
            ((RunnableAction)currentConversation).getRunnable().run();
        }
    }

    public void nextSpeechBubble(){
        if (conversationQueue.size == 0){
            isFinished = true;
            return;
        }

        //if current conversation is a speech then hide the speech bubble
        if (currentConversation instanceof SpeechBubble) {
            ((SpeechBubble)currentConversation).hide();
        }

        Object nextConversation = conversationQueue.removeFirst();
        if (nextConversation instanceof SpeechBubble) {
            ((SpeechBubble)nextConversation).show(stage);
        }
        else{
            ((Runnable)nextConversation).run();
        }
        currentConversation = nextConversation;
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

    public Object getCurrentConversation() {
        return currentConversation;
    }
}
