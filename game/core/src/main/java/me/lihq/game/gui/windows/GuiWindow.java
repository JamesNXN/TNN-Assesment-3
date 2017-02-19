package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;

/**
 * Basic window used by gui. Interacts with a gui and gameworld.
 */

abstract class GuiWindow extends Dialog {
    public Gui gui;
    GameWorld gameWorld;

    GuiWindow(String title, Skin skin, Gui gui, GameWorld gameWorld) {
        super(title, skin);

        this.gui = gui;
        this.gameWorld = gameWorld;

        getTitleLabel().setAlignment(Align.center);
        setMovable(false);
        setModal(true);
    }
}

