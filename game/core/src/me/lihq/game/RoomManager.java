package me.lihq.game;

import com.badlogic.gdx.utils.Array;

import me.lihq.game.models.Room;

/**
 * The map is a collection of Rooms , it links them all together.
 */
public class RoomManager
{
    private Assets assets;
    private Array<Room> roomArray;

    /**
     * Constructs the map
     */
    public RoomManager(Assets assets)
    {
        this.assets = assets;
        initialiseRooms();
    }

    /**
     * This function initialises all the rooms of the Ron Cooke Hub and their transitions
     */
    public void initialiseRooms()
    {

        Room mainRoom = new Room(0, assets.mainRoomMap, "Main Foyer");

        Room rch037 = new Room(1, assets.rch037Map, "RCH/037 Lecture Theatre");

        Room portersOffice = new Room(2, assets.portersOfficeMap, "Porters Office");

        Room kitchen = new Room(3, assets.kitchenMap, "Kitchen");

        Room islandOfInteraction = new Room(4, assets.islandMap, "Island of Interaction");

        Room toilet = new Room(5, assets.toiletMap, "Toilet");

        Room computerRoom = new Room(6, assets.computerRoomMap, "Computer Room");

        Room lakeHouse = new Room(7, assets.lakehouseMap, "Lakehouse");

        Room outside = new Room(8, assets.outsideMap, "Outside Ron Cooke Hub");

        Room pod = new Room(9, assets.podMap, "Pod");


        roomArray = new Array<>();
        roomArray.addAll(mainRoom, rch037, portersOffice, kitchen, islandOfInteraction, toilet, computerRoom, lakeHouse, outside, pod);

        /**
         * Assign the murder room
         */
        roomArray.random().setMurderRoom(true);
    }

    /**
     * This returns a room from the list based on the id.
     *
     * @param id - The ID of the room they request.
     * @return (Room) the corresponding room
     */
    public Room getRoom(int id)
    {
        for (Room room : roomArray) {
            if (room.getID() == id) return room;
        }

        return null;
    }


    /**
     * Gets the rooms in the map
     *
     * @return (Array<Room>) List of rooms that the map initialised
     */
    public Array<Room> getRoomArray()
    {
        return roomArray;
    }

}
