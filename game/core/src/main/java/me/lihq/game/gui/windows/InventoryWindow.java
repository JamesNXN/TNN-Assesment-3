package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Inventory;

/**
 * Window that displays data in notebook
 */

public class InventoryWindow extends SlotWindow {
    public InventoryWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("INVENTORY", skin, gui, gameWorld);

        button("OK", true);
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }

    /**
     * Set up slots that will be added to the window
     */
    @Override
    Array<Slot> setUpSlotArray() {
        Inventory inventory = gameWorld.getPlayer().getInventory();
        Array<Slot> slotArray = new Array<>();

        for (Clue clue : inventory.getCollectedClues()) {
            Slot slot = new Slot(clue.getName(), getSkin());
            slot.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gui.displayInfo(clue.getDescription());
                }
            });

            slotArray.add(slot);
        }
        return slotArray;
    }
}
