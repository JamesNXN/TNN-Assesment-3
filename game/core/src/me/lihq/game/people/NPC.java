package me.lihq.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import me.lihq.game.GameMain;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class which is responsible for the non-playable characters within the game that the player will meet.
 */
public class NPC extends AbstractPerson
{

    //These variables are specific to the NPC only

    /**
     * Associated clues
     */
    public Array<Clue> associatedClues = new Array<>();

    /**
     * The motive string details why the NPC committed the murder.
     */
    private String motive = "";

    private boolean isKiller = false;
    private boolean isVictim = false;

    /**
     * Used in questioning for more info check Interaction class
     */
    private Array<Clue> exhaustedClues = new Array<>(0);

    private Personality personality;

    /**
     * Define an NPC with location coordinates , room, spritesheet and whether or not they can be the killer
     *
     * @param spriteSheet - Sprite sheet for this NPC
     */
    public NPC(String name, TextureAtlas spriteSheet, String jsonFile)
    {
        super(name, spriteSheet);

        importDialogue(jsonFile);
    }

    @Override
    public void importDialogue(String fileName)
    {
        jsonData = new JsonReader().parse(Gdx.files.internal("people/NPCs/" + fileName));
        this.personality = Personality.valueOf(jsonData.getString("personality"));
    }

    public boolean isKiller()
    {
        return isKiller;
    }

    public boolean isVictim()
    {
        return isVictim;
    }

    public String getMotive()
    {
        return motive;
    }

    public NPC setMotive(String motive)
    {
        this.motive = motive;
        return this;
    }

    public boolean setKiller()
    {
        if (isVictim()) return false;

        isKiller = true;
        System.out.println(getName() + " is the killer");
        return true;
    }

    public boolean setVictim()
    {
        if (isKiller()) return false;

        isVictim = true;
        System.out.println(getName() + " is the victim");
        return true;
    }


    /**
     * This handles speech for a clue that has a question style
     *
     * @param clue  the clue to be questioned about
     * @param style the style of questioning
     * @return (String) the speech
     */
    @Override
    public String getSpeech(Clue clue, Personality style)
    {
        if (style == this.personality) {
            return getSpeech(clue);
        } else {
            return getSpeech("");
        }
    }

    /**
     * This method returns the NPCs personality
     *
     * @return (Personality) the NPCs personality {@link me.lihq.game.people.Personality}
     */
    @Override
    public Personality getPersonality()
    {
        return this.personality;
    }

    public Array<Clue> getExhaustedClues() {return this.exhaustedClues;}

    public void addExhaustedClue(Clue clue) {
        this.exhaustedClues.ensureCapacity(1);
        this.exhaustedClues.add(clue);
    }


    @Override
    public String toString() {
        return this.getName();
    }
}
