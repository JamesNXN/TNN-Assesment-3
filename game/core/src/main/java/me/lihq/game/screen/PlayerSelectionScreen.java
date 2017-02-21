package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.AssetLoader;
import me.lihq.game.GameMain;
import me.lihq.game.gui.Slot;
import me.lihq.game.people.Player;

/**
 * NEW
 * Screen that users pick their detectives.
 */

public class PlayerSelectionScreen extends AbstractScreen {
    private Array<Player> playerArray;

    private Stage stage;

    private Table selectionTable;
    private Dialog selectionConfirmWindow;

    private Player selectedPlayer;

    public PlayerSelectionScreen(GameMain game) {
        super(game);

        AssetLoader assetLoader = game.assetLoader;

        Json json = new Json();
        playerArray = new Array<>();

        Array<JsonValue> playerJsonData = json.readValue(Array.class, assetLoader.playerJsonData);
        for (JsonValue data : playerJsonData) {
            playerArray.add(new Player(data, assetLoader.playerSpriteSheetArray.get(data.getInt("id"))));
        }

        stage = new Stage(new FitViewport(GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT));

        selectionTable = new Table();
        selectionTable.setFillParent(true);

        selectionConfirmWindow = new Dialog("Confirm selection?", game.assetLoader.uiSkin){
            @Override
            protected void result(Object object) {
                if (object.equals(true)){
                    game.navigationScreen = new NavigationScreen(game, selectedPlayer);
                    game.setScreen(game.navigationScreen);
                }
                else {
                    hide();
                }
            }
        };

        selectionConfirmWindow.button("OK", true);
        selectionConfirmWindow.button("Cancel", false);
    }

    @Override
    public void show() {
        for (Player player : playerArray) {
            Slot playerSlot = new Slot(player, game.assetLoader.uiSkin);
            playerSlot.addListener(new InputListener(){
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    playerSlot.setCursorOver(true);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    playerSlot.setCursorOver(false);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    selectedPlayer = player;

                    selectionConfirmWindow.getContentTable().clear();
                    selectionConfirmWindow.text(player.getDescription());
                    selectionConfirmWindow.show(stage);
                }
            });

            selectionTable.add(playerSlot);
        }

        stage.addActor(selectionTable);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
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

    }
}
