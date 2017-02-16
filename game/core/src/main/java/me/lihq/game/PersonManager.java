package me.lihq.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.NPC;

/**
 * Initialises player and NPCs and hold them
 */

public class PersonManager {
    private Array<NPC> npcArray;

    private NPC killer;
    private NPC victim;

    public PersonManager(RoomManager roomManager, AssetLoader assetLoader){
        Json json = new Json();
        npcArray = new Array<>();

        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, assetLoader.npcJsonData);
        for (JsonValue data : npcJsonDataArray){
            npcArray.add(new NPC(data, assetLoader.npcSpriteSheetMapArray.get(data.getInt("id"))));
        }

        //Generate who the Killer and Victim are
        npcArray.shuffle();
        victim = npcArray.pop();
        victim.setVictim(true);
        killer = npcArray.random();
        killer.setKiller(true);

        int roomIndex = 0;
        for (NPC npc : npcArray) {
            roomIndex %= roomManager.getRoomArray().size-1;

            Room selectedRoom = roomManager.getRoomArray().get(roomIndex);
            npc.setCurrentRoom(selectedRoom);
            selectedRoom.addNPC(npc);
            Vector2Int position = npc.getCurrentRoom().getRandomLocation();
            npc.setTilePosition(position.x, position.y);

            System.out.println(npc.getName() + " has been placed in room " + selectedRoom + " at " + position);

            roomIndex++;
        }
    }
    public Array<NPC> getNpcArray() {
        return npcArray;
    }

    public NPC getKiller() {
        return killer;
    }

    public NPC getVictim() {
        return victim;
    }
}
