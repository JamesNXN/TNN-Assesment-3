package me.lihq.game.models;

//TODO: Tidy up getters and setters add them if needed, some places we are using them others not.


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import me.lihq.game.Assets;
import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.living.AbstractPerson.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class defines a room which is a game representation of a real world room in the Ron Cooke Hub.
 */
public class Room
{
    /**
     * This stores the name of the room.
     * <p>
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
    private List<Clue> cluesInRoom = new ArrayList<>();

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
    private float animationStateTime = 0f;

    /**
     * Constructor that builds a Room object from the given parameters
     *
     * @param id      - The integer ID of the room
     * @param mapFile - The String that points to the tmx map file.
     * @param name    - The name of the room
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
     * Sets the room to be the murder room
     */
    public void setMurderRoom()
    {
        this.murderRoom = true;
        System.out.println("Room " + getID() + " is the murder room");
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
    public String getName()
    {
        return this.name;
    }

    /**
     * Adds a clue to the room.
     *
     * @param newClue - The clue to add to the room
     */
    public void addClue(Clue newClue)
    {
        System.out.println("Added Clue " + newClue.getName() + " at location " + newClue.getPosition() + " in room " + getID());

        if (!cluesInRoom.contains(newClue)) {
            cluesInRoom.add(newClue);
        }
    }

    /**
     * This method takes a location parameter and checks it for a clue, if a clue is found it is removed from the map and return
     *
     * @param x - The x coordinate the player is at
     * @param y - The y coordinate the player is at
     */
    public Clue getClue(int x, int y)
    {
        //Apply direction change
        Clue out = null;
        //Check for a clue at that coordinate
        for (Clue c : cluesInRoom) {
            if (c.getPosition().x == x && c.getPosition().y == y) {
                //This is just temporary indicator that you have found the clue
                //We will use the speech box in the future
                out = c;
            }
        }
        if (out != null) {
            this.cluesInRoom.remove(out);
        }

        return out;
    }

    public void drawClues(float delta, Batch batch)
    {
        animationStateTime += delta;

        for (Clue c : cluesInRoom) {
            TextureRegion currentFrame = Assets.CLUE_GLINT.getKeyFrame(animationStateTime, true);
            batch.draw(currentFrame, c.getTileX() * Settings.TILE_SIZE, c.getTileY() * Settings.TILE_SIZE);
        }
    }

    public boolean isHidingPlace(int x, int y)
    {
        int amountOfLayers = map.getLayers().getCount() - 1;

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tl = (TiledMapTileLayer) map.getLayers().get(currentLayer);

            if (tl.getCell(x, y) == null) {
                continue;
            }

            if (!tl.getCell(x, y).getTile().getProperties().containsKey("hidingSpot")) {
                continue;
            }

            if (tl.getCell(x, y).getTile().getProperties().get("hidingSpot").toString().equals("true")) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method takes a current X and Y coordinate and checks through all the layers on the map to see if any tile IS NOT
     * movable. If any tile IS NOT movable, it returns false.
     *
     * @param x - The x coordinate to check
     * @param y - The y coordinate to check
     * @return - whether or not that tile can be walked on.
     */
    public boolean isWalkableTile(int x, int y)
    {
        //reduced by one because the last layer is to be displayed over the top of the player and therefore is ignored.
        int amountOfLayers = map.getLayers().getCount() - 1;
        int emptyCellCount = 0; //The amount of empty cells on the map in the location x and y.


        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer) map.getLayers().get(currentLayer);

            if (tiledLayer.getName().equals("Blood") && !GameMain.me.player.getRoom().isMurderRoom()) {
                //Don't check the layer as the blood splat isn't there
                continue;
            }

            if (tiledLayer.getCell(x, y) == null) {
                emptyCellCount++; //for every empty cell increase the emptyCellCount by 1
                continue;
            }

            if (!tiledLayer.getCell(x, y).getTile().getProperties().containsKey("walkable")) {
                continue;
            }

            if (tiledLayer.getCell(x, y).getTile().getProperties().get("walkable").toString().equals("false")) {
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
     * @return -  whether or not the tile is a trigger tile.
     */
    public boolean isTriggerTile(int x, int y)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);

        if (layer.getCell(x, y) == null) return false;

        int amountOfLayers = map.getLayers().getCount();

        for (int currentLayer = 0; currentLayer < amountOfLayers; currentLayer++) {
            TiledMapTileLayer tl = (TiledMapTileLayer) map.getLayers().get(currentLayer);

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
     * This method gets the rotation that the map is that they are standing on.
     * <p>
     * If they aren't on a mat, it returns null
     *
     * @param x - The x coordinate to check
     * @param y = The y coordinate to  check
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
     * This will check the map for any potential hiding locations, and returns them as a list of coordinates
     *
     * @return list of coordinates
     */
    public List<Vector2Int> getHidingSpots()
    {

        List<Vector2Int> hidingSpots = new ArrayList<>();

        int roomWidth = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getWidth();
        int roomHeight = ((TiledMapTileLayer) this.getTiledMap().getLayers().get(0)).getHeight();

        for (int x = 0; x < roomWidth; x++) {
            for (int y = 0; y < roomHeight; y++) {
                for (MapLayer layer : this.getTiledMap().getLayers()) {
                    TiledMapTileLayer thisLayer = (TiledMapTileLayer) layer;
                    TiledMapTileLayer.Cell cellInTile = thisLayer.getCell(x, y);

                    if (cellInTile == null) continue;

                    if (!cellInTile.getTile().getProperties().containsKey("hidingSpot")) continue;

                    if (cellInTile.getTile().getProperties().get("hidingSpot").toString().equals("true")) {
                        hidingSpots.add(new Vector2Int(x, y));
                        break;
                    }
                }
            }
        }
        return hidingSpots;

    }


    /**
     * Thus gets a random possible location to hide a clue in
     *
     * @return Coordinates of the tile where the clue is to be hidden, null if there are none available
     */
    public Vector2Int getRandHidingSpot()
    {

        if (!this.getHidingSpots().isEmpty()) {
            List<Vector2Int> potentialHidingSpots = getHidingSpots();
            Collections.shuffle(potentialHidingSpots);

            return potentialHidingSpots.get(0);

        } else {

            return null;

        }

    }

    /**
     * This method will get the transition data (if available)
     * for the associated door mat in this room at the location x y.
     *
     * @param x - The current x coordinate in the room (in terms of tiles not pixels)
     * @param y - The current y coordinate in the room (in terms of tiles not pixels)
     * @return - a Transition data type. Which stores the relevant information
     */
    public Transition getTransitionData(int x, int y)
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
     * This object stores data the links the rooms together
     */
    public static class Transition
    {
        public Vector2Int from = new Vector2Int(0, 0);
        /**
         * The direction the player should face when they enter the roo,
         */
        public Direction newDirection = null;
        /**
         * The entry point to the room in terms of tiles
         */
        public Vector2Int newTileCoordinates = new Vector2Int(0, 0);
        /**
         * The new room to transition to
         */
        private Room newRoom;

        public Transition()
        {
        }

        public Transition setTo(Room room, int newTileCoordinateX, int newTileCoordinateY, Direction newDirection)
        {
            this.newRoom = room;
            this.newTileCoordinates = new Vector2Int(newTileCoordinateX, newTileCoordinateY);
            this.newDirection = newDirection;
            return this;
        }

        public Transition setFrom(int oldTiledCoordinateX, int oldTiledCoordinateY)
        {
            this.from = new Vector2Int(oldTiledCoordinateX, oldTiledCoordinateY);
            return this;
        }

        public Room getNewRoom()
        {
            return newRoom;
        }
    }
}


