package me.lihq.game.gui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.GameMain;
import me.lihq.game.GameWorld;
import me.lihq.game.Time;
import me.lihq.game.gui.windows.AccuseWindow;
import me.lihq.game.gui.windows.InfoWindow;
import me.lihq.game.gui.windows.InventoryWindow;
import me.lihq.game.gui.windows.NpcNoteWindow;
import me.lihq.game.gui.windows.PersonalityMeterWindow;
import me.lihq.game.gui.windows.ClueSelectionWindow;
import me.lihq.game.models.Room;
import me.lihq.game.models.Score;

/**
 * NEW
 * A container for all of the gui element of the game
 */

public class Gui {
    private GameWorld gameWorld;

    private RoomTag roomTag;
    private StatusBar statusBar;

    private SpriteBatch guiBatch;
    private Stage guiStage;

    private FadeInOut fadeInOut;

    // all of the gui windows in the game
    private InfoWindow infoWindow;
    private InventoryWindow inventoryWindow;
    private PersonalityMeterWindow personalityMeterWindow;
    private NpcNoteWindow npcNoteWindow;
    private ClueSelectionWindow clueSelectionWindow;
    private AccuseWindow accuseWindow;

    public Gui(GameMain game, GameWorld gameWorld){
        this.gameWorld = gameWorld;

        //table for main game screen gui
        Table table = new Table();
        table.setFillParent(true);
        table.align(Align.bottom);

        guiBatch = new SpriteBatch();
        guiStage = new Stage(new FitViewport(GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT), guiBatch);

        fadeInOut = new FadeInOut();

        roomTag = new RoomTag(game.assetLoader.roomTagBorder, game.assetLoader.roomTagFont);
        table.addActor(roomTag);


        statusBar = new StatusBar(game, this);
        table.add(statusBar);

        guiStage.addActor(table);
        guiStage.addActor(fadeInOut);


        // time and score must be added into gui stage for them to be updated
        guiStage.addActor(Time.getInstance());
        guiStage.addActor(Score.getInstance());


        //instantiate all of the gui windows altogether
        infoWindow = new InfoWindow(game.assetLoader.uiSkin, this, gameWorld);
        //pressing space will also close the info window
        infoWindow.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.SPACE){
                    infoWindow.hide();
                }
                return true;
            }
        });

        inventoryWindow = new InventoryWindow(game.assetLoader.uiSkin, this, gameWorld);
        personalityMeterWindow = new PersonalityMeterWindow(game.assetLoader.uiSkin, this, gameWorld);
        npcNoteWindow = new NpcNoteWindow(game.assetLoader.uiSkin, this, gameWorld);
        clueSelectionWindow = new ClueSelectionWindow(game.assetLoader.uiSkin, this, gameWorld);
        accuseWindow = new AccuseWindow(game.assetLoader.uiSkin, this, gameWorld);
    }

    /**
     * Set the room tag to be appear from top left corner of the screen
     * @param room room to be used for room tag
     */
    public void setRoomTag(Room room){
        roomTag.setRoomName(room.getName());
        roomTag.addAction(Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(1f),
                    Actions.moveBy(0, -roomTag.getHeight() - 10, 1f)),
                Actions.delay(2f),
                Actions.fadeOut(1f)));
    }

    /**
     * Show a pop up window that displays an actor and a string below.
     * @param actor Actor to show.
     * @param info String to be added below.
     */
    public void displayInfo(Actor actor, String info){
        infoWindow.getContentTable().clearChildren();
        infoWindow.getContentTable().add(actor).row();
        Label infoLabel = new Label(info, infoWindow.getSkin(), "dialog");
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.center);
        infoWindow.getContentTable().add(infoLabel).width(500);
        infoWindow.show(guiStage);
    }

    /**
     * Show a pop up window that displays a string.
     * @param info String to be displayed.
     */
    public void displayInfo(String info){
        infoWindow.getContentTable().clearChildren();
        Label infoLabel = new Label(info, infoWindow.getSkin(), "dialog");
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.center);
        infoWindow.getContentTable().add(infoLabel).width(500);
        infoWindow.show(guiStage);
    }

    public void render(float delta){
        guiStage.act(delta);
        guiStage.draw();
    }

    public Stage getGuiStage(){
        return guiStage;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public InventoryWindow getInventoryWindow() {
        return inventoryWindow;
    }

    public PersonalityMeterWindow getPersonalityMeterWindow() {
        return personalityMeterWindow;
    }

    public NpcNoteWindow getNpcNoteWindow() {
        return npcNoteWindow;
    }

    public ClueSelectionWindow getClueSelectionWindow() {
        return clueSelectionWindow;
    }

    public AccuseWindow getAccuseWindow() {
        return accuseWindow;
    }

    public FadeInOut getFadeInOut() {
        return fadeInOut;
    }

    public void dispose(){
        guiStage.dispose();
    }
}
