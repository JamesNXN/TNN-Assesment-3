package me.lihq.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Settings;

import java.util.Set;

/**
 * This class defines the clues that the player needs to find in order to solve the murder.
 */
public class Clue extends Sprite
{
    /**
     * The image to be used for the clue.
     */
    private static String imagePath = "clueSheet.png";

    /**
     * The name of the clue.
     */
    private String clueName = "Super Secret Clue";

    /**
     * The 2D vector position of the clue.
     */
    private Vector2Int position;

    //TODO: Clues generate from the killer
    //TODO: Initialise Characters -> Generate Killer -> Generate Clues

    //TODO: Don't we need to associate a clue with a list of applicable NPCs?
    //~Jason
    //=== NEEDS DISCUSSING ===
    //TODO: Does a clue need a room ID as a room has a list of clues in it...
    private int roomID = -1;
    private int imageSrcX;
    private int imageSrcY;

    /**
     * Creates a new clue.
     * @param name - Clue name.
     * @param roomID - RoomID of room the clue is in.
     * @param x - x coordinate of clues position.
     * @param y - y coordinate of clues position.
     * @param imageSrcX - x coordinate of imageSrc.
     * @param imageSrcY - y coordinate of clues imageSrc.
     */
    public Clue(String name, int roomID, int x, int y, int imageSrcX, int imageSrcY)
    {
        super(new Texture(imagePath));
        this.clueName = name;
        this.roomID = roomID;
        this.position.x = x;
        this.position.y = y;
        this.imageSrcX = imageSrcX * Settings.TILE_SIZE;
        this.imageSrcY = imageSrcY * Settings.TILE_SIZE;
    }

    /**
     * @param obj - The clue object.
     * @return - Returns if it is equal as a boolean result.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Clue) {
            Clue c = (Clue) obj;

            //Might have to do same coordinates AND same room AND same name

            return c.getClueName().equals(this.getClueName());
        }

        return false;
    }

    /**
     * Getter for Clue name.
     * @return - Returns name of clue.
     */
    public String getClueName()
    {
        return this.clueName;
    }

    public void setClueName(String name)
    {
        this.clueName = name;
    }

    /**
     * Setter for clue coordinates.
     * @param x - The x coordinate for where the clue is.
     * @param y - The y coordinate for where the clue is.
     */
    public void setCoords(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;

        this.setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

    /**
     * Getter for RoomID.
     * @return - Returns the ID.
     */
    public int getRoomID()
    {
        return roomID;
    }
}
