package me.lihq.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.models.Room;

/**
 * NEW
 * Manager class for rooms. it initialises all the in game rooms with TiledMap files
 */
public class RoomManager
{
    private Array<Room> roomArray;
    private Room murderRoom;

    /**
     * Constructs the map
     */
    public RoomManager(AssetLoader assetLoader)
    {
        roomArray = new Array<>();
        initialiseRooms(assetLoader);
    }

    /**
     * This function initialises all the rooms of the Ron Cooke Hub and their transitions
     */
    private void initialiseRooms(AssetLoader assetLoader)
    {
        for (TiledMap map : assetLoader.mapArray){
            roomArray.add(new Room(map, assetLoader.arrowAtlas));
        }

        /**
         * Assign the murder room
         */
        murderRoom = roomArray.random();
        murderRoom.setMurderRoom(true);
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

    public Room getMurderRoom() {
        return murderRoom;
    }
}
