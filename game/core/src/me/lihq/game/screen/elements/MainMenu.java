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

/**
 * Created by vishal on 17/12/2016.
 * Reusable Main Menu UI, can be used for the pause screen aswell.
 */

public class MainMenu {

    public Stage stage;
    private Skin buttonSkin;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final Color BACKGROUND_COLOR = Color.GRAY;

    public MainMenu() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        initButtonSkin();

        BitmapFont font = new BitmapFont();
        SpriteBatch batch= new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        font.setColor(Color.ORANGE);
        font.draw(batch, "Welcome to the Lorem Ipsum Murder Mystery Game", 10, 10);
        batch.end();

        TextButton newGameButton = new TextButton("New game", buttonSkin);
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
        TextButton Settings = new TextButton("Settings", buttonSkin);
        Settings.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/8);
        TextButton Quit = new TextButton("Quit", buttonSkin);
        Quit.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/4);

        stage.addActor(Settings);
        stage.addActor(newGameButton);
        stage.addActor(Quit);

        /**newGameButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                stage.dispose();
                System.out.println("potato");
            }
        });*/


    }

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
        Gdx.gl.glClearColor(135, 206, 235, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }
    public void dispose() {

        stage.dispose();
    }

}