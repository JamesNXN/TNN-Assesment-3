package me.lihq.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import me.lihq.game.model.Player;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class PlayerController extends InputAdapter {

    private Player player;

    public PlayerController (Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            player.move(-1, 0);
        if(keycode == Input.Keys.RIGHT)
            player.move(1, 0);
        if(keycode == Input.Keys.UP)
            player.move(0, 1);
        if(keycode == Input.Keys.DOWN)
            player.move(0, -1);
        return false;
    }
}
