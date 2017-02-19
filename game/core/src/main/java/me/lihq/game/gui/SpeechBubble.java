package me.lihq.game.gui;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;
import me.lihq.game.people.AbstractPerson;

public class SpeechBubble extends Table {
    private AbstractPerson speakingPerson;

    private Table nameTable;
    private Table dialogueTable;
    private Skin skin;

    public SpeechBubble(AbstractPerson speakingPerson, String dialogue, Skin skin){
        super(skin);
        this.speakingPerson = speakingPerson;

        setTransform(true);
        align(Align.top);

        nameTable = new Table();
        Label nameLabel = new Label(speakingPerson.getName(), skin, "speechName");

        dialogueTable = new Table();
        Label dialogueLabel = new Label(dialogue, skin, "speechDialogue");
        dialogueLabel.setWrap(true);
        dialogueLabel.setAlignment(Align.center);
        dialogueTable.add(dialogueLabel).align(Align.top).width(250);
        add(dialogueTable).width(300).padTop(10);

        pack();
        //height increment to account for speech bubble tail
        setHeight(dialogueTable.getHeight() + 100);

        nameTable.add(nameLabel).align(Align.left).expandX();
        nameTable.setSize(getWidth(), nameLabel.getHeight());
        nameTable.setPosition(20, getTop() - 10, Align.topLeft);
        addActor(nameTable);

        setOrigin(getWidth()/2, getHeight()/2);

        switch (speakingPerson.getDirection()){
            case EAST:
            case SOUTH:
                setBackground(skin.getDrawable("bubble-lower-right"));
                setPosition(50, GameMain.GAME_HEIGHT/2, Align.bottomLeft);
                break;

            case WEST:
            case NORTH:
                setBackground(skin.getDrawable("bubble-lower-left"));
                setPosition(GameMain.GAME_WIDTH - 50, GameMain.GAME_HEIGHT/2, Align.bottomRight);
                break;

        }

        setScale(0);
    }

    public void show(Stage stage){
        addAction(Actions.scaleTo(1f, 1f, 0.5f, Interpolation.swingOut));
        stage.addActor(this);
    }

    public void hide(){
        addAction(Actions.sequence(
                Actions.scaleTo(0f, 0f, 0.5f, Interpolation.swingIn),
                Actions.run(this::remove)));
    }
}
