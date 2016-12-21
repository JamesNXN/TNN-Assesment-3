package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;

/**
 * Created by joeshuff on 21/12/2016.
 */
public class RoomTag {

    private String roomName = "";

    private Vector2 position = new Vector2(25f, 0f);

    private boolean visible = false;

    private float MAX_ANIM_TIME = Settings.TPS / 1.5f;

    private float animTime = 0f;

    private float MAX_TIME_SHOWN = Settings.TPS * 5;

    private float timeShown = 0f;

    public RoomTag(String roomName)
    {
        this.roomName = roomName;
        visible = true;
    }

    public void render(SpriteBatch batch)
    {
        boolean toClose = false;

        if (!batch.isDrawing())
        {
            batch.begin();
            toClose = true;
        }

        batch.draw(Assets.TAG_BORDER, position.x, Gdx.graphics.getHeight() - position.y, 350, 150);

        BitmapFont font = new BitmapFont();
        font.draw(batch, roomName, 30, 30);

        if (toClose)
        {
            batch.end();
        }
    }

    public void update()
    {
        if (animTime <= MAX_ANIM_TIME)
        {
            animTime ++;
        }
        else if (timeShown <= MAX_TIME_SHOWN)
        {
            timeShown ++;
        }
        else
        {
            animTime ++;

            if (animTime >= 2 * MAX_ANIM_TIME)
            {
                GameMain.me.getNavigationScreen().setRoomTag(null);
            }
        }

        this.position.y = Interpolation.pow4.apply(0f, 175f, animTime / MAX_ANIM_TIME);
    }
}
