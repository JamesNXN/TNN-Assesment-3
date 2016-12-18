package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

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

    public static TextureRegion UP_ARROW;
    public static TextureRegion DOWN_ARROW;
    public static TextureRegion LEFT_ARROW;
    public static TextureRegion RIGHT_ARROW;

    public static Texture loadTexture(String file)
    {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load()
    {
        opening = loadTexture("title.png");
        introFrame1 = new TextureRegion(opening, 0, 0, 1000, 750);
        introFrame2 = new TextureRegion(opening, 0, 750, 1000, 750);

        Texture arrows = loadTexture("arrows.png");
        LEFT_ARROW = new TextureRegion(arrows, 0, 0, 32, 32);
        RIGHT_ARROW = new TextureRegion(arrows, 32, 0, 32, 32);
        DOWN_ARROW = new TextureRegion(arrows, 0, 32, 32, 32);
        UP_ARROW = new TextureRegion(arrows, 32, 32, 32, 32);
    }

    public static TextureRegion getArrowDirection(String direction)
    {
        if (direction.equals("NORTH"))
        {
            return UP_ARROW;
        }
        else if (direction.equals("SOUTH"))
        {
            return DOWN_ARROW;
        }
        else if (direction.equals("WEST"))
        {
            return LEFT_ARROW;
        }
        else if (direction.equals("EAST"))
        {
            return RIGHT_ARROW;
        }

        return null;
    }

}
