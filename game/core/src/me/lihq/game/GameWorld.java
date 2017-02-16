package me.lihq.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.models.Clue;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;
import me.lihq.game.screen.elements.RoomArrow;

public class GameWorld {
    private Player player;
    private SpriteBatch gameWorldBatch;
    private Stage gameWorldStage;

    private Group characterGroup;
    private Group clueGroup;
    private Group roomArrowGroup;

    private CustomTiledMapRenderer tiledMapRenderer;

    public GameWorld(GameMain game){
        gameWorldBatch = new SpriteBatch();
        gameWorldStage = new Stage(new FitViewport(GameMain.GAME_WIDTH / Settings.ZOOM,
                GameMain.GAME_HEIGHT / Settings.ZOOM), gameWorldBatch);

        game.roomManager = new RoomManager(game.assetLoader);
        game.personManager = new PersonManager(game.roomManager, game.assetLoader);
        game.clueManager = new ClueManager(game.roomManager, game.assetLoader);

        player = new Player(game.assetLoader.playerJsonData, game.assetLoader.playerSpriteSheet, game);
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
}
