package me.lihq.game.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.gui.Gui;

/**
 * NEW
 * Button that opens personality meter window
 */
public class PersonalityMeterButton extends GuiButton {
    public PersonalityMeterButton(Skin skin, Gui gui) {
        super("Personality Meter", skin, gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gui.getPersonalityMeterWindow().show(gui.getGuiStage());
            }
        });
    }
}
