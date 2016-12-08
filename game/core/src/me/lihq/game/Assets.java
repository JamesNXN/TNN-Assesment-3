package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class defines the assets that the game uses.
 */
public class Assets
{

    public static Texture items;
    public static Texture clues;
    public static Texture opening;

    public static TextureRegion introFrame1;
    public static TextureRegion introFrame2;

    public static TextureRegion npc1;

    public static Texture loadTexture(String file)
    {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load()
    {
        opening = loadTexture("title.png");
        introFrame1 = new TextureRegion(opening, 0, 0, 1000, 750);
        introFrame2 = new TextureRegion(opening, 0, 750, 1000, 750);

        //Temporary as it errors if it doesnt have a file
        items = loadTexture("player.png");
    }

}
