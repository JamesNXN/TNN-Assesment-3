package me.lihq.game.screen;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import me.lihq.game.GameMain;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Settings;
import me.lihq.game.living.controller.PlayerController;
import me.lihq.game.Assets;
import me.lihq.game.living.Player;

/**
 * This is the screen that is responsible for the navigation of the player around the game.
 * It displays the current room that the player is in, and allows the user to move the player around between rooms.
 */
public class NavigationScreen extends AbstractScreen
{

    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;
    private PlayerController playerController;

    //TODO: add more information about this class

    /**
     * Initialises the navigation screen
     *
     * @param game
     */
    public NavigationScreen(GameMain game)
    {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        Assets.load();
        //TODO: Consider where we have the camera here or GameMain
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

        viewport = new FitViewport(w, h, camera);

        map = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        //TODO: Move player to GameMain
        player = new Player("Test name", "player.png");

        player.setX(10);
        player.setY(10);

        playerController = new PlayerController(player);
    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(playerController);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        camera.position.x = player.getX() * Settings.TILE_SIZE;
        camera.position.y = player.getY() * Settings.TILE_SIZE;
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
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
        map.dispose();
        tiledMapRenderer.dispose();
    }
}
