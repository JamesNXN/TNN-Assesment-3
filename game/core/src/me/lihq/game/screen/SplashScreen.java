package me.lihq.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.lihq.game.GameMain;

public class SplashScreen extends AbstractScreen{
    private Animation<TextureRegion> splashAnimation;
    private SpriteBatch batch;

    private float stateTime = 0;

    private BitmapFont clickMe;

    /**
     * This constructor sets the relevant properties of the class.
     *
     * @param game this provides access to the gameMain class so that screens can set the states of the game.
     */
    public SplashScreen(GameMain game) {
        super(game);

        batch = new SpriteBatch();

        clickMe = game.assets.getFontWithSize(50);
    }

    @Override
    public void show() {
        game.assets.loadSplashAssets();
        game.assets.getManager().finishLoading();
        game.assets.assignSplashAssets();

        splashAnimation = new Animation<>(0.5f, game.assets.splash.getRegions());


        game.assets.loadGameAssets();

    }

    @Override
    public void render(float delta) {
        batch.begin();

        stateTime += delta;

        TextureRegion splashTexture = splashAnimation.getKeyFrame(stateTime, true);
        batch.draw(splashTexture,0,0,GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);

        if (game.assets.getManager().update()){

            clickMe.draw(batch, "CLICK ME!", 550, 400);

            if (Gdx.input.justTouched()){
                game.assets.assignGameAssets();

                //instantiate all the game screens after the assets are loaded
                game.mainMenuScreen = new MainMenuScreen(game);
                game.navigationScreen = new NavigationScreen(game);
                game.pauseScreen = new PauseScreen(game);
                game.setScreen(game.mainMenuScreen);
            }
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
    }
}
