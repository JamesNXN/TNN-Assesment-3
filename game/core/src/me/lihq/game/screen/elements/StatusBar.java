package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;

/**
 * The status bar shown throughout the game
 * Contains UI controls for presenting the game status to the player
 */
public class StatusBar
{

    public static final int HEIGHT = 50; //Used to set height of status bar
    private static final int ITEM_COUNT = 4; //Used to set width of controls on bar
    private static final int WIDTH = (int) Gdx.graphics.getWidth() / ITEM_COUNT;
    private static final Color BACKGROUND_COLOR = Color.GRAY;

    public Stage stage;
    private Skin buttonSkin;
    private Skin labelSkin;


    /**
     * The initializer for the StatusBar
     * Sets up UI controls and adds them to the stage ready for rendering
     */
    public StatusBar()
    {

        stage = new Stage();
        initSkins();

        Table statusBar = new Table();
        statusBar.setSize(Gdx.graphics.getWidth(), HEIGHT);
        statusBar.setPosition(0, 0);
        statusBar.row().height(HEIGHT);
        statusBar.defaults().width(WIDTH);

        Label scoreLabel = new Label("Score: 0", labelSkin);
        scoreLabel.setAlignment(Align.center, Align.center);
        statusBar.add(scoreLabel).uniform();

        TextButton personalityMeter = new TextButton("Personality Meter", buttonSkin);
        statusBar.add(personalityMeter).uniform();

        TextButton inventoryButton = new TextButton("Inventory", buttonSkin);
        statusBar.add(inventoryButton).uniform();

        TextButton pauseButton = new TextButton("Pause", buttonSkin);
        statusBar.add(pauseButton).uniform();

        stage.addActor(statusBar);
    }

    /**
     * Renders the status bar
     * Should be called within the render() method of a screen
     */
    public void render()
    {
        stage.act();
        stage.draw();
    }

    public void dispose()
    {
        stage.dispose();
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
        //Create a font
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
        //Create a font
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
