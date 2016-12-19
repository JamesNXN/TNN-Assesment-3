package me.lihq.game.models;

import me.lihq.game.GameMain;

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

    public void initialiseRooms()
    {
        rooms.add(new Room(0, "mainroom.tmx", "Main Foyer")
                .addTransition(new Room.Transition().setFrom(17, 17).setTo(2, 1, 5))    //To Porters Office

                .addTransition(new Room.Transition().setFrom(27, 13).setTo(3, 1, 3))    //To Kitchen

                .addTransition(new Room.Transition().setFrom(26, 29).setTo(4, 11, 14))    //To Island of Interaction

                .addTransition(new Room.Transition().setFrom(18, 2).setTo(5, 1, 1))    //To Toilet

                .addTransition(new Room.Transition().setFrom(2, 8).setTo(6, 2, 2))      //To Computer Room

                .addTransition(new Room.Transition().setFrom(3, 5).setTo(8, 19, 4)) //To Outside
                .addTransition(new Room.Transition().setFrom(4, 5).setTo(8, 20, 4)) //To Outside

                .addTransition(new Room.Transition().setFrom(11, 1).setTo(1, 2, 5))  //To RCH/037
                .addTransition(new Room.Transition().setFrom(12, 1).setTo(1, 3, 5)));  //To RCH/037

        rooms.add(new Room(1, "rch037.tmx", "RCH/037 Lecture Theatre")
                .addTransition(new Room.Transition().setFrom(2, 5).setTo(0, 11, 1))  //To Main Room
                .addTransition(new Room.Transition().setFrom(3, 5).setTo(0, 12, 1))  //To Main Room

                .addTransition(new Room.Transition().setFrom(11, 17).setTo(6, 7, 1))    //To Computer Room
                .addTransition(new Room.Transition().setFrom(12, 17).setTo(6, 8, 1)));  //To Computer Room

        rooms.add(new Room(2, "porters.tmx", "Porters Office")
                .addTransition(new Room.Transition().setFrom(1, 5).setTo(0, 17, 17)));  //To Main Room

        rooms.add(new Room(3, "kitchen.tmx", "Kitchen")
                .addTransition(new Room.Transition().setFrom(1, 3).setTo(0, 27, 13)));  //To Main Room

        rooms.add(new Room(4, "islandofinteraction.tmx", "Island of Interaction")
                .addTransition(new Room.Transition().setFrom(11, 14).setTo(4, 26, 29)));  //To Main Room

        rooms.add(new Room(5, "toilet.tmx", "Toilet")
                .addTransition(new Room.Transition().setFrom(1, 1).setTo(0, 18, 2)));  //To Main Room

        rooms.add(new Room(6, "computerroom.tmx", "Computer Room")
                .addTransition(new Room.Transition().setFrom(13, 11).setTo(7, 11, 1))    //To Lakehouse

                .addTransition(new Room.Transition().setFrom(7, 1).setTo(1, 11, 17))    //To RCH/037
                .addTransition(new Room.Transition().setFrom(8, 1).setTo(1, 12, 17))    //To RCH/037

                .addTransition(new Room.Transition().setFrom(2, 2).setTo(0, 2, 8)));  //To Main Room

        rooms.add(new Room(7, "lakehouse.tmx", "Lakehouse")
                .addTransition(new Room.Transition().setFrom(11, 1).setTo(6, 13, 11)));  //To Computer Room

        rooms.add(new Room(8, "outside.tmx", "Outside Ron Cooke Hub")
                .addTransition(new Room.Transition().setFrom(19, 4).setTo(0, 3, 5))    //To Main Room
                .addTransition(new Room.Transition().setFrom(20, 4).setTo(0, 4, 5))    //To Main Room

                .addTransition(new Room.Transition().setFrom(9, 11).setTo(9, 18, 9))    //To Pod
                .addTransition(new Room.Transition().setFrom(9, 12).setTo(9, 18, 10)));  //To Pod

        rooms.add(new Room(9, "pod.tmx", "Pod")
                .addTransition(new Room.Transition().setFrom(18, 9).setTo(8, 9, 11))    //To Outside
                .addTransition(new Room.Transition().setFrom(18, 10).setTo(8, 9, 12)));  //To Outside
    }

    public void moveRoom(int currentRoomID, int currentX, int currentY)
    {
        Room currentRoom = rooms.get(currentRoomID);

        Room.Transition newRoomData = currentRoom.getNewRoom(currentX, currentY);

        //TODO: Change current information and do map change transition
        GameMain.me.changeRoom(newRoomData);
    }

    public Room getRoom(int id)
    {
        return rooms.get(id);
    }

}
