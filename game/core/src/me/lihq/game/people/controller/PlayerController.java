package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import me.lihq.game.Settings;
import me.lihq.game.people.Player;

import static me.lihq.game.people.AbstractPerson.*;

/**
 * This class allows the player to be moved and controlled.
 */
public class PlayerController extends InputAdapter
{
    public int timer = 0;
    private boolean north, south, west, east;
    private Player player;

    private boolean interact = false;

    public PlayerController(Player player)
    {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.ENTER)
        {
            interact = true;
            return true;
        }

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

        //TODO: The following 3 key reads could do with being placed in another controller
        if (keycode == Input.Keys.J)
        {
            Settings.DEBUG_OPTIONS.put("showWalkable", !Settings.DEBUG_OPTIONS.get("showWalkable"));
            return true;
        }

        if (keycode == Input.Keys.H)
        {
            Settings.DEBUG_OPTIONS.put("showHideable", !Settings.DEBUG_OPTIONS.get("showHideable"));
            return true;
        }

        if (keycode == Input.Keys.F3)
        {
            Settings.DEBUG = !Settings.DEBUG;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        if (keycode == Input.Keys.ENTER)
        {
            interact = false;
            return true;
        }

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

    public void update()
    {
        if (interact)
        {
            player.checkForClue();
            interact = false;
        }

        if (!south && !north && !east && !west) {
            timer = 0;
        }

        Direction goTo = null;

        if (north) {
            goTo = Direction.NORTH;
        } else if (south) {
            goTo = Direction.SOUTH;
        } else if (east) {
            goTo = Direction.EAST;
        } else if (west) {
            goTo = Direction.WEST;
        }

        if (goTo == null) return;

        timer++;

        if (timer > Settings.TPS / 12) {
            player.move(goTo);
            return;
        }

        if (player.getState() != PersonState.WALKING) {
            player.setDirection(goTo);
        }
    }
}
