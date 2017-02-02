package me.lihq.game.models;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.lihq.game.people.NPC;

/**
 * This class defines a room which is a game representation of a real world room in the Ron Cooke Hub.
 */
public class Room
{
    /**
     * This list stores the coordinates of all hideable slots in this room
     * <p>
     * Hideable slots are tiles that the clues can be hidden in
     */
    public Array<Vector2Int> hidingSpots;

    private String name;

    private int ID;
    /**
     * The string that points to the tmx map file for this room.
     */
    private TiledMap mapFile;
    /**
     * This is a list of the clues in the room.
     */
    private Array<Clue> clueArray = new Array<>();
    private Array<NPC> npcArray = new Array<>();

    private boolean isMurderRoom = false;

    /**
     * Constructor that builds a Room object from the given parameters
     *
     * @param id      - The integer ID of the room
     * @param map - Tmx map file.
     * @param name    - The name of the room
     */
    public Room(int id, TiledMap map, String name)
    {
        this.ID = id;
        this.mapFile = map;
        this.name = name;

        mapFile.getLayers().get("Collision").setVisible(false);
        mapFile.getLayers().get("HidingSpot").setVisible(false);

        hidingSpots = new Array<>();
        getHidingSpots();
    }

    /**
     * This method checks equality between the Room and another object
     *
     * @param obj - The object to be checked against
     * @return (boolean) Returns true if the obj is of type Room and has the exact same ID. {@link #ID}
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Room) {
            Room r = (Room) obj;
            return r.getID() == this.getID();
        }

        return false;
    }

    public boolean isMurderRoom()
    {
        return isMurderRoom;
    }


    public void setMurderRoom(boolean murderRoom)
    {
        this.isMurderRoom = murderRoom;
        System.out.println("Room " + getID() + " is the murder room");
    }

    public int getID()
    {
        return this.ID;
    }

    public String getName()
    {
        return this.name;
    }

    public void addClue(Clue newClue)
    {
        System.out.println("Added Clue " + newClue.getName() + " at location " + newClue.getPosition() + " in room " + getID());

        if (!clueArray.contains(newClue, false)) {
            clueArray.add(newClue);
        }
    }

    /**
     * This method takes a location parameter and checks it for a clue, if a clue is found it is removed from the map and return
     *
     * @param x - The x coordinate the player is at
     * @param y - The y coordinate the player is at
     * @return (Clue) returns null if there is no clue at coordinate x,y and returns the clue itself otherwise
     */
    public Clue getClue(int x, int y)
    {
        //Apply direction change
        Clue out = null;
        //Check for a clue at that coordinate
        for (Clue c : clueArray) {
            if (c.getPosition().x == x && c.getPosition().y == y) {
                out = c;
            }
        }
        if (out != null) {
            this.clueArray.removeValue(out, false);
        }

        return out;
    }


    /**
     * This method checks whether the tile at x, y is a tile you can hide a clue
     * in
     *
     * @param x - The x coordinate to check
     * @param y - The y coordinate to check
     * @return (boolean) whether the tile is a hideable tile.
     */
    public boolean isHidingPlace(int x, int y)
    {
        return hidingSpots.contains(new Vector2Int(x, y), false);
    }


    /**
     * This method checks ALL layers for the tile at x, y to see if it is a trigger tile.
     * If any of them are true, it returns true
     *
     * @param x - The x coordinate to check
     * @param y - The y coordinate to check
     * @return - (boolean) whether or not the tile is a trigger tile.
     */
    public boolean isTriggerTile(int x, int y)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) mapFile.getLayers().get(0);

        if (layer.getCell(x, y) == null) return false;

        int amountOfLayers = mapFile.getLayers().getCount();

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tl = (TiledMapTileLayer) mapFile.getLayers().get(currentLayer);

            if (tl.getCell(x, y) == null) {
                continue;
            }

            if (!tl.getCell(x, y).getTile().getProperties().containsKey("trigger")) {
                continue;
            }

            if (tl.getCell(x, y).getTile().getProperties().get("trigger").toString().equals("true")) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method gets the rotation that the mat is that they are standing on.
     * If they aren't on a mat, it returns null
     *
     * @param x - The x coordinate to check
     * @param y = The y coordinate to  check
     * @return a String representing the direction they are facing
     */
    public String getEntranceRotation(int x, int y)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) mapFile.getLayers().get("Doors");

        if (layer.getCell(x, y) == null) return null;

        return (String) layer.getCell(x, y).getTile().getProperties().get("dir");
    }

    public TiledMap getTiledMap()
    {
        return this.mapFile;
    }

    /**
     * This will check the map for any potential hiding locations, and add them as a list of coordinates
     *
     */
    public void getHidingSpots()
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) mapFile.getLayers().get("HidingSpot");
        int roomTileWidth = layer.getWidth();
        int roomTileHeight = layer.getHeight();

        for (int x = 0; x < roomTileWidth; x++) {
            for (int y = 0; y < roomTileHeight; y++) {
                TiledMapTileLayer.Cell cellInTile = layer.getCell(x, y);

                if (cellInTile == null) continue;

                if (!cellInTile.getTile().getProperties().containsKey("hidingSpot")) continue;

                if (cellInTile.getTile().getProperties().get("hidingSpot").toString().equals("true")) {
                    hidingSpots.add(new Vector2Int(x, y));
                    break;
                }
            }
        }
    }


    /**
     * This gets a random possible location to hide a clue in
     *
     * @return (Vector2Int) Coordinates of the tile where the clue is to be hidden, null if there are none available
     */
    public Vector2Int getRandHidingSpot()
    {
        if (hidingSpots.size!= 0) {
            return hidingSpots.random();
        } else {
            return null;
        }
    }

    /**
     * This method returns a random location in the room that is walkable
     *
     * @return (Vector2Int) the random walkable tile generated.
     */
    public Vector2Int getRandomLocation()
    {
        int roomWidth = ((TiledMapTileLayer) getTiledMap().getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) getTiledMap().getLayers().get(0)).getHeight();

        Array<Vector2Int> possibleLocations = new Array<>();

        for (int x = 0; x < roomWidth; x++) {
            for (int y = 0; y < roomHeight; y++) {
                if (isWalkableTile(x, y)) {
                    possibleLocations.add(new Vector2Int(x, y));
                }
            }
        }

        return possibleLocations.random();
    }

    public boolean isWalkableTile(int x, int y) {
        TiledMapTileLayer layer = (TiledMapTileLayer) mapFile.getLayers().get("Collision");
        if (layer.getCell(x, y) == null){
            return true;
        }
        else{
            return false;
        }
    }
}


