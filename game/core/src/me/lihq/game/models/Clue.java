package me.lihq.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.lihq.game.Settings;


/**
 * This class defines the clues that the player needs to find in order to solve the murder.
 */
public class Clue extends Sprite
{
    private String name;
    private String description;
    private Vector2Int tileCoordinates = new Vector2Int(0,0);

    public Clue(String name, String description, TextureRegion texture, int tileX, int tileY)
    {
        super(texture);
        this.name = name;
        this.description = description;
        this.tileCoordinates.x = tileX;
        this.tileCoordinates.y = tileY;
        this.setPosition(tileCoordinates.x * Settings.TILE_SIZE, tileCoordinates.y * Settings.TILE_SIZE);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Clue) {
            Clue c = (Clue) obj;

            //Might have to do same coordinates AND same room AND same name

            return c.getName().equals(this.getName());
        }

        return false;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }


    public void setTileCoordinates(int x, int y)
    {
        this.tileCoordinates.x = x;
        this.tileCoordinates.y = y;

        this.setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

}
