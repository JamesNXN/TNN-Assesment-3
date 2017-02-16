package me.lihq.game.screen.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;

/**
 * Created by Jeff on 31/01/2017.
 */

public abstract class MenuTable extends Table {
    //Initialising necessary objects and variables

    protected GameMain game;
    protected Skin menuSkin;

    private Table titleTable;
    private Table buttonTable;

    /**
     * Constructor for the menu
     *
     * @param game - The game object the menu is being loaded for
     */
    public MenuTable(final GameMain game, String title) {
        this.game = game;
        menuSkin = game.assetLoader.menuSkin;

        align(Align.top);
        setFillParent(true);

        titleTable = new Table();
        buttonTable = new Table();

        Label titleLabel = new Label(title, menuSkin, "title", Color.RED);
        titleTable.add(titleLabel).expandX().fillX();

        add(titleTable).padTop(100).row();
        add(buttonTable).expand(true, true);
    }


    public void addButton(Button button){
        buttonTable.add(button).padBottom(Value.percentHeight(0.5f,button));
        buttonTable.row();
    }

    public Table getTitleTable(){
        return titleTable;
    }

    public Table getButtonTable(){
        return buttonTable;
    }
}
