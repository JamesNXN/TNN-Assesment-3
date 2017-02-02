package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.MainMenu;

/**
 * This controls the MainMenuScreen that is the first thing the user sees
 */
public class MainMenuScreen extends AbstractScreen
{
    private Stage stage;

    /**
     * The constructor for the MainMenuScreen
     *
     * @param game - The game it is getting created for
     */
    public MainMenuScreen(GameMain game)
    {
        super(game);

        stage = new Stage(new FitViewport(GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT));

        //Creates a MainMenu object thus creating the main menu
        MainMenu menu = new MainMenu(game);
        stage.addActor(menu);
    }

    /**
     * This method is called to show the MainMenu Screen
     */
    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);
    }


    /**
     * This method is called once a render loop to render the menu
     *
     * @param delta - The time in seconds since the last draw
     */
    @Override
    public void render(float delta)
    {
        stage.act();
        stage.draw();
    }

    /**
     * This is called when the window is resized
     *
     * @param width  - The new width
     * @param height - The new height
     */
    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height);
    }

    /**
     * This is called when the focus is lost on the window
     */
    @Override
    public void pause()
    {

    }

    /**
     * This method is called when the window is brought back into focus
     */
    @Override
    public void resume()
    {

    }

    /**
     * This method is called when the user hides the window
     */
    @Override
    public void hide()
    {

    }

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public void dispose()
    {
        stage.dispose();
    }


}
