package me.lihq.game.models;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.lihq.game.Collidable;
import me.lihq.game.Settings;
import me.lihq.game.TileObject;
import me.lihq.game.people.Direction;

public class Door extends Actor implements Collidable, TileObject{
    private Rectangle collisionBox;

    private Vector2Int tilePosition;
    private Direction direction;

    private int connectedRoomId;

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
     * constructor for testing
     */
    public Door(Direction direction, int connectedRoomId, Vector2Int tilePosition){
        this.collisionBox = new Rectangle();
    }

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
