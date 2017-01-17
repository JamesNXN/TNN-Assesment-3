package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.screen.elements.Menu;
/**
 * Created by vishal on 18/12/2016.
 */
public class PauseScreen extends AbstractScreen {

    private Menu pauseMenu;
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;

    /**
     * This constructor sets the relevant properties of the class.
     *
     * @param game this provides access to the gameMain class so that screens can set the states of the game.
     */
    public PauseScreen(GameMain game) {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //Sets up the camera
        camera.setToOrtho(false,w,h);
        camera.update();

        //Creates the Pause menu
        pauseMenu = new Menu(game,true);
    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(pauseMenu.stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(float delta) {
        //Renders the pause menu
        pauseMenu.render();
    }

    @Override
    public void resize(int width, int height) {
        pauseMenu.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //Disposes of the Pause menu
        pauseMenu.dispose();
    }
}
