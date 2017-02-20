package me.lihq.game.people.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import me.lihq.game.GameMain;
import me.lihq.game.gui.ConversationSpeechBubble;
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
    private GameMain game;
    private Player player;

    /**
     * Constructor to create the PlayerController to control the provided Player
     *
     * @param game - The game to be attached
     */
    public PlayerController(GameMain game)
    {
        this.game = game;
        this.player = game.gameWorld.getPlayer();
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

        // player press space to get the conversation flowing, but it should stop when the game is
        // waiting for player input for interaction option
        else if (player.isInConversation()
                && game.gameWorld.getConversationManager().getCurrentConversation() instanceof ConversationSpeechBubble){
            if (keycode == Input.Keys.SPACE){
                game.gameWorld.getConversationManager().nextSpeechBubble();
            }
        }

        return false;
    }

    /**
     * This method is called when a key is released
     * @param keycode - the keycode of the key that was released
     * @return (boolean) - Returns true if the event was handled correctly and false if not
     */
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
        return true;
    }
}
