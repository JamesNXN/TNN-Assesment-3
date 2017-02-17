package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import me.lihq.game.GameMain;
import me.lihq.game.GameWorld;
import me.lihq.game.screen.elements.MainMenu;

class PersonalityMeterWindow extends GuiWindow {
    private ProgressBar meterBar;

    PersonalityMeterWindow(Skin skin, Gui gui, GameWorld gameWorld) {
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
    void refresh() {
        meterBar.setValue(gameWorld.getPlayer().getPersonalityLevel());
    }
}
