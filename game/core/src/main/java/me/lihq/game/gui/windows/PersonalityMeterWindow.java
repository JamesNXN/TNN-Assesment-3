package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import me.lihq.game.GameMain;
import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;

/**
 * NEW
 * Window that displays player's current personality meter.
 */

public class PersonalityMeterWindow extends GuiWindow {
    private ProgressBar meterBar;

    public PersonalityMeterWindow(Skin skin, Gui gui, GameWorld gameWorld) {
        super("PERSONALITY METER", skin, gui, gameWorld);

        meterBar = new ProgressBar(0, 100, 1, false, skin);
        getContentTable().add(meterBar).size(GameMain.GAME_WIDTH * 0.5f, 100);
        button("OK", true);
    }

    @Override
    protected void result(Object object) {
        if (object.equals(true)){
            hide();
        }
    }

    @Override
    public Dialog show(Stage stage, Action action) {
        meterBar.setValue(gameWorld.getPlayer().getPersonalityMeter().getMeter());
        return super.show(stage, action);
    }
}
