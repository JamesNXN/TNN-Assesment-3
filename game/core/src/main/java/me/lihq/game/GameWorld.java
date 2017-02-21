package me.lihq.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.gui.Gui;
import me.lihq.game.gui.InteractionSelectionBubble;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Door;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.Direction;
import me.lihq.game.people.Npc;
import me.lihq.game.people.PersonState;
import me.lihq.game.people.Player;
import me.lihq.game.models.RoomArrow;
import me.lihq.game.screen.GameClearScreen;

public class GameWorld {
    private GameMain game;
    private Gui gui;

    public RoomManager roomManager;
    public NpcManager npcManager;
    public ClueManager clueManager;

    private Player player;
    private Stage gameWorldStage;

    private Group characterGroup;
    private Group clueGroup;
    private Group roomArrowGroup;

    private CustomTiledMapRenderer tiledMapRenderer;

    private Vector2 targetCameraPosition;
    private float targetCameraZoom;

    private Interaction interaction;
    private ConversationManager conversationManager;

    private boolean isGameClear = false;

    public GameWorld(GameMain game, Player selectedPlayer){
        this.game = game;

        SpriteBatch gameWorldBatch = new SpriteBatch();
        gameWorldStage = new Stage(new FitViewport(GameMain.GAME_WIDTH / Settings.ZOOM,
                GameMain.GAME_HEIGHT / Settings.ZOOM), gameWorldBatch);

        roomManager = new RoomManager(game.assetLoader);
        npcManager = new NpcManager(roomManager, game.assetLoader);
        clueManager = new ClueManager(npcManager, roomManager, game.assetLoader);

        player = selectedPlayer;
        player.setCurrentRoom(roomManager.getRoom(0));
        Vector2Int randomLocation = player.getCurrentRoom().getRandomLocation();
        player.setTilePosition(randomLocation.x, randomLocation.y);
        player.setGameWorld(this);

        targetCameraPosition = new Vector2(player.getX() + player.getWidth()/2, player.getY());
        targetCameraZoom = ((OrthographicCamera)(gameWorldStage.getCamera())).zoom;

        characterGroup = new Group();
        characterGroup.setName("characterGroup");
        clueGroup = new Group();
        roomArrowGroup = new Group();

        tiledMapRenderer = new CustomTiledMapRenderer(player.getCurrentRoom(), gameWorldBatch);

        gameWorldStage.addActor(roomArrowGroup);

        characterGroup.addActor(player);

        for (Npc npc : player.getCurrentRoom().getNpcArray()) {
            characterGroup.addActor(npc);
        }

        for (Clue clue : player.getCurrentRoom().getClueArray()){
            clueGroup.addActor(clue);
        }

        for (RoomArrow arrow : player.getCurrentRoom().getRoomArrowArray()){
            roomArrowGroup.addActor(arrow);
        }

        gameWorldStage.addActor(clueGroup);
        gameWorldStage.addActor(characterGroup);

        interaction = new Interaction(this);
        conversationManager = new ConversationManager(game.assetLoader.uiSkin);
    }

    public void setGui(Gui gui){
        this.gui = gui;
    }

    public void changeRoom(int roomId){
        player.setState(PersonState.STANDING);

        //actual room transition happens after fade out
        RunnableAction runnableAction = new RunnableAction();
        runnableAction.setRunnable(() -> {
            Room exitRoom = player.getCurrentRoom();
            Room entryRoom = roomManager.getRoom(roomId);
            Vector2 entryPosition = new Vector2();
            Direction entryDirection = player.getDirection();

            for (Door door : entryRoom.getEntryArray()){
                if (door.getConnectedRoomId() == exitRoom.getID()){
                    entryPosition.set(door.getX() + door.getWidth()/2, door.getY() + door.getHeight()/2);
                    entryDirection = door.getDirection();
                    break;
                }
            }

            Direction finalEntryDirection = entryDirection;

            characterGroup.clear();
            clueGroup.clear();
            roomArrowGroup.clear();

            characterGroup.addActor(player);

            player.setCurrentRoom(entryRoom);
            player.setDirection(finalEntryDirection);
            player.setPosition(entryPosition.x, entryPosition.y);

            for (Npc npc : player.getCurrentRoom().getNpcArray()) {
                characterGroup.addActor(npc);
            }
            for (Clue clue : player.getCurrentRoom().getClueArray()){
                clueGroup.addActor(clue);
            }
            for (RoomArrow arrow : player.getCurrentRoom().getRoomArrowArray()){
                roomArrowGroup.addActor(arrow);
            }

            gui.setRoomTag(entryRoom);

            //prevents camera lerp when moving between rooms
            OrthographicCamera camera = (OrthographicCamera) gameWorldStage.getCamera();
            camera.position.x = player.getDefaultCameraFocusX();
            camera.position.y = player.getDefaultCameraFocusY();
            targetCameraPosition.x = camera.position.x;
            targetCameraPosition.y = camera.position.y;
            camera.update();

            tiledMapRenderer.setRenderingRoom(player.getCurrentRoom());
            tiledMapRenderer.setView((OrthographicCamera) gameWorldStage.getCamera());

            player.setCanMove(true);
        });

        gui.screenFadeInOut(runnableAction);
    }

