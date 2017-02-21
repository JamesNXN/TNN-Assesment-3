package me.lihq.game.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;

/**
 * NEW
 * Basic gui button that interacts with corresponding windows
 */

abstract class GuiButton extends TextButton {
    protected Gui gui;
    protected GameWorld gameWorld;

    GuiButton(String text, Skin skin, Gui gui) {
        super(text, skin);
        this.gui = gui;
        this.gameWorld = gui.getGameWorld();
    }

    public GuiButton(String text, Skin skin, String styleName, Gui gui) {
        super(text, skin, styleName);
        this.gui = gui;
        this.gameWorld = gui.getGameWorld();
    }
}
