package me.lihq.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.models.Clue;
import me.lihq.game.models.ClueType;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;

/**
 * Clue manager handles creating and assigning clues to rooms
 */
public class ClueManager {
    public static ClueManager instance;

    /**
     * Parameters needed for ClueManager:
     *
     * relevantNormalClueArray - array of clues related to the murderer
     */
    private Array<Clue> relevantNormalClueArray;
    private Clue weaponClue;
    private Clue motiveClue;

    public ClueManager(NpcManager npcManager, RoomManager roomManager, AssetLoader assetLoader){
        relevantNormalClueArray = new Array<>();

        // load the entire clue roster
        Json json = new Json();
        Array<JsonValue> clueJsonData = json.readValue(Array.class, assetLoader.clueJsonData);

        Array<Clue> totalIrrelevantNormalClueArray = new Array<>();
        Array<Clue> totalWeaponClueArray = new Array<>();

        for (JsonValue data : clueJsonData){
            Clue clue = new Clue(data, assetLoader.clueGlint);

            if (clue.getClueType() == ClueType.NORMAL) {
                if (clue.getRelatedNpcIdArray().contains(npcManager.getMurderer().getId(), true)) {
                    System.out.println(clue.getName());
                    relevantNormalClueArray.add(clue);
                }
                else{
                    totalIrrelevantNormalClueArray.add(clue);
                }
            }
            else if (clue.getClueType() == ClueType.WEAPON){
                totalWeaponClueArray.add(clue);
            }
            else if (clue.getClueType() == ClueType.MOTIVE && clue.getRelatedNpcIdArray().contains(npcManager.getMurderer().getId(), true)){
                motiveClue = clue;
            }
        }

        //assign murder weapon to the murderer
        weaponClue = totalWeaponClueArray.random();
        weaponClue.getRelatedNpcIdArray().add(npcManager.getMurderer().getId());

        //distribute normal clues to the rooms
        totalIrrelevantNormalClueArray.shuffle();

        //every room must have at least 1 clue, so the minimum clue count is 10. Maximum is set to relevant clue count x 1.5.
        int newSize = MathUtils.random(10 - relevantNormalClueArray.size, (int)(relevantNormalClueArray.size * 1.5f));
        totalIrrelevantNormalClueArray.setSize(newSize);

        Array<Clue> cluesToBeDistributed = new Array<>();
        cluesToBeDistributed.addAll(relevantNormalClueArray);
        cluesToBeDistributed.addAll(totalIrrelevantNormalClueArray);
        cluesToBeDistributed.add(weaponClue);

        cluesToBeDistributed.shuffle();

        Array<Room> roomArray = new Array<>();
        roomArray.addAll(roomManager.getRoomArray());
        roomArray.shuffle();

        int clueCount = cluesToBeDistributed.size;
        for (int i = 0; i < clueCount; i++) {
            // the number of rooms is 10, therefore index mod 10 to prevent array index exception
            Room room = roomArray.get(i % 10);
            Array<Vector2Int> possibleHidingSpots = roomArray.get(i % 10).getHidingSpots();
            possibleHidingSpots.shuffle();
            Vector2Int randHidingSpot = possibleHidingSpots.pop();

            Clue clue = cluesToBeDistributed.pop();
            clue.setTilePosition(randHidingSpot.x, randHidingSpot.y);
            room.addClue(clue);

            if (cluesToBeDistributed.size == 0){
                break;
            }
        }

        roomManager.getMurderRoom().addClue(motiveClue);

        instance = this;
    }

    public Array<Clue> getRelevantNormalClueArray() {
        return relevantNormalClueArray;
    }

    public Clue getWeaponClue() {
        return weaponClue;
    }

    public Clue getMotiveClue() {
        return motiveClue;
    }
}
