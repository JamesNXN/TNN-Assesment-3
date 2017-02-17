package me.lihq.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;
import me.lihq.game.screen.PauseScreen;

/**
 * The status bar shown throughout the game
 * Contains UI controls for presenting the game status to the player
 */
public class StatusBar extends Table
{
    /**
     * The height of the StatusBar
     */
    public static final int HEIGHT = 50; //Used to set height of status bar

    /**
     * The amount of items that are in the StatusBar
     */
    private static final int ITEM_COUNT = 4; //Used to set width of controls on bar

    /**
     * The width of the StatusBar
     */
    private static final int WIDTH = Gdx.graphics.getWidth() / ITEM_COUNT;

    /**
     * The initializer for the StatusBar
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    public StatusBar(final GameMain game, Gui gui)
    {
        setSize(Gdx.graphics.getWidth(), HEIGHT);
        setPosition(0, 0);
        row().height(HEIGHT);
        defaults().width(WIDTH);

        Label scoreLabel = new Label("Score: 0", game.assetLoader.uiSkin);
        scoreLabel.setAlignment(Align.center, Align.center);
        add(scoreLabel).uniform();

        PersonalityMeterButton personalityMeter = new PersonalityMeterButton(game.assetLoader.uiSkin, gui);
        add(personalityMeter).uniform();

        InventoryButton inventoryButton = new InventoryButton(game.assetLoader.uiSkin, gui);
        add(inventoryButton).uniform();

        TextButton pauseButton = new TextButton("Pause", game.assetLoader.uiSkin);
        add(pauseButton).uniform();
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PauseScreen(game));
            }
        });
    }
}
