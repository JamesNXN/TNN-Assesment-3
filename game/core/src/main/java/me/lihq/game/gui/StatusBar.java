package me.lihq.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;
import me.lihq.game.screen.NavigationScreen;
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
     * The background color of the StatusBar
     */
    private static final Color BACKGROUND_COLOR = Color.GRAY;

    /**
     * The different skins for different elements
     */
    private Skin buttonSkin;
    private Skin labelSkin;

    /**
     * The initializer for the StatusBar
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    public StatusBar(final GameMain game, Gui gui)
    {
        initSkins();

        setSize(Gdx.graphics.getWidth(), HEIGHT);
        setPosition(0, 0);
        row().height(HEIGHT);
        defaults().width(WIDTH);

        Label scoreLabel = new Label("Score: 0", labelSkin);
        scoreLabel.setAlignment(Align.center, Align.center);
        add(scoreLabel).uniform();

        TextButton personalityMeter = new TextButton("Personality Meter", buttonSkin);
        add(personalityMeter).uniform();

        InventoryButton inventoryButton = new InventoryButton(skin, gui);
        add(inventoryButton).uniform();

        TextButton pauseButton = new TextButton("Pause", buttonSkin);
        add(pauseButton).uniform();
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PauseScreen(game));
            }
        });
    }

    /**
     * Sets up skin variables used for defining UI control styles
     */
    private void initSkins()
    {
        initButtonSkin();
        initLabelSkin();
    }

    /**
     * Sets up the skin for buttons on the status bar
     */
    private void initButtonSkin()
    {
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.BLACK);
        textButtonStyle.checked = buttonSkin.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.over = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);

    }

    /**
     * Sets up the skin for labels on the status bar
     */
    private void initLabelSkin()
    {
        BitmapFont font = new BitmapFont();
        labelSkin = new Skin();

        //Create a texture
        Pixmap pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(BACKGROUND_COLOR);
        pixmap.fill();
        labelSkin.add("background", new Texture(pixmap));

        //Create a button style
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        labelStyle.background = labelSkin.getDrawable("background");
        labelSkin.add("default", labelStyle);

    }
}
