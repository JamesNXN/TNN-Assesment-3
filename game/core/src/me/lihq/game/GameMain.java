package me.lihq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.FPSLogger;
import me.lihq.game.controller.PlayerController;

public class GameMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TiledMap map;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
    Viewport viewport;
    FPSLogger FPS;

	@Override
	public void create () {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.zoom = 1;
        camera.update();
        //PlayerController player = new PlayerController();
        //Gdx.input.setInputProcessor(player);
        map = new TmxMapLoader().load("map.tmx");
        viewport = new FitViewport(w/2, h/2, camera);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        FPS = new FPSLogger();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    
	@Override
	public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        FPS.log();

	}

    @Override
	public void dispose () {
		map.dispose();

	}
}
