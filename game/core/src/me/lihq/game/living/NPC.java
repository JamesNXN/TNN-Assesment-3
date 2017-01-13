package me.lihq.game.living;
import me.lihq.game.models.Clue;

import java.util.ArrayList;
import java.util.List;

/**
 * The class which is responsible for the non-playable characters within the game that the player will meet.
 */
public class NPC extends AbstractPerson
{

    //These variables are specific to the NPC only
    /**
     * The roomID specifies which room the NPC will be in during the game.
     */
    private int roomID = -1;

    /**

    /**
     * The motive string details why the NPC committed the murder.
     */
    private String motive = "";

    //The NPCs 'blood' graphics will also be on the regular NPCs sprite sheet


    //These are characteristics about the NPC that could be used as clues by the player in a "Guess who" style.

    /**
     * These two booleans decide whether an NPC has the potential to be a killer and if, in this particular game, they
     * are the killer.
     */
    private boolean canBeKiller = false;
    private boolean isKiller = false;

    /**
     * Associated clues
     */
    private List<Clue> associatedClues = new ArrayList<>();

    /**
     * Define an NPC with location coordinates , room, spritesheet and whether or not they can be the killer
     *
     * @param tileX       - x coordinate of tile that the NPC will be initially rendered on.
     * @param tileY       - y coordinate of tile that the NPC will be initially rendered on.
     * @param roomID      - ID of room they are in
     * @param spriteSheet - Spritesheet for this NPC
     * @param canBeKiller - Boolean whether they can or cannot be the killer
     */
    public NPC(String name, String spriteSheet, int tileX, int tileY, int roomID, boolean canBeKiller)
    {

        super(name, spriteSheet, tileX, tileY);

        this.setRoomID(roomID);

        this.canBeKiller = canBeKiller;

    }


    /**
     * Allow the NPC to move around their room.
     *
     * @param dx - how far to move in the x direction
     * @param dy - how far to move in the y direction
     */
    public void move(int dx, int dy)
    {

    }

    public void addClues(List<Clue> clues)
    {
        this.associatedClues.addAll(clues);
    }

    public List<Clue> getClues()
    {
        return this.associatedClues;
    }

    public boolean hasClue(Clue clue)
    {
        return this.associatedClues.contains(clue);

    }

    /**
     * Getter for RoomID
     *
     * @return returns the RoomID
     */
    public int getRoomID()
    {
        return roomID;
    }

    /**
     * Setter for RoomID
     *
     * @param roomID - The RoomID you want to assign.
     * @return Returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setRoomID(int roomID)
    {
        this.roomID = roomID;
        return this;
    }

    /**
     * Getter for canBeKiller
     *
     * @return Returns value of canBeKiller for this object.
     */
    public boolean canBeKiller()
    {
        return canBeKiller;
    }

    /**
     * Getter for isKiller.
     *
     * @return Returna value of isKiller for this object.
     */
    public boolean isKiller()
    {
        return isKiller;
    }

    /**
     * Getter for motive.
     *
     * @return Returns the motive string for this object.
     */
    public String getMotive()
    {
        return motive;
    }

    /**
     * Setter for the NPC's motive string.
     *
     * @param motive - The motive this particular NPC has for committing the murder.
     * @return Returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setMotive(String motive)
    {
        this.motive = motive;
        return this;
    }
}
