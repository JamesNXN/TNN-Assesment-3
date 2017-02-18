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
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;

/**
 * Entry format for inventory window and Npc note window
 */

public class Slot extends Button {

    private Actor slotActor;
    private boolean isCursorOver = false;


    /**
     * Slot for clues in inventory window
     */
    Slot(Clue clue, Gui gui, Skin skin) {
        super(skin);

        slotActor = clue;

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

    /**
     * Slot for Npcs in Npc note window
     */
    public Slot(NPC npc, Gui gui, Skin skin) {
        super(skin);

        slotActor = npc;

        align(Align.center);
        SlotCharacter slotCharacter = new SlotCharacter(npc);
        slotCharacter.scaleBy(1.5f);
        Container<SlotCharacter> npcContainer = new Container<>(slotCharacter);
        npcContainer.align(Align.center);
        add(npcContainer).size(100,150).row();

        Label npcLabel = new Label(npc.getName(), skin);
        npcLabel.setWrap(true);
        npcLabel.setAlignment(Align.center);
        add(npcLabel);
    }

    public Slot(Player player, Skin skin){
        super(skin);

        slotActor = player;

        align(Align.center);
        SlotCharacter slotCharacter = new SlotCharacter(player);
        slotCharacter.scaleBy(1.5f);
        Container<SlotCharacter> npcContainer = new Container<>(slotCharacter);
        npcContainer.align(Align.center);
        add(npcContainer).size(100,150).row();

        Label playerLabel = new Label(player.getName(), skin);
        playerLabel.setWrap(true);
        playerLabel.setAlignment(Align.center);
        add(playerLabel);
    }

    public boolean isCursorOver() {
        return isCursorOver;
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
