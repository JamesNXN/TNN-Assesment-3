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
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2))    //To Porters Office
                    .addTransition(new Room.Transition().setFrom(1, 1).setTo(2, 0, 0))    //To Kitchen
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2))    //To Island of Interaction
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2))    //To Toilet
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2))    //To Outside
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2)));  //To RCH/037

        rooms.add(new Room(1, "map.tmx", "RCH/037 Lecture Theatre")
                    .addTransition(new Room.Transition().setFrom(10, 10).setTo(2, 0, 0))  //To Main Foyer
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2)));  //To Computer Room

        rooms.add(new Room(2, "map.tmx", "Porters Office")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(1, 0, 0)));  //To Main Foyer

        rooms.add(new Room(3, "map.tmx", "Kitchen")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(0, 1, 1)));  //To Main Foyer

        rooms.add(new Room(4, "map.tmx", "Island of Interaction")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(0, 0, 0)));  //To Main Foyer

        rooms.add(new Room(5, "map.tmx", "Toilet")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(0, 0, 0)));  //To Main Foyer

        rooms.add(new Room(6, "map.tmx", "Computer Room")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(0, 0, 0))    //To Lakehouse
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2))    //To RCH/037
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2)));  //To Main Foyer

        rooms.add(new Room(7, "map.tmx", "Lakehouse")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(0, 0, 0)));  //To Computer Room

        rooms.add(new Room(8, "map.tmx", "Outside Ron Cooke Hub")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(0, 0, 0))    //To Pod
                    .addTransition(new Room.Transition().setFrom(5, 5).setTo(1, 2, 2)));  //To Main Foyer

        rooms.add(new Room(9, "map.tmx", "Pod")
                    .addTransition(new Room.Transition().setFrom(0, 0).setTo(0, 0, 0)));  //To Outside
    }

    public Room.Transition moveRoom(int currentRoomID, int currentX, int currentY)
    {
        Room currentRoom = rooms.get(currentRoomID);

        Room.Transition newRoomData = currentRoom.getNewRoom(currentX, currentY);

        //TODO: Change current information and do map change transition
        return newRoomData;
    }

    public Room getRoom(int id)
    {
        return rooms.get(id);
    }

}
