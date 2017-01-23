package me.lihq.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.lihq.game.*;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.NPC;
import me.lihq.game.people.controller.PlayerController;
import me.lihq.game.screen.elements.DebugOverlay;
import me.lihq.game.screen.elements.RoomArrow;
import me.lihq.game.screen.elements.RoomTag;
import me.lihq.game.screen.elements.StatusBar;

import java.util.List;

/**
 * This is the screen that is responsible for the navigation of the player around the game.
 * It displays the current room that the player is in, and allows the user to move the player around between rooms.
 */
public class NavigationScreen extends AbstractScreen
{
    /**
     * The controller that listens for key inputs
     */
    public PlayerController playerController;
    /**
     * This is the SpeechboxManager for the main game
     */
    public SpeechboxManager speechboxMngr;
    /**
     * This is the main ConversationManager that controls the conversation mechanic
     */
    public ConversationManagement convMngt;
    /**
     * This boolean determines whether the black is fading in or out
     */
    private boolean fadeToBlack = true;
    /**
     * This is the current map that is being shown
     */
    private TiledMap map;
    /**
     * This boolean determines whether the map needs to be updated in the next render loop
     */
    private boolean changeMap = false;
    /**
     * This is the list of NPCs in the current Room
     */
    private List<NPC> currentNPCS;
    /**
     * This is the map renderer that also renders Sprites
     */
    private OrthogonalTiledMapRendererWithPeople tiledMapRenderer;
    /**
     * The camera to render the map to
     */
    private OrthographicCamera camera = new OrthographicCamera();
    private Viewport viewport;
    private SpriteBatch spriteBatch;
    /**
     * The input multiplexer for the game
     */
    private InputMultiplexer multiplexer;
    /**
     * This stores whether the game is paused or not
     */
    private boolean pause = false;
    /**
     * This is the StatusBar that shows at the bottom
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
     * @param game - The main game instance
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

        tiledMapRenderer = new OrthogonalTiledMapRendererWithPeople(game.player.getRoom().getTiledMap());

        playerController = new PlayerController(game.player);

        spriteBatch = new SpriteBatch();

        statusBar = new StatusBar(game);

        speechboxMngr = new SpeechboxManager();

        convMngt = new ConversationManagement(game.player, speechboxMngr);

        tiledMapRenderer.addPerson(game.player);

        arrow = new RoomArrow(game.player);


    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show()
    {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(speechboxMngr.multiplexer);
        multiplexer.addProcessor(playerController);
        multiplexer.addProcessor(statusBar.stage);

        Gdx.input.setInputProcessor(multiplexer);

    }

    /**
     * This method is called once a game tick
     */
    @Override
    public void update()
    {
        if (!pause) { //this statement contains updates that shouldn't happen during a pause
            playerController.update();
            game.player.update();
            arrow.update();

            for (AbstractPerson n : currentNPCS) {
                n.update();
            }

            speechboxMngr.update();
        }

        //Some things should be updated all the time.
        updateTransition();

        if (roomTag != null) {
            roomTag.update();
        }
    }

    /**
     * This method is called once a game tick to update the room transition animation
     */
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

    /**
     * This is called when the player decides to move to another room
     */
    public void initialiseRoomChange()
    {
        pause = true; //pause all non necessary updates like player movement
        roomTransition = true;
    }

    /**
     * This is called when the room transition animation has completed so the necessary variables
     * can be returned to their normal values
     */
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
        for (AbstractPerson n : currentNPCS) {
            n.pushCoordinatesToSprite();

        }

        if (changeMap) {
            tiledMapRenderer.setMap(game.player.getRoom().getTiledMap());
            tiledMapRenderer.clearPeople();
            tiledMapRenderer.addPerson((List<AbstractPerson>) ((List<? extends AbstractPerson>) currentNPCS));
            tiledMapRenderer.addPerson(game.player);
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

        if (Settings.DEBUG) {
            DebugOverlay.renderDebugInfo(spriteBatch);
        }

        spriteBatch.end();

        statusBar.render();
        speechboxMngr.render();

    }

    /**
     * This is called when the window is resized
     *
     * @param width  - The new width
     * @param height - The new height
     */
    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
        statusBar.resize(width, height);
    }

    /**
     * This is called when the focus is lost on the window
     */
    @Override
    public void pause()
    {

    }

    /**
     * This method is called when the window is brought back into focus
     */
    @Override
    public void resume()
    {

    }

    /**
     * This method is called when the user hides the window
     */
    @Override
    public void hide()
    {

    }

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public void dispose()
    {
        map.dispose();
        tiledMapRenderer.dispose();
        statusBar.dispose();
        spriteBatch.dispose();
    }

    /**
     * This lets the tiledMapRenderer know that on the next render it should reload the current room from the player.
     */
    public void updateTiledMapRenderer()
    {
        this.changeMap = true;
        this.currentNPCS = game.getNPCS(game.player.getRoom());
    }

    /**
     * This method sets the RoomTag to the parameter which is then rendered next render loop
     *
     * @param tag - The RoomTag to be rendered
     */
    public void setRoomTag(RoomTag tag)
    {
        this.roomTag = tag;
    }

    /**
     * This method gets the list of current NPCs in the current room
     *
     * @return (List<NPC>) the value of currentNPCs {@link #currentNPCS}
     */
    public List<NPC> getNPCs()
    {
        return currentNPCS;
    }
}
