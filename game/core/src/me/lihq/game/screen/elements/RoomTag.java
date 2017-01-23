package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;

/**
 * This class is for the RoomTag that is to be displayed at the top left
 * of the screen when a new room is entered.
 */
public class RoomTag
{
    /**
     * This is the room name it is to display
     */
    private String roomName = "";

    /**
     * This is the position of the window relative to the top left of the window
     * <p>
     * The rendering calculation is done by the render method
     */
    private Vector2 position = new Vector2(25f, 0f);

    /**
     * This is the amount of time it takes for the tag to both float in and float out.
     */
    private float MAX_ANIM_TIME = Settings.TPS / 1.5f;

    /**
     * The current amount of time the animation has taken
     */
    private float animTime = 0f;

    /**
     * The max amount of time the window should stay rendered onto the screen
     */
    private float MAX_TIME_SHOWN = Settings.TPS * 5;

    /**
     * The current amount of time the window has been shown.
     */
    private float timeShown = 0f;

    /**
     * Initiate a new RoomTag. When the variable roomTag in NavigationScreen is NOT NULL, it is updated and rendered.
     *
     * @param roomName - The name to display.
     */
    public RoomTag(String roomName)
    {
        this.roomName = roomName;
    }

    /**
     * This method renders the room tag to the provided SpriteBatch.
     * <p>
     * It is called by the screens render method.
     *
     * @param batch - The SpriteBatch to draw the tag to
     */
    public void render(SpriteBatch batch)
    {
        boolean toClose = false;

        if (!batch.isDrawing()) {
            batch.begin();
            toClose = true;
        }

        //10 Characters fits in the middle of the default size.
        //Therefore, change the size of the box depending on amount of characters

        int extraCharacters = roomName.length() - 10;

        batch.draw(Assets.TAG_BORDER, position.x, Gdx.graphics.getHeight() - position.y, 350 + (15 * extraCharacters), 150);

        Assets.FONT.setColor(Color.WHITE);
        Assets.FONT.draw(batch, roomName, position.x * 5.1f, Gdx.graphics.getHeight() - position.y + 75);

        if (toClose) {
            batch.end();
        }
    }

    /**
     * This method is called once per game tick. It is used to control the animation of the tag.
     */
    public void update()
    {
        if (animTime <= MAX_ANIM_TIME) {
            animTime++;
        } else if (timeShown <= MAX_TIME_SHOWN) {
            timeShown++;
        } else {
            animTime++;

            if (animTime >= 2 * MAX_ANIM_TIME) {
                GameMain.me.getNavigationScreen().setRoomTag(null);
            }
        }

        this.position.y = Interpolation.pow2.apply(0f, 175f, animTime / MAX_ANIM_TIME);
    }
}
