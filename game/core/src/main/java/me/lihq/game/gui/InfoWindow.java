package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import me.lihq.game.GameWorld;

class InfoWindow extends GuiWindow {
    InfoWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("INFO", skin, gui, gameWorld);

        button("OK", true);
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }
}
