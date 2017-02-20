package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;

public class AccuseWindow extends SlotWindow{
    AccuseWindow(String title, Skin skin, Gui gui, GameWorld gameWorld) {
        super(title, skin, gui, gameWorld);
    }

    @Override
    Array<Slot> setUpSlotArray() {
        return null;
    }
}
