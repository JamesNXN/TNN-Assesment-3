package me.lihq.game.screen;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.InputMultiplexer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Settings;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.living.controller.PlayerController;
import me.lihq.game.screen.elements.OrthogonalTiledMapRendererWithSprite;
import me.lihq.game.screen.elements.RoomTag;
import me.lihq.game.screen.elements.StatusBar;
import org.omg.CORBA.ARG_OUT;

import static me.lihq.game.living.AbstractPerson.*;


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

    /**
     * This is the current map that is being shown
     */
    private TiledMap map;

    /**
     * This boolean determines whether the map needs to be updated in the next render loop
     */
    private boolean changeMap = false;

    /**
     *
     */
    private OrthogonalTiledMapRendererWithSprite tiledMapRenderer;
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    private InputMultiplexer multiplexer;
    private boolean pause = false;

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
    private float ANIM_TIME = Settings.TPS / 1.5f;
    /**
     * The black sprite that is used to fade in/out
     */
    private Sprite BLACK_BACKGROUND = new Sprite();
    /**
     * The current animation frame of the fading in/out
     */
    private float animTimer = 0.0f;

    /**
     * The Sprite that is to draw the arrows on the screen by doors
     */
    private Sprite ARROW_SPRITE = null;

    /**
     * This is the room name tag that is to be rendered to the screen
     *
     * If it is null then there is no tag to display
     */
    private RoomTag roomTag = null;

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

        map = new TmxMapLoader().load("maps/mainroom.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprite(map);

        playerController = new PlayerController(game.player);

        spriteBatch = new SpriteBatch();

        tiledMapRenderer.addSprite(game.player);

        statusBar = new StatusBar();

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(playerController);
        multiplexer.addProcessor(statusBar.stage);
    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void update()
    {
        if (!pause) { //this statement contains updates that shouldn't happen during a pause
            playerController.update();
            game.player.update();
        }

        //Some things should be updated all the time.
        updateTransition();

        if (roomTag != null)
        {
            roomTag.update();
        }
    }

    private void updateTransition()
    {
        if (roomTransition)
        {
            BLACK_BACKGROUND.setAlpha(Interpolation.pow4.apply(0, 1, animTimer / ANIM_TIME));

            if (fadeToBlack)
            {
                animTimer ++;

                if (animTimer == ANIM_TIME)
                {
                    game.gameMap.moveRoom(game.player, game.player.getTileCoordinates().x, game.player.getTileCoordinates().y);
                }

                if (animTimer > ANIM_TIME)
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
        pause = true; //pause all non necessary updates like player movement
        roomTransition = true;
    }

    public void finishRoomTransition()
    {
        animTimer = 0;
        roomTransition = false;
        fadeToBlack = true;
        pause = false;

        roomTag = new RoomTag(game.player.getRoom().getName());
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

        if (changeMap)
        {
            tiledMapRenderer.setMap(map);
            changeMap = false;
        }

        camera.position.x = game.player.getX();
        camera.position.y = game.player.getY();
        camera.update();

        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();
        //Everything to be drawn relative to bottom left of the map
        tiledMapRenderer.getBatch().begin();

        if (game.player.getRoom().isTriggerTile(game.player.getTileCoordinates().x, game.player.getTileCoordinates().y) && game.player.getState() != PersonState.WALKING)
        {
            if (ARROW_SPRITE != null)
            {
                ARROW_SPRITE.draw(tiledMapRenderer.getBatch());
            }
            else
            {
                String rotation = game.player.getRoom().getMatRotation(game.player.getTileCoordinates().x, game.player.getTileCoordinates().y);
                ARROW_SPRITE = new Sprite(Assets.getArrowDirection(rotation));

                int x = (game.player.getTileCoordinates().x * 32) + (Direction.valueOf(rotation).getDx() * 32);
                int y = (game.player.getTileCoordinates().y * 32) + (Direction.valueOf(rotation).getDy() * 32);

                ARROW_SPRITE.setPosition(x, y);
            }
        }
        else
        {
            ARROW_SPRITE = null;
        }

        tiledMapRenderer.getBatch().end();

        //Everything to be drawn relative to bottom left of the screen
        spriteBatch.begin();

        if (roomTransition)
        {
            BLACK_BACKGROUND.draw(spriteBatch);
        }

        if (roomTag != null)
        {
            roomTag.render(spriteBatch);
        }

        spriteBatch.end();
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
        changeMap = true;
    }

    public void setRoomTag(RoomTag tag)
    {
        this.roomTag = tag;
    }
}
