package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Queue;

import me.lihq.game.gui.speechbubbles.ConversationSpeechBubble;
import me.lihq.game.gui.speechbubbles.SpeechBubble;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Npc;

/**
 * NEW
 * A manager class that takes care of conversation flow between a player and a npc. It uses a queue
 * to load up speech bubble or action to be executed in FIFO order.
 */

public class ConversationManager{
    private Stage stage;
    private Skin skin;

    /** the queue that takes either a speech bubble or a runnable action in order.*/
    private Queue<Object> conversationQueue;

    private Npc interactingCharacter;

    private Object currentConversation;
    private boolean isFinished = false;

    ConversationManager(Skin skin){
        this.skin = skin;
        conversationQueue = new Queue<>();
    }

    /**
     * Takes a speech bubble and put it in a queue
     * @param speechBubble speech bubble to be put in a queue
     */
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

    /**
     * Terminates ongoing conversation and clear the conversation queue.
     */
    public void clear(){
        if (currentConversation != null && currentConversation instanceof SpeechBubble) {
            ((SpeechBubble)currentConversation).hide();
        }
        conversationQueue.clear();
        interactingCharacter = null;
        stage = null;
    }

    /**
     * Initiate the conversation with the queue that has been loaded.
     * @param stage stage that the speech bubbles will be added to and shown
     */
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

    /**
     * Move on to the next dialogue in the queue. If the queue is empty, terminate the conversation.
     */
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
