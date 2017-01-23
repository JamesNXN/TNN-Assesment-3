package me.lihq.game.people;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.Assets;
import me.lihq.game.Settings;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

import java.util.Comparator;

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
     * This is whether the NPC can move or not. It is mainly used to not let them move during converstation
     */
    public boolean canMove = true;
    /**
     * This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room
     * Uses the Vector2Int as the tileCoordinates should never be floats as the person should only be between tiles during the move process.
     */
    protected Vector2Int tileCoordinates = new Vector2Int(0, 0);
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
    /**
     * The following variables control the walking animation speed
     */
    protected float animTimer;
    protected float animTime = Settings.TPS / 3f;
    /**
     * This stores the sprite sheet of the Player/NPC
     */
    protected Texture spriteSheet;
    /**
     * This stores the current region of the above texture that is to be drawn
     * to the map
     */
    protected TextureRegion currentRegion;
    /**
     * This is the JSON data for the Player/NPC
     */
    protected JsonValue jsonData;
    /**
     * The direction determines the way the character is facing.
     */
    protected Direction direction = Direction.EAST;
    /**
     * This is the current walking state of the Person. {@link #getState()}
     */
    protected PersonState state;
    /**
     * The Name of the Person
     */
    private String name;
    /**
     * The current room of the AbstractPerson.
     */
    private Room currentRoom;

    /**
     * This constructs the player calling super on the sprite class
     *
     * @param name  - The name of the Person
     * @param img   - this a path to the sprite sheet image
     * @param tileX - This is the start x coordinate for the Person
     * @param tileY - This is the start y coordinate for the Person
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
     * Either WALKING or STANDING
     *
     * @return (PersonState) the current state of the Person
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
        getRoom().lockCoordinate(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy());

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

        getRoom().unlockCoordinate(tileCoordinates.x, tileCoordinates.y);

        updateTextureRegion();
    }

    /**
     * Reads in the JSON file of tha character and stores dialogue in the dialogue HashMap
     *
     * @param fileName
     */
    public abstract void importDialogue(String fileName);

    /**
     * Gets a random item from the correct dictionary key.
     *
     * @param key
     * @return
     */
    public String getSpeech(String key)
    {
        //TODO: Randomise the noneResponse
        try {
            if (!jsonData.get("Responses").has(key)) {
                return jsonData.get("noneResponses").getString(0);
            } else {
                return jsonData.get("Responses").getString(key);
            }
        } catch (Exception e) {
            return "error speech not working";
        }
    }

    /**
     * This method returns the response based on the clue given
     *
     * @param clue - The clue to get the response for
     * @return (String) the string response
     */
    public String getSpeech(Clue clue)
    {
        return this.getSpeech(clue.getName());
    }


    /**
     * This handles speech for a clue that has a question style
     *
     * @param clue  the clue to be questioned about
     * @param style the style of questioning
     * @return the speech
     */
    public abstract String getSpeech(Clue clue, Personality style);

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
            setRegion(new TextureRegion(spriteSheet, 96, row * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT));
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

    /**
     * This returns the persons personality
     *
     * @return (Personality) returns the Persons personality
     */
    public abstract Personality getPersonality();

    /**
     * This method returns the Persons name as a String
     *
     * @return (String) the persons name {@link #name}
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for direction.
     *
     * @return (Direction) Returns the direction the person is facing.
     */
    public Direction getDirection()
    {
        return this.direction;
    }

    /**
     * Setter for the direction the person is facing.
     *
     * @param dir - Desired direction for the person to face.
     */
    public void setDirection(Direction dir)
    {
        this.direction = dir;
    }


    /**
     * Setter for the animation time.
     *
     * @param animTime - The animation time you want to set.
     */
    public void setAnimTime(float animTime)
    {
        this.animTime = animTime;
    }

    /**
     * This method returns the room that the Person is in
     *
     * @return (Room) the room the Person is in {@link #currentRoom}
     */
    public Room getRoom()
    {
        return this.currentRoom;
    }

    /**
     * This method sets the currentRoom to the room parameter
     *
     * @param room - The room to change currentRoom to {@link #currentRoom}
     */
    public void setRoom(Room room)
    {
        this.currentRoom = room;
    }

    /**
     * This method gets the Persons tile coordinates
     *
     * @return (Vector2Int) the value of tileCoordinates {@link #tileCoordinates}
     */
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
         *
         * @return returns the value of dx.
         */
        public int getDx()
        {
            return this.dx;
        }

        /**
         * Getter for dy.
         *
         * @return returns the value of dy.
         */
        public int getDy()
        {
            return this.dy;
        }

        /**
         * This method takes the current direction and gets the opposite direction
         *
         * @return (Direction) the opposite direction to this
         */
        public Direction getOpposite()
        {
            if (this == Direction.NORTH) return Direction.SOUTH;
            if (this == Direction.EAST) return Direction.WEST;
            if (this == Direction.SOUTH) return Direction.NORTH;
            if (this == Direction.WEST) return Direction.EAST;

            return null;
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

    /**
     * These are the possible personalities of the person
     */
    public enum Personality
    {
        NICE,
        NEUTRAL,
        AGGRESSIVE
    }

    /**
     * This class is to sort a list of AbstractPerson in highest Y coordinate to lowest Y coordinate
     * <p>
     * It is used to render NPCs and the Player in the correct order to avoid it appearing as though someone
     * is standing on top of someone else
     */
    public static class PersonPositionComparator implements Comparator<AbstractPerson>
    {
        /**
         * This method compares the 2 objects.
         *
         * @param o1 - The first object to compare
         * @param o2 - The second object to compare
         * @return (int) if <0 o1 is considered to be first in the list
         */
        @Override
        public int compare(AbstractPerson o1, AbstractPerson o2)
        {
            return o2.getTileCoordinates().y - o1.getTileCoordinates().y;
        }
    }

}
