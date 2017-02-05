package me.lihq.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import me.lihq.game.Assets;
import me.lihq.game.Collidable;
import me.lihq.game.Settings;
import me.lihq.game.TileObject;


/**
 * This class defines the clues that the player needs to find in order to solve the murder.
 */
public class Clue extends Actor implements Collidable, TileObject
{
    /**
     * The name of the clue, set when you initialise the clue and gettable using {@link #getName()}
     */
    private String name;

    /**
     * The description of the clue, set when you initialise the clue and gettable using {@link #getDescription()}
     */
    private String description;

    /**
     * This is the location on the map in terms of tiles can be set using {@link #setTilePosition(int, int)}
     * Note: this is different to com.badlogic.gdx.graphics.g2d.Sprite.position that is the position on the screen in terms of pixels,
     * whereas this is in terms of map tiles relative to the bottom left of the map.
     */
    private Vector2Int tileCoordinates = new Vector2Int(0, 0);

    private Animation<TextureRegion> clueGlint;
    private float animStateTime = 0;

    private Rectangle collisionBox;


    /**
     * Creates a clue
     *
     * @param name        the name of the clue i.e. what it is
     * @param description describes what the clue is
     * @param assets     the asset loader for textures
     */
    public Clue(String name, String description, Assets assets)
    {
        debug();
        this.name = name;
        this.description = description;

        clueGlint = new Animation<>(0.1f, assets.clueGlint.findRegions("glint"));
        setSize(Settings.TILE_SIZE, Settings.TILE_SIZE);

        collisionBox = new Rectangle();
        collisionBox.setSize(Settings.TILE_SIZE);
    }

    /**
     * This method checks equality of this Clue object and another object.
     *
     * @param obj - The clue object.
     * @return - Returns True if it is of the type Clue and the names are exactly the same
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Clue) {
            Clue c = (Clue) obj;
            return c.getName().equals(this.getName());
        }

        return false;
    }

    /**
     * Getter for Clue name.
     *
     * @return - (String) Returns name of clue.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for clue description
     *
     * @return - (String) Returns the description of the clue.
     */
    public String getDescription()
    {
        return this.description;
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void act(float delta) {
        animStateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = batch.getColor();
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);

        batch.draw(clueGlint.getKeyFrame(animStateTime,true), getX(), getY(), Settings.TILE_SIZE, Settings.TILE_SIZE);
        batch.setColor(color);
    }

    @Override
    public void setTilePosition(int x, int y) {
        tileCoordinates.x = x;
        tileCoordinates.y = y;

        setPosition(getTilePosition().x * Settings.TILE_SIZE,
                getTilePosition().y * Settings.TILE_SIZE);

        collisionBox.setPosition(getX(), getY());
    }

    @Override
    public Vector2Int getTilePosition() {
        return tileCoordinates;
    }
}
