package me.lihq.game.models;

import me.lihq.game.GameMain;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.living.AbstractPerson.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * The map is a collection of Rooms , it links them all together.
 */
public class Map
{

    List<Room> rooms = new ArrayList<Room>();

    public Map()
    {
        initialiseRooms();
    }

    /**
     * This initiates all the rooms and their transitions
     */
    public void initialiseRooms()
    {
        rooms.add(new Room(0, "mainroom.tmx", "Main Foyer")
                .addTransition(new Room.Transition().setFrom(17, 17).setTo(2, 1, 5, Direction.EAST))    //To Porters Office

                .addTransition(new Room.Transition().setFrom(27, 13).setTo(3, 1, 3, Direction.EAST))    //To Kitchen

                .addTransition(new Room.Transition().setFrom(26, 29).setTo(4, 11, 14, Direction.WEST))    //To Island of Interaction

                .addTransition(new Room.Transition().setFrom(18, 2).setTo(5, 1, 1, Direction.EAST))    //To Toilet

                .addTransition(new Room.Transition().setFrom(2, 8).setTo(6, 2, 2, Direction.EAST))      //To Computer Room

                .addTransition(new Room.Transition().setFrom(3, 5).setTo(8, 19, 4, Direction.SOUTH)) //To Outside
                .addTransition(new Room.Transition().setFrom(4, 5).setTo(8, 20, 4, Direction.SOUTH)) //To Outside

                .addTransition(new Room.Transition().setFrom(11, 1).setTo(1, 2, 5, Direction.SOUTH))  //To RCH/037
                .addTransition(new Room.Transition().setFrom(12, 1).setTo(1, 3, 5, Direction.SOUTH)));  //To RCH/037

        rooms.add(new Room(1, "rch037.tmx", "RCH/037 Lecture Theatre")
                .addTransition(new Room.Transition().setFrom(2, 5).setTo(0, 11, 1, Direction.NORTH))  //To Main Room
                .addTransition(new Room.Transition().setFrom(3, 5).setTo(0, 12, 1, Direction.NORTH))  //To Main Room

                .addTransition(new Room.Transition().setFrom(11, 17).setTo(6, 7, 1, Direction.NORTH))    //To Computer Room
                .addTransition(new Room.Transition().setFrom(12, 17).setTo(6, 8, 1, Direction.NORTH)));  //To Computer Room

        rooms.add(new Room(2, "portersoffice.tmx", "Porters Office")
                .addTransition(new Room.Transition().setFrom(1, 5).setTo(0, 17, 17, Direction.WEST)));  //To Main Room

        rooms.add(new Room(3, "kitchen.tmx", "Kitchen")
                .addTransition(new Room.Transition().setFrom(1, 3).setTo(0, 27, 13, Direction.WEST)));  //To Main Room

        rooms.add(new Room(4, "islandofinteraction.tmx", "Island of Interaction")
                .addTransition(new Room.Transition().setFrom(11, 14).setTo(0, 26, 29, Direction.WEST)));  //To Main Room

        rooms.add(new Room(5, "toilet.tmx", "Toilet")
                .addTransition(new Room.Transition().setFrom(1, 1).setTo(0, 18, 2, Direction.WEST)));  //To Main Room

        rooms.add(new Room(6, "computerroom.tmx", "Computer Room")
                .addTransition(new Room.Transition().setFrom(13, 11).setTo(7, 11, 1, Direction.NORTH))    //To Lakehouse

                .addTransition(new Room.Transition().setFrom(7, 1).setTo(1, 11, 17, Direction.SOUTH))    //To RCH/037
                .addTransition(new Room.Transition().setFrom(8, 1).setTo(1, 12, 17, Direction.SOUTH))    //To RCH/037

                .addTransition(new Room.Transition().setFrom(2, 2).setTo(0, 2, 8, Direction.EAST)));  //To Main Room

        rooms.add(new Room(7, "lakehouse.tmx", "Lakehouse")
                .addTransition(new Room.Transition().setFrom(11, 1).setTo(6, 13, 11, Direction.WEST)));  //To Computer Room

        rooms.add(new Room(8, "outside.tmx", "Outside Ron Cooke Hub")
                .addTransition(new Room.Transition().setFrom(19, 4).setTo(0, 3, 5, Direction.NORTH))    //To Main Room
                .addTransition(new Room.Transition().setFrom(20, 4).setTo(0, 4, 5, Direction.NORTH))    //To Main Room

                .addTransition(new Room.Transition().setFrom(9, 11).setTo(9, 18, 9, Direction.WEST))    //To Pod
                .addTransition(new Room.Transition().setFrom(9, 12).setTo(9, 18, 10, Direction.WEST)));  //To Pod

        rooms.add(new Room(9, "pod.tmx", "Pod")
                .addTransition(new Room.Transition().setFrom(18, 9).setTo(8, 9, 11, Direction.EAST))    //To Outside
                .addTransition(new Room.Transition().setFrom(18, 10).setTo(8, 9, 12, Direction.EAST)));  //To Outside
    }

    /**
     * This takes the current room and location and moves the player to the new room
     *
     * param currentRoomID - The current room the player is in
     * @param currentX - The current X coordinate
     * @param currentY - The current Y coordinate
     */
    public void moveRoom(int currentRoomID, int currentX, int currentY)
    {
        Room currentRoom = rooms.get(currentRoomID);

        Room.Transition newRoomData = currentRoom.getNewRoom(currentX, currentY);

        GameMain.me.changeRoom(newRoomData);
    }

    /**
     * This returns a room from the list based on the id.
     *
     * @param id - The ID of the room they request.
     * @return the corresponding room
     */
    public Room getRoom(int id)
    {
        return rooms.get(id);
    }

}
