package me.lihq.game.people;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.Settings;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

/**
 * The abstract person is an abstract representation of a person. A person can be a non playable character or Player.
 * It extends the sprite class which provides methods for the person to be rendered in the game.
 */
public abstract class AbstractPerson extends Actor
{
    /**
     * The height of the texture region for each person
     */
    protected static final int SPRITE_HEIGHT = 48;

    /**
     * The width of the texture region for each person
     */
    protected static final int SPRITE_WIDTH = 32;
    /**
     * This is whether the NPC can move or not. It is mainly used to not let them move during conversation
     */
    public boolean canMove = true;

    private final float moveSpeed = 100f;

    /**
     * This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room
     * Uses the Vector2Int as the tilePosition should never be floats as the person should only be between tiles during the move process.
     */
    protected Vector2Int tilePosition = new Vector2Int(0, 0);

    /**
     * The distance in pixel that the actor moved after the move() method ran. It is recorded in order to stop the actor from
     * moving more than 1 tile per move() method.
     */
    protected Vector2 movedDistance = new Vector2();

    /**
     * A store of the destination for a movement.
     */
    protected Vector2Int destinationTile = new Vector2Int(0, 0);

    /**
     * The following variables control the walking animation speed
     */
    protected float animTimer;
    protected float animTime = Settings.TPS / 3f;

    protected float animStateTime = 0;
    /**
     * This stores the sprite sheet of the Player/NPC
     */
    protected TextureAtlas spriteSheet;

    protected Animation<TextureRegion> walkDown;
    protected Animation<TextureRegion> walkUp;
    protected Animation<TextureRegion> walkRight;
    protected Animation<TextureRegion> walkLeft;

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
     * @param spriteSheet   - this the texture for the sprite
     */
    public AbstractPerson(String name, TextureAtlas spriteSheet)
    {
        debug();
        this.name = name;
        this.spriteSheet = spriteSheet;
        this.state = PersonState.STANDING;

        // size for collision check box
        setSize(Settings.TILE_SIZE - 2, Settings.TILE_SIZE - 2);

        walkUp = new Animation<>(0.1f, spriteSheet.findRegions("walkUp"));
        walkDown = new Animation<>(0.1f, spriteSheet.findRegions("walkDown"));
        walkRight = new Animation<>(0.1f, spriteSheet.findRegions("walkRight"));
        walkLeft = new Animation<>(0.1f, spriteSheet.findRegions("walkLeft"));
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

    public void setState(PersonState state){
        this.state = state;
    }

    /**
     * This sets the tile coordinates of the person.
     *
     * @param x The x coordinate of the tile grid.
     * @param y The y coordinate of the tile grid.
     */
    public void setTilePosition(int x, int y)
    {
        tilePosition.x = x;
        tilePosition.y = y;
        setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

//    /**
//     * move character one tile to the specified direction
//     * @param direction direction that the character needs move
//     */
//
//    public void move(Direction direction){
//        this.direction = direction;
//
//        state = PersonState.WALKING;
//        destinationTile.x = getTilePosition().x + direction.getDx();
//        destinationTile.y = getTilePosition().y + direction.getDy();
//    }


    @Override
    public void act(float delta) {
        if (state == PersonState.WALKING){
            animStateTime += delta;

            float vectorDistanceX = direction.getDx() * moveSpeed * delta;
            float vectorDistanceY = direction.getDy() * moveSpeed * delta;

            if (collisionDetection(vectorDistanceX, vectorDistanceY)) {
                moveBy(vectorDistanceX, vectorDistanceY);
            }
        }
        else{
            animStateTime = 0;
        }
        super.act(delta);
    }

    private boolean collisionDetection(float vectorDistanceX, float vectorDistanceY) {
        TiledMapTileLayer layer = (TiledMapTileLayer) getRoom().getTiledMap().getLayers().get("Collision");

        float tileWidth = layer.getTileWidth();
        float tileHeight = layer.getTileHeight();

        float translatedX = getX() + vectorDistanceX;
        float translatedY = getY() + vectorDistanceY;
        float translatedRight = translatedX + getWidth();
        float translatedTop = translatedY + getHeight();

        TiledMapTileLayer.Cell upperLeftTile = layer.getCell((int) (translatedX / tileWidth), (int) (translatedTop / tileHeight));
        TiledMapTileLayer.Cell upperRightTile = layer.getCell((int) (translatedRight / tileWidth), (int) (translatedTop / tileHeight));
        TiledMapTileLayer.Cell lowerLeftTile = layer.getCell((int) (translatedX / tileWidth), (int) (translatedY / tileHeight));
        TiledMapTileLayer.Cell lowerRightTile = layer.getCell((int) (translatedRight / tileWidth), (int) (translatedY / tileHeight));

        return upperLeftTile == null && upperRightTile == null && lowerLeftTile == null && lowerRightTile == null;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = null;
        switch (direction){
            case EAST:
                currentFrame = walkRight.getKeyFrame(animStateTime, true);
                break;

            case WEST:
                currentFrame = walkLeft.getKeyFrame(animStateTime, true);
                break;

            case NORTH:
                currentFrame = walkUp.getKeyFrame(animStateTime, true);
                break;

            case SOUTH:
                currentFrame = walkDown.getKeyFrame(animStateTime, true);
        }

        batch.draw(currentFrame, getX(), getY(), SPRITE_WIDTH, SPRITE_HEIGHT);
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

    public abstract Personality getPersonality();

    public String getName()
    {
        return this.name;
    }

    public Direction getDirection()
    {
        return this.direction;
    }

    public void setDirection(Direction dir)
    {
        this.direction = dir;
    }

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

    public Vector2Int getTilePosition()
    {
        return tilePosition;
    }
}
