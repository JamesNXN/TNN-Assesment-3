package me.lihq.game.models;

/**
 * Created by vishal on 20/11/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private int ID;
    private String mapFile;

    private List<Clue> cluesInRoom = new ArrayList<Clue>();

    private boolean murderRoom = false;

    public Room(int id, String mapFile, String name) {
        this.ID = id;
        this.mapFile = mapFile;
        this.name = name;
    }

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
}


