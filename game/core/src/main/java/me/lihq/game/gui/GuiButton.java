package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.GameWorld;

/**
 * Basic gui button that interacts with gui and game world
 */

abstract class GuiButton extends TextButton {
    protected Gui gui;
    protected GameWorld gameWorld;

    GuiButton(Skin skin, String text, Gui gui) {
        super(text, skin);
        this.gui = gui;
        this.gameWorld = gui.getGameWorld();
    }
}
