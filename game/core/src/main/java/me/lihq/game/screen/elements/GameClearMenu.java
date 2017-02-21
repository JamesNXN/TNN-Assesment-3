package me.lihq.game.screen.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.GameMain;
import me.lihq.game.Time;
import me.lihq.game.models.Score;
import me.lihq.game.screen.NavigationScreen;
import me.lihq.game.screen.PlayerSelectionScreen;

/**
 * NEW
 * Table that contains contents for game clear screen
 */

public class GameClearMenu extends MenuTable{

    /**
     * Constructor for the menu
     *
     * @param game  - The game object the menu is being loaded for
     */
    public GameClearMenu(GameMain game) {
        super(game, "GAME CLEAR!");

        TextButton retry = new TextButton("Retry", menuSkin);

        TextButton quit = new TextButton("Quit", menuSkin);

        int totalTime = (int) Time.getInstance().getTime();

        //convert seconds into minutes and seconds
        String timeString = String.valueOf(totalTime/60) + ":" + String.valueOf(totalTime%60);

        int totalScore = Score.getInstance().getFinalScore(totalTime);
        Label timeLabel = new Label("Time taken: " + timeString, menuSkin, "default", Color.WHITE);
        Label scoreLabel = new Label("Score: " + totalScore, menuSkin, "default", Color.WHITE);
        Label highScoreLabel = new Label("New Highscore!", menuSkin, "default", Color.RED);

        contentTable.add(timeLabel).row();
        contentTable.add(scoreLabel).row();
        if (Score.getInstance().isHighScore(totalScore)) {
            contentTable.add(highScoreLabel).row();
        }

        addButton(retry);
        addButton(quit);

        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.playerSelectionScreen = new PlayerSelectionScreen(game);
                game.setScreen(game.playerSelectionScreen);
            }
        });

        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }
}
