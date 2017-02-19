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
    Table nameTable;
    Table contentTable;

    /**
     * Constructs a template for speech bubble classes used for interaction
     * @param skin skin for table construction
     */
    public SpeechBubble(Skin skin){
        super(skin);

        nameTable = new Table();

        contentTable = new Table(skin);
        add(contentTable).width(300).padTop(10);
    }

    public Table getContentTable(){
        return contentTable;
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
