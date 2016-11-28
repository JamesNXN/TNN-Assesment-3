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
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2))
                    .addTransition(new Room.Transition().setFrom(1, 1).setTo(2, 0, 0)));

        rooms.add(new Room(1, "map.tmx", "RCH/037 Lecture Theatre")
                    .addTransition(new Room.Transition().setFrom(10, 10).setTo(2, 0, 0)));
    }

    public Room.Transition moveRoom(int currentRoomID, int currentX, int currentY)
    {
        Room currentRoom = rooms.get(currentRoomID);

        Room.Transition newRoomData = currentRoom.getNewRoom(currentX, currentY);

        int newRoomID = newRoomData.newRoom;
        int newX = newRoomData.to.x;
        int newY = newRoomData.to.y;

        //Change current information and do map change transition
        return newRoomData;
    }

    public Room getRoom(int id)
    {
        return rooms.get(id);
    }

}
