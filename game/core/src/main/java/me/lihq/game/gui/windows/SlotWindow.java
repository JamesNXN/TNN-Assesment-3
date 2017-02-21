package me.lihq.game.gui.windows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.GameWorld;
import me.lihq.game.gui.Gui;
import me.lihq.game.gui.Slot;

/**
 * NEW
 * Window that has organised slots that users can interact with. Used for inventory, question and accuse window.
 */

abstract class SlotWindow extends GuiWindow {
    private SlotWindowStyle style;

    SlotWindow(String title, Skin skin, Gui gui, GameWorld gameWorld, SlotWindowStyle style) {
        super(title, skin, gui, gameWorld);
        this.style = style;

        setUpSlotArray();
    }

    SlotWindow(String title, Skin skin, Gui gui, GameWorld gameWorld) {
        super(title, skin, gui, gameWorld);

        style = new SlotWindowStyle();
        setUpSlotArray();
    }

    /**
     * Sets up the table array that will be arranged in the window. Called every time the window is opened.
     * @return table array to be arranged
     */
    abstract Array<Table> setUpSlotArray();

    /**
     * Takes the table array from setUpSlotArray and arrange them according to the slot window style it has.
     * Called every time the window is opened.
     * @param slotArray table array it uses to build the window
     */
    void refresh(Array<Table> slotArray) {
        getContentTable().clear();
        getContentTable().align(Align.topLeft);

        for (int i = 0; i < slotArray.size; i++){
            if ((i+1) % style.COLUMN_COUNT == 0){
                getContentTable().add(slotArray.get(i)).width(style.COLUMN_WIDTH).padTop(style.COLUMN_GAP_VERTICAL)
                        .padBottom(style.COLUMN_GAP_VERTICAL).row();
            }
            else{
                getContentTable().add(slotArray.get(i)).width(style.COLUMN_WIDTH).padTop(style.COLUMN_GAP_VERTICAL)
                        .padBottom(style.COLUMN_GAP_VERTICAL).padRight(style.COLUMN_GAP_HORIZONTAL);
            }
        }
    }

    @Override
    public float getPrefWidth() {
        return style.WINDOW_WIDTH;
    }

    @Override
    public float getPrefHeight() {
        return style.WINDOW_HEIGHT;
    }

    @Override
    public Dialog show(Stage stage) {
        refresh(setUpSlotArray());
        return super.show(stage);
    }

    public SlotWindowStyle getSlotWindowStyle() {
        return style;
    }

    public void setSlotWindowStyle(SlotWindowStyle style){
        this.style = style;
    }

    /**
     * Used for configuring the arrangements and the size of the window.
     */
    static public class SlotWindowStyle{
        //default values
        public int COLUMN_COUNT = 4;
        public float COLUMN_WIDTH = 150;
        public float COLUMN_GAP_HORIZONTAL = 100;
        public float COLUMN_GAP_VERTICAL = COLUMN_GAP_HORIZONTAL * 0.1f;
        public float WINDOW_WIDTH = COLUMN_WIDTH * COLUMN_COUNT + COLUMN_GAP_HORIZONTAL * (COLUMN_COUNT - 1) * 1.1f;
        public float WINDOW_HEIGHT = 700;

        public SlotWindowStyle(){
        }

        public SlotWindowStyle(int columnCount, float columnWidth, float horizontalColumnGap,
                               float verticalColumnGap, float windowWidth, float windowHeight){
            COLUMN_COUNT = columnCount;
            COLUMN_WIDTH = columnWidth;
            COLUMN_GAP_HORIZONTAL = horizontalColumnGap;
            COLUMN_GAP_VERTICAL = verticalColumnGap;
            WINDOW_WIDTH = windowWidth;
            WINDOW_HEIGHT = windowHeight;
        }
    }
}
