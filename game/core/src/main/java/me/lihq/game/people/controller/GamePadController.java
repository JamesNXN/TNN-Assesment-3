package me.lihq.game.people.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import me.lihq.game.Settings;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Direction;
import me.lihq.game.people.PersonState;
import me.lihq.game.people.Player;


/**
 * This class allows the game to be played using a controller
 * it binds on top of the PlayerController class so that the game logic
 * does not need to be changed at all to make this addition.
 */
public class GamePadController implements ControllerListener {
    /**
     * pointer to player instance
     */
    private Player player;


    /**
     * boolean for handling controller connects and disconnects
     */

    private boolean controllerConnected = false;

    /**
     * boolean used for ensuring axis movement does not overlap eg x movement doesn't interfere
     * with y movement
     */
    private boolean xAxisInUse = false;
    private boolean yAxisInUse = false;

    /**
     * constructor for gamepad used to initialise for controller listener
     *
     * @param player
     */
    public GamePadController(Player player) {
        this.player = player;
    }

    public void connected(Controller controller) {
        controllerConnected = true;
        //// TODO: 31/01/2017 add a gui element to display when controller connects and disconects
    }

    public void disconnected(Controller controller) {
        controllerConnected = false;
    }

    public boolean buttonDown(Controller controller, int buttonCode) {
        Gdx.app.log("controller buttons", String.valueOf(buttonCode));
        if (buttonCode == XBox360Pad.BUTTON_A){
            player.interact();
            return true;
        }
        else if (buttonCode == XBox360Pad.BUTTON_B) {
            ////// TODO: 05/02/2017 add ignore for b button when implemented or running
            return true;

        }
        else if (buttonCode == XBox360Pad.BUTTON_START) {
            //// TODO: 05/02/2017 add pause on start press
            return true;
        }
        else if (buttonCode == XBox360Pad.BUTTON_X) {
            //// TODO: 05/02/2017 display inventory
            return true;
        }
        else if (buttonCode == XBox360Pad.BUTTON_Y) {
            //// TODO: 05/02/2017 display map
            return true;
        }
        return false;
    }

    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

//    public boolean axisMoved(Controller controller, int axisCode, float value){
//        return false;
//    }

    public boolean axisMoved(Controller controller, int axisCode, float value) {
        int newValue = (int) (value * 10f);
//        Gdx.app.log("controller AXIS code", String.valueOf(axisCode));
//        Gdx.app.log("controller AXIS float", String.valueOf(newValue);
        if (axisCode == XBox360Pad.AXIS_LEFT_X  && yAxisInUse == false) {
            //Gdx.app.log("controller axis value X", String.valueOf(newValue));
            if (newValue > 5 && xAxisInUse == false) {
                xAxisInUse = true;
                player.setDirection(Direction.EAST);
                player.setState(PersonState.WALKING);
                return true;
            }
            else if (newValue < -5 && xAxisInUse == false) {
                xAxisInUse = true;
                player.setDirection(Direction.WEST);
                player.setState(PersonState.WALKING);
                return true;
            }
            else if (newValue == 0 && xAxisInUse == true) {
                player.setState(PersonState.STANDING);
                xAxisInUse = false;
                return true;
            }
        }
        else if (axisCode == XBox360Pad.AXIS_LEFT_Y && xAxisInUse == false) {
            //Gdx.app.log("controller axis value Y", String.valueOf(newValue));
            if (newValue > 5 && yAxisInUse == false) {
                yAxisInUse = true;
                player.setDirection(Direction.SOUTH);
                player.setState(PersonState.WALKING);
                return true;
            }
            else if (newValue < -5 && yAxisInUse == false) {
                yAxisInUse = true;
                player.setDirection(Direction.NORTH);
                player.setState(PersonState.WALKING);
                return true;
            }
            else if (newValue == 0 && yAxisInUse == true) {
                player.setState(PersonState.STANDING);
                yAxisInUse = false;
                return true;
            }
        }
        return false;
    }

    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        Gdx.app.log("controller DPAD", String.valueOf(value));
        if (value == XBox360Pad.BUTTON_DPAD_UP) {
            player.setDirection(Direction.NORTH);
            player.setState(PersonState.WALKING);
            return true;
        }
        else if (value == XBox360Pad.BUTTON_DPAD_RIGHT ) {
            player.setDirection(Direction.EAST);
            player.setState(PersonState.WALKING);
            return true;
        }
        else if (value == XBox360Pad.BUTTON_DPAD_DOWN) {
            player.setDirection(Direction.SOUTH);
            player.setState(PersonState.WALKING);
            return true;
        }
        else if (value == XBox360Pad.BUTTON_DPAD_LEFT) {
            player.setDirection(Direction.WEST);
            player.setState(PersonState.WALKING);
            return true;
        }
        else if (value == XBox360Pad.DPAD_CENTER) {
            player.setState(PersonState.STANDING);
            return true;
        }
        return false;
    }

    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return true;
    }

    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return true;
    }

    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return true;
    }
}