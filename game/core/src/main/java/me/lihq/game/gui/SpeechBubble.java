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

public abstract class SpeechBubble extends Table {
    private AbstractPerson speakingPerson;

    Table nameTable;
    Label nameLabel;
    Table contentTable;

    private Skin skin;

    /**
     * Constructs a template for speech bubble classes used for interaction
     * @param skin skin for table construction
     * @param speakingPerson person that the speech bubble should be attached to
     */
    public SpeechBubble(AbstractPerson speakingPerson, Skin skin){
        super(skin);

        this.skin = skin;
        this.speakingPerson = speakingPerson;

        setTransform(true);
        align(Align.top);

        nameTable = new Table();

        contentTable = new Table(skin);
        add(contentTable).width(300).padTop(10);
    }

    public Table getContentTable(){
        return contentTable;
    }

    public void show(Stage stage){
        pack();
        //height increment to account for speech bubble tail
        setHeight(contentTable.getHeight() + 100);

        if (nameLabel != null) {
            nameTable.add(nameLabel).align(Align.left).expandX();
            nameTable.setSize(getWidth(), nameLabel.getHeight());
            nameTable.setPosition(20, getTop() - 10, Align.topLeft);
            addActor(nameTable);
        }

        setOrigin(getWidth()/2, getHeight()/2);

        addAction(Actions.scaleTo(1f, 1f, 0.5f, Interpolation.swingOut));
        stage.addActor(this);

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

    public void hide(){
        addAction(Actions.sequence(
                Actions.scaleTo(0f, 0f, 0.5f, Interpolation.swingIn),
                Actions.run(this::remove)));
    }

    public AbstractPerson getSpeakingPerson() {
        return speakingPerson;
    }
}
