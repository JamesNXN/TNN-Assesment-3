package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.InputProcessor;
import com.sun.prism.shader.Texture_LinearGradient_REFLECT_AlphaTest_Loader;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by vishal on 17/12/2016.
 * Reusable Main Menu UI, can be used for the pause screen aswell.
 */

public class MainMenu {

    //Initialising necessary objects and variables
    public Stage stage;
    private Skin buttonSkin;
    private OrthographicCamera camera;
    private SpriteBatch batch = new SpriteBatch();
    private static final Color BACKGROUND_COLOR = Color.GRAY;
    private static final int WIDTH = Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8;

    private TextButton newGameButton;
    private TextButton settingsButton;
    private TextButton quitButton;

    private Animation introDuck;
    private float stateTime = 0f;
    private TextureRegion currentFrame;

    private TextureRegion test1;

    public MainMenu(final GameMain game) {

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
        OrthographicCamera camera = new OrthographicCamera();

        loadDuck();

        //An attempt to create a title above the buttons
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        font.setColor(Color.ORANGE);
        font.draw(batch, "Welcome to the Lorem Ipsum Murder Mystery Game", WIDTH, Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight()/8);
        batch.end();

        //Creating the buttons using the button skin
        newGameButton = new TextButton("New game", buttonSkin);
        newGameButton.setPosition(WIDTH , Gdx.graphics.getHeight()/2);
        settingsButton = new TextButton("Settings", buttonSkin);
        settingsButton.setPosition(WIDTH , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);
        quitButton = new TextButton("Quit", buttonSkin);
        quitButton.setPosition(WIDTH , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/4);

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

        startTimer();
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

    public void loadDuck()
    {
        TextureRegion stage1 = new TextureRegion(Assets.loadTexture("title.png"), 0, 0, 480, 360);
        TextureRegion stage2 = new TextureRegion(Assets.loadTexture("title.png"), 0, 360, 480, 360);

        test1 = stage1;

        TextureRegion[] anim = new TextureRegion[]{stage1, stage1, stage2};

        introDuck = new Animation(0.7f, anim);
        introDuck.setPlayMode(Animation.PlayMode.LOOP);

        currentFrame = introDuck.getKeyFrame(0);
    }

    int secondsOfIntro = 0;

    public void startTimer()
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                secondsOfIntro ++;

                if (secondsOfIntro == 3)
                {
                    showMenu();
                    introDuck = null;
                }
            }
        };

        timer.schedule(task, 1000, 1000);
    }

    public void showMenu()
    {
        //Loading the buttons onto the stage
        stage.addActor(settingsButton);
        stage.addActor(newGameButton);
        stage.addActor(quitButton);
    }

    public void render(float delta) {
        //Determining the background colour of the menu
        Gdx.gl.glClearColor(135, 206, 235, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Rendering the buttons
        batch.begin();

        if (introDuck != null)
        {
            stateTime += delta;
            currentFrame = introDuck.getKeyFrame(stateTime, true);
            batch.draw(currentFrame.getTexture(), -240, -180);
//            batch.draw(test1.getTexture(), -240, -180);
        }

        batch.end();

        stage.act();
        stage.draw();

    }
    public void dispose() {
        //Called when disposing the main menu
        stage.dispose();
    }

}