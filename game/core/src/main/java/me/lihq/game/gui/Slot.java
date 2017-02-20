package me.lihq.game.gui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.models.Clue;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Npc;
import me.lihq.game.people.Player;

/**
 * Entry format for inventory window and Npc note window
 */

public class Slot extends Button {

    private Actor slotActor;
    private boolean isCursorOver = false;


    /**
     * Slot that contains only string
     */
    public Slot(String string, Skin skin) {
        super(skin);

        Label clueLabel = new Label(string, skin);
        clueLabel.setWrap(true);
        clueLabel.setAlignment(Align.center);
        add(clueLabel).size(100,70).row();
    }

    /**
     * Slot that contains a character and string
     */
    public Slot(AbstractPerson person, Skin skin) {
        super(skin);

        slotActor = person;

        align(Align.center);
        SlotCharacter slotCharacter = new SlotCharacter(person);
        slotCharacter.scaleBy(1.5f);
        Container<SlotCharacter> slotCharacterContainer = new Container<>(slotCharacter);
        slotCharacterContainer.align(Align.center);
        add(slotCharacterContainer).size(100,150).row();

        Label nameLabel = new Label(person.getName(), skin);
        nameLabel.setWrap(true);
        nameLabel.setAlignment(Align.center);
        add(nameLabel).row();
    }

    public void setCursorOver(boolean cursorOver) {
        isCursorOver = cursorOver;
    }

    public Actor getSlotActor() {
        return slotActor;
    }

    /**
     * Actor that renders character image in slot. Only used in slot class
     */
    private class SlotCharacter extends Actor{
        private Animation<TextureRegion> walkingAnimation;
        private float animStateTime = 0;

        private SlotCharacter(AbstractPerson person){
            walkingAnimation = person.walkDown;
            setSize(person.getWidth(), person.getHeight());
            setOrigin(getWidth()/2, getHeight()/2);
        }

        @Override
        public void act(float delta) {
            //initiate animation when the cursor is on the slot
            if (isCursorOver){
                animStateTime += delta;
            }
            // halt animation when the cursor exits the slot
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
