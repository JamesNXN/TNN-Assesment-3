package me.lihq.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.*;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Door;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.Direction;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;
import me.lihq.game.people.controller.GamepadAddon;
import me.lihq.game.people.controller.PlayerController;
import me.lihq.game.screen.elements.DebugOverlay;
import me.lihq.game.screen.elements.RoomArrow;
import me.lihq.game.screen.elements.RoomTag;
import me.lihq.game.screen.elements.StatusBar;

/**
 * This is the screen that is responsible for the navigation of the player around the game.
 * It displays the current room that the player is in, and allows the user to move the player around between rooms.
 */
public class NavigationScreen extends AbstractScreen
{

    private Player player;
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
    private Group characterGroup;

    private Group clueGroup;

    /**
     * This is the map renderer
     */
    private TiledMapRenderer tiledMapRenderer;

    private SpriteBatch spriteBatch;

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
     * The black sprite that is used to fade in/out
     */
    private Sprite BLACK_BACKGROUND = new Sprite();


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
     * gamepad listener for 360 controllers on windows 10 only!!!
     */

    private GamepadAddon gamePad;

    private Stage stage;

    /**
     * Initialises the navigation screen
     *
     * @param game - The main game instance
     */


    public NavigationScreen(GameMain game)
    {
        super(game);

        game.roomManager = new RoomManager(game.assets);
        game.personManager = new PersonManager(game.roomManager, game.assets);
        game.clueManager = new ClueManager(game.roomManager, game.assets);

        player = new Player("Player", game.assets.playerSpriteSheet, this);
        player.setCurrentRoom(game.roomManager.getRoom(0));
        Vector2Int randomLocation = player.getCurrentRoom().getRandomLocation();
        player.setTilePosition(randomLocation.x, randomLocation.y);

        spriteBatch = new SpriteBatch();
        stage = new Stage(new FitViewport(GameMain.GAME_WIDTH / Settings.ZOOM,
                GameMain.GAME_HEIGHT / Settings.ZOOM), spriteBatch);

        characterGroup = new Group();
        characterGroup.setName("characterGroup");

        clueGroup = new Group();
        clueGroup.setName("clueGroup");

        Pixmap pixMap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);

        pixMap.setColor(Color.BLACK);
        pixMap.fill();

        BLACK_BACKGROUND = new Sprite(new Texture(pixMap));

        tiledMapRenderer = new TiledMapRenderer(player.getCurrentRoom(), spriteBatch);

        statusBar = new StatusBar(game);

        speechboxMngr = new SpeechboxManager();

        convMngt = new ConversationManagement(player, speechboxMngr);

        arrow = new RoomArrow(player, game.assets);

        gamePad = new GamepadAddon(player);

        playerController = new PlayerController(player);

        pixMap.dispose();
    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show()
    {
//        InputMultiplexer multiplexer = new InputMultiplexer();
//        multiplexer.addProcessor(speechboxMngr.multiplexer);
//        multiplexer.addProcessor(playerController);
//        multiplexer.addProcessor(statusBar.stage);
//
//        Controllers.addListener(gamePad);

        characterGroup.addActor(player);

        for (NPC npc : player.getCurrentRoom().getNpcArray()) {
            characterGroup.addActor(npc);
        }

        for (Clue clue : player.getCurrentRoom().getClueArray()){
            clueGroup.addActor(clue);
        }

        stage.addActor(clueGroup);
        stage.addActor(characterGroup);
        Gdx.input.setInputProcessor(playerController);
    }

    public void changeRoom(int roomId){
        Room exitRoom = player.getCurrentRoom();
        Room entryRoom = game.roomManager.getRoom(roomId);
        Direction entryDirection = null;
        Vector2 entryPosition = new Vector2();

        for (Door door : entryRoom.getEntryArray()){
            if (door.getConnectedRoomId() == exitRoom.getID()){
                entryPosition.set(door.getX() + door.getWidth()/2, door.getY() + door.getHeight()/2);
                entryDirection = door.getDirection();
            }
        }

        player.setCurrentRoom(entryRoom);
        player.setPosition(entryPosition.x, entryPosition.y);
        player.setDirection(entryDirection);

        stage.getCamera().position.x = player.getX();
        stage.getCamera().position.y = player.getY();
        stage.getCamera().update();

        tiledMapRenderer.setView((OrthographicCamera) stage.getCamera());

        tiledMapRenderer.setMap(entryRoom.getTiledMap());

        characterGroup.clear();
        clueGroup.clear();

        characterGroup.addActor(player);

        for (NPC npc : entryRoom.getNpcArray()) {
            characterGroup.addActor(npc);
        }

        for (Clue clue : entryRoom.getClueArray()){
            clueGroup.addActor(clue);
        }
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        stage.getCamera().position.x = player.getX();
        stage.getCamera().position.y = player.getY();
        stage.getCamera().update();

        tiledMapRenderer.setView((OrthographicCamera) stage.getCamera());

        tiledMapRenderer.render();

        stage.act();
        stage.draw();

        tiledMapRenderer.renderLastLayer();

        //Everything to be drawn relative to bottom left of the screen
        spriteBatch.begin();

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
        stage.getViewport().update(width, height);
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
     * This method sets the RoomTag to the parameter which is then rendered next render loop
     *
     * @param tag - The RoomTag to be rendered
     */
    public void setRoomTag(RoomTag tag)
    {
        this.roomTag = tag;
    }
}
