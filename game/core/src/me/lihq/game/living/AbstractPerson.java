package me.lihq.game.living;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

/**
 * The abstract person is an abstract representation of a person. A person can be a non playable character or Player.
 * It extends the sprite class which provides methods for the person to be rendered in the game.
 */
public abstract class AbstractPerson extends Sprite
{
    /**
     * The height of the texture region for each person
     */
    protected static int SPRITE_HEIGHT = 48;

    /**
     * The width of the texture region for each person
     */
    protected static int SPRITE_WIDTH = 32;

    /**
     * This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room
     * Uses the Vector2Int as the tileCoordinates should never be floats as the person should only be between tiles during the move process.
     */
    protected Vector2Int tileCoordinates = new Vector2Int(0, 0);

    /**
     * The Name of the Person
     */
    private String name;

    /**
     * This is the players location in the current room.
     * Note this is different to sprite position, the sprite position is the location that the person is currently drawn.
     * Avoid using Sprites setPosition as if it is changed mid render it will cause jolting.
     */
    protected Vector2 coordinates = new Vector2().set(0.0f, 0.0f);

    /**
     * A store of the starting point for a movement.
     */
    protected Vector2Int startTile = new Vector2Int(0, 0);

    /**
     * A store of the destination for a movement.
     */
    protected Vector2Int destinationTile = new Vector2Int(0, 0);
    protected float animTimer;
    protected float animTime = Settings.TPS / 3.5f;
    protected Texture spriteSheet;
    protected TextureRegion currentRegion;
    /**
     * The direction determines the way the character is facing.
     */
    protected Direction direction = Direction.EAST;

    protected PersonState state;

    /**
     * The current room of the AbstractPerson.
     */
    private Room currentRoom;

