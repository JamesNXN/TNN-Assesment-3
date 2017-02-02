package me.lihq.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.JsonReader;
import me.lihq.game.GameMain;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the player that the person playing the game will be represented by.
 */
public class Player extends AbstractPerson
{
    /**
     * This object stores the clues that the player has picked up
     */
    public List<Clue> collectedClues = new ArrayList<>();
    /**
     * This stores whether the player is in the middle of a conversation or not
     */
    public boolean inConversation = false;
    /**
     * The personality will be a percent score (0-100) 0 being angry, 50 being neutral, and 100 being happy/nice.
     */
    private int personalityLevel = 50;
    /**
     * The score the player has earned so far.
     */
    private int score = 0;

    /**
     * This is the constructor for player, it creates a new playable person
     *
     * @param name   - The name for the new player.
     * @param spriteSheet - The image used to represent it.
     */
    public Player(String name, TextureAtlas spriteSheet) {
        super(name, spriteSheet);
        importDialogue("Player.JSON");
    }

    /**
     * Reads in the JSON file of tha character and stores dialogue in the dialogue HashMap
     *
     * @param fileName - The file to read from
     */
    @Override
    public void importDialogue(String fileName)
    {
        jsonData = new JsonReader().parse(Gdx.files.internal("people/player/" + fileName));
    }

    /**
     * This method will change the players personality by the given amount.
     * It will cap the personality between 0 and 100.
     * <p>
     * If the change takes it out of these bounds, it will change it to the min or max.
     *
     * @param change - The amount to change by, can be positive or negative
     */
    public void addToPersonality(int change)
    {
        personalityLevel = personalityLevel + change;

        if (personalityLevel < 0) {
            personalityLevel = 0;
        } else if (personalityLevel > 100) {
            personalityLevel = 100;
        }
    }

    /**
     * This method is called when the player interacts with the map
     */
    public void interact()
    {
        if (inConversation) return;

        NPC npc = getFacingNPC();
        if (npc != null) {
            GameMain.instance.getNavigationScreen().convMngt.startConversation(npc);
        } else {
            checkForClue();
        }
    }

    /**
     * This method tries to get an NPC if the player is facing one
     *
     * @return (NPC) returns null if there isn't an NPC in front of them or the NPC is moving. Otherwise, it returns the NPC
     */
    private NPC getFacingNPC()
    {
        for (NPC npc : GameMain.instance.getNPCs(getRoom())) {
            if ((npc.getTilePosition().x == getTilePosition().x + getDirection().getDx()) && (npc.getTilePosition().y == getTilePosition().y + getDirection().getDy())) {
                if (npc.getState() != PersonState.STANDING) return null;

                return npc;
            }
        }

        return null;
    }

    /**
     * This method checks to see if the tile the player is facing has a clue hidden in it or not
     */
    private void checkForClue()
    {
        int x = getTilePosition().x + getDirection().getDx();
        int y = getTilePosition().y + getDirection().getDy();


        if (!this.getRoom().isHidingPlace(x, y)) {
            return;
        }

        Clue clueFound = getRoom().getClue(x, y);
        if (clueFound != null) {
            GameMain.instance.getNavigationScreen().speechboxMngr.addSpeechBox(new SpeechBox("You found: " + clueFound.getDescription(), 6));
            this.collectedClues.add(clueFound);
        } else {
            GameMain.instance.getNavigationScreen().speechboxMngr.addSpeechBox(new SpeechBox("Sorry no clue here", 1));
        }
    }

    /**
     * This method returns whether or not the player is standing on a tile that initiates a Transition to another room
     *
     * @return (boolean) Whether the player is on a trigger tile or not
     */
    public boolean isOnTriggerTile()
    {
        return this.getRoom().isTriggerTile(this.tilePosition.x, this.tilePosition.y);
    }

    /**
     * Getter for personality, it uses the personalityLevel of the player and thus returns either AGGRESSIVE, NEUTRAL or NICE
     *
     * @return - (Personality) Returns the personality of this player.
     */
    @Override
    public Personality getPersonality()
    {
        if (personalityLevel < 33) {
            return Personality.AGGRESSIVE;

        } else if (personalityLevel < 66) {
            return Personality.NEUTRAL;

        } else if (personalityLevel <= 100) {
            return Personality.NICE;
        }
        return Personality.NEUTRAL;
    }

    /**
     * This gets the players personality level; this similar to Personality but a integer representation
     *
     * @return (int) value between 0-100
     */
    public int getPersonalityLevel()
    {
        return this.personalityLevel;
    }


    /**
     * This method gets the speech based on what clue it is and the selected personality
     *
     * @param clue  the clue to be questioned about
     * @param style the style of questioning
     * @return (String) - The speech to add to the SpeechBox
     */
    @Override
    public String getSpeech(Clue clue, Personality style)
    {
        String key = clue.getName();
        if (!jsonData.get("Responses").has(key)) {
            return jsonData.get("noneResponses").getString(0);
        } else {
            return jsonData.get("Responses").get(key).getString(style.toString());
        }
    }


}
