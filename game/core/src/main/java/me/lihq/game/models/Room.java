package me.lihq.game.models;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.people.Npc;

/**
 * This class defines a room which is a game representation of a real world room in the Ron Cooke Hub.
 */
public class Room
{

    /**
     * Parameters needed by Room Object:
     *
     * hidingSpots - an array that stores coordinates of spots that clues could be hidden in
     * name - a unique name for the room used for displaying the name of the room in game
     * id - a unique id used for identifying the room
     * mapFile - the tmx map file used to create the room
     * clueArray - an array of clues that are in the room
     * npcArray - an array of npcs that are in the room
     * exitArray - an array of door objects that allow the player to leave the room and enter another
     * entryArray - an array of door objects that allow the player to enter the room from another room
     * roomArrowArray - an array of arrow objects that are rendered when the player gets close to a door
     * isMurderRoom - a boolean value defining whether or not the murder occurred in the room
     */

    private Array<Vector2Int> hidingSpots;
    private String name;
    private int ID;
    private TiledMap mapFile;
    private Array<Clue> clueArray;
    private Array<Npc> npcArray;
    private Array<Door> exitArray;
    private Array<Door> entryArray;
    private Array<RoomArrow> roomArrowArray;
    private boolean isMurderRoom = false;

    /**
     * Constructor used for creating the room object
     * @param map - map file
     * @param roomArrowAtlas - texture file
     */
    public Room(TiledMap map, TextureAtlas roomArrowAtlas) {
        this.ID = (int) map.getProperties().get("id");
        this.mapFile = map;
        this.name = (String) map.getProperties().get("name");

        mapFile.getLayers().get("Collision").setVisible(false);
        mapFile.getLayers().get("HidingSpot").setVisible(false);

        clueArray = new Array<>();
        npcArray = new Array<>();
        exitArray = new Array<>();
        entryArray = new Array<>();
        roomArrowArray = new Array<>();

        exitArray.addAll(getExit());
        entryArray.addAll(getEntry());
        roomArrowArray.addAll(getRoomArrow(exitArray, roomArrowAtlas));

        hidingSpots = new Array<>();
        hidingSpots.addAll(getHidingSpots());
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

    /**
     * This method checks if the tile the player or NPC would walk onto
     * would cause a collision or not
     * @param x - x coordinate of tile
     * @param y - y coordinate of tile
     * @return (boolean) Returns true if no collision would occur
     */
    public boolean isWalkableTile(int x, int y) {
        TiledMapTileLayer layer = (TiledMapTileLayer) mapFile.getLayers().get("Collision");
        if (layer.getCell(x, y) == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method adds a clue into the room if it does not already contain the same clue
     * @param newClue - clue to be added to the room
     */
    public void addClue(Clue newClue)
    {
        System.out.println("Added Clue " + newClue.getName() + " at location " + newClue.getTilePosition() + " in room " + getID());

        if (!clueArray.contains(newClue, false)) {
            clueArray.add(newClue);
        }
    }

    /**
     * This method adds an npc into the room
     * @param npc - npc to be added
     */
    public void addNPC(Npc npc){
        npcArray.add(npc);
    }

    /**
     * Getters and setters needed for use by other classes
     */

    public Array<Npc> getNpcArray(){
        return npcArray;
    }

    public Array<Clue> getClueArray(){
        return clueArray;
    }

    public Array<Door> getExitArray(){
        return exitArray;
    }

    public Array<Door> getEntryArray() {
        return entryArray;
    }

    public Array<RoomArrow> getRoomArrowArray(){ return roomArrowArray; }

    private Array<Door> getExit()
    {
        MapLayer layer = mapFile.getLayers().get("Transition");
        Array<Door> exitArray = new Array<>();

        for (MapObject object : layer.getObjects()) {
            if (object.getProperties().get("type").equals("Exit")) {
                exitArray.add(new Door((RectangleMapObject) object));
            }
        }
        return exitArray;
    }

    private Array<Door> getEntry(){
        MapLayer layer = mapFile.getLayers().get("Transition");
        Array<Door> entryArray = new Array<>();

        for (MapObject object : layer.getObjects()) {
            if (object.getProperties().get("type").equals("Entry")) {
                entryArray.add(new Door((RectangleMapObject) object));
            }
        }
        return entryArray;
    }

    private Array<RoomArrow> getRoomArrow(Array<Door> exitArray, TextureAtlas arrowAtlas){
        Array<RoomArrow> arrowArray = new Array<>();

        for (Door exit : exitArray){
            arrowArray.add(new RoomArrow(exit, arrowAtlas));
        }
        return arrowArray;
    }
    public TiledMap getTiledMap()
    {
        return this.mapFile;
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


    public Vector2Int getRandHidingSpot()
    {
        if (hidingSpots.size!= 0) {
            return hidingSpots.random();
        } else {
            return null;
        }
    }

    public Vector2Int getRandomLocation()
    {
        int roomWidth = ((TiledMapTileLayer) getTiledMap().getLayers().get("Collision")).getWidth();
        int roomHeight = ((TiledMapTileLayer) getTiledMap().getLayers().get("Collision")).getHeight();

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
    /**
    * This will check the map for any potential hiding locations, and add them as a list of coordinates
    *
    */
    public Array<Vector2Int> getHidingSpots() {
        TiledMapTileLayer layer = (TiledMapTileLayer) mapFile.getLayers().get("HidingSpot");
        int roomTileWidth = layer.getWidth();
        int roomTileHeight = layer.getHeight();

        Array<Vector2Int> spots = new Array<>();

        for (int x = 0; x < roomTileWidth; x++) {
            for (int y = 0; y < roomTileHeight; y++) {
                TiledMapTileLayer.Cell cellInTile = layer.getCell(x, y);

                if (cellInTile != null) {
                    spots.add(new Vector2Int(x, y));
                    break;
                }
            }
        }
        return spots;
    }
}


