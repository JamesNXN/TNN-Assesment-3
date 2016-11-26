package me.lihq.game.models;

import me.lihq.game.GameMain;

import java.util.ArrayList;
import java.util.List;

/**
 * The map is a collection of Rooms that links them all together.
 *
 */
public class Map {

    List<Room> rooms = new ArrayList<Room>();

    public Map()
    {
        initialiseRooms();
    }

    public void initialiseRooms()
    {
        rooms.add(new Room(0, "map.tmx", "Main Foyer")
                    .setTransition(5,5,2,1,1)
                    .setTransition(12,12,3,1,1));

        rooms.add(new Room(1, "map.tmx", "RCH/037 Lecture Theatre")
                    .setTransition(2,2,4,4,4));
    }

    public List<Integer> moveRoom(int currentRoomID, int currentX, int currentY)
    {
        Room currentRoom = rooms.get(currentRoomID);

        List<Integer> newRoomData = currentRoom.getNewRoom(currentX, currentY);

        int newRoomID = newRoomData.get(0);
        int newX = newRoomData.get(1);
        int newY = newRoomData.get(2);

        //Change current information and do map change transition
        return newRoomData;
    }

    public Room getRoom(int id)
    {
        return rooms.get(id);
    }

}
