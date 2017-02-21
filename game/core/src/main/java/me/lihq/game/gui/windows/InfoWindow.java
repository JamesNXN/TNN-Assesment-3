package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;

/**
 * NEW
 * Window that can display information that the player may need; string and/or actor
 */

public class InfoWindow extends GuiWindow {
    public InfoWindow(Skin skin, Gui gui, GameWorld gameWorld) {
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
