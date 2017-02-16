package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import me.lihq.game.Settings;
import me.lihq.game.people.Direction;
import me.lihq.game.people.PersonState;
import me.lihq.game.people.Player;

/**
 * This class allows the player to be moved and controlled.
 */
public class PlayerController extends InputAdapter
{
    /**
     * This stores the player that the controller controls
     */
    private Player player;

    /**
     * Constructor to create the PlayerController to control the provided Player
     *
     * @param player - The player that we want this controller to control
     */
    public PlayerController(Player player)
    {
        this.player = player;
    }

    /**
     * This method is called when a key press is read
     *
     * @param keycode - The code of the key pressed
     * @return (boolean) Whether this method acted upon the keypress or not. Used for InputMultiplexers
     */
    @Override
    public boolean keyDown(int keycode)
    {
        if (player.isCanMove()) {
            if (player.getState() == PersonState.STANDING) {
                if (keycode == Input.Keys.SPACE) {
                    player.interact();
                    return true;
                }

                else if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                    player.setDirection(Direction.WEST);
                    player.setState(PersonState.WALKING);
                    return true;
                }

                else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                    player.setDirection(Direction.EAST);
                    player.setState(PersonState.WALKING);
                    return true;
                }

                else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                    player.setDirection(Direction.NORTH);
                    player.setState(PersonState.WALKING);
                    return true;
                }


                else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                    player.setDirection(Direction.SOUTH);
                    player.setState(PersonState.WALKING);
                    return true;
                }
            }
        }

        //TODO: The following 3 key reads could do with being placed in another controller
        if (keycode == Input.Keys.J) {
            Settings.DEBUG_OPTIONS.put("showWalkable", !Settings.DEBUG_OPTIONS.get("showWalkable"));
            return true;
        }

        if (keycode == Input.Keys.H) {
            Settings.DEBUG_OPTIONS.put("showHideable", !Settings.DEBUG_OPTIONS.get("showHideable"));
            return true;
        }

        if (keycode == Input.Keys.F3) {
            Settings.DEBUG = !Settings.DEBUG;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            player.setState(PersonState.STANDING);
            return true;
        }

        else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            player.setState(PersonState.STANDING);
            return true;
        }

        else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            player.setState(PersonState.STANDING);
            return true;
        }


        else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            player.setState(PersonState.STANDING);
            return true;
        }
        return false;
    }
}
