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
 * Created by vishal on 17/12/2016.
 */
public class MainMenuScreen extends AbstractScreen {

    private MainMenu mainMenu;
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;

    public MainMenuScreen(GameMain game){
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        //Setting up the camera
        camera.setToOrtho(false,w,h);
        camera.update();

        //Determining the size of the screen
        viewport = new FitViewport(w/ Settings.ZOOM, h/Settings.ZOOM, camera);

        //Creates a Main Menu object thus creating the main menu
        mainMenu= new MainMenu(game,0);

    }

    @Override
    public void show() {
        //I don't actually remember what this did, could someone update this?
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(mainMenu.stage);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(float delta) {
        //Renders the main menu
        mainMenu.render();
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
        //Disposes the main menu
        mainMenu.dispose();
    }


}
