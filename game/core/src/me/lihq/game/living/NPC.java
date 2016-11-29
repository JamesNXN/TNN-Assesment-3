package me.lihq.game.living;

import me.lihq.game.Settings;

/**
 * Created by joeshuff on 20/11/2016.
 */
public class NPC extends AbstractPerson
{

    //These variables are specific to the NPC only
    private int roomID = -1;
    private String name = "";

    private boolean canBeKiller = false;
    private boolean isKiller = false;
    private String motive = "";

    //The NPCs 'blood' graphics will also be on the regular NPCs sprite sheet
    private HAIR_COLOR hairColor = HAIR_COLOR.GINGER;

    private boolean hasGlasses = false;
    private WRITING_HAND writingHand = WRITING_HAND.RIGHT;
    private boolean hasLipstick = false; //Really not sure about this one, should probably change to something more neutral

    private ACCESSORY accessory = ACCESSORY.NONE;
    private int shoeSize = 12;

    public NPC(int x, int y, int roomID, String spriteSheet, boolean canBeKiller)
    {

        super(spriteSheet);

        this.setRoomID(roomID);

        this.tileCoordinates.x = x;
        this.tileCoordinates.y = y;

        this.setX(x * Settings.TILE_SIZE);
        this.setY(y * Settings.TILE_SIZE);

        this.canBeKiller = canBeKiller;

    }

    @Override
    public void move(int dx, int dy)
    {

    }

    public NPC setHasGlasses(boolean hasGlasses)
    {
        this.hasGlasses = hasGlasses;
        return this;
    }

    public NPC setHasLipstick(boolean hasLipstick)
    {
        this.hasLipstick = hasLipstick;
        return this;
    }

    public NPC setCharacterName(String name)
    {
        this.name = name;
        return this;
    }

    public int getRoomID()
    {
        return roomID;
    }

    public NPC setRoomID(int roomID)
    {
        this.roomID = roomID;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public boolean canBeKiller()
    {
        return canBeKiller;
    }

    public boolean isKiller()
    {
        return isKiller;
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

    public HAIR_COLOR getHairColor()
    {
        return hairColor;
    }

    //Setters for all NPC attributes
    public NPC setHairColor(HAIR_COLOR color)
    {
        this.hairColor = color;
        return this;
    }

    public boolean hasGlasses()
    {
        return hasGlasses;
    }

    public WRITING_HAND getWritingHand()
    {
        return writingHand;
    }

    public NPC setWritingHand(WRITING_HAND hand)
    {
        this.writingHand = hand;
        return this;
    }

    public boolean hasLipstick()
    {
        return hasLipstick;
    }

    public ACCESSORY getAccessory()
    {
        return accessory;
    }

    public NPC setAccessory(ACCESSORY accessory)
    {
        this.accessory = accessory;
        return this;
    }

    public int getShoeSize()
    {
        return shoeSize;
    }

    public NPC setShoeSize(int shoeSize)
    {
        this.shoeSize = shoeSize;
        return this;
    }

    //These variables are to describe a players features to make the clues useful
    public enum HAIR_COLOR
    {
        LIGHT_BROWN, DARK_BROWN, GINGER, BLONDE, BLACK, WHITE, GRAY
    }

    public enum WRITING_HAND
    {
        LEFT, RIGHT
    }

    public enum ACCESSORY
    {
        BAG, BRIEFCASE, HANDBAG, WATCH, NONE
    }
}
