package me.lihq.game.models;

/**
 * Created by vishal on 20/11/2016.
 */

import com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Room {
    private String name;
    private int ID;
    private String mapFile;

    private List<Clue> cluesInRoom = new ArrayList<Clue>();

    private boolean murderRoom = false;

    private TiledMap map;

    //Room transitions stored as (currentX, currentY) points to a (newRoomID, newX, newY)
    private List<Transition> roomTransitions = new ArrayList<Transition>();

    public Room(int id, String mapFile, String name) {
        this.ID = id;
        this.mapFile = mapFile;
        this.name = name;
        this.map = new TmxMapLoader().load(mapFile);
    }

    //TODO: Popup notification on room entrance

    //Returns True if it's the room the murder took place in
    public boolean isMurderRoom() {
        return murderRoom;
    }

    public int getID() {
        return this.ID;
    }

    /**
     * Changes coordinates of clue
     */
    public void moveClue(Clue clue, int x, int y) {
        if (cluesInRoom.contains(clue)) {
            clue.setCoords(x, y);
        }
    }

    public void addClue(Clue newClue) {
        if (!cluesInRoom.contains(newClue)) {
            cluesInRoom.add(newClue);
        }
    }

    public void removeClue(Clue toRemove) {
        if (cluesInRoom.contains(toRemove)) {
            cluesInRoom.remove(toRemove);
        }
    }

    public boolean isWalkableTile(int x, int y)
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);

        if (layer.getCell(x, y) == null) return false;

        //TODO: NEEDS UNCOMMENTING ONCE WE HAVE MAPS WITH THIS PROPERTY
        //if (!layer.getCell(x, y).getTile().getProperties().containsKey("walkable")) return false;

        //return Boolean.valueOf(layer.getCell(x, y).getTile().getProperties().get("walkable").toString());

        return true;
    }

    public boolean isTriggerTile(int x, int y) {
        return false;
    }

    public boolean getTrigger(int x, int y) {
        if (isTriggerTile(x, y))
        {
            //Get trigger type property
        }

        return false;
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
    public Transition getNewRoom(int x, int y) {
        return hasTransition(new Vector2Int(x, y));
    }

    private Transition hasTransition(Vector2Int v)
    {
        for (Transition l : roomTransitions)
        {
            if (l.from.equals(v))
            {
                return (l);
            }
        }

        return null;
    }

    public static class Transition
    {
        public Vector2Int from = new Vector2Int(0, 0);

        public int newRoom = 0;

        public Vector2Int to = new Vector2Int(0, 0);

        public Transition() {}

        public Transition setTo(int room, int x, int y)
        {
            this.newRoom = room;
            this.to = new Vector2Int(x, y);
            return this;
        }

        public Transition setFrom(int x, int y)
        {
            this.from = new Vector2Int(x, y);
            return this;
        }
    }
}


