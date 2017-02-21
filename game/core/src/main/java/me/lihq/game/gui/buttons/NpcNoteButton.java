package me.lihq.game.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.gui.Gui;

/**
 * NEW
 * Button that opens npc list window
 */
public class NpcNoteButton extends GuiButton {
    public NpcNoteButton(Skin skin, Gui gui) {
        super("NPC List", skin, gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.getNpcNoteWindow().show(gui.getGuiStage());
            }
        });
    }
}
