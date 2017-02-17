package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

class NpcNoteButton extends GuiButton {
    NpcNoteButton(Skin skin, Gui gui) {
        super(skin, "NPC List", gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.getNpcNoteWindow().show(gui.getGuiStage());
            }
        });
    }
}
