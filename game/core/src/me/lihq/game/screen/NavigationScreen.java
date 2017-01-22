package me.lihq.game.screen;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.InputMultiplexer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import me.lihq.game.GameMain;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.Settings;
import me.lihq.game.living.controller.PlayerController;

import me.lihq.game.screen.elements.OrthogonalTiledMapRendererWithSprite;
import me.lihq.game.screen.elements.RoomArrow;
import me.lihq.game.screen.elements.RoomTag;

import me.lihq.game.screen.elements.SpeechBox;
import me.lihq.game.screen.elements.SpeechBoxButton;
import me.lihq.game.screen.elements.StatusBar;

import java.util.ArrayList;
import java.util.List;


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

    private SpeechBox speechBox;

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
    private RoomArrow arrow;

    /**
     * This is the room name tag that is to be rendered to the screen
     * <p>
     * If it is null then there is no tag to display
     */
    private RoomTag roomTag = null;


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
        camera.setToOrtho(false, w, h);
        camera.update();

        Pixmap pixMap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);

        pixMap.setColor(Color.BLACK);
        pixMap.fill();

        BLACK_BACKGROUND = new Sprite(new Texture(pixMap));

        viewport = new FitViewport(w / Settings.ZOOM, h / Settings.ZOOM, camera);

        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprite(game.player.getRoom().getTiledMap());

        playerController = new PlayerController(game.player);

        spriteBatch = new SpriteBatch();

        statusBar = new StatusBar(game);

        tiledMapRenderer.addSprite(game.player);

        arrow = new RoomArrow(game.player);

        ArrayList<SpeechBoxButton> buttons = new ArrayList<>();
        SpeechBoxButton.EventHandler eventHandler = (String name) -> {
            System.out.println(name + " was pressed");
        };
        buttons.add(new SpeechBoxButton("Button 1", eventHandler));
        buttons.add(new SpeechBoxButton("Button 2", eventHandler));
        buttons.add(new SpeechBoxButton("Button 3", eventHandler));
        speechBox = new SpeechBox("Hello, my name is Example NPC Name!", buttons);

    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show()
    {

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(playerController);
        multiplexer.addProcessor(speechBox.stage);
        multiplexer.addProcessor(statusBar.stage);

        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public void update()
    {
        if (!pause) { //this statement contains updates that shouldn't happen during a pause
            playerController.update();
            game.player.update();
            arrow.update();
        }

        //Some things should be updated all the time.
        updateTransition();

        if (roomTag != null) {
            roomTag.update();
        }
    }

    private void updateTransition()
    {
        if (roomTransition) {
            BLACK_BACKGROUND.setAlpha(Interpolation.pow4.apply(0, 1, animTimer / ANIM_TIME));

            if (fadeToBlack) {
                animTimer++;

                if (animTimer == ANIM_TIME) {
                    game.player.moveRoom();
                }
                
                if (animTimer > ANIM_TIME) {
                    fadeToBlack = false;
                }
            } else {
                animTimer--;

                if (animTimer < 0) {
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

        if (changeMap) {
            tiledMapRenderer.setMap(game.player.getRoom().getTiledMap());
            changeMap = false;
        }

        camera.position.x = game.player.getX();
        camera.position.y = game.player.getY();
        camera.update();

        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();
        //Everything to be drawn relative to bottom left of the map
        tiledMapRenderer.getBatch().begin();

        arrow.draw(tiledMapRenderer.getBatch());

        game.player.getRoom().drawClues(delta, tiledMapRenderer.getBatch());

        tiledMapRenderer.getBatch().end();

        //Everything to be drawn relative to bottom left of the screen
        spriteBatch.begin();

        if (roomTransition) {
            BLACK_BACKGROUND.draw(spriteBatch);
        }

        if (roomTag != null) {
            roomTag.render(spriteBatch);
        }

        spriteBatch.end();

        speechBox.render();
        statusBar.render();

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
        speechBox.resize(width,height);
        statusBar.resize(width, height);
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
        speechBox.dispose();
        statusBar.dispose();
        spriteBatch.dispose();
    }

    /**
     * This lets the tiledMapRenderer know that on the next render it should reload the current room from the player.
     */
    public void updateTiledMapRenderer()
    {
        this.changeMap = true;
    }

    public void setRoomTag(RoomTag tag)
    {
        this.roomTag = tag;
    }
}
