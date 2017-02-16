package me.lihq.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import me.lihq.game.GameMain;

/**
 * This class is for the RoomTag that is to be displayed at the top left
 * of the screen when a new room is entered.
 */
public class RoomTag extends Table
{
    private Texture roomTagBackground;
    private Label roomNameLabel;

    public RoomTag(Texture roomTagBackground, BitmapFont roomTagFont) {
        setTransform(true);
        this.roomTagBackground = roomTagBackground;
        setColor(getColor().r, getColor().g, getColor().b, 0);

        Label.LabelStyle style = new Label.LabelStyle(roomTagFont, Color.WHITE);
        roomNameLabel = new Label("",style);

        add(roomNameLabel);
    }

    public void setRoomName(String roomName){
        roomNameLabel.setText(roomName);

        //required for getting width of room name
        GlyphLayout layout = new GlyphLayout(roomNameLabel.getStyle().font, roomName);

        setSize(layout.width + 50, roomNameLabel.getHeight() + 50);
        setPosition(10, GameMain.GAME_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(roomTagBackground,getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
    }
}
