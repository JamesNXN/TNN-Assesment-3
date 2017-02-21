package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Inventory;

/**
 * NEW
 * Window that displays collected clues which players can choose to present for accusation.
 */

public class AccuseWindow extends SlotWindow{
    private Array<Clue> cluesToBePresented;

    public AccuseWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("", skin, gui, gameWorld);

        button("OK", true);
        button("Cancel", false);
    }

    @Override
    Array<Table> setUpSlotArray() {
        cluesToBePresented = new Array<>();
        Inventory inventory = gameWorld.getPlayer().getInventory();
        Array<Table> slotArray = new Array<>();

        for (Clue clue : inventory.getCollectedClues()) {
            Slot slot = new Slot(clue.getName(), getSkin());
            slot.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    String displayString = clue.getDescription();
                    if (inventory.getHint(clue) != null) {
                        displayString += "\n Related NPCs: " + inventory.getHint(clue).getRelatedNpcNames();
                    }
                    gui.displayInfo(displayString);
                }
            });

            //add checkbox to the slot keeping track of which clue to be presented
            Table table = new Table();
            CheckBox checkBox = new CheckBox("Present", getSkin());
            checkBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Clicked");
                    if (checkBox.isChecked()){
                        cluesToBePresented.add(clue);
                    }
                    else{
                        cluesToBePresented.removeValue(clue, false);
                    }
                }
            });
            table.add(slot).row();
            table.add(checkBox);

            slotArray.add(table);
        }
        return slotArray;
    }

    @Override
    protected void result(Object object) {
        // if pressed ok, continue to accuse logic
        if (object.equals(true)){
            gameWorld.getInteraction().accuse(cluesToBePresented);
        }
        // if pressed cancel, terminate interaction without penalty
        else{
            hide();
            gameWorld.getConversationManager().addSpeechBubble(gameWorld.getPlayer(), "Sorry, it was a mistake.");
            gameWorld.getConversationManager().addSpeechBubble(gameWorld.getInteraction().getInteractingNpc(), "Thought so!");
            gameWorld.getConversationManager().nextSpeechBubble();
        }
    }
}
