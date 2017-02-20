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
public abstract class AbstractPerson extends Actor implements Collidable, TileObject {

    /**
    * Parameters needed by AbstractPerson:
     *
     * SPRITE_HEIGHT - The height of the texture region for each person
     * SPRITE_WIDTH - The width of the texture region for each person
     * canMove - A boolean value defining whether the person can move or not. It's main use is to not allow characters to move during room transition or conversations
     * isInConversation - A boolean value defining whether the person is in a conversation or not
     * MOVE_SPEED - defines the speed at which people will move
     * tilePosition - This is the location of the person in the room in terms of tiles eg (0,0) would be the bottom left of the room.
     *                Uses the Vector2Int as the tilePosition should never be floats as the person should only be between tiles during the move process.
     * animStateTime - defines the animations current state in terms of time
     * walkUp, walkDown, walkRight and walkLeft - contains the animation for walking in different directions
     * collisionBox - A Rectangle object used for detecting collisions between actors in LibGDX
     * direction - determines the direction the character is facing
     * state - defines whether the person is walking or standing
     * jsonData - information about the person as imported via json
     * id - the unique id number of the person
     * name - the name of the person
     * description - the description of the person
     * currentRoom - the room the person is currently in
    */

    private final int SPRITE_HEIGHT = 48;
    private final int SPRITE_WIDTH = 32;

    private boolean canMove = true;

    private boolean isInConversation = false;

    private final float MOVE_SPEED = 100f;

    protected Vector2Int tilePosition = new Vector2Int(0, 0);

    private float animStateTime = 0;

    public Animation<TextureRegion> walkDown;
    public Animation<TextureRegion> walkUp;
    public Animation<TextureRegion> walkRight;
    public Animation<TextureRegion> walkLeft;

    protected Rectangle collisionBox;

    protected Direction direction = Direction.SOUTH;

    private PersonState state;

    private JsonValue jsonData;

    private int id;

    private String name;

    private String description;

    private Room currentRoom;


    /**
     * This constructor is called by player and npc to load the json and texture data
     * correctly such that the appropriate object is created
     *
     * @param jsonData    - The json data of the Person
     * @param spriteSheet - this the texture for the sprite
     */
    public AbstractPerson(JsonValue jsonData, TextureAtlas spriteSheet) {
        this.jsonData = jsonData;
        id = jsonData.getInt("id");
        name = jsonData.getString("name");
        description = jsonData.getString("description");
        this.state = PersonState.STANDING;

        collisionBox = new Rectangle();
        collisionBox.setSize(Settings.TILE_SIZE * 0.7f);

        setSize(SPRITE_WIDTH, SPRITE_HEIGHT);
        setOrigin(getWidth() / 2, getHeight() / 2);

        walkUp = new Animation<>(0.1f, spriteSheet.findRegions("walkUp"));
        walkDown = new Animation<>(0.1f, spriteSheet.findRegions("walkDown"));
        walkRight = new Animation<>(0.1f, spriteSheet.findRegions("walkRight"));
        walkLeft = new Animation<>(0.1f, spriteSheet.findRegions("walkLeft"));
    }


    /**
     * Detects collision with wall
     *
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
     *
     * @return return true when there is collision
     */
    private boolean characterCollisionDetection(Rectangle collisionBox) {
        Array<Npc> npcArray = getCurrentRoom().getNpcArray();

        boolean characterCollision = false;
        for (Npc person : npcArray) {
            if (person.getCollisionBox().overlaps(collisionBox) && !person.equals(this)) {
                characterCollision = true;
                break;
            }
        }
        return characterCollision;
    }

    /**
     * Act and Draw methods needed by LibGDX to render the object correctly
     */
    @Override
    public void act(float delta) {
        //offset given in order to align the box in the mid bottom of the character
        collisionBox.setPosition(getX() + getWidth() / 2 - collisionBox.getWidth() / 2, getY());

        if (state == PersonState.WALKING) {
            animStateTime += delta;

            float vectorDistanceX = direction.getDx() * MOVE_SPEED * delta;
            float vectorDistanceY = direction.getDy() * MOVE_SPEED * delta;
            collisionBox.setPosition(collisionBox.x + vectorDistanceX, collisionBox.y + vectorDistanceY);

            if (!wallCollisionDetection(collisionBox) && !characterCollisionDetection(collisionBox)) {
                moveBy(vectorDistanceX, vectorDistanceY);
            }
        } else {
            tilePosition.x = (int) (getX() / Settings.TILE_SIZE);
            tilePosition.y = (int) (getY() / Settings.TILE_SIZE);
            animStateTime = 0;
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = null;
        switch (direction) {
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
    /**
     * Getters and setters needed for use by other classes
     */
    public PersonState getState() {
        return state;
    }

    public void setState(PersonState state) {
        this.state = state;
    }

    @Override
    public void setTilePosition(int x, int y) {
        tilePosition.x = x;
        tilePosition.y = y;

        //screen position in pixels
        setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

    public JsonValue getJsonData() {
        return jsonData;
    }

    public void setInConversation(boolean inConversation) {
        isInConversation = inConversation;
        if (isInConversation){
            setCanMove(false);
        }
        else{
            setCanMove(true);
        }
    }

    public boolean isInConversation() {
        return isInConversation;
    }

    public abstract Personality getPersonality();

    public abstract Dialogue getDialogue();

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getDescription() { return this.description;}

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Vector2Int getTilePosition() {
        return tilePosition;
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }
}
