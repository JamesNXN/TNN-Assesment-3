package me.lihq.game.people;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.Collidable;
import me.lihq.game.Settings;
import me.lihq.game.TileObject;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

/**
 * The abstract person is an abstract representation of a person. A person can be a non playable character or Player.
 * It extends the sprite class which provides methods for the person to be rendered in the game.
 */
public abstract class AbstractPerson extends Actor implements Collidable,TileObject
{
    /**
     * The height of the texture region for each person
     */
    private final int SPRITE_HEIGHT = 48;

    /**
     * The width of the texture region for each person
     */
    private final int SPRITE_WIDTH = 32;
    /**
     * This is whether the NPC can move or not. It is mainly used to not let them move during conversation or room transition
     */
    private boolean canMove = true;

    private final float MOVE_SPEED = 500f;

    /**
     * This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room
     * Uses the Vector2Int as the tilePosition should never be floats as the person should only be between tiles during the move process.
     */
    protected Vector2Int tilePosition = new Vector2Int(0, 0);

    private float animStateTime = 0;

    public Animation<TextureRegion> walkDown;
    public Animation<TextureRegion> walkUp;
    public Animation<TextureRegion> walkRight;
    public Animation<TextureRegion> walkLeft;

    protected Rectangle collisionBox;
    /**
     * The direction determines the way the character is facing.
     */
    protected Direction direction = Direction.SOUTH;
    /**
     * This is the current walking state of the Person. {@link #getState()}
     */
    private PersonState state;
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
     * @param jsonData  - The json data of the Person
     * @param spriteSheet   - this the texture for the sprite
     */
    public AbstractPerson(JsonValue jsonData, TextureAtlas spriteSheet)
    {
        debug();
        name = jsonData.getString("name");
        this.state = PersonState.STANDING;

        collisionBox = new Rectangle();
        collisionBox.setSize(Settings.TILE_SIZE * 0.7f);

        setSize(SPRITE_WIDTH, SPRITE_HEIGHT);
        setOrigin(getWidth()/2, getHeight()/2);

        walkUp = new Animation<>(0.1f, spriteSheet.findRegions("walkUp"));
        walkDown = new Animation<>(0.1f, spriteSheet.findRegions("walkDown"));
        walkRight = new Animation<>(0.1f, spriteSheet.findRegions("walkRight"));
        walkLeft = new Animation<>(0.1f, spriteSheet.findRegions("walkLeft"));
    }

    public AbstractPerson(JsonValue jsonData){    //// TEST CONSTRUCTOR
        name = jsonData.getString("name");
        this.state = PersonState.STANDING;

        collisionBox = new Rectangle();
        collisionBox.setSize(Settings.TILE_SIZE * 0.7f);

        setSize(SPRITE_WIDTH, SPRITE_HEIGHT);
        setOrigin(getWidth()/2, getHeight()/2);
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

    @Override
    public void setTilePosition(int x, int y)
    {
        tilePosition.x = x;
        tilePosition.y = y;

        //screen position in pixels
        setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }


    @Override
    public void act(float delta) {
        //offset given in order to align the box in the mid bottom of the character
        collisionBox.setPosition(getX() + getWidth() / 2 - collisionBox.getWidth()/2, getY());

        if (state == PersonState.WALKING){
            animStateTime += delta;

            float vectorDistanceX = direction.getDx() * MOVE_SPEED * delta;
            float vectorDistanceY = direction.getDy() * MOVE_SPEED * delta;
            collisionBox.setPosition(collisionBox.x + vectorDistanceX, collisionBox.y + vectorDistanceY);

            if (!wallCollisionDetection(collisionBox) && !characterCollisionDetection(collisionBox)) {
                moveBy(vectorDistanceX, vectorDistanceY);
            }
        }
        else{
            tilePosition.x = (int) (getX() / Settings.TILE_SIZE);
            tilePosition.y = (int) (getY() / Settings.TILE_SIZE);
            animStateTime = 0;
        }
        super.act(delta);
    }

    /**
     * Detects collision with wall
     * @return return true when there is collision
     */
    private boolean wallCollisionDetection(Rectangle collisionBox) {
        TiledMapTileLayer layer = (TiledMapTileLayer) getCurrentRoom().getTiledMap().getLayers().get("Collision");

        float tileWidth = layer.getTileWidth();
        float tileHeight = layer.getTileHeight();

        float translatedX = collisionBox.x;
        float translatedY = collisionBox.y;
        float translatedRight = translatedX + collisionBox.getWidth();
        float translatedTop = translatedY + collisionBox.getHeight();

        TiledMapTileLayer.Cell upperLeftTile = layer.getCell((int) (translatedX / tileWidth), (int) (translatedTop / tileHeight));
        TiledMapTileLayer.Cell upperRightTile = layer.getCell((int) (translatedRight / tileWidth), (int) (translatedTop / tileHeight));
        TiledMapTileLayer.Cell lowerLeftTile = layer.getCell((int) (translatedX / tileWidth), (int) (translatedY / tileHeight));
        TiledMapTileLayer.Cell lowerRightTile = layer.getCell((int) (translatedRight / tileWidth), (int) (translatedY / tileHeight));

        return upperLeftTile != null || upperRightTile != null || lowerLeftTile != null || lowerRightTile != null;
    }

    /**
     * Detects collision with characters
     * @return return true when there is collision
     */
    private boolean characterCollisionDetection(Rectangle collisionBox){
        Array<NPC> npcArray = getCurrentRoom().getNpcArray();

        boolean characterCollision = false;
        for (NPC person : npcArray) {
            if (person.getCollisionBox().overlaps(collisionBox) && !person.equals(this)) {
                characterCollision = true;
                break;
            }
        }
        return characterCollision;
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

        //necessary for alpha actions
        Color color = batch.getColor();
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);

        batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(), SPRITE_WIDTH, SPRITE_HEIGHT, getScaleX(), getScaleY(), getRotation());
        batch.setColor(color);
    }
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

    public Room getCurrentRoom()
    {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room room)
    {
        this.currentRoom = room;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Vector2Int getTilePosition()
    {
        return tilePosition;
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }
}
