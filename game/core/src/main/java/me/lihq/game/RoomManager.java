package me.lihq.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.models.Room;

/**
 * The map is a collection of Rooms , it links them all together.
 */
public class RoomManager
{
    private Array<Room> roomArray;

    /**
     * Constructs the map
     */
    public RoomManager(Assets assets)
    {
        roomArray = new Array<>();
        initialiseRooms(assets);
    }

    /**
     * This function initialises all the rooms of the Ron Cooke Hub and their transitions
     */
    private void initialiseRooms(Assets assets)
    {
        for (TiledMap map : assets.mapArray){
            roomArray.add(new Room(map, assets.arrowAtlas));
        }

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
