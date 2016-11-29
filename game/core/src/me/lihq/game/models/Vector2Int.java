package me.lihq.game.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.NumberUtils;

/**
 * Created by joeshuff on 28/11/2016.
 */
//TODO: ADD DOCUMENTATION
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
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vector2Int other = (Vector2Int)obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }
}
