package me.lihq.game.living;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Assets;
import me.lihq.game.Settings;

/**
 * The abstract person is an abstract representation of a person. A person can be a non playable character or Player.
 * It extends the sprite class which provides methods for the person to be rendered in the game.
 */
public abstract class AbstractPerson extends Sprite
{

    //Storing the characters coordinates on the map
    /**
     * This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room
     */
    protected Vector2 tileCoordinates = new Vector2().set(0, 0);
    protected int offsetX = 0;
    protected int offsetY = 0;

    /**
     * The direction determines the way the character is facing.
     */
    protected DIRECTION direction = DIRECTION.NORTH;


    /**
     * This constructs the player calling super on the sprite class
     *
     * @param img this a path to the image
     */
    public AbstractPerson(String img)
    {
        super(Assets.loadTexture(img));

        this.setPosition(tileCoordinates.x * Settings.TILE_SIZE, tileCoordinates.y * Settings.TILE_SIZE);

    }

    public int getOffsetX()
    {
        return this.offsetX;
    }

    public void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }

    public int getOffsetY()
    {
        return this.offsetY;
    }

    public void setOffsetY(int offsetY)
    {
        this.offsetY = offsetY;
    }

    public DIRECTION getDirection()
    {
        return this.direction;
    }

    public void setDirection(DIRECTION direction)
    {
        this.direction = direction;
    }


    public enum DIRECTION
    {
        NORTH, SOUTH, EAST, WEST
    }
}
