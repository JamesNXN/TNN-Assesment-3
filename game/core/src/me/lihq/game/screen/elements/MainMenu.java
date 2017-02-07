package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.lihq.game.GameMain;
import me.lihq.game.screen.NavigationScreen;

/**
 * Main Menu UI table
 */

public class MainMenu extends MenuTable
{

    /**
     * Constructor for the menu
     *
     * @param game - The game object the menu is being loaded for
     */
    public MainMenu(GameMain game) {
        super(game, "Murder Mystery Game!");

        TextButton newGameButton = new TextButton("New Game", menuSkin);

        TextButton settings = new TextButton("Settings", menuSkin);

        TextButton quit = new TextButton("Quit", menuSkin);

        //Loading the buttons onto the stage

        addButton(newGameButton);
        addButton(settings);
        addButton(quit);

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
        //Making the "Settings" button clickable and causing it to assignGameAssets the settings screen
        settings.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                //Change to settings screen once its been made
            }
        });
    }


}