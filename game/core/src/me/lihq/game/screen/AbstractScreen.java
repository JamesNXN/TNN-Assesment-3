package me.lihq.game.screen;

import com.badlogic.gdx.Screen;
import me.lihq.game.GameMain;


/**
 * This is defines all the common methods and properties for each screen. It implements the screen interface from com.badlogic.gdx.Screen.
 * A screen is a like the current view of the game. E.g. Main Menu or Pause screen or dialogue screen.
 */
public abstract class AbstractScreen implements Screen
{

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

    @Override
    public abstract void show();


    /**
     * Called every game tick, all screen related logic should be done in this method.
     */
    public abstract void update();

    @Override
    public abstract void render(float delta);

    @Override
    public abstract void resize(int width, int height);

    @Override
    public abstract void pause();

    @Override
    public abstract void resume();

    @Override
    public abstract void hide();

    @Override
    public abstract void dispose();

}
