package me.lihq.game.screen;


import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import me.lihq.game.GameMain;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Settings;
import me.lihq.game.living.controller.PlayerController;
import me.lihq.game.screen.elements.StatusBar;

/**
 * This is the screen that is responsible for the navigation of the player around the game.
 * It displays the current room that the player is in, and allows the user to move the player around between rooms.
 */
public class NavigationScreen extends AbstractScreen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;
    public PlayerController playerController;
    private SpriteBatch spriteBatch;

    private StatusBar statusBar;

    //TODO: add more information about this class
    /**
     * Initialises the navigation screen
     * @param game
     */
    public NavigationScreen(GameMain game) {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false,w,h);
        camera.update();

        viewport = new FitViewport(w/Settings.ZOOM, h/Settings.ZOOM, camera);

        map = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        playerController = new PlayerController(game.player);

        spriteBatch = new SpriteBatch();

        statusBar = new StatusBar();
    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(playerController);
        multiplexer.addProcessor(statusBar.stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void update() {

        playerController.update();
        game.player.update();

    }

    /**
     * Called when the screen should render itself.
	 * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        game.player.pushCoordinatesToSprite();

        camera.position.x = game.player.getX();
        camera.position.y = game.player.getY();
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        spriteBatch.setProjectionMatrix(camera.combined);
        //place Sprites to be drawn in the sprite batch
        spriteBatch.begin();
        game.player.draw(spriteBatch);
        spriteBatch.end();

        statusBar.render();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        map.dispose();
        tiledMapRenderer.dispose();
        statusBar.dispose();
    }

    public void setTiledMapRenderer(TiledMap map)
    {
        this.map = map;
        tiledMapRenderer.setMap(map);
    }
}
