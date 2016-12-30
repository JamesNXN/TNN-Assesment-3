package me.lihq.game.living;

import me.lihq.game.Settings;

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
     * These two booleans decide whether an NPC has the potential to be a killer and if, in this particular game, they
     * are the killer.
     */
    private boolean canBeKiller = false;
    private boolean isKiller = false;
    /**
     * The motive string details why the NPC committed the murder.
     */
    private String motive = "";

    //The NPCs 'blood' graphics will also be on the regular NPCs sprite sheet


    //These are characteristics about the NPC that could be used as clues by the player in a "Guess who" style.

    /**
     * Initialise hair color as ginger, they have ginger hair.
     */
    private HAIR_COLOR hairColor = HAIR_COLOR.GINGER;

    /**
     * Initialise hasGlasses as false, they don't have glasses.
     */
    private boolean hasGlasses = false;

    /**
     * Initialise writingHand as right handed.
     */
    private WRITING_HAND writingHand = WRITING_HAND.RIGHT;

    /**
     * Intialise hasLipstick as false, they don't have lipstick.
     */
    private boolean hasLipstick = false; //Really not sure about this one, should probably change to something more neutral

    /**
     * Initialise accessory as none, they do not have one.
     */
    private ACCESSORY accessory = ACCESSORY.NONE;


    /**
     * Initialise shoeSize as 12, they have size 12 shoes.
     */
    private int shoeSize = 12;


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


    /**
     * Setter for HasGlasses.
     *
     * @param hasGlasses - the value you want for hasGlasses
     * @return returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setHasGlasses(boolean hasGlasses)
    {
        this.hasGlasses = hasGlasses;
        return this;
    }

    /**
     * Setter for hasLipstick
     *
     * @param hasLipstick - the value you want for hasLipstick
     * @return returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setHasLipstick(boolean hasLipstick)
    {
        this.hasLipstick = hasLipstick;
        return this;
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

    /**
     * Getter for hairColor
     *
     * @return Returns the value of hairColor for this object.
     */
    public HAIR_COLOR getHairColor()
    {
        return hairColor;
    }


    /**
     * Setter for HairColor
     *
     * @param color - The color you want to assign.
     * @return Returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setHairColor(HAIR_COLOR color)
    {
        this.hairColor = color;
        return this;
    }


    /**
     * Getter for whether the NPC has glasses
     *
     * @return Returns the value of hasGlasses for this object.
     */
    public boolean hasGlasses()
    {
        return hasGlasses;
    }


    /**
     * Getter for whether the NPC is right or left handed.
     *
     * @return Returns the value of writingHand for this object.
     */
    public WRITING_HAND getWritingHand()
    {
        return writingHand;
    }

    /**
     * Setter for whether the NPC is right or left handed.
     *
     * @param hand - Which hand they write with.
     * @return Returns the NPC object as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setWritingHand(WRITING_HAND hand)
    {
        this.writingHand = hand;
        return this;
    }

    /**
     * Getter for whether the NPC has lipstick.
     *
     * @return Returns the value of hasLipstick for this object.
     */
    public boolean hasLipstick()
    {
        return hasLipstick;
    }

    /**
     * Getter for whether the NPC's Accessory.
     *
     * @return Return the value of accessory for this object.
     */
    public ACCESSORY getAccessory()
    {
        return accessory;
    }

    /**
     * Setter for whether the NPC's Accessory.
     *
     * @param accessory Whether the NPC has an accessory.
     * @return returns the NPC class as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setAccessory(ACCESSORY accessory)
    {
        this.accessory = accessory;
        return this;
    }

    /**
     * Getter for the NPC's shoe size.
     *
     * @return Returns the value of the NPC's shoe size.
     */
    public int getShoeSize()
    {
        return shoeSize;
    }

    /**
     * @param shoeSize The shoe size you want to assign to this NPC.
     * @return returns the NPC class as this is how the NPC's are built
     * by returning and adding each part.
     */
    public NPC setShoeSize(int shoeSize)
    {
        this.shoeSize = shoeSize;
        return this;
    }

    /**
     * These variables are to describe a players features to make the clues useful, this one refers to hair colours.
     * <li>{@link #LIGHT_BROWN}</li>
     * <li>{@link #DARK_BROWN}</li>
     * <li>{@link #GINGER}</li>
     * <li>{@link #BLONDE}</li>
     * <li>{@link #BLACK}</li>
     * <li>{@link #WHITE}</li>
     * <li>{@link #GRAY}</li>
     */
    public enum HAIR_COLOR
    {
        /**
         * Light brown hair.
         */
        LIGHT_BROWN,

        /**
         * Dark brown hair.
         */
        DARK_BROWN,

        /**
         * Ginger hair.
         */
        GINGER,

        /**
         * Blonde hair.
         */
        BLONDE,

        /**
         * Black hair.
         */
        BLACK,

        /**
         * White hair.
         */
        WHITE,

        /**
         * Gray hair.
         */
        GRAY
    }

    /**
     * These variables are to describe a players features to make the clues useful, this one refers to the hands they
     * can write with.
     * <li>{@link #LEFT}</li>
     * <li>{@link #RIGHT}</li>
     */
    public enum WRITING_HAND
    {
        /**
         * Left handed.
         */
        LEFT,

        /**
         * Right handed.
         */
        RIGHT
    }

    /**
     * These variables are to describe a players features to make the clues useful, this one refers to Accessories
     * they could have.
     * <li>{@link #BAG}</li>
     * <li>{@link #BRIEFCASE}</li>
     * <li>{@link #HANDBAG}</li>
     * <li>{@link #WATCH}</li>
     * <li>{@link #NONE}</li>
     */
    public enum ACCESSORY
    {
        /**
         * They have a bag.
         */
        BAG,

        /**
         * They have a briefcase.
         */
        BRIEFCASE,

        /**
         * They have a handbag.
         */
        HANDBAG,

        /**
         * They have a watch.
         */
        WATCH,

        /**
         * They have no accessory.
         */
        NONE
    }
}
