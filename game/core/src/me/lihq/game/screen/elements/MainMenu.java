package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.lihq.game.GameMain;

/**
 * Reusable Main initMenu UI, can be used for the pause screen as well.
 */

public class MainMenu extends Table
{
    /**
     * The background color of the menu
     */
    private static final Color BACKGROUND_COLOR = Color.GRAY;

    /**
     * The width of the menu
     */
    private static final int WIDTH = Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8;

    //Initialising necessary objects and variables

    /**
     * The default button skins
     */
    private Skin buttonSkin;

    /**
     * Constructor for the menu
     *
     * @param game      - The game object the menu is being loaded for
     */
    public MainMenu(final GameMain game)
    {

        //Initialising the skin made for the buttons
        initButtonSkin();

        //Initialising things required for text


        //Loading the menu or pause screen
        initMenu(game);
    }

    /**
     * This method is called if you want to initialise the main menu
     *
     * @param game - The game to initialise the menu for
     */
    private void initMenu(final GameMain game)
    {
        //Creating the buttons using the button skin
        //An if statement that lets the same class be used for both the pause and main menu
        //screens. It also prints an error message to the console if called using an incorrect argument

        BitmapFont font = new BitmapFont();

        LabelStyle textStyle = new LabelStyle(font, Color.RED);

        //Creating the label containing text and determining  its size and location on screen
        Label text;


        TextButton newGameButton = new TextButton("", buttonSkin);

        if (pauseMenu) {
            newGameButton.setText("Resume Game");
            text = new Label("Pause", textStyle);

        } else {
            text = new Label("Welcome To the Lorem Ipsum Murder Mystery Game!", textStyle);
            newGameButton.setText("New Game");
        }

        text.setFontScale(2, 2);

        text.setBounds(Gdx.graphics.getWidth() / 2 - text.getWidth(), Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 3 + Gdx.graphics.getHeight() / 16, text.getWidth(), text.getHeight());

        newGameButton.setPosition(WIDTH, Gdx.graphics.getHeight() / 2);
        TextButton settings = new TextButton("Settings", buttonSkin);
        settings.setPosition(WIDTH, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 8);
        TextButton quit = new TextButton("Quit", buttonSkin);
        quit.setPosition(WIDTH, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 4);

        //Loading the buttons onto the stage
        stage.addActor(text);
        stage.addActor(settings);
        stage.addActor(newGameButton);
        stage.addActor(quit);

        //Making the "New Game" button clickable and causing it to start the game
        newGameButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(game.navigationScreen);
            }
        });

        //Making the "Quit" button clickable and causing it to close the game
        quit.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });
        //Making the "Settings" button clickable and causing it to load the settings screen
        settings.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                //Change to settings screen once its been made
            }
        });
    }


    /**
     * This method creates the skins for the buttons
     */
    private void initButtonSkin()
    {
        //Create a font
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.ORANGE);
        pixmap.fill();
        buttonSkin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = buttonSkin.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.over = buttonSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);

    }

    /**
     * This method is called to render the main menu to the stage
     */
    public void render()
    {
        //Determining the background colour of the menu
        Gdx.gl.glClearColor(135, 206, 235, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Rendering the buttons
        stage.act();
        stage.draw();
    }

    /**
     * This method disposes of all elements
     */
    public void dispose()
    {
        stage.dispose();
    }

    /**
     * This method is called when the window is resized.
     *
     * @param width  - The new width
     * @param height - The new height
     */
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
    }
}