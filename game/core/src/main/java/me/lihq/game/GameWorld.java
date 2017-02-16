package me.lihq.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.gui.Gui;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Door;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.Direction;
import me.lihq.game.people.NPC;
import me.lihq.game.people.PersonState;
import me.lihq.game.people.Player;
import me.lihq.game.models.RoomArrow;

public class GameWorld {
    private GameMain game;
    private Gui gui;

    private Player player;
    private Stage gameWorldStage;

    private Group characterGroup;
    private Group clueGroup;
    private Group roomArrowGroup;

    private CustomTiledMapRenderer tiledMapRenderer;

    public GameWorld(GameMain game){
        this.game = game;

        SpriteBatch gameWorldBatch = new SpriteBatch();
        gameWorldStage = new Stage(new FitViewport(GameMain.GAME_WIDTH / Settings.ZOOM,
                GameMain.GAME_HEIGHT / Settings.ZOOM), gameWorldBatch);

        game.roomManager = new RoomManager(game.assetLoader);
        game.personManager = new PersonManager(game.roomManager, game.assetLoader);
        game.clueManager = new ClueManager(game.roomManager, game.assetLoader);

        player = new Player(game.assetLoader.playerJsonData, game.assetLoader.playerSpriteSheet, this);
        player.setCurrentRoom(game.roomManager.getRoom(0));
        Vector2Int randomLocation = player.getCurrentRoom().getRandomLocation();
        player.setTilePosition(randomLocation.x, randomLocation.y);

        characterGroup = new Group();
        characterGroup.setName("characterGroup");

        clueGroup = new Group();
        clueGroup.setName("clueGroup");

        roomArrowGroup = new Group();
        roomArrowGroup.setName("roomArrowGroup");

        tiledMapRenderer = new CustomTiledMapRenderer(player.getCurrentRoom(), gameWorldBatch);

        gameWorldStage.addActor(roomArrowGroup);

        characterGroup.addActor(player);

        for (NPC npc : player.getCurrentRoom().getNpcArray()) {
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
            Room entryRoom = game.roomManager.getRoom(roomId);
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

            for (NPC npc : player.getCurrentRoom().getNpcArray()) {
                characterGroup.addActor(npc);
            }
            for (Clue clue : player.getCurrentRoom().getClueArray()){
                clueGroup.addActor(clue);
            }
            for (RoomArrow arrow : player.getCurrentRoom().getRoomArrowArray()){
                roomArrowGroup.addActor(arrow);
            }

            gui.setRoomTag(entryRoom);

            player.setCanMove(true);
        });

        gui.screenFadeInOut(runnableAction);
    }

    public Stage getGameWorldStage() {
        return gameWorldStage;
    }

    public void render(float delta){
        gameWorldStage.getCamera().position.x = player.getX();
        gameWorldStage.getCamera().position.y = player.getY();
        gameWorldStage.getCamera().update();

        tiledMapRenderer.setRenderingRoom(player.getCurrentRoom());
        tiledMapRenderer.setView((OrthographicCamera) gameWorldStage.getCamera());

        tiledMapRenderer.render();

        gameWorldStage.act();

        //sort characters by their y coordinate so that actors with lesser y coordinate get drawn first
        characterGroup.getChildren().sort((actor1, actor2) -> (int) (actor2.getY() - actor1.getY()));
        gameWorldStage.draw();

        tiledMapRenderer.renderLastLayer();
    }

    public Player getPlayer() {
        return player;
    }

    public void dispose(){
        tiledMapRenderer.dispose();
        gameWorldStage.dispose();
    }
}
