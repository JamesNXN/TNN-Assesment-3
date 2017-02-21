package me.lihq.game.gui.buttons;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.gui.Gui;

/**
 * NEW
 * Gui button that opens inventory window.
 */

public class InventoryButton extends GuiButton {
    public InventoryButton(Skin skin, Gui gui) {
        super("Inventory", skin, "statusBar", gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.getInventoryWindow().show(gui.getGuiStage());
            }
        });
    }
}
