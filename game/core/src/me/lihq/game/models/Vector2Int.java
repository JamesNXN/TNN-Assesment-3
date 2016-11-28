package me.lihq.game.models;

/**
 * Created by joeshuff on 28/11/2016.
 */
public class Vector2Int {

    public int x = 0;
    public int y = 0;

    public Vector2Int(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vector2Int)
        {
            Vector2Int v = (Vector2Int) obj;
            return (getX() == v.getX() && getY() == v.getY());
        }

        return false;
    }
}
