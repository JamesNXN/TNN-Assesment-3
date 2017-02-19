package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;
import me.lihq.game.models.Inventory;
import me.lihq.game.people.Npc;

public class NpcNoteWindow extends GuiWindow{
    private final int COLUMN_COUNT = 4;
    private final float SLOT_WIDTH = 150;
    private final float SLOT_GAP_HORIZONTAL = 100;
    private final float SLOT_GAP_VERTICAL = SLOT_GAP_HORIZONTAL * 0.25f;
    private final float WINDOW_WIDTH = SLOT_WIDTH * COLUMN_COUNT + SLOT_GAP_HORIZONTAL * (COLUMN_COUNT - 1) * 1.1f;
    private final float WINDOW_HEIGHT = 700;

    public NpcNoteWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("Npc Note", skin, gui, gameWorld);

        button("OK", true);
    }

    @Override
    void refresh() {
        Inventory inventory = gameWorld.getPlayer().getInventory();
        getContentTable().clear();
        getContentTable().align(Align.topLeft);

        Array<Npc> entryArray = new Array<>();
        entryArray.addAll(inventory.getMetCharacters());

        for (int i = 0; i < entryArray.size; i++){
            Slot slot = new Slot(entryArray.get(i), getSkin());
            slot.addListener(new InputListener(){
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    slot.setCursorOver(true);
                    super.enter(event, x, y, pointer, fromActor);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    slot.setCursorOver(false);
                    super.exit(event, x, y, pointer, toActor);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    gui.displayInfo(((Npc)slot.getSlotActor()).getDescription());
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
