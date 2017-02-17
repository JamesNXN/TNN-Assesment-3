package me.lihq.game.gui;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Gui button that opens notebook window.
 */

class InventoryButton extends GuiButton {
    InventoryButton(Skin skin, Gui gui) {
        super(skin, "Inventory", gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.getInventoryWindow().refresh();
                gui.getInventoryWindow().show(gui.getGuiStage());
            }
        });
    }
}
