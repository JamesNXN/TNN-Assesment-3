package me.lihq.game.screen.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import me.lihq.game.Assets;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Player;

/**
 * This is the arrow the indicates the movement to a new room when the player is on a floor mat.
 */
public class RoomArrow extends Actor
{
    /**
     * The player that the arrow is associated with
     */
    private Player player;

    /**
     * This constructs the RoomArrow, calling the super() method the the sprite class it extends
     *
     * @param player the player that the arrow is to be associated with
     */
    public RoomArrow(Player player, Assets assets)
    {
        assets.getArrowDirection("NORTH");
        this.player = player;

    }


    /**
     * This is called every tick, all the game logic related to the RoomArrow is contained here,
     * it checks to see if the player is on a trigger tile (a floor mat for example) and if so displays an arrow
     * otherwise it is hidden
     */
    public void update()
    {

    }
}
