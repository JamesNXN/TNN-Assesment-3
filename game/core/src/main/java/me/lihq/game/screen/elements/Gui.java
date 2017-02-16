package me.lihq.game.screen.elements;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;
import me.lihq.game.models.Room;
import me.lihq.game.screen.NavigationScreen;

public class Gui extends Table{
    private GameMain game;
    private RoomTag roomTag;
    private StatusBar statusBar;


    public Gui(GameMain game, NavigationScreen screen){
        this.game = game;
        setFillParent(true);
        align(Align.bottom);

        roomTag = new RoomTag(game.assets.roomTagBorder, game.assets.roomTagFont);
        addActor(roomTag);

        statusBar = new StatusBar(game, screen);
        add(statusBar);
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
