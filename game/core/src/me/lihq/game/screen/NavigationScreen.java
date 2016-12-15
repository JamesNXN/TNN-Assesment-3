package me.lihq.game.screen;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.InputMultiplexer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
public class NavigationScreen extends AbstractScreen
{

    public PlayerController playerController;
    /**
     * This boolean determines whether the black is fading in or out
     */
    boolean fadeToBlack = true;
    private TiledMap map;
    private OrthogonalTiledMapRendererWithSprite tiledMapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;
    private SpriteBatch spriteBatch;

    //TODO: add more information about this class
    /**
     * Initialises the navigation screen
     *
     * @param game
     */
    private StatusBar statusBar;
    /**
     * This determines whether the player is currently changing rooms, it will fade out to black, change
     * the room, then fade back in.
     */
    private boolean roomTransition = false;
    /**
     * The amount of ticks it takes for the black to fade in and out
     */
    private float ANIM_TIME = Settings.TPS / 2.0f;
    /**
     * The black sprite that is used to fade in/out
     */
    private Sprite BLACK_BACKGROUND = new Sprite();
    /**
     * The current animation frame of the fading in/out
     */
    private float animTimer = 0.0f;

    public NavigationScreen(GameMain game)
    {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera.setToOrtho(false, w, h);
        camera.update();

        Pixmap pixMap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);

        pixMap.setColor(Color.BLACK);
        pixMap.fill();

        BLACK_BACKGROUND = new Sprite(new Texture(pixMap));

        viewport = new FitViewport(w / Settings.ZOOM, h / Settings.ZOOM, camera);

        map = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprite(map);

        playerController = new PlayerController(game.player);

        spriteBatch = new SpriteBatch();

        tiledMapRenderer.addSprite(game.player);

        statusBar = new StatusBar();

    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show()
    {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(playerController);
        multiplexer.addProcessor(statusBar.stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void update()
    {
        playerController.update();
        game.player.update();

        updateTransition();
    }

    private void updateTransition()
    {
        if (roomTransition)
        {
            BLACK_BACKGROUND.setAlpha(animTimer / ANIM_TIME);

            if (fadeToBlack)
            {
                animTimer ++;

                if (animTimer == ANIM_TIME)
                {
                    game.gameMap.moveRoom(game.player.getRoom().getID(), game.player.getTileCoordinates().x, game.player.getTileCoordinates().y);
                }

                if (animTimer > (ANIM_TIME * 1.2)) //This is so it stays solid black for longer
                {
                    fadeToBlack = false;
                }
            }
            else
            {
                animTimer --;

                if (animTimer < 0)
                {
                    finishRoomTransition();
                }
            }
        }
    }

    public void initialiseRoomChange()
    {
        roomTransition = true;
    }

    public void finishRoomTransition()
    {
        animTimer = 0;
        roomTransition = false;

        //TODO : RENDER THE MAP NAME TAG
    }
    /**
     * Called when the screen should render itself.
     *
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


        if (roomTransition)
        {
            spriteBatch.begin();

            BLACK_BACKGROUND.draw(spriteBatch);

            spriteBatch.end();
        }

        statusBar.render();
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
        statusBar.dispose();
    }

    public void setTiledMapRenderer(TiledMap map)
    {
        this.map = map;
        tiledMapRenderer.setMap(map);
    }
}