    public void startInteraction(Npc interactingNpc){
        interaction.setInteractingNpc(interactingNpc);

        player.setInConversation(true);
        interactingNpc.setInConversation(true);
        interactingNpc.setDirection(player.getDirection().getOpposite());
        conversationManager.addSpeechBubble(player, player.getDialogue().getIntroduction());
        conversationManager.addSpeechBubble(interactingNpc, interactingNpc.getDialogue().getIntroduction());
        conversationManager.addSpeechBubble(new InteractionSelectionBubble(player, game.assetLoader.uiSkin, conversationManager, gui));
        conversationManager.startConversation(gui.getGuiStage());

        targetCameraPosition.set((player.getX() + interactingNpc.getRight())/2, (player.getTop() + interactingNpc.getY())/2);
        OrthographicCamera camera = (OrthographicCamera) gameWorldStage.getCamera();
        targetCameraZoom = camera.zoom - 0.5f;
    }

    public void haltInteraction(){
        conversationManager.getInteractingCharacter().setInConversation(false);
        player.setInConversation(false);

        conversationManager.clear();
        targetCameraPosition.set(player.getDefaultCameraFocusX(), player.getDefaultCameraFocusY());
        OrthographicCamera camera = (OrthographicCamera) gameWorldStage.getCamera();
        targetCameraZoom = camera.zoom + 0.5f;
    }

    public void render(float delta){
        if (conversationManager.isFinished()){
            conversationManager.setFinished(false);
            haltInteraction();
        }

        if (isGameClear){
            game.setScreen(new GameClearScreen(game));
        }

        //camera move lerp effect
        OrthographicCamera camera = (OrthographicCamera) gameWorldStage.getCamera();
        camera.position.x = camera.position.x + (targetCameraPosition.x - camera.position.x) * 0.2f;
        camera.position.y = camera.position.y + (targetCameraPosition.y - camera.position.y) * 0.2f;
        camera.zoom = camera.zoom + (targetCameraZoom - camera.zoom) * 0.4f;
        camera.update();

        tiledMapRenderer.setRenderingRoom(player.getCurrentRoom());
        tiledMapRenderer.setView((OrthographicCamera) gameWorldStage.getCamera());

        tiledMapRenderer.render();

        gameWorldStage.act();

        //sort characters by their y coordinate so that actors with lesser y coordinate get drawn first
        characterGroup.getChildren().sort((actor1, actor2) -> (int) (actor2.getY() - actor1.getY()));
        gameWorldStage.draw();

        tiledMapRenderer.renderLastLayer();
    }

    public void setGameClear(boolean gameClear) {
        isGameClear = gameClear;
    }

    public Player getPlayer() {
        return player;
    }

    public Stage getGameWorldStage() {
        return gameWorldStage;
    }

    public Gui getGui() {
        return gui;
    }

    public Interaction getInteraction(){
        return interaction;
    }

    public ConversationManager getConversationManager(){
        return conversationManager;
    }

    public void setTargetCameraPosition(float x, float y) {
        this.targetCameraPosition.x = x;
        this.targetCameraPosition.y = y;
    }

    public void dispose(){
        tiledMapRenderer.dispose();
        gameWorldStage.dispose();
    }
}
