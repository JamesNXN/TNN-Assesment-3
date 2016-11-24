package me.lihq.game.living;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by joeshuff on 20/11/2016.
 */
public abstract class AbstractPerson extends Sprite {

    //Storing the characters coordinates on the map
    protected Vector2 position = new Vector2().set(0,0);
    protected int offsetX = 0;
    protected int offsetY = 0;
    protected DIRECTION direction = DIRECTION.NORTH;

    //Stores the location path of the characters source image sprite sheet
    private String imagePath = "";

    public AbstractPerson(String img)
    {
        super(new Texture(img));
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void setDirection(DIRECTION direction) {this.direction = direction;}

    public DIRECTION getDirection() {return this.direction;}

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String path) {
        this.imagePath = path;
    }

    public enum DIRECTION {
        NORTH, SOUTH, EAST, WEST
    }
}
