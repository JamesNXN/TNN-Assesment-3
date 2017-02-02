package me.lihq.game;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.NPC;
import me.lihq.game.people.Player;

/**
 * Initialises player and NPCs and hold them
 */

public class PersonManager {
    private Player player;
    private Array<NPC> npcArray;

    private NPC killer;
    private NPC victim;

    public PersonManager(RoomManager roomManager, Assets assets){
        npcArray = new Array<>();

        //Add ALL NPCs to the list
        //This is how you initialise an NPC
        player = new Player("Player", assets.playerSpriteSheet);
        player.setRoom(roomManager.getRoom(0));
        player.setTilePosition(roomManager.getRoom(0).getRandomLocation().x, roomManager.getRoom(0).getRandomLocation().y);

        //TODO: Sort NPC personalities
        NPC npc = new NPC("Colin", assets.colinSpriteSheet, "Colin.JSON");
        npcArray.add(npc);

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

        int amountOfRooms = roomManager.getRoomArray().size;

        List<Integer> roomsLeft = new ArrayList<>();

        for (int i = 0; i < amountOfRooms; i++) {
            roomsLeft.add(i);
        }

        for (NPC loopNpc : npcArray) {
            /*
            Refill the rooms left list if there are more npcArray than Rooms. This will put AT LEAST one NPC per room if so.
             */
            if (roomsLeft.isEmpty()) {
                for (int i = 0; i < amountOfRooms; i++) {
                    roomsLeft.add(i);
                }
            }

            /*
            Pick a random room and put that NPC in it
             */
            int toTake = new Random().nextInt(roomsLeft.size() - 1);
            int selectedRoom = roomsLeft.get(toTake);
            roomsLeft.remove(toTake);

            loopNpc.setRoom(roomManager.getRoom(selectedRoom));
            Vector2Int position = loopNpc.getRoom().getRandomLocation();
            loopNpc.setTilePosition(position.x, position.y);

            System.out.println(loopNpc.getName() + " has been placed in room " + selectedRoom + " at " + position);
        }
    }

    public Player getPlayer() {
        return player;
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
