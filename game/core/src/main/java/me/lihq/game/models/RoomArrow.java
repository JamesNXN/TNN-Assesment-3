package me.lihq.game.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import me.lihq.game.Collidable;
import me.lihq.game.Settings;
import me.lihq.game.TileObject;
import me.lihq.game.models.Door;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.Direction;

/**
 * This is the arrow the indicates the movement to a new room when the player is on a floor mat.
 */
public class RoomArrow extends Actor implements TileObject, Collidable
{
    private TextureRegion image;
    private Direction direction;
    private Vector2Int tilePosition;

    private Rectangle collisionBox;

    private boolean isPlayerOn = false;

    public RoomArrow(Door exit, TextureAtlas arrowAtlas)
    {
        direction = exit.getDirection();
        image = new TextureRegion(getArrowTexture(arrowAtlas, direction));
        setSize(Settings.TILE_SIZE, Settings.TILE_SIZE);
        tilePosition = new Vector2Int();
        setTilePosition(exit.getTilePosition().x - direction.getDx(),
                exit.getTilePosition().y - direction.getDy());


        collisionBox = new Rectangle(getX(), getY(), exit.getCollisionBox().getWidth(), exit.getCollisionBox().getHeight());
        setVisible(false);
    }

    private TextureRegion getArrowTexture(TextureAtlas atlas, Direction direction){
        TextureRegion arrowTexture = null;
        switch (direction){
            case EAST:
                arrowTexture = new TextureRegion(atlas.findRegion("rightArrow"));
                break;

            case WEST:
                arrowTexture = new TextureRegion(atlas.findRegion("leftArrow"));
                break;

            case NORTH:
                arrowTexture = new TextureRegion(atlas.findRegion("upArrow"));
                break;

            case SOUTH:
                arrowTexture = new TextureRegion(atlas.findRegion("downArrow"));
                break;
        }
        return arrowTexture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // draw it in the center
        batch.draw(image, getX() + collisionBox.getWidth() / 2 - getWidth() / 2,
                getY() + collisionBox.getHeight() / 2 - getHeight() / 2, getWidth(), getHeight());
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void setTilePosition(int x, int y) {
        tilePosition.x = x;
        tilePosition.y = y;

        setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

    @Override
    public Vector2Int getTilePosition() {
        return tilePosition;
    }
}
