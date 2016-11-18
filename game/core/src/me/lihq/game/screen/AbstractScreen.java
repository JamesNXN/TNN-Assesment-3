package me.lihq.game.screen;

import com.badlogic.gdx.Screen;
import me.lihq.game.GameMain;

/**
 * Created by brookehatton on 17/11/2016.
 */

public class AbstractScreen implements Screen {

    GameMain game;

    public AbstractScreen (GameMain game) {
        this.game = game;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
