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

        clueArray.add(new Clue("Big Footprint", "A big footprint left at the crime scene by the killer.", new TextureRegion(assets.clueSheet, 0, 0, 32, 32)));
        clueArray.add(new Clue("Small Footprint", "A small footprint left at the crime scene by the killer.", new TextureRegion(assets.clueSheet, 0, 0, 32, 32)));
        clueArray.add(new Clue("Glasses", "A pair of glasses these were found by another detective at the crime scene.", new TextureRegion(assets.clueSheet, 0, 0, 32, 32)));
        clueArray.add(new Clue("Bag", "A bag. Someone must have left in a hurry.", new TextureRegion(assets.clueSheet, 0, 0, 32, 32)));
        clueArray.add(new Clue("Lipstick", "Lipstick, a killers best friend.", new TextureRegion(assets.clueSheet, 0, 0, 32, 32)));
        clueArray.add(new Clue("Right Handed", "This indicates the killer is right handed", new TextureRegion(assets.clueSheet, 0, 0, 32, 32)));
        clueArray.add(new Clue("Dark Hair", "A dark hair from the crime scene", new TextureRegion(assets.clueSheet, 0, 0, 32, 32)));
        //tempClues.add(new Clue("Clue 8", "test Desc", new TextureRegion(Assets.CLUE_SHEET, 0, 0, 32, 32)));
        //tempClues.add(new Clue("Clue 9", "test Desc", new TextureRegion(Assets.CLUE_SHEET, 0, 0, 32, 32)));
        //tempClues.add(new Clue("Clue 10", "test Desc", new TextureRegion(Assets.CLUE_SHEET, 0, 0, 32, 32)));

        clueArray.shuffle();

        for (Room room : roomManager.getRoomArray()) {
            Vector2Int randHidingSpot = room.getRandHidingSpot();

            if (randHidingSpot != null) {
                room.addClue(clueArray.pop().setTileCoordinates(randHidingSpot));

                if (clueArray.size == 0){
                    break;
                }
            }

        }
    }

}
