package me.lihq.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

public class ClueManager {
    private Array<Clue> clueArray;

    public ClueManager(RoomManager roomManager, Assets assets){
        //This is a temporary list of clues
        clueArray = new Array<>();

        clueArray.add(new Clue("Big Footprint", "A big footprint left at the crime scene by the killer.", assets));
        clueArray.add(new Clue("Small Footprint", "A small footprint left at the crime scene by the killer.", assets));
        clueArray.add(new Clue("Glasses", "A pair of glasses these were found by another detective at the crime scene.", assets));
        clueArray.add(new Clue("Bag", "A bag. Someone must have left in a hurry.", assets));
        clueArray.add(new Clue("Lipstick", "Lipstick, a killers best friend.", assets));
        clueArray.add(new Clue("Right Handed", "This indicates the killer is right handed", assets));
        clueArray.add(new Clue("Dark Hair", "A dark hair from the crime scene", assets));

        clueArray.shuffle();

        for (Room room : roomManager.getRoomArray()) {
            Vector2Int randHidingSpot = room.getRandHidingSpot();

            if (randHidingSpot != null) {
                Clue clue = clueArray.pop();
                clue.setTilePosition(randHidingSpot.x, randHidingSpot.y);
                room.addClue(clue);

                if (clueArray.size == 0){
                    break;
                }
            }
        }
    }

    public Array<Clue> getClueArray() {
        return clueArray;
    }

}
