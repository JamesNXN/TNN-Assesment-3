/*
* This is the link to the executable jar file created from this project
*
* http://www.lihq.me/Downloads/Assessment2/Game.jar
*
* or visit http://www.lihq.me
* and click "Download Game"
 */

package me.lihq.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.models.Room;
import me.lihq.game.people.NPC;
import me.lihq.game.screen.AbstractScreen;
import me.lihq.game.screen.MainMenuScreen;
import me.lihq.game.screen.NavigationScreen;
import me.lihq.game.screen.PauseScreen;
import me.lihq.game.screen.SplashScreen;

import java.util.*;

/**
 * This is the class responsible for the game as a whole. It manages the current states and entry points of the game
 */
public class GameMain extends Game
{
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 750;

    /**
     * Asset container for referencing assets throughout the game
     */
    public Assets assets;

    public RoomManager roomManager;

    public PersonManager personManager;

    public ClueManager clueManager;


    public MainMenuScreen mainMenuScreen;
    public PauseScreen pauseScreen;
    public NavigationScreen navigationScreen;

    /**
     * An FPSLogger, FPSLogger allows us to check the game FPS is good enough
     */
    FPSLogger FPS;

    /**
     * This is called at start up. It initialises the game.
     */
    @Override
    public void create(){
        assets = new Assets();

        //Set up the SplashScreen
        this.setScreen(new SplashScreen(this));

        //Instantiate the FPSLogger to show FPS
        FPS = new FPSLogger();
    }

    /**
     * This defines what's rendered on the screen for each frame.
     */
    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        FPS.log();//this is where fps is displayed

        super.render(); // This calls the render method of the screen that is currently set
    }

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public void dispose()
    {
        assets.dispose();
    }

    /**
     * Overrides the getScreen method to return our AbstractScreen type.
     * This means that we can access the additional methods like update.
     *
     * @return The current screen of the game.
     */
    @Override
    public AbstractScreen getScreen()
    {
        return (AbstractScreen) super.getScreen();
    }

}
