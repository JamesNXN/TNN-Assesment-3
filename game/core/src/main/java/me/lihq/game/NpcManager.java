package me.lihq.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.Npc;

/**
 * Initialises NPCs and assigns them to rooms
 */

public class NpcManager {
    // used for situations when passing as a parameter isn't optimal
    public static NpcManager instance;

    /**
     * Parameters needed for NpcManager:
     *
     * npcArray - the array of created npcs
     * murderer - the npc randomly selected to be the murderer
     * victim - the npc randomly selected to be the victim
     */
    private Array<Npc> npcArray;

    private Npc murderer;
    private Npc victim;

    public NpcManager(RoomManager roomManager, AssetLoader assetLoader){
        Json json = new Json();
        npcArray = new Array<>();

        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, assetLoader.npcJsonData);
        for (JsonValue data : npcJsonDataArray){
            npcArray.add(new Npc(data, assetLoader.npcSpriteSheetMapArray.get(data.getInt("id"))));
        }

        //Generate who the Killer and Victim are
        npcArray.shuffle();
        victim = npcArray.pop();
        victim.setVictim(true);
        murderer = npcArray.random();
        murderer.setMurderer(true);

        int roomIndex = 0;
        for (Npc npc : npcArray) {
            roomIndex %= roomManager.getRoomArray().size-1;

            Room selectedRoom = roomManager.getRoomArray().get(roomIndex);
            npc.setCurrentRoom(selectedRoom);
            selectedRoom.addNPC(npc);
            Vector2Int position = npc.getCurrentRoom().getRandomLocation();
            npc.setTilePosition(position.x, position.y);

            System.out.println(npc.getName() + " has been placed in room " + selectedRoom + " at " + position);

            roomIndex++;
        }

        npcArray.add(victim);

        instance = this;
    }

    public Npc getNpc(int id){
        for (Npc npc : npcArray){
            if (npc.getId() == id){
                return npc;
            }
        }
        return null;
    }

    /**
     * Getters for NpcManager variables
     */
    public Array<Npc> getNpcArray() {
        return npcArray;
    }

    public Npc getMurderer() {
        return murderer;
    }

    public Npc getVictim() {
        return victim;
    }
}
