package me.lihq.game.people;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.models.Clue;

/**
 * The class which is responsible for the non-playable characters within the game that the player will meet.
 */
public class NPC extends AbstractPerson
{
    /**
     * The motive string details why the NPC committed the murder.
     */
    private String motive;

    private boolean isKiller = false;
    private boolean isVictim = false;

    private int id;

    private boolean falselyAccused = false;

    /**
     * Used in questioning for more info check Interaction class
     */
    private Array<Clue> exhaustedClues = new Array<>(0);

    private Personality personality;

    /**
     * Define an NPC
     *
     * @param spriteSheet - Sprite sheet for this NPC
     */
    public NPC(JsonValue jsonData, TextureAtlas spriteSheet)
    {
        super(jsonData, spriteSheet);
        this.id = jsonData.getInt("id");
    }


    public NPC(){} //// TEST CONSTRUCTOR

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

    public void setKiller(boolean killer)
    {
        isKiller = killer;
        System.out.println(getName() + " is the killer");
    }

    public void setVictim(boolean victim)
    {
        isVictim = victim;
        System.out.println(getName() + " is the victim");
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

    public int getId() { return this.id;}

    public void setFalselyAccused() {
        this.falselyAccused = true;
    }

    public boolean getFalselyAccused() { return this.falselyAccused;}
}
