package me.lihq.game.screen;

import com.badlogic.gdx.Screen;
import me.lihq.game.GameMain;


/**
 * Created by brookehatton on 17/11/2016.
 */

public abstract class AbstractScreen implements Screen {

    private GameMain game;

    public AbstractScreen (GameMain game) {
        this.game = game;
    }

    @Override
    public abstract void show();

    @Override
    public abstract void render(float delta);

    @Override
    public abstract void resize(int width, int height);

    @Override
    public abstract void pause();

    @Override
    public abstract void resume();

    @Override
    public abstract void hide();

    @Override
    public abstract void dispose();

    public GameMain getGame() {
        return game;
    }
}
