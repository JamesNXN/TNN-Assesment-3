package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.lihq.game.GameMain;
import me.lihq.game.models.Score;
import me.lihq.game.screen.NavigationScreen;
import me.lihq.game.screen.PlayerSelectionScreen;

/**
 * NEW
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

        TextButton quit = new TextButton("Quit", menuSkin);

        Label highScoreLabel = new Label("High score: " + Score.getInstance().getHighScore(), menuSkin, "default", Color.RED);
        contentTable.add(highScoreLabel);

        //Loading the buttons onto the stage
        addButton(newGameButton);
        addButton(quit);

        //Making the "New Game" button clickable and causing it to start the game
        newGameButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.playerSelectionScreen = new PlayerSelectionScreen(game);
                game.setScreen(game.playerSelectionScreen);
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
    }


}