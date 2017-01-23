package me.lihq.game;

import me.lihq.game.models.Clue;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;
import me.lihq.game.screen.elements.SpeechBox;
import me.lihq.game.screen.elements.SpeechBoxButton;

import java.util.ArrayList;

/**
 * Created by Ben on 23/12/2016.
 */
public class ConversationManagement
{

    /**
     * The player that will be starting the conversation
     */
    private Player player;

    /**
     * The manager for speechboxes, controls the flow of speechboxes
     */
    private SpeechboxManager speechboxMngr;

    /**
     * Stores the current NPC that is being questioned
     */
    private NPC tempNPC;

    /**
     * This stores the position of the clue in the players list for use in the questioning
     */
    private int tempCluePos;

    /**
     * This stores the style of questioning for how the player wants to ask the question
     */
    private AbstractPerson.Personality tempQuestionStyle;

    /**
     * This constructs a converstation manager
     * @param player the player that will initiate the conversation
     * @param speechboxManager the speechbox manager that is in charge of displaying the converstation
     */
    public ConversationManagement(Player player, SpeechboxManager speechboxManager)
    {

        this.player = player;
        this.speechboxMngr = speechboxManager;

    }

    public void startConversation(NPC npc)
    {
        this.tempCluePos = -1;
        this.tempQuestionStyle = null;
        this.tempNPC = npc;

        npc.setDirection(player.getDirection().getOpposite());
        npc.canMove = false;
        player.canMove = false;

        //Introduction
        speechboxMngr.addSpeechBox(new SpeechBox(this.player.getName(), this.player.getSpeech("Introduction"), 5));
        speechboxMngr.addSpeechBox(new SpeechBox(this.tempNPC.getName(), this.tempNPC.getSpeech("Introduction"), 5));


        queryQuestionType();

    }

    /**
     * This constructs the speech box that finds out what question the player wishes to ask the NPC
     */
    private void queryQuestionType()
    {

        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.TYPE, result);

        if (!player.collectedClues.isEmpty()) {
            buttons.add(new SpeechBoxButton("Question?", 0, eventHandler));
        }
        if (player.collectedClues.size() > 3) {
            buttons.add(new SpeechBoxButton("Accuse?", 1, eventHandler));
        }
        if (buttons.size() > 0 ) {
            speechboxMngr.addSpeechBox(new SpeechBox("What do you want to do?", buttons, -1));
        } else {
            speechboxMngr.addSpeechBox(new SpeechBox("You need to find some clues before you question a suspect", 5));
            finishConverstation();
        }


    }


    /**
     * This constructs the speechbox that asks the player how they wish to ask the question
     */
    private void queryQuestionStyle()
    {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> handleResponse(QuestionStage.STYLE, result);

        buttons.add(new SpeechBoxButton("Nicely", 0, eventHandler));
        buttons.add(new SpeechBoxButton("Neutrally", 1, eventHandler));
        buttons.add(new SpeechBoxButton("Aggressively", 2, eventHandler));
        speechboxMngr.addSpeechBox(new SpeechBox("How do you want to ask the question?", buttons, -1));

    }

    /**
     * This constructs the speechbox that asks the player what clue they wish to ask about
     */
    private void queryWhichClue()
    {
        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (result) -> {
            handleResponse(QuestionStage.CLUE, result);
        };


        int i = 0;
        for (Clue c : this.player.collectedClues) {
            buttons.add(new SpeechBoxButton(c.getName(), i, eventHandler));
            i++;
        }

        speechboxMngr.addSpeechBox(new SpeechBox("What clue do you want to ask about?", buttons, -1));
    }

    private void questionNPC()
    {
        speechboxMngr.addSpeechBox(new SpeechBox(player.getName(), player.getSpeech(player.collectedClues.get(tempCluePos), tempQuestionStyle), 3));
        speechboxMngr.addSpeechBox(new SpeechBox(tempNPC.getName(), tempNPC.getSpeech(player.collectedClues.get(tempCluePos), tempQuestionStyle), 3));
        finishConverstation();
    }

    private void accuseNPC() {
        if (this.tempNPC.isKiller()) {
            speechboxMngr.addSpeechBox(new SpeechBox("You found the killer well done", -1));
            finishConverstation();
        } else {
            speechboxMngr.addSpeechBox(new SpeechBox("They are clearly not the killer, just look at them.", 5));
            finishConverstation();
        }
    }
    private void finishConverstation() {
        this.tempNPC.canMove = true;
        this.player.canMove = true;
    }
    private void handleResponse(QuestionStage stage, int option)
    {
        speechboxMngr.rmCurrentSpeechBox();

        switch (stage) {
            case TYPE:
                if (option == 0) {
                    queryQuestionStyle();
                } else if (option == 1) {
                    accuseNPC();
                }
                break;

            case STYLE:
                this.tempQuestionStyle = convertToQuestionStyle(option);
                queryWhichClue();
                break;

            case CLUE:
                this.tempCluePos = option;
                questionNPC();
                break;
        }

    }


    /**
     * Takes an int and returns a personality style
     *
     * @param style 0 = Nice
     *              1 = Neutral
     *              2 = AGGRESSIVE
     *              default is Neutral
     * @return
     */
    private AbstractPerson.Personality convertToQuestionStyle(int style)
    {
        switch (style) {
            case 0:
                return AbstractPerson.Personality.NICE;

            case 1:
                return AbstractPerson.Personality.NEUTRAL;

            case 2:
                return AbstractPerson.Personality.AGGRESSIVE;

        }
        //defaults to Neutral
        return AbstractPerson.Personality.NEUTRAL;
    }

    public enum QuestionStage
    {

        /**
         * This stage indicates that the player has been asked what type of question they have asked
         * e.g. question or accuse
         */
        TYPE,

        /**
         * Thus stage means that the player has been asked the how they want to ask the question
         * e.g. nice, neutral or harsh
         */
        STYLE,

        /**
         * This stage indicates that the player has been asked what clue they want to ask about.
         */
        CLUE


    }

}
