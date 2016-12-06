package me.lihq.game.living.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import me.lihq.game.Settings;
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

    //TODO: Add short clicks just changing direction

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

    public int timer = 0;

    public void update()
    {
        if (!south && !north && !east && !west)
        {
            timer = 0;
        }

        Direction goTo = null;

        if (north) {
            goTo = Direction.NORTH;
        }
        else if (south) {
            goTo = Direction.SOUTH;
        }
        else if (east) {
            goTo = Direction.EAST;
        }
        else if (west) {
            goTo = Direction.WEST;
        }

        if (goTo == null) return;

        timer ++;

        if (timer > Settings.TPS / 12)
        {
            player.move(goTo);
            return;
        }

        player.setDirection(goTo);
    }
}
