package me.lihq.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import me.lihq.game.GameMain;

class FadeInOut extends Actor {
    Texture blackBlank;

    public FadeInOut(){
        Pixmap pixMap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

        pixMap.setColor(Color.BLACK);
        pixMap.fill();
        blackBlank = new Texture(pixMap);
        pixMap.dispose();

        Color color = getColor();
        setColor(color.r, color.g, color.b, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //necessary for alpha actions
        Color color = batch.getColor();
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(blackBlank, 0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        batch.setColor(color);
    }
}
