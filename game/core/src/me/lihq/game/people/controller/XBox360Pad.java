package me.lihq.game.people.controller;


import com.badlogic.gdx.controllers.PovDirection;
/**
 * This code was taken from http://www.java-gaming.org/index.php?topic=29223.0
 * With thanks that is! It gives us codes for use with the game pad controller
 */
public class XBox360Pad {


    public static final int BUTTON_A = 0;
    public static final int BUTTON_B = 1;
    public static final int BUTTON_X = 2;
    public static final int BUTTON_Y = 3;

    public static final int BUTTON_LB = 4;
    public static final int BUTTON_RB = 5;

    public static final int BUTTON_BACK = 6;
    public static final int BUTTON_START = 7;

    public static final int BUTTON_L3 = 8;
    public static final int BUTTON_R3 = 9;

    public static final int BUTTON_POV_UP = 10;
    public static final int BUTTON_POV_RIGHT = 11;
    public static final int BUTTON_POV_DOWN = 12;
    public static final int BUTTON_POV_LEFT = 13;
    public static final PovDirection BUTTON_DPAD_UP = PovDirection.north;
    public static final PovDirection BUTTON_DPAD_DOWN = PovDirection.south;
    public static final PovDirection BUTTON_DPAD_RIGHT = PovDirection.east;
    public static final PovDirection BUTTON_DPAD_LEFT = PovDirection.west;
    public static final PovDirection DPAD_CENTER = PovDirection.center;

    public static final int AXIS_LEFT_Y = 0; //-1 is up | +1 is down
    public static final int AXIS_LEFT_X = 1; //-1 is left | +1 is right

    public static final int AXIS_RIGHT_X = 2; //-1 is left | +1 is right
    public static final int AXIS_RIGHT_Y = 3; //-1 is up | +1 is down

    public static final int AXIS_LEFT_TRIGGER = 4; //value 0 to 1f
    public static final int AXIS_RIGHT_TRIGGER = 5; //value 0 to -1f
}