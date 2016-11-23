package me.lihq.game.models;

import me.lihq.game.Settings;

/**
 * Created by vishal on 21/11/2016.
 */
public class Clue {
    private String clueName = "Super Secret Clue";

    private int x = 0;
    private int y = 0;

    //=== NEEDS DISCUSSING ===
    //Does a clue need a room ID as a room has a list of clues in it...
    private int roomID = -1;

    //Don't we need to associate a clue with a list of applicable NPCs?
    //~Jason

    private String imagePath = "clueSheet.png";

    public Clue(String name, int roomID, int x, int y) {
        this.clueName = name;
        this.roomID = roomID;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Clue) {
            Clue c = (Clue) obj;

            //Might have to do same coordinates AND same room AND same name

            return c.getClueName().equals(this.getClueName());
        }

        return false;
    }

    public String getClueName() {
        return this.clueName;
    }

    public void setClueName(String name) {
        this.clueName = name;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRoomID() {
        return roomID;
    }
}
