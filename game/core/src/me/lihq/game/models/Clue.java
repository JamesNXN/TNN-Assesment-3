package me.lihq.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.lihq.game.Settings;


/**
 * This class defines the clues that the player needs to find in order to solve the murder.
 */
public class Clue extends Sprite
{
    /**
     * The name of the clue, set when you initialise the clue and gettable using {@link #getName()}
     */
    private String name;

    /**
     * The description of the clue, set when you initialise the clue and gettable using {@link #getDescription()}
     */
    private String description;

    /**
     * This is the location on the map in terms of tiles can be set using {@link #setTileCoordinates(int, int)}
     * Note: this is different to com.badlogic.gdx.graphics.g2d.Sprite.position that is the position on the screen in terms of pixels,
     * whereas this is in terms of map tiles.
     */
    private Vector2Int tileCoordinates = new Vector2Int(0,0);

    /**
     * Creates a clue
     * @param name the name of the clue i.e. what it is
     * @param description describes what the clue is
     * @param texture the texture region of the clue
     */
    public Clue(String name, String description, TextureRegion texture)
    {
        super(texture);
        this.name = name;
        this.description = description;
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
            return c.getName().equals(this.getName());
        }

        return false;
    }

    /**
     * Getter for Clue name.
     * @return - Returns name of clue.
     */
    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public Clue setTileCoordinates(Vector2Int v)
    {
        return setTileCoordinates(v.x, v.y);
    }

    /**
     * Setter for clue tile coordinates.
     * @param x - The x coordinate for where the clue is, in terms of tiles.
     * @param y - The y coordinate for where the clue is, in terms of tiles.
     */
    public Clue setTileCoordinates(int x, int y)
    {
        this.tileCoordinates.x = x;
        this.tileCoordinates.y = y;

        setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);

        return this;
    }

    public Vector2Int getPosition()
    {
        return this.tileCoordinates;
    }

    public int getTileX() {
        return tileCoordinates.x;
    }

    public int getTileY() {
        return tileCoordinates.y;
    }

}
