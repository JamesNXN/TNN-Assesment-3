package me.lihq.game.gui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        SlotNPC slotNPC = new SlotNPC(npc);
        slotNPC.scaleBy(1.5f);
        Container<SlotNPC> npcContainer = new Container<>(slotNPC);
        npcContainer.align(Align.center);
        add(npcContainer).size(100,150).row();

        Label npcLabel = new Label(npc.getName(), skin);
        npcLabel.setWrap(true);
        npcLabel.setAlignment(Align.center);
        add(npcLabel);

        addListener(new InputListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                slotNPC.isCursorOver = true;
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                slotNPC.isCursorOver = false;
                super.exit(event, x, y, pointer, toActor);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gui.displayInfo(npc.getDescription());
            }
        });
    }

    private class SlotNPC extends Actor{
        private Animation<TextureRegion> walkingAnimation;
        private float animStateTime = 0;
        boolean isCursorOver = false;

        private SlotNPC(NPC npc){
            walkingAnimation = npc.walkDown;
            setSize(npc.getWidth(), npc.getHeight());
            setOrigin(getWidth()/2, getHeight()/2);
        }

        @Override
        public void act(float delta) {
            if (isCursorOver){
                animStateTime += delta;
            }
            else{
                animStateTime = 0;
            }
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(walkingAnimation.getKeyFrame(animStateTime, true), getX(), getY(),
                    getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
