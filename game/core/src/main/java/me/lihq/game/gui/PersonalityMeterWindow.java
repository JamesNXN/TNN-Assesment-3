package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import me.lihq.game.GameWorld;

public class PersonalityMeterWindow extends GuiWindow {
    PersonalityMeterWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("PERSONALITY METER", skin, gui, gameWorld);

        button("OK", true);
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }

    @Override
    void refresh() {

    }
}
