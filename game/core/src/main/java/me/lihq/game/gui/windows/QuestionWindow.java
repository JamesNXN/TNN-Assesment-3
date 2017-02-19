package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;

public class QuestionWindow extends SlotWindow {
    QuestionWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("Question", skin, gui, gameWorld);
    }

    @Override
    Array<Slot> setUpSlotArray() {
        return null;
    }
}
