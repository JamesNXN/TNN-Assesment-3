package me.lihq.game.gui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;
import me.lihq.game.GameWorld;
import me.lihq.game.people.AbstractPerson;

public class SpeechBubble extends Table {
    private AbstractPerson speakingPerson;

    private Table nameTable;
    private Table dialogueTable;
    private Skin skin;

    public SpeechBubble(AbstractPerson speakingPerson, String dialogue, Skin skin){
        super(skin);

        debug();
        this.speakingPerson = speakingPerson;

        setTransform(true);
        setSize(250, 150);

        nameTable = new Table();
        Label nameLabel = new Label(speakingPerson.getName(), skin, "speechName");

        nameTable.add(nameLabel).align(Align.left).expandX();
        nameTable.setSize(getWidth(), nameLabel.getHeight());
        nameTable.setPosition(15, getTop() - nameTable.getHeight() - 15);
        addActor(nameTable);

        dialogueTable = new Table();
        Label dialogueLabel = new Label(dialogue, skin, "speechDialogue");
        dialogueTable.add(dialogueLabel);
        add(dialogueTable);

        switch (speakingPerson.getDirection()){
            case EAST:
            case SOUTH:
                setBackground(skin.getDrawable("bubble-lower-right"));
                setPosition(GameMain.GAME_WIDTH/4, GameMain.GAME_HEIGHT/2, Align.bottom);
                break;

            case WEST:
            case NORTH:
                setBackground(skin.getDrawable("bubble-lower-left"));
                setPosition(GameMain.GAME_WIDTH/4 * 3, GameMain.GAME_HEIGHT/2, Align.bottom);
                break;

        }
    }

    public void show(Stage stage){
        stage.addActor(this);
    }

    public void hide(){
        remove();
    }
}
