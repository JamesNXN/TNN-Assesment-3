package me.lihq.game.models;

/**
 * This class defines a room which is a game representation of a real world room in the Ron Cooke Hub.
 */

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.living.AbstractPerson.Direction;

import java.util.*;

public class Room
{
    private String name;
    private int ID;
    private String mapFile;

    private List<Clue> cluesInRoom = new ArrayList<Clue>();

    private boolean murderRoom = false;

    private TiledMap map;

    //Room transitions stored as (currentX, currentY) points to a (newRoomID, newX, newY)
    private List<Transition> roomTransitions = new ArrayList<Transition>();

    public Room(int id, String mapFile, String name)
    {
        this.ID = id;
        this.mapFile = mapFile;
        this.name = name;

        this.map = new TmxMapLoader().load("maps/" + this.mapFile);
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
        int amountOfLayers = map.getLayers().getCount() - 1;
        int emptyCellCount = 0; //The amount of empty cells on the map in the location x and y.


        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tl = (TiledMapTileLayer) map.getLayers().get(currentLayer);

            if (tl.getCell(x, y) == null)
            {
                emptyCellCount++; //for every empty cell increase the emptyCellCount by 1
                continue;
            }

            if (!tl.getCell(x, y).getTile().getProperties().containsKey("walkable")) {
                continue;
            }

            if (Boolean.valueOf(tl.getCell(x, y).getTile().getProperties().get("walkable").toString().equals("false"))) {
                return false;
            }
        }

        /*
        Check to see if the number of empty layer cells matches the number of layers,
        if it does the this must be an empty area of the map that is not walkable
         */
        if (emptyCellCount == amountOfLayers) {
            return false;
        }

        return true;
    }

    public boolean isTriggerTile(int x, int y)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);

        if (layer.getCell(x, y) == null) return false;

        int amountOfLayers = map.getLayers().getCount();

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tl = (TiledMapTileLayer) map.getLayers().get(currentLayer);

            if (tl.getCell(x, y) == null)
            {
                continue;
            }

            if (!tl.getCell(x, y).getTile().getProperties().containsKey("trigger")) {
                continue;
            }

            if (Boolean.valueOf(tl.getCell(x, y).getTile().getProperties().get("trigger").toString().equals("true"))) {
                return true;
            }
        }

        return false;
    }

    public String getMatRotation(int x, int y)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Doors");

        return (String) layer.getCell(x, y).getTile().getProperties().get("dir");
    }

    public TiledMap getTiledMap()
    {
        return this.map;
    }

    public Room addTransition(Transition t)
    {
        roomTransitions.add(t);
        return this;
    }

    /**
     * This method will take the current x and y coordinate and attempt to move to another room
     *
     * @param x - The current x coordinate in the room
     * @param y - The current y coordinate in the room
     * @return - a List with length 3.
     * 0 - New Room ID
     * 1 - New X
     * 2 - New Y
     */
    public Transition getNewRoom(int x, int y)
    {
        return hasTransition(new Vector2Int(x, y));
    }

    private Transition hasTransition(Vector2Int v)
    {
        for (Transition l : roomTransitions) {
            if (l.from.equals(v)) {
                return (l);
            }
        }

        return null;
    }

    public static class Transition
    {
        public Vector2Int from = new Vector2Int(0, 0);

        public int newRoom = 0;

        public Direction newDirection = null;

        public Vector2Int to = new Vector2Int(0, 0);

        public Transition()
        {
        }

        public Transition setTo(int room, int x, int y)
        {
            this.newRoom = room;
            this.to = new Vector2Int(x, y);
            return this;
        }

        public Transition setTo(int room, int x, int y, Direction dir)
        {
            this.newRoom = room;
            this.to = new Vector2Int(x, y);
            this.newDirection = dir;
            return this;
        }

        public Transition setFrom(int x, int y)
        {
            this.from = new Vector2Int(x, y);
            return this;
        }
    }
}


