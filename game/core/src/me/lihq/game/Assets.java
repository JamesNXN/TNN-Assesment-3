package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * This class defines the assets that the game uses.
 */
public class Assets
{
    /**
     * This is the asset sheet for clues
     */
    public static Texture CLUE_SHEET;

    /**
     * This is the texture of splashscreen frames
     */
    public static Texture OPENING;

    /**
     * The 2 seperate frames for the splashscreen
     */
    public static TextureRegion INTROFRAME1;
    public static TextureRegion INTROFRAME2;

    /**
     * These TextureRegions store the 4 different directions that the room changing
     * arrows can face.
     */
    public static TextureRegion UP_ARROW;
    public static TextureRegion DOWN_ARROW;
    public static TextureRegion LEFT_ARROW;
    public static TextureRegion RIGHT_ARROW;

    /**
     * This is the asset for the RoomTag {@link me.lihq.game.screen.elements.RoomTag}
     */
    public static Texture TAG_BORDER;

    /**
     * This is the default font for the game. Used in RoomTag
     */
    public static BitmapFont FONT;

    /**
     * This it the animation for the clue glint to be drawn where a clue is hidden
     */
    public static Animation CLUE_GLINT;

    /**
     * @param file - The file that contains the textures.
     * @return Returns the new texture.
     */
    public static Texture loadTexture(String file)
    {
        return new Texture(Gdx.files.internal(file));
    }

    /**
     * Loads all the elements the game needs such as the player.
     */
    public static void load()
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fofer.otf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 45;
        FONT = generator.generateFont(parameter);
        generator.dispose();

        OPENING = loadTexture("title.png");
        INTROFRAME1 = new TextureRegion(OPENING, 0, 0, 1000, 750);
        INTROFRAME2 = new TextureRegion(OPENING, 0, 750, 1000, 750);

        Texture arrows = loadTexture("arrows.png");
        LEFT_ARROW = new TextureRegion(arrows, 0, 0, 32, 32);
        RIGHT_ARROW = new TextureRegion(arrows, 32, 0, 32, 32);
        DOWN_ARROW = new TextureRegion(arrows, 0, 32, 32, 32);
        UP_ARROW = new TextureRegion(arrows, 32, 32, 32, 32);

        TAG_BORDER = loadTexture("border.png");

        CLUE_SHEET = loadTexture("clueSheet.png");

        Texture glintFile = loadTexture("glintSheet.png");
        TextureRegion[][] splitFrames = TextureRegion.split(glintFile, 32, 32);
        TextureRegion[] frames = splitFrames[0];

        CLUE_GLINT = new Animation(0.1f, frames);
    }

    /**
     * This method takes a direction and returns the corresponding arrow asset for that direction
     *
     * @param direction - The direction to fetch
     * @return (TextureRegion) the corresponding TextureRegion
     */
    public static TextureRegion getArrowDirection(String direction)
    {
        if (direction.equals("NORTH")) {
            return UP_ARROW;
        } else if (direction.equals("SOUTH")) {
            return DOWN_ARROW;
        } else if (direction.equals("WEST")) {
            return LEFT_ARROW;
        } else if (direction.equals("EAST")) {
            return RIGHT_ARROW;
        }

        return null;
    }

    /**
     * This method gets the default font but at the requested size
     *
     * @param size - The size you want the font to be
     * @return (BitmapFont) the resulting font
     */
    public static BitmapFont getFontWithSize(int size)
    {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Fofer.otf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

}
