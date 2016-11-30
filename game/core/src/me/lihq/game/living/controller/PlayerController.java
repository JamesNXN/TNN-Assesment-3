package me.lihq.game.living.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import me.lihq.game.living.Player;

import static me.lihq.game.living.AbstractPerson.*;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class PlayerController extends InputAdapter
{

    private Player player;

    public PlayerController(Player player)
    {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            player.setDirection(DIRECTION.WEST);
            player.move = true;
            return true;
        }

        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            player.setDirection(DIRECTION.EAST);
            player.move = true;
            return true;
        }


        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            player.setDirection(DIRECTION.NORTH);
            player.move = true;
            return true;
        }

        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            player.setDirection(DIRECTION.SOUTH);
            player.move = true;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            player.setDirection(DIRECTION.WEST);
            player.move = false;
            return true;
        }

        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            player.setDirection(DIRECTION.EAST);
            player.move = false;
            return true;
        }


        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            player.setDirection(DIRECTION.NORTH);
            player.move = false;
            return true;
        }

        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            player.setDirection(DIRECTION.SOUTH);
            player.move = false;
            return true;
        }

        return false;
    }
}
