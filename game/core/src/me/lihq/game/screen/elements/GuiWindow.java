package me.lihq.game.screen.elements;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameWorld;
import me.lihq.game.Gui;

/**
 * Basic window used by gui. Interacts with a gui and gameworld.
 */

class GuiWindow extends Dialog {
    protected Gui gui;
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

