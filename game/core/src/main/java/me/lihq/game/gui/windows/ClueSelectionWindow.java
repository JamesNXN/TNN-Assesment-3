package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.speechbubbles.QuestionStyleSelectionBubble;
import me.lihq.game.gui.Slot;
import me.lihq.game.models.Clue;
import me.lihq.game.models.ClueType;
import me.lihq.game.models.Inventory;

/**
 * NEW
 * Used for choosing a clue for questioning
 */

public class ClueSelectionWindow extends SlotWindow {
    public ClueSelectionWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("", skin, gui, gameWorld);
    }

    @Override
    Array<Table> setUpSlotArray() {
        Inventory inventory = gameWorld.getPlayer().getInventory();
        Array<Table> slotArray = new Array<>();

        for (Clue clue : inventory.getCollectedClues()) {
            //only normal clues can be questioned about
            if (clue.getClueType() != ClueType.NORMAL){
                continue;
            }
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