    /**
     * This constructs the player calling super on the sprite class
     *
     * @param img this a path to the image
     */
    public AbstractPerson(String name, String img, int tileX, int tileY)
    {
        super(new TextureRegion(Assets.loadTexture(img), 0, 0, SPRITE_WIDTH, SPRITE_HEIGHT));
        this.name = name;
        this.spriteSheet = Assets.loadTexture(img);
        this.currentRegion = new TextureRegion(Assets.loadTexture(img), 0, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
        this.setTileCoordinates(tileX, tileY);
        this.setPosition(tileCoordinates.getX() * Settings.TILE_SIZE, tileCoordinates.getY() * Settings.TILE_SIZE);
        this.state = PersonState.STANDING;
    }

    /**
     * This method moves the coordinates in the AbstractPersons coordinates to
     * the Sprites position so that it can then be rendered at the correct location.
     */
    public void pushCoordinatesToSprite()
    {
        setPosition(coordinates.x, coordinates.y);
    }

    /**
     * This method returns the Persons walking state.
     * <p>
     * Either WALKING or STANDING
     */
    public PersonState getState()
    {
        return state;
    }

    /**
     * This sets the tile coordinates of the person.
     *
     * @param x The x coordinate of the tile grid.
     * @param y The y coordinate of the tile grid.
     */
    public void setTileCoordinates(int x, int y)
    {
        tileCoordinates.x = x;
        tileCoordinates.y = y;

        setCoords(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

    /**
     * This is called to update the players position.
     * Called from the game loop, it interpolates the movement so that the person moves smoothly from tile to tile.
     */
    public void update()
    {
        if (this.state == PersonState.WALKING) {
            this.coordinates.x = Interpolation.linear.apply(startTile.x * Settings.TILE_SIZE, destinationTile.x * Settings.TILE_SIZE, animTimer / animTime);
            this.coordinates.y = Interpolation.linear.apply(startTile.y * Settings.TILE_SIZE, destinationTile.y * Settings.TILE_SIZE, animTimer / animTime);

            this.animTimer += 1f;

            if (animTimer > animTime) {
                this.setTileCoordinates(destinationTile.x, destinationTile.y);
                this.finishMove();
            }
        }

        updateTextureRegion();
    }

    /**
     * Sets up the move, initialising the start position and destination as well as the state of the person.
     * This allows the movement to be smooth and fluid.
     *
     * @param dir the direction that the person is moving in.
     */
    public void initialiseMove(Direction dir)
    {
        this.direction = dir;

        this.startTile.x = this.tileCoordinates.x;
        this.startTile.y = this.tileCoordinates.y;

        this.destinationTile.x = this.startTile.x + dir.getDx();
        this.destinationTile.y = this.startTile.y + dir.getDy();
        this.animTimer = 0f;

        this.state = PersonState.WALKING;
    }

    /**
     * Finalises the move by resetting the animation timer and setting the state back to standing.
     * Called when the player is no longer moving.
     */
    public void finishMove()
    {
        animTimer = 0f;

        this.state = PersonState.STANDING;

        updateTextureRegion();
    }


    /**
     * Updates the texture region based upon how far though the animation time it is.
     */
    public void updateTextureRegion()
    {
        float quarter = animTime / 4;
        float half = animTime / 2;
        float threeQuarters = quarter * 3;

        int row = 1;

        switch (direction) {
            case NORTH:
                row = 3;
                break;
            case EAST:
                row = 2;
                break;
            case SOUTH:
                row = 0;
                break;
            case WEST:
                row = 1;
                break;
        }

        if (animTimer > threeQuarters) {
            setRegion(new TextureRegion(spriteSheet, 64, row * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT));
        } else if (animTimer > half) {
            setRegion(new TextureRegion(spriteSheet, 0, row * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT));
        } else if (animTimer > quarter) {
            setRegion(new TextureRegion(spriteSheet, 32, row * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT));
        } else if (animTimer == 0) {
            setRegion(new TextureRegion(spriteSheet, 0, row * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT));
        }
    }

    /**
     * Used internally to store the coordinates of the person.
     *
     * @param x the x coordinate you wish to store
     * @param y the y coordinate you wish to store
     */
    private void setCoords(float x, float y)
    {
        coordinates.x = x;
        coordinates.y = y;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for direction.
     * @return Returns the direction the person is facing.
     */
    public Direction getDirection()
    {
        return this.direction;
    }

    /**
     * Setter for the direction the person is facing.
     * @param dir - Desired direction for the person to face.
     */
    public void setDirection(Direction dir)
    {
        this.direction = dir;
    }


    /**
     * Setter for the animation time.
     * @param animTime - The animation time you want to set.
     */
    public void setAnimTime(float animTime)
    {
        this.animTime = animTime;
    }

    public Room getRoom()
    {
        return this.currentRoom;
    }

    public void setRoom(Room room)
    {
        this.currentRoom = room;
    }

    public Vector2Int getTileCoordinates()
    {
        return tileCoordinates;
    }

    /**
     * This is used to describe the direction the person is currently facing or moving in.
     * <li>{@link #NORTH}</li>
     * <li>{@link #SOUTH}</li>
     * <li>{@link #EAST}</li>
     * <li>{@link #WEST}</li>
     */
    public enum Direction
    {
        /**
         * person is facing north
         */
        NORTH(0, 1),

        /**
         * person is facing south
         */
        SOUTH(0, -1),

        /**
         * person is facing east
         */
        EAST(1, 0),

        /**
         * person is facing west
         */
        WEST(-1, 0);

        private int dx, dy;

        /**
         * @param dx x coordinate.
         * @param dy y coordinate.
         */
        Direction(int dx, int dy)
        {
            this.dx = dx;
            this.dy = dy;
        }

        /**
         * Getter for dx.
         * @return returns the value of dx.
         */
        public int getDx()
        {
            return this.dx;
        }

        /**
         * Getter for dy.
         * @return returns the value of dy.
         */
        public int getDy()
        {
            return this.dy;
        }
    }

    /**
     * The state of the person explains what they are currently doing.
     * <li>{@link #WALKING}</li>
     * <li>{@link #STANDING}</li>
     */
    public enum PersonState
    {
        /**
         * Person is walking.
         */
        WALKING,

        /**
         * Person is standing still.
         */
        STANDING;
    }

}
