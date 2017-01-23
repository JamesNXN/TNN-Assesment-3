package me.lihq.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
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
    public List<Clue> associatedClues = new ArrayList<>();
    private Random random;

    /**
     * The motive string details why the NPC committed the murder.
     */
    private String motive = "";

    /**
     * These two booleans decide whether an NPC has the potential to be a killer and if, in this particular game, they
     * are the killer.
     */
    private boolean canBeKiller = false;
    private boolean isKiller = false;
    private boolean isVictim = false;

    /**
     * This stores the players personality {@link me.lihq.game.people.AbstractPerson.Personality}
     */
    private Personality personality;

    /**
     * Define an NPC with location coordinates , room, spritesheet and whether or not they can be the killer
     *
     * @param tileX       - x coordinate of tile that the NPC will be initially rendered on.
     * @param tileY       - y coordinate of tile that the NPC will be initially rendered on.
     * @param room        - ID of room they are in
     * @param spriteSheet - Spritesheet for this NPC
     * @param canBeKiller - Boolean whether they can or cannot be the killer
     */
    public NPC(String name, String spriteSheet, int tileX, int tileY, Room room, boolean canBeKiller, String jsonFile)
    {
        super(name, "people/NPCs/" + spriteSheet, tileX, tileY);
        this.setRoom(room);
        this.random = new Random();
        this.canBeKiller = canBeKiller;

        importDialogue(jsonFile);
    }

    /**
     * This method is called once a game tick to randomise movement.
     */
    @Override
    public void update()
    {
        super.update();
        this.randomMove();
    }

    /**
     * Reads in the JSON file of tha character and stores dialogue in the dialogue HashMap
     *
     * @param fileName - The filename to read from
     */
    @Override
    public void importDialogue(String fileName)
    {
        jsonData = new JsonReader().parse(Gdx.files.internal("people/NPCs/" + fileName));
        this.personality = Personality.valueOf(jsonData.getString("personality"));
    }

    /**
     * Allow the NPC to move around their room.
     *
     * @param dir the direction person should move in
     */
    public void move(Direction dir)
    {
        if (this.state != PersonState.STANDING) {
            return;
        }

        if (!canMove) return;

        if (!getRoom().isWalkableTile(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy())) {
            setDirection(dir);
            return;
        }

        initialiseMove(dir);
    }

    /**
     * This method attempts to move the NPC in a random direction
     */
    private void randomMove()
    {
        if (getState() == PersonState.WALKING) return;

        if (random.nextDouble() > 0.01) {
            return;
        }

        Direction dir;

        Double dirRand = random.nextDouble();
        if (dirRand < 0.5) {
            dir = this.direction;
        } else if (dirRand < 0.62) {
            dir = Direction.NORTH;
        } else if (dirRand < 0.74) {
            dir = Direction.SOUTH;
        } else if (dirRand < 0.86) {
            dir = Direction.EAST;
        } else {
            dir = Direction.WEST;
        }

        move(dir);
    }


    /**
     * Getter for canBeKiller
     *
     * @return (boolean) Returns value of canBeKiller for this object. {@link #canBeKiller}
     */
    public boolean canBeKiller()
    {
        return canBeKiller;
    }

    /**
     * Getter for isKiller.
     *
     * @return (boolean) Return a value of isKiller for this object. {@link #isKiller}
     */
    public boolean isKiller()
    {
        return isKiller;
    }

    /**
     * Getter for isVictim
     *
     * @return (boolean) Returns the value of isVictim for this object {@link #isVictim}
     */
    public boolean isVictim()
    {
        return isVictim;
    }

    /**
     * Getter for motive.
     *
     * @return (String) Returns the motive string for this object. {@link #motive}
     */
    public String getMotive()
    {
        return motive;
    }

    /**
     * Setter for the NPC's motive string.
     *
     * @param motive - The motive this particular NPC has for committing the murder.
     * @return (NPC) Returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setMotive(String motive)
    {
        this.motive = motive;
        return this;
    }

    /**
     * This method sets the NPC as the killer for this game.
     * <p>
     * It first checks they aren't the victim and if they can be the killer
     *
     * @return (boolean) Returns whether it successfully set the NPC to the killer or not
     */
    public boolean setKiller()
    {
        if (isVictim() || !canBeKiller) return false;

        isKiller = true;
        System.out.println(getName() + " is the killer");
        return true;
    }

    /**
     * This method sets the NPC to be the victim for the game
     * <p>
     * It first checks if the NPC isn't also the killer
     *
     * @return (boolean) Returns whether it successfully set the NPC to the victim or not
     */
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
     * @return (Personality) the NPCs personality {@link me.lihq.game.people.AbstractPerson.Personality}
     */
    @Override
    public Personality getPersonality()
    {
        return this.personality;
    }
}
