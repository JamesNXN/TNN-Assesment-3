package me.lihq.game.screen.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.lihq.game.Assets;
import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.Player;

/**
 * This is the arrow the indicates the movement to a new room when the player is on a floor mat.
 */
public class RoomArrow extends Sprite
{
    /**
     * The player that the arrow is associated with
     */
    private Player player;

    /**
     * Whether the arrow is to be shown or not
     */
    private Boolean visible = false;

    /**
     * This constructs the RoomArrow, calling the super() method the the sprite class it extends
     *
     * @param player the player that the arrow is to be associated with
     */
    public RoomArrow(Player player)
    {
        super(Assets.getArrowDirection("NORTH"));
        this.player = player;

    }

    /**
     * This is called to draw the RoomArrow
     *
     * @param batch - the batch to draw the arrow to
     */
    @Override
    public void draw(Batch batch)
    {
        if (this.visible) {
            super.draw(batch);
        }
    }

    /**
     * This is called every tick, all the game logic related to the RoomArrow is contained here,
     * it checks to see if the player is on a trigger tile (a floor mat for example) and if so displays an arrow
     * otherwise it is hidden
     */
    public void update()
    {
        if (this.player.isOnTriggerTile()) {

            //this is the rotation of the mat (ie the way the arrow should face)
            String rotation = player.getRoom().getMatRotation(player.getTileCoordinates().x, player.getTileCoordinates().y);

            int x = (player.getTileCoordinates().x * 32) + (AbstractPerson.Direction.valueOf(rotation).getDx() * 32);
            int y = (player.getTileCoordinates().y * 32) + (AbstractPerson.Direction.valueOf(rotation).getDy() * 32);

            //if the arrow is already being displayed at the correct location we do not need to update it
            if (this.visible && this.getX() == x && this.getY() == y) {
                return;
            }

            this.setRegion(Assets.getArrowDirection(rotation));
            this.setPosition(x, y);
            this.visible = true;

        } else {
            this.visible = false;
        }

    }
}
