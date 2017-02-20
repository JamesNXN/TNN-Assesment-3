package me.lihq.game.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.GameMain;
import me.lihq.game.GameWorld;
import me.lihq.game.gui.windows.InfoWindow;
import me.lihq.game.gui.windows.InventoryWindow;
import me.lihq.game.gui.windows.NpcNoteWindow;
import me.lihq.game.gui.windows.PersonalityMeterWindow;
import me.lihq.game.gui.windows.ClueSelectionWindow;
import me.lihq.game.models.Room;

public class Gui {
    private GameMain game;
    private GameWorld gameWorld;

    private Table table;

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


    public Gui(GameMain game, GameWorld gameWorld){
        this.game = game;
        this.gameWorld = gameWorld;

        table = new Table();
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


        infoWindow = new InfoWindow(game.assetLoader.uiSkin, this, gameWorld);
        inventoryWindow = new InventoryWindow(game.assetLoader.uiSkin, this, gameWorld);
        personalityMeterWindow = new PersonalityMeterWindow(game.assetLoader.uiSkin, this, gameWorld);
        npcNoteWindow = new NpcNoteWindow(game.assetLoader.uiSkin, this, gameWorld);
        clueSelectionWindow = new ClueSelectionWindow(game.assetLoader.uiSkin, this, gameWorld);
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

    /**
     * Carry out a fade in and fade out screen transition followed by an action
     * @param runnableAction action to be carried out after the transition
     */
    public void screenFadeInOut(RunnableAction runnableAction){
        fadeInOut.addAction(Actions.sequence(
                Actions.fadeIn(0.5f),
                Actions.run(runnableAction.getRunnable()),
                Actions.fadeOut(0.5f)));
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

    public void dispose(){
        guiStage.dispose();
    }
}
