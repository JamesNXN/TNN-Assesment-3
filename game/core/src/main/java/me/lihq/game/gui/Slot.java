package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.models.Clue;
import me.lihq.game.people.NPC;
import me.lihq.game.people.PersonState;

/**
 * Entry format for inventory window and Npc note window
 */

class Slot extends Button {
    Slot(Clue clue, Gui gui, Skin skin) {
        super(skin);

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

    Slot(NPC npc, Gui gui, Skin skin) {
        super(skin);

        align(Align.center);
        NPC npcCopy = new NPC(npc);
        npcCopy.scaleBy(1.5f);
        Container<NPC> npcContainer = new Container<>(npcCopy);
        npcContainer.align(Align.center);
        add(npcContainer).size(100,150).row();

        Label clueLabel = new Label(npc.getName(), skin);
        clueLabel.setWrap(true);
        clueLabel.setAlignment(Align.center);
        add(clueLabel);

        addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                npcCopy.setState(PersonState.FIXED_WALKING);
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                npcCopy.setState(PersonState.STANDING);
                super.exit(event, x, y, pointer, toActor);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gui.displayInfo(npcCopy.getDescription());
            }
        });
    }
}
