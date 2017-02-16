package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.GameWorld;

/**
 * Basic gui button that interacts with gui and game world
 */

class GuiButton extends ImageButton {
    protected Gui gui;
    protected GameWorld gameWorld;

    GuiButton(Skin skin, String styleName, Gui gui) {
        super(skin, styleName);
        this.gui = gui;
        this.gameWorld = gui.getGameWorld();
    }
}
