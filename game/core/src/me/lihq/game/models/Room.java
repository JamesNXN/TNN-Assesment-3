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
    /**
     * This stores the name of the room.
     *
     * It is displayed on the tag when they enter the room
     */
    private String name;

    /**
     * The integer ID of the room
     */
    private int ID;

    /**
     * The string that points to the tmx map file for this room.
     */
    private String mapFile;

    /**
     * This is a list of the clues in the room.
     */
    private List<Clue> cluesInRoom = new ArrayList<Clue>();

    /**
     * This stores whether or not the room is the room where the murder happened
     */
    private boolean murderRoom = false;

    /**
     * This stores the TMX map loaded from the String mapFile
     */
    private TiledMap map;

    /**
     * Room transitions stored as custom Transition object. Defines where the transition is from and where it goes to
     */
    private List<Transition> roomTransitions = new ArrayList<Transition>();

    /**
     * Constructor that builds a Room object from the given parameters
     * @param id - The integer ID of the room
     * @param mapFile - The String that points to the tmx map file.
     * @param name - The name of the room
     */
    public Room(int id, String mapFile, String name)
    {
        this.ID = id;
        this.mapFile = mapFile;
        this.name = name;

        this.map = new TmxMapLoader().load("maps/" + this.mapFile);
    }

    /**
     * Returns True if it's the room the murder took place in
     */
    public boolean isMurderRoom()
    {
        return murderRoom;
    }

    /**
     * Returns the integer ID of the room
     */
    public int getID()
    {
        return this.ID;
    }

    /**
     * This returns the String name of the room
     */
    public String getName() {return this.name;}

    /**
     * This moves a clue from its current position to a new defined position
     *
     * @param clue - The clue to change the position of
     * @param x - The X coordinate to move it to
     * @param y - The Y coordinate to move it to
     */
     public void moveClue(Clue clue, int x, int y)
     {
        if (cluesInRoom.contains(clue)) {
            clue.setCoords(x, y);
        }
    }

    /**
     * Adds a clue to the room.
     *
     * @param newClue - The clue to add to the room
     */
    public void addClue(Clue newClue)
    {
        if (!cluesInRoom.contains(newClue)) {
            cluesInRoom.add(newClue);
        }
    }

    /**
     * This removes a clue from the room
     *
     * @param toRemove - The clue to remove
     */
    public void removeClue(Clue toRemove)
    {
        if (cluesInRoom.contains(toRemove)) {
            cluesInRoom.remove(toRemove);
        }
    }

    /**
     * This method takes a current X and Y coordinate and checks through all the layers on the map to see if any tile IS NOT
     * moveable. If any tile IS NOT moveable, it returns false.
     *
     * @param x - The x coordinate to check
     * @param y - The y coordinate to check
     * @return - Boolean as to whether or not that tile can be walked on.
     */
    public boolean isWalkableTile(int x, int y)
    {
        //reduced by one because the last layer is to be displayed over the top of the player and therefore is ignored.
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

    /**
     * This method checks ALL layers for the tile at x, y to see if it is a trigger tile.
     * If any of them are true, it returns true
     *
     * @param x - The x coordinate to check
     * @param y - The y coordinate to check
     * @return - Boolean as to whether or not the tile is a trigger tile.
     */
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

    /**
     * This method gets the rotation that the map is that they are standing on.
     *
     * If they aren't on a mat, it returns null
     *
     * @param x - The x coordinate to check
     * @param y = The y coordinate to check
     * @return a String representing the direction they are facing
     */
    public String getMatRotation(int x, int y)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Doors");

        if (layer.getCell(x, y) == null) return null;

        return (String) layer.getCell(x, y).getTile().getProperties().get("dir");
    }

    /**
     * This returns the tiledMap for this room
     *
     * @return - TiledMap the map relating to this room.
     */
    public TiledMap getTiledMap()
    {
        return this.map;
    }

    /**
     * This adds a transition to the map.
     *
     * @param t - The transition to be added
     * @return - Room; itself
     */
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
     * @return - a Transition data type. Which stores the relevant information
     * 0 - New Room ID
     * 1 - New X
     * 2 - New Y
     */
    public Transition getNewRoom(int x, int y)
    {
        return hasTransition(new Vector2Int(x, y));
    }

    /**
     * This method checks through all the transitions of this room and if one exists where the FROM property is equal to the parameter 'v' then
     * it returns that Transition, else, it returns null
     *
     * @param v - The vector containing the FROM coordinates
     * @return - nullable Transition - the transition if it exists, else, null
     */
    private Transition hasTransition(Vector2Int v)
    {
        for (Transition l : roomTransitions) {
            if (l.from.equals(v)) {
                return (l);
            }
        }

        return null;
    }

    /**
     * This object stores 2 sets of coordinates, a room ID, and a direction
     *
     * newRoom - the new Room ID to be moved to
     * to - The new X, Y coordinates to enter the new room
     * from - The X, Y coordinates they are moving from THIS room
     * direction - The direction to face in the new room
     */
    public static class Transition
    {
        public Vector2Int from = new Vector2Int(0, 0);

        public int newRoom = 0;

        public Direction newDirection = null;

        public Vector2Int to = new Vector2Int(0, 0);

        public Transition()
        {
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


