package me.lihq.game.model;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class Player {

    private int x;

    private int y;

    public Player (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move (int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
