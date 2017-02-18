package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.models.Inventory;
import me.lihq.game.people.NPC;

class NpcNoteWindow extends GuiWindow{
    private final int COLUMN_COUNT = 4;
    private final float SLOT_WIDTH = 150;
    private final float SLOT_GAP_HORIZONTAL = 100;
    private final float SLOT_GAP_VERTICAL = SLOT_GAP_HORIZONTAL * 0.25f;
    private final float WINDOW_WIDTH = SLOT_WIDTH * COLUMN_COUNT + SLOT_GAP_HORIZONTAL * (COLUMN_COUNT - 1) * 1.1f;
    private final float WINDOW_HEIGHT = 700;

    NpcNoteWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("NPC Note", skin, gui, gameWorld);

        button("OK", true);
    }

    @Override
    void refresh() {
        Inventory inventory = gameWorld.getPlayer().getInventory();
        getContentTable().clear();
        getContentTable().align(Align.topLeft);

        Array<NPC> entryArray = new Array<>();
        entryArray.addAll(inventory.getMetCharacters());

        for (int i = 0; i < entryArray.size; i++){
            Slot slot = new Slot(entryArray.get(i), gui, getSkin());

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
