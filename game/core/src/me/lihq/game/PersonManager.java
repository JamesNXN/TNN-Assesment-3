package me.lihq.game;

import com.badlogic.gdx.utils.Array;

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

    public PersonManager(RoomManager roomManager, Assets assets){
        npcArray = new Array<>();

        //Add ALL NPCs to the list
        //This is how you initialise an NPC

        //TODO: Sort NPC personalities
        NPC npc1 = new NPC("Colin", assets.colinSpriteSheet, "Colin.JSON");
        npcArray.add(npc1);

        NPC npc2 = new NPC("Diana", assets.dianaSpriteSheet, "Diana.JSON");
        npcArray.add(npc2);

        NPC npc3 = new NPC("Lily", assets.lilySpriteSheet, "Lily.JSON");
        npcArray.add(npc3);

        NPC npc4 = new NPC("Mary", assets.marySpriteSheet, "Mary.JSON");
        npcArray.add(npc4);

        NPC npc5 = new NPC("Mike", assets.mikeSpriteSheet, "Mike.JSON");
        npcArray.add(npc5);

        NPC npc6 = new NPC("Will", assets.willSpriteSheet, "Will.JSON");
        npcArray.add(npc6);


        //Generate who the Killer and Victim are
        npcArray.shuffle();
        victim = npcArray.pop();
        killer = npcArray.random();

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
