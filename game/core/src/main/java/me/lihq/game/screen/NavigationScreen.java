package me.lihq.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import me.lihq.game.*;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Door;
import me.lihq.game.models.Room;
import me.lihq.game.people.Direction;
import me.lihq.game.people.NPC;
import me.lihq.game.people.PersonState;
import me.lihq.game.people.Player;
import me.lihq.game.people.controller.GamePadController;
import me.lihq.game.people.controller.PlayerController;
import me.lihq.game.gui.Gui;
import me.lihq.game.models.RoomArrow;

/**
 * This is the screen that is responsible for the navigation of the player around the game.
 * It displays the current room that the player is in, and allows the user to move the player around between rooms.
 */
public class NavigationScreen extends AbstractScreen
{
    /**
     * The controller that listens for key inputs
     */
    public PlayerController playerController;
    public GamePadController gamePadController;

    private GameWorld gameWorld;
    private Gui gui;

    /**
     * Initialises the navigation screen
     *
     * @param game - The main game instance
     * @param selectedPlayer
     */


    public NavigationScreen(GameMain game, Player selectedPlayer)
    {
        super(game);

        game.gameWorld = new GameWorld(game, selectedPlayer);
        this.gameWorld = game.gameWorld;
        gui = new Gui(game, gameWorld);

        gameWorld.setGui(gui);

        playerController = new PlayerController(game);
    }

    /**
     * This is ran when the navigation screen becomes the visible screen in GameMain
     */
    @Override
    public void show()
    {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(gui.getGuiStage());
        multiplexer.addProcessor(playerController);
        Gdx.input.setInputProcessor(multiplexer);

//        Controllers.addListener(gamePadController);

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            if (game.pauseScreen == null){
                game.pauseScreen = new PauseScreen(game);
            }
            game.setScreen(game.pauseScreen);
        }

        gameWorld.render(delta);
        gui.render(delta);
    }

    /**
     * This is called when the window is resized
     *
     * @param width  - The new width
     * @param height - The new height
     */
    @Override
    public void resize(int width, int height)
    {
        gameWorld.getGameWorldStage().getViewport().update(width, height);
        gui.getGuiStage().getViewport().update(width,height);
    }

    /**
     * This is called when the focus is lost on the window
     */
    @Override
    public void pause()
    {

    }

    /**
     * This method is called when the window is brought back into focus
     */
    @Override
    public void resume()
    {

    }

    /**
     * This method is called when the user hides the window
     */
    @Override
    public void hide()
    {

    }

    /**
     * This is to be called when you want to dispose of all data
     */
    @Override
    public void dispose()
    {
        gameWorld.dispose();
        gui.dispose();
    }
}
