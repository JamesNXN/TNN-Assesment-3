package me.lihq.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.*;


/**
 * This class defines the clues that the player needs to find in order to solve the murder.
 */
public class Clue extends Actor implements Collidable, TileObject {
    /**
     * Parameters needed for clues:
     *
     * name - name of the clue
     * decription - description of the clue
     * relatedNpcIdArray - array of NPC id's to link clues to NPC's
     * tileCoordinates - Vector2Int object that contains the x and y coordinate for the clue in a room, set by clue manager
     * clueGlint - animated texture that contains the animation needed for the clueGlint
     * animStateTime - the time state of the clueGlint animation needed to render the animated texture
     * collisionBox - a rectangle box needed by LibGDX to calculate collisions between actors
     * clueType - a enum value used to determine whether the clue is a weapon, motive or other clue
     */
    private String name;

    private String description;

    private Array<Integer> relatedNpcIdArray;

    private Vector2Int tileCoordinates = new Vector2Int(0, 0);

    private Animation<TextureRegion> clueGlint;

    private float animStateTime = 0;

    private Rectangle collisionBox;

    private ClueType clueType;

    /**
     * Constructor for creating Clue objects from json data and textures
     * @param jsonData - json data loaded through the asset loader for each clue
     * @param clueGlintAtlas - a texture atlas for the animated textures
     */

    public Clue(JsonValue jsonData, TextureAtlas clueGlintAtlas)
    {
        Json json = new Json();
        this.name = jsonData.getString("name");
        this.description = jsonData.getString("description");
        this.relatedNpcIdArray = json.readValue(Array.class, jsonData.get("relatedNpcId"));
        this.clueType = json.readValue(ClueType.class, jsonData.get("clueType"));

        clueGlint = new Animation<>(0.1f, clueGlintAtlas.findRegions("glint"));
        setSize(Settings.TILE_SIZE, Settings.TILE_SIZE);

        collisionBox = new Rectangle();
        collisionBox.setSize(Settings.TILE_SIZE);
    }

    /**
     * constructor used for testing of methods without Json
     */
    public Clue(String name, String description, Array<Integer> relatedNpcIdArray, ClueType clueType){
        this.name = name;
        this.description = description;
        this.relatedNpcIdArray = relatedNpcIdArray;
        this.clueType = clueType;

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
     * Getters and setters needed for use in other classes
     */
    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public Array<Integer> getRelatedNpcIdArray(){
        return relatedNpcIdArray;
    }

    public ClueType getClueType() { return clueType;}

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public Vector2Int getTilePosition() {
        return tileCoordinates;
    }

    @Override
    public void setTilePosition(int x, int y) {
        tileCoordinates.x = x;
        tileCoordinates.y = y;

        setPosition(getTilePosition().x * Settings.TILE_SIZE,
                getTilePosition().y * Settings.TILE_SIZE);

        collisionBox.setPosition(getX(), getY());
    }

    /**
     * act and draw methods needed to render the clue in game through LibGDX
     */
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
}
