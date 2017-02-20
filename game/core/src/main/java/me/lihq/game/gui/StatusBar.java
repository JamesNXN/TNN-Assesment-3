package me.lihq.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;
import me.lihq.game.gui.buttons.InventoryButton;
import me.lihq.game.gui.buttons.NpcNoteButton;
import me.lihq.game.gui.buttons.PersonalityMeterButton;
import me.lihq.game.models.Score;

/**
 * The status bar shown throughout the game
 * Contains UI controls for presenting the game status to the player
 */
class StatusBar extends Table
{
    /**
     * The height of the StatusBar
     */
    private static final int HEIGHT = 50; //Used to set height of status bar

    /**
     * The amount of items that are in the StatusBar
     */
    private static final int ITEM_COUNT = 4; //Used to set width of controls on bar

    /**
     * The width of the StatusBar
     */
    private static final int WIDTH = Gdx.graphics.getWidth() / ITEM_COUNT;

    private Label scoreLabel;

    /**
     * The initializer for the StatusBar
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    StatusBar(final GameMain game, Gui gui)
    {
        setSize(Gdx.graphics.getWidth(), HEIGHT);
        setPosition(0, 0);
        row().height(HEIGHT);
        defaults().width(WIDTH);

        scoreLabel = new Label("Score: " + Score.getInstance().getCurrentScore(), game.assetLoader.uiSkin);
        scoreLabel.setAlignment(Align.center, Align.center);
        add(scoreLabel).uniform();

        PersonalityMeterButton personalityMeter = new PersonalityMeterButton(game.assetLoader.uiSkin, gui);
        add(personalityMeter).uniform();

        InventoryButton inventoryButton = new InventoryButton(game.assetLoader.uiSkin, gui);
        add(inventoryButton).uniform();

        NpcNoteButton npcNoteButton = new NpcNoteButton(game.assetLoader.uiSkin, gui);
        add(npcNoteButton).uniform();
    }

    @Override
    public void act(float delta) {
        scoreLabel.setText("Score: " + Score.getInstance().getCurrentScore());
        super.act(delta);
    }
}
