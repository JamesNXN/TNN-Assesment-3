package me.lihq.game.people.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import me.lihq.game.Settings;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Player;


/**
 * This class allows the game to be played using a controller
 * it binds on top of the PlayerController class so that the game logic
 * does not need to be changed at all to make this addition.
 */
public class GamepadAddon implements ControllerListener {
    /**
     * pointer to player instance
     */
    private Player player;

    /**
     * Booleans storing what keys have been pressed and not released
     */
    private boolean north;
    private boolean south;
    private boolean west;
    private boolean east;

    /**
     * This timer is used to measure how long input has been read for
     */
    public int timer = 0;


    /**
     * boolean for handling controller connects and disconnects
     */

    private boolean controllerConnected = false;

    public GamepadAddon(Player player){
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
        Gdx.app.log("controller", String.valueOf(buttonCode));
        if (buttonCode == XBox360Pad.BUTTON_A){
            player.interact();
            return true;
        }

        if (buttonCode == XBox360Pad.BUTTON_B){
            //// TODO: 31/01/2017 Add ignore function eg A = interact B = back out
            return true;
        }

        if (buttonCode == XBox360Pad.BUTTON_START){
            //// TODO: 31/01/2017 add pausing on start press
            return true;
        }
        return false;
    }

    public boolean buttonUp(Controller controller, int buttonCode) {
        if (buttonCode == XBox360Pad.BUTTON_B){
            //// TODO: 31/01/2017 finalise backout eg after button press up
            return true;
        }

        if (buttonCode == XBox360Pad.BUTTON_START){
            //// TODO: 31/01/2017 add pausing on start press
            return true;
        }
        return false;
    }

    public boolean axisMoved(Controller controller, int axisCode, float value) {
        Gdx.app.log("controller axis", String.valueOf(axisCode).concat(String.valueOf(value)));
        if (axisCode == XBox360Pad.AXIS_LEFT_X && value == -1){
            this.west = true;
            return true;
        }

        if (axisCode == XBox360Pad.AXIS_LEFT_X && value == 0){
            this.west = false;
            this. east = false;
            return true;
        }

        if (axisCode == XBox360Pad.AXIS_LEFT_X && value == 1){
            this.east = true;
            return true;
        }

        if (axisCode == XBox360Pad.AXIS_LEFT_Y && value == 1) {
            this.north = true;
            return true;
        }

        if (axisCode == XBox360Pad.AXIS_LEFT_Y && value == 0) {
            this.north = false;
            this.south = false;
            return true;
        }

        if (axisCode == XBox360Pad.AXIS_LEFT_Y && value == -1){
            this.south = true;
            return true;
        }

        if (value == 0){
            this.south = false;
            this.north = false;
            this.east = false;
            this.west = false;
        }
        return false;
    }

    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        Gdx.app.log("controller axis", String.valueOf(povCode).concat(String.valueOf(value)));
        if (value == XBox360Pad.BUTTON_DPAD_UP){
            this.north = true;
            return true;
        }

        if (value == XBox360Pad.BUTTON_DPAD_RIGHT){
            this.east = true;
            return true;
        }

        if (value == XBox360Pad.BUTTON_DPAD_DOWN){
            this.south = true;
            return true;
        }

        if (value == XBox360Pad.BUTTON_DPAD_LEFT){
            this.west = true;
            return true;
        }
        if (value == PovDirection.center){
            this.west = false;
            this.north = false;
            this.south = false;
            this.east = false;
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
    public void update()
    {
        if (!south && !north && !east && !west) {
            timer = 0;
        }

        AbstractPerson.Direction goTo = null;

        if (north) {
            goTo = AbstractPerson.Direction.NORTH;
        } else if (south) {
            goTo = AbstractPerson.Direction.SOUTH;
        } else if (east) {
            goTo = AbstractPerson.Direction.EAST;
        } else if (west) {
            goTo = AbstractPerson.Direction.WEST;
        }

        if (goTo == null) return;

        timer++;

        if (timer > Settings.TPS / 12) {
            player.move(goTo);
            return;
        }

        if (player.getState() != AbstractPerson.PersonState.WALKING) {
            player.setDirection(goTo);
        }
    }
}