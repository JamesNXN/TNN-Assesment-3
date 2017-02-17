package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PersonalityMeterButton extends GuiButton {
    PersonalityMeterButton(Skin skin, Gui gui) {
        super(skin, "Personality Meter", gui);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                gui.getPersonalityMeterWindow().show(gui.getGuiStage());
            }
        });
    }
}
