package me.lihq.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * interface for objects that have a collision box.
 */

public interface Collidable {
    Rectangle getCollisionBox();
}
