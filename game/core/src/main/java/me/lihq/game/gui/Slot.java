package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Entry format for inventory
 */

class Slot extends Button {
    private Actor gameActor;

    Slot(Actor gameActor, Gui gui, Skin skin) {
        super(skin);

        this.gameActor = gameActor;
        add(gameActor).size(100,100).row();
        add(gameActor.getName()).row();

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.displayInfo(null, gameActor.getName());
            }
        });
    }

    public Actor getObject() {
        return gameActor;
    }
}
