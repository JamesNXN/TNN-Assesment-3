package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.models.Clue;

/**
 * Entry format for inventory
 */

class Slot extends Button {
    private Clue clue;

    Slot(Clue clue, Gui gui, Skin skin) {
        super(skin);

        this.clue = clue;
        Label clueLabel = new Label(clue.getName(), skin);
        clueLabel.setWrap(true);
        clueLabel.setAlignment(Align.center);
        add(clueLabel).size(100,100);

        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.displayInfo(clue.getDescription());
            }
        });
    }

    public Actor getObject() {
        return clue;
    }
}
