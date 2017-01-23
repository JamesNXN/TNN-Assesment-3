package me.lihq.game.screen;

import com.badlogic.gdx.Screen;
import me.lihq.game.GameMain;


/**
 * This is defines all the common methods and properties for each screen. It implements the screen interface from com.badlogic.gdx.Screen.
 * A screen is a like the current view of the game. E.g. Main Menu or Pause screen or dialogue screen.
 */
public abstract class AbstractScreen implements Screen
{
    /**
     * The game that the Screen is live in
     */
    protected GameMain game;

    /**
     * This constructor sets the relevant properties of the class.
     *
     * @param game this provides access to the gameMain class so that screens can set the states of the game.
     */
    public AbstractScreen(GameMain game)
    {
        this.game = game;
    }

    /**
     * This method overrides the Screen method show()
     */
    @Override
    public abstract void show();

    /**
     * Called every game tick, all screen related logic should be done in this method.
     */
    public abstract void update();

    /**
     * This method is called every render loop
     *
     * @param delta - The time in seconds since the last draw
     */
    @Override
    public abstract void render(float delta);

    /**
     * This method is called on window resize
     *
     * @param width  - The new width
     * @param height - The new height
     */
    @Override
    public abstract void resize(int width, int height);

    /**
     * This is called when the focus is lost on the window
     */
    @Override
    public abstract void pause();

    /**
     * This method is called when the window is brought back into focus
     */
    @Override
    public abstract void resume();

    /**
     * This method is called when the user hides the window
     */
    @Override
    public abstract void hide();

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public abstract void dispose();

}
