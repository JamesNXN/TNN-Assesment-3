package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.InputProcessor;
import me.lihq.game.GameMain;
import com.badlogic.gdx.audio.Music;


/**
 * Created by vishal on 17/12/2016.
 * Reusable Main Menu UI, can be used for the pause screen aswell.
 */

public class MainMenu {

    //Initialising necessary objects and variables
    public Stage stage;
    private Skin buttonSkin;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private static final int WIDTH = Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8;

    public MainMenu(final GameMain game, int MenuType) {

        //Initialising new stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        //Initialising the skin made for the buttons
        initButtonSkin();

        //Loading music and playing it on loop
        Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Mighty Like Us.mp3"));
        menuMusic.setLooping(true);
        menuMusic.play();

        BitmapFont font = new BitmapFont();
        SpriteBatch batch= new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();

        //An attempt to create a title above the buttons
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        font.setColor(Color.ORANGE);
        font.draw(batch, "Welcome to the Lorem Ipsum Murder Mystery Game", WIDTH, Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight()/8);
        batch.end();

        if (MenuType==0){
            Menu(game);
        }
        else if (MenuType==1){
            Pause(game);
        }
        else {
            System.out.println("MenuType value is incorrect");
        }

    }

    private void Menu(final GameMain game){
        //Creating the buttons using the button skin
        TextButton newGameButton = new TextButton("New game", buttonSkin);
        newGameButton.setPosition(WIDTH , Gdx.graphics.getHeight()/2);
        TextButton Settings = new TextButton("Settings", buttonSkin);
        Settings.setPosition(WIDTH , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);
        TextButton Quit = new TextButton("Quit", buttonSkin);
        Quit.setPosition(WIDTH , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/4);

        //Loading the buttons onto the stage
        stage.addActor(Settings);
        stage.addActor(newGameButton);
        stage.addActor(Quit);

        //Making the "New Game" button clickable and causing it to start the game
        newGameButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(game.screen1);
                System.out.println("Button Clicked successfully");
            }
        });
    }

    private void Pause(final GameMain game){
        //Creating the buttons using the button skin
        TextButton ResumeButton = new TextButton("Resume Game", buttonSkin);
        ResumeButton.setPosition(WIDTH , Gdx.graphics.getHeight()/2);
        TextButton Settings = new TextButton("Settings", buttonSkin);
        Settings.setPosition(WIDTH , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);
        TextButton Quit = new TextButton("Quit", buttonSkin);
        Quit.setPosition(WIDTH , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/4);

        //Loading the buttons onto the stage
        stage.addActor(Settings);
        stage.addActor(ResumeButton);
        stage.addActor(Quit);

        //Making the "New Game" button clickable and causing it to start the game
        ResumeButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(game.screen1);
                System.out.println("Button Clicked successfully");
            }
        });

    }

    //Creating the Skin for the buttons
    private void initButtonSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.ORANGE);
        pixmap.fill();
        buttonSkin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonSkin.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.down = buttonSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = buttonSkin.newDrawable("background", BACKGROUND_COLOR);
        textButtonStyle.over = buttonSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = buttonSkin.getFont("default");
        buttonSkin.add("default", textButtonStyle);

    }

    public void render() {
        //Determining the background colour of the menu
        Gdx.gl.glClearColor(135, 206, 235, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Rendering the buttons
        stage.act();
        stage.draw();

    }
    public void dispose() {
        //Called when disposing the main menu
        stage.dispose();
    }

}