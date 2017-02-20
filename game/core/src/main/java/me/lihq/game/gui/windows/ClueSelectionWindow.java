package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.QuestionStyleSelectionBubble;
import me.lihq.game.gui.Slot;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Inventory;

/**
 * Used for choosing a clue for questioning
 */

public class ClueSelectionWindow extends SlotWindow {
    public ClueSelectionWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("Select clue to question about", skin, gui, gameWorld);
    }

    @Override
    Array<Slot> setUpSlotArray() {
        Inventory inventory = gameWorld.getPlayer().getInventory();
        Array<Slot> slotArray = new Array<>();

        for (Clue clue : inventory.getCollectedClues()) {
            Slot slot = new Slot(clue.getName(), getSkin());
            slot.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    hide();
                    QuestionStyleSelectionBubble bubble = new QuestionStyleSelectionBubble(gameWorld, clue, getSkin());
                    gameWorld.getConversationManager().addSpeechBubble(bubble);
                    gameWorld.getConversationManager().nextSpeechBubble();
                }
            });

            slotArray.add(slot);
        }
        return slotArray;
    }
}
