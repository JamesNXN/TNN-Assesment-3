package me.lihq.game.screen.elements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.Gui;

/**
 * Entry format for notebook and accuse window
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
                gui.displayInfo(null, (gameActor).getDescription());
            }
        });
    }

    public Actor getObject() {
        return gameActor;
    }
}
