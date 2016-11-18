package me.lihq.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class PlayerController extends InputAdapter {

    private OrthographicCamera camera;

    public PlayerController (OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            this.camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            this.camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            this.camera.translate(0,-32);
        if(keycode == Input.Keys.DOWN)
            this.camera.translate(0,32);
        return false;
    }
}
