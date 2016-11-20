package me.lihq.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.FPSLogger;
import me.lihq.game.screen.GameScreen;


public class GameMain extends Game {
    FPSLogger FPS;
    private GameScreen screen1;

    //This is a static reference to itself. Comes in REALLY handy when in other classes that don't have a reference to the main game
    public static GameMain me = null;

    //Game wide variables


	@Override
	public void create () {
        screen1 = new GameScreen(this);

        me = this;

        this.setScreen(screen1);
        
        FPS = new FPSLogger();
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        FPS.log();

        super.render();

	}

    @Override
	public void dispose () {
//		map.dispose();

	}
}
