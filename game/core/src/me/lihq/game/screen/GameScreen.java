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
import me.lihq.game.controller.PlayerController;
import me.lihq.game.models.Player;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class GameScreen extends AbstractScreen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;
    private PlayerController playerController;

    public GameScreen(GameMain game) {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();

        viewport = new FitViewport(w, h, camera);

        map = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        player = new Player("Test name");

        player.setX(10);
        player.setY(10);

        //Should we make it so that a player has a player controller. Rather than a controller has a player?
        playerController = new PlayerController(player);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(playerController);
    }

    @Override
    public void render(float delta) {
        camera.position.x = player.getX()* Settings.TILE_SIZE;
        camera.position.y = player.getY()* Settings.TILE_SIZE;
        camera.update();
        tiledMapRenderer.setView(camera); // not sure if this belongs here or in the constructor.
        tiledMapRenderer.render();

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
    }
}
