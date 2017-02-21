package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.lihq.game.GameMain;
import me.lihq.game.screen.NavigationScreen;

/**
 * NEW
 * Table that contains contents for pause screen
 */

public class PauseMenu extends MenuTable{
    /**
     * Constructor for the menu
     *
     * @param game - The game object the menu is being loaded for
     */
    public PauseMenu(GameMain game) {
        super(game, "PAUSE");

        TextButton resumeButton = new TextButton("Resume", menuSkin);

        TextButton quit = new TextButton("Quit", menuSkin);

        //Loading the buttons onto the stage

        addButton(resumeButton);
        addButton(quit);

        //Making the "resume" button clickable and causing it to pause the game
        resumeButton.addListener(new ClickListener()
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
    }


}
