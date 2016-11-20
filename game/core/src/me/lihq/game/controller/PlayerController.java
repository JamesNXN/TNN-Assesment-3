package me.lihq.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import me.lihq.game.models.Player;

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
        if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A)
            player.move(-1, 0);
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D)
            player.move(1, 0);
        if(keycode == Input.Keys.UP || keycode == Input.Keys.W)
            player.move(0, 1);
        if(keycode == Input.Keys.DOWN || keycode == Input.Keys.S)
            player.move(0, -1);
        return false;
    }
}
