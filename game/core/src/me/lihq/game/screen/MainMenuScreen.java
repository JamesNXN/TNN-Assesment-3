package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.Menu;

/**
 * Created by vishal on 17/12/2016.
 */
public class MainMenuScreen extends AbstractScreen
{

    private Menu menu;
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;

    public MainMenuScreen(GameMain game)
    {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //Setting up the camera
        camera.setToOrtho(false, w, h);
        camera.update();

        //Creates a Main Menu object thus creating the main menu
        menu = new Menu(game, false);

    }

    @Override
    public void show()
    {
        //I don't actually remember what this did, could someone update this?
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(menu.stage);
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(float delta)
    {
        //Renders the main menu
        menu.render();
    }

    @Override
    public void resize(int width, int height)
    {
        menu.resize(width, height);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        //Disposes the main menu
        menu.dispose();
    }


}
