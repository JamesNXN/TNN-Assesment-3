package me.lihq.game.people;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.models.Clue;

/**
 * EXTENDED
 * The class which is responsible for the non-playable characters within the game that the player will meet.
 */
public class Npc extends AbstractPerson {
    /**
     * Parameters needed by Npc object:
     *
     * dialogue - contains the npc's dialogue information
     * isKiller - boolean value that defines whether or not the npc is the murderer
     * isVictim - boolean value that defines whether or not the npc is the victim
     * isFalseAccused - boolean value that defines whether the npc has been falsely accused by the player
     * questionedClueArray - an array of clues containing the clues the player has successfully questioned the npc with
     * personality - contains the personality of the npc
     * randomTimeLimit - the amount of time that the npc will either stand still or walk for
     * randomTimeSum - the amount of time that the npc has spent either stood still or walking
     * randomMoveToggle - a boolean that contains whether the npc will randomly walk around or stand still is toggled randomly
     */
    private NpcDialogue dialogue;

    private boolean isKiller = false;
    private boolean isVictim = false;
    private boolean isFalseAccused = false;

    private Array<Clue> questionedClueArray = new Array<>();

    private Personality personality;

    private float randomTimeLimit = 0;

    private float randomTimeSum = 0;

    private boolean randomMoveToggle = false;

    /**
     * Constructor used to create Npc Objects
     *
     * @param jsonData - jsonData required to create the object
     * @param spriteSheet - textures for the object
     */
    public Npc(JsonValue jsonData, TextureAtlas spriteSheet)
    {
        super(jsonData, spriteSheet);

        dialogue = new NpcDialogue(this);

        Json json = new Json();
        this.personality = json.readValue("personality", Personality.class, jsonData);
    }

    /**
     * Act method needed to render the npc and move him randomly
     */
    @Override
    public void act(float delta) {
        if (isCanMove()) {
            randomTimeSum += delta;
            if (randomTimeLimit <= randomTimeSum) {
                randomTimeSum = 0;

                if (randomMoveToggle) {
                    setState(PersonState.WALKING);
                    setDirection(getRandomDirection());

                    randomTimeLimit = MathUtils.random(0.5f, 1.5f);
                } else {
                    setState(PersonState.STANDING);

                    randomTimeLimit = MathUtils.random(5, 10);
                }
                randomMoveToggle = !randomMoveToggle;
            }
        }
        super.act(delta);
    }

    /**
     * Getters and setters needed.
     */
    private Direction getRandomDirection(){
        int randomInt = MathUtils.random(3);
        switch (randomInt){
            case 0:
                return Direction.EAST;
            case 1:
                return Direction.WEST;
            case 2:
                return Direction.NORTH;
            case 3:
                return Direction.SOUTH;
        }
        return null;
    }

    public boolean isKiller()
    {
        return isKiller;
    }

    public boolean isVictim()
    {
        return isVictim;
    }

    public void setMurderer(boolean killer)
    {
        isKiller = killer;
        System.out.println(getName() + " is the killer");
    }

    public void setVictim(boolean victim)
    {
        isVictim = victim;
        System.out.println(getName() + " is the victim");
    }

    @Override
    public NpcDialogue getDialogue() {
        return dialogue;
    }

    @Override
    public Personality getPersonality()
    {
        return this.personality;
    }

    public Array<Clue> getQuestionedClueArray() {return this.questionedClueArray;}

    public void addQuestionedClue(Clue clue) {
        this.questionedClueArray.add(clue);
    }

    public void setFalseAccused(boolean falseAccused) {
        isFalseAccused = falseAccused;
    }

    public boolean isFalseAccused() { return isFalseAccused;}

    /**
     * Method that will return the object as a string
     * @return returns the name of the npc
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
