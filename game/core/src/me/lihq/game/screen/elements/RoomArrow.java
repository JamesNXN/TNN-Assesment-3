package me.lihq.game.screen.elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.lihq.game.Assets;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.living.Player;

/**
 * Created by brookehatton on 29/12/2016.
 */
public class RoomArrow extends Sprite
{

    private Player player;

    private Boolean visible = false;

    public RoomArrow(Player player) {
        super(Assets.getArrowDirection("NORTH"));
        this.player = player;

    }


    public void render(Batch batch) {

        if (this.player.getRoom().isTriggerTile(this.player.getTileCoordinates().x, this.player.getTileCoordinates().y) && this.player.getState() != AbstractPerson.PersonState.WALKING) {
            if (this.visible) {
                this.draw(batch);
            } else {
                String rotation = player.getRoom().getMatRotation(player.getTileCoordinates().x, player.getTileCoordinates().y);
                this.setRegion(Assets.getArrowDirection(rotation));
                System.out.println(Assets.getArrowDirection(rotation));

                int x = (player.getTileCoordinates().x * 32) + (AbstractPerson.Direction.valueOf(rotation).getDx() * 32);
                int y = (player.getTileCoordinates().y * 32) + (AbstractPerson.Direction.valueOf(rotation).getDy() * 32);

                this.setPosition(x, y);
                this.visible = true;

            }
        } else {
            this.visible = false;
        }
    }


}
