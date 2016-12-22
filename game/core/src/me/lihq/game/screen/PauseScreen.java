package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.screen.elements.MainMenu;
/**
 * Created by vishal on 18/12/2016.
 */
public class PauseScreen extends AbstractScreen {

    private MainMenu pauseMenu;
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

        //Determines screen size
        viewport = new FitViewport(w/ Settings.ZOOM, h/Settings.ZOOM, camera);

        //Creates the Pause menu
        pauseMenu= new MainMenu(game,1);
    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(pauseMenu.stage);
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
