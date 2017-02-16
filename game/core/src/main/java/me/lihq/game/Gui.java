package me.lihq.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.GameMain;
import me.lihq.game.models.Room;
import me.lihq.game.screen.NavigationScreen;
import me.lihq.game.screen.elements.FadeInOut;
import me.lihq.game.screen.elements.RoomTag;
import me.lihq.game.screen.elements.StatusBar;

public class Gui {
    private GameMain game;

    private Table table;

    private RoomTag roomTag;
    private StatusBar statusBar;

    private SpriteBatch guiBatch;
    private Stage guiStage;

    private FadeInOut fadeInOut;


    public Gui(GameMain game){
        this.game = game;
        table = new Table();
        table.setFillParent(true);
        table.align(Align.bottom);

        guiBatch = new SpriteBatch();
        guiStage = new Stage(new FitViewport(GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT), guiBatch);

        fadeInOut = new FadeInOut();

        roomTag = new RoomTag(game.assetLoader.roomTagBorder, game.assetLoader.roomTagFont);
        table.addActor(roomTag);


        statusBar = new StatusBar(game);
        table.add(statusBar);

        guiStage.addActor(table);
    }

    public void setRoomTag(Room room){
        roomTag.setRoomName(room.getName());
        roomTag.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(1f),
                    Actions.moveBy(0, -roomTag.getHeight() - 10, 1f)),
                Actions.delay(2f),
                Actions.fadeOut(1f)));
    }
}
