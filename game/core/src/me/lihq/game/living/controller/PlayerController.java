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
    private boolean north, south, west, east;
    private Player player;

    public PlayerController(Player player)
    {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            this.west = true;
            return true;
        }

        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            this.east = true;
            return true;
        }


        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            this.north = true;
            return true;
        }

        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            this.south = true;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            this.west = false;
            return true;
        }

        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            this.east = false;
            return true;
        }


        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            this.north = false;
            return true;
        }

        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            this.south = false;
            return true;
        }

        return false;
    }

    public void update(float delta)
    {
        if (north) {
            player.move(DIRECTION.NORTH);
            return;
        }
        if (south) {
            player.move(DIRECTION.SOUTH);
            return;
        }
        if (east) {
            player.move(DIRECTION.EAST);
            return;
        }
        if (west) {
            player.move(DIRECTION.WEST);
            return;
        }

    }
}
