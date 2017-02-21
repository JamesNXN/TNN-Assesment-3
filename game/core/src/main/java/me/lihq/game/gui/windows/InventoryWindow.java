package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Inventory;

/**
 * NEW
 * Window that displays data in inventory. Clicked slot will display the description of the clue
 * and the related npcs.
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
    Array<Table> setUpSlotArray() {
        Inventory inventory = gameWorld.getPlayer().getInventory();
        Array<Table> slotArray = new Array<>();

        for (Clue clue : inventory.getCollectedClues()) {
            Slot slot = new Slot(clue.getName(), getSkin());
            slot.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    String displayString = clue.getDescription();
                    if (inventory.getHint(clue) != null){
                        displayString +=  "\n Related NPCs: " + inventory.getHint(clue).getRelatedNpcNames();
                    }
                    gui.displayInfo(displayString);
                }
            });

            slotArray.add(slot);
        }
        return slotArray;
    }
}
