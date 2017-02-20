package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;
import me.lihq.game.models.Inventory;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Npc;

public class NpcNoteWindow extends SlotWindow{

    public NpcNoteWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("Npc Note", skin, gui, gameWorld);

        button("OK", true);
    }

    @Override
    Array<Table> setUpSlotArray() {
        Inventory inventory = gameWorld.getPlayer().getInventory();
        Array<Table> slotArray = new Array<>();

        for (AbstractPerson person : inventory.getMetCharacters()) {
            Slot slot = new Slot(person, getSkin());
            slot.addListener(new InputListener() {
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
                    gui.displayInfo(person.getDescription());
                }
            });

            slotArray.add(slot);
        }
        return slotArray;
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }
}
