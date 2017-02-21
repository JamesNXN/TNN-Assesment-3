package me.lihq.game.models;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Json;

import me.lihq.game.Collidable;
import me.lihq.game.Settings;
import me.lihq.game.TileObject;
import me.lihq.game.people.Direction;

/**
 * NEW
 * This class defines the doors that allow a player to travel between different rooms
 */
public class Door extends Actor implements Collidable, TileObject{
    /**
     * Parameters needed for Door:
     *
     * collisionBox - used to detect when a player walks into a door to travel to the next room
     * tilePosition - used for defining the position of the door in the room
     * direction - used to define the rotation of the door visually
     * connectedRoomId - used to define the room that the door will move the player in and out from
     */
    private Rectangle collisionBox;

    private Vector2Int tilePosition;
    private Direction direction;

    private int connectedRoomId;

    /**
     * Constructor for the door object will take a mapObject found in the asset
     * folder as an input using the information in that to define the object
     * @param mapObject - map file needed to get information about the door
     */
    public Door(RectangleMapObject mapObject){
        collisionBox = mapObject.getRectangle();

        setPosition(collisionBox.getX(), collisionBox.getY());
        tilePosition = new Vector2Int((int)(collisionBox.getX() / Settings.TILE_SIZE),
                (int)(collisionBox.getY() / Settings.TILE_SIZE));

        switch (String.valueOf(mapObject.getProperties().get("direction"))){
            case "EAST":
                direction = Direction.EAST;
                break;

            case "WEST":
                direction = Direction.WEST;
                break;

            case "SOUTH":
                direction = Direction.SOUTH;
                break;

            case "NORTH":
                direction = Direction.NORTH;
                break;
        }

        connectedRoomId = (int) mapObject.getProperties().get("connectedRoomId");
    }

    /**
     * constructor for testing only
     */
    public Door(Direction direction, int connectedRoomId, Vector2Int tilePosition){
        this.collisionBox = new Rectangle();
        this.direction = direction;
        this.connectedRoomId = connectedRoomId;
        this.tilePosition = tilePosition;
    }

    /**
     * Getters and setters needed for use by other classes
     */
    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void setTilePosition(int x, int y) {
        tilePosition.x = x;
        tilePosition.y = y;
    }

    @Override
    public Vector2Int getTilePosition() {
        return tilePosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getConnectedRoomId() {
        return connectedRoomId;
    }
}
