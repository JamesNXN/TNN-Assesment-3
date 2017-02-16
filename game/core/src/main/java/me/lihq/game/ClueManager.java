package me.lihq.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.models.Clue;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

public class ClueManager {
    private Array<Clue> clueArray;

    public ClueManager(RoomManager roomManager, AssetLoader assetLoader){
        //This is a temporary list of clues
        clueArray = new Array<>();

        Json json = new Json();
        Array<JsonValue> clueJsonData = json.readValue(Array.class, assetLoader.clueJsonData);
        for (JsonValue data : clueJsonData){
            clueArray.add(new Clue(data, assetLoader.clueGlint));
        }

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
