package me.lihq.game.models;

/**
 * Created by vishal on 20/11/2016.
 */

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Room
{
    private String name;
    private int ID;
    private String mapFile;

    private List<Clue> cluesInRoom = new ArrayList<Clue>();

    private boolean murderRoom = false;

    private TiledMap map;

    //Room transitions stored as (currentX, currentY) points to a (newRoomID, newX, newY)
    private HashMap<Vector2, List<Integer>> roomTransitions = new HashMap<Vector2, List<Integer>>();

    public Room(int id, String mapFile, String name)
    {
        this.ID = id;
        this.mapFile = mapFile;
        this.name = name;
        this.map = new TmxMapLoader().load(mapFile);
    }

    //TODO: Popup notification on room entrance

    //Returns True if it's the room the murder took place in
    public boolean isMurderRoom()
    {
        return murderRoom;
    }

    public int getID()
    {
        return this.ID;
    }

    /**
     * Changes coordinates of clue
     */
    public void moveClue(Clue clue, int x, int y)
    {
        if (cluesInRoom.contains(clue)) {
            clue.setCoords(x, y);
        }
    }

    public void addClue(Clue newClue)
    {
        if (!cluesInRoom.contains(newClue)) {
            cluesInRoom.add(newClue);
        }
    }

    public void removeClue(Clue toRemove)
    {
        if (cluesInRoom.contains(toRemove)) {
            cluesInRoom.remove(toRemove);
        }
    }

    public boolean isWalkableTile(int x, int y)
    {
        //Check the map tile at x, y

        return false;
    }

    public boolean isTriggerTile(int x, int y)
    {
        return false;
    }

    public boolean getTrigger(int x, int y)
    {
        if (isTriggerTile(x, y)) {
            //Get trigger type property
        }

        return false;
    }

    public Room setTransition(int x, int y, int newRoom, int newX, int newY)
    {
        roomTransitions.put(new Vector2(x,y), Arrays.asList(newRoom, newX, newY));
        return this;
    }

    /**
     * This method will take the current x and y coordinate and attempt to move to another room
     *
     * @param x - The current x coordinate in the room
     * @param y - The current y coordinate in the room
     * @return - a List with length 3.
     *             0 - New Room ID
     *             1 - New X
     *             2 - New Y
     */
    public List<Integer> getNewRoom(int x, int y)
    {
        return roomTransitions.get(new Vector2(x,y));
    }
}


