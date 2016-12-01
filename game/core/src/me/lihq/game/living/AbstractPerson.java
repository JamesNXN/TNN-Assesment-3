package me.lihq.game.living;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Assets;
import me.lihq.game.Settings;
import me.lihq.game.models.Vector2Int;

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
    protected Vector2Int tileCoordinates = new Vector2Int(0, 0);
    protected Vector2Int startPosition = new Vector2Int(0,0);
    protected Vector2Int destinationPosition = new Vector2Int(0,0);
    protected float animTimer;
    protected float ANIM_TIME = 0.25f;
    protected ACTOR_STATE state;
    protected boolean left, right, up, down;
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

        this.setPosition(tileCoordinates.getX() * Settings.TILE_SIZE, tileCoordinates.getY() * Settings.TILE_SIZE);
        this.state = ACTOR_STATE.STANDING;
    }

    public void setTileCoordinates(int x, int y)
    {
        tileCoordinates.x = x;
        tileCoordinates.y = y;

        setPosition(x*Settings.TILE_SIZE,y*Settings.TILE_SIZE);
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
        NORTH(0,1),
        SOUTH(0,-1),
        EAST(1,0),
        WEST(-1,0);


        private int dx, dy;

        DIRECTION(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return this.dx;
        }

        public int getDy() {
            return this.dy;
        }


    }



    public enum ACTOR_STATE {
        WALKING,
        STANDING;
    }
}
