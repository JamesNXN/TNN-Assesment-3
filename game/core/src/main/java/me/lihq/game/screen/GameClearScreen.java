package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.lihq.game.GameMain;
import me.lihq.game.screen.elements.GameClearMenu;
import me.lihq.game.screen.elements.MainMenu;

/**
 * NEW
 * Screen for game clear. Displays completed time and score, and option to retry or quit.
 */

public class GameClearScreen  extends AbstractScreen{
    private Stage stage;

    private GameClearMenu menu;

    public GameClearScreen(GameMain game) {
        super(game);

        stage = new Stage(new FitViewport(GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT));

        //Creates a MainMenu object thus creating the main menu
        menu = new GameClearMenu(game);
    }

    @Override
    public void show() {
        stage.addActor(menu);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
