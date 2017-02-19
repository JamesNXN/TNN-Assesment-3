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

public class InventoryWindow extends GuiWindow {
    private final int COLUMN_COUNT = 4;
    private final float SLOT_WIDTH = 150;
    private final float SLOT_GAP_HORIZONTAL = 100;
    private final float SLOT_GAP_VERTICAL = SLOT_GAP_HORIZONTAL * 0.25f;
    private final float WINDOW_WIDTH = SLOT_WIDTH * COLUMN_COUNT + SLOT_GAP_HORIZONTAL * (COLUMN_COUNT - 1) * 1.1f;
    private final float WINDOW_HEIGHT = 700;

    public InventoryWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("INVENTORY", skin, gui, gameWorld);

        button("OK", true);
    }

    /**
     * called every time inventory is opened. Clears all the children and rebuild.
     */
    void refresh(){
        Inventory inventory = gameWorld.getPlayer().getInventory();
        getContentTable().clear();
        getContentTable().align(Align.topLeft);

        Array<Clue> entryArray = new Array<>();
        entryArray.addAll(inventory.getCollectedClues());

        for (int i = 0; i < entryArray.size; i++){
            Clue slotClue = entryArray.get(i);
            Slot slot = new Slot(slotClue.getName(), getSkin());
            slot.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gui.displayInfo(slotClue.getDescription());
                }
            });

            if ((i+1) % COLUMN_COUNT == 0){
                getContentTable().add(slot).width(SLOT_WIDTH).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).row();
            }
            else{
                getContentTable().add(slot).width(SLOT_WIDTH).padTop(SLOT_GAP_VERTICAL)
                        .padBottom(SLOT_GAP_VERTICAL).padRight(SLOT_GAP_HORIZONTAL);
            }
        }
    }

    @Override
    public float getPrefWidth() {
        return WINDOW_WIDTH;
    }

    @Override
    public float getPrefHeight() {
        return WINDOW_HEIGHT;
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }
}
