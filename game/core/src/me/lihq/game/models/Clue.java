package me.lihq.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Settings;

import java.util.Set;

/**
 * Created by vishal on 21/11/2016.
 */
public class Clue extends Sprite
{
    private static String imagePath = "clueSheet.png";
    private String clueName = "Super Secret Clue";
    private Vector2Int position;

    //TODO: Clues generate from the killer
    //TODO: Initialise Characters -> Generate Killer -> Generate Clues

    //TODO: Don't we need to associate a clue with a list of applicable NPCs?
    //~Jason
    //=== NEEDS DISCUSSING ===
    //TODO: Does a clue need a room ID as a room has a list of clues in it...
    private int roomID = -1;
    private int imageSrcX;
    private int imageSrcY;

    public Clue(String name, int roomID, int x, int y, int imageSrcX, int imageSrcY)
    {
        super(new Texture(imagePath));
        this.clueName = name;
        this.roomID = roomID;
        this.position.x = x;
        this.position.y = y;
        this.imageSrcX = imageSrcX * Settings.TILE_SIZE;
        this.imageSrcY = imageSrcY * Settings.TILE_SIZE;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Clue) {
            Clue c = (Clue) obj;

            //Might have to do same coordinates AND same room AND same name

            return c.getClueName().equals(this.getClueName());
        }

        return false;
    }

    public String getClueName()
    {
        return this.clueName;
    }

    public void setClueName(String name)
    {
        this.clueName = name;
    }

    public void setCoords(int x, int y)
    {
        this.position.x = x;
        this.position.y = y;

        this.setPosition(x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
    }

    public int getRoomID()
    {
        return roomID;
    }
}
