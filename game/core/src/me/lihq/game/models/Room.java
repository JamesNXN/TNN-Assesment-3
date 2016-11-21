package me.lihq.game.models;

/**
 * Created by vishal on 20/11/2016.
 */

import me.lihq.game.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    private String RoomName= "";
    private int ID;
    private List<Clue> cluesinroom = new ArrayList<Clue>();
    /**Setting coordinates of room entrance/exit */
    private int x;
    private int y;
    private String NPC;

    /**Returns True if it's the room the murder took place in*/
    public boolean ismurderroom(boolean murderroom){
        return (murderroom);
    };

    public void setID(int ID){
        this.ID= ID;
    }
    public int getID(){
        return(this.ID);
    }
    public void SetCoords(int x, int y){
        this.x=x* Settings.PIXEL_SIZE;
        this.y=y* Settings.PIXEL_SIZE;
    }
    public int getx(){
        return(this.x);
    }
    public int gety(){
        return (this.y);
    }

    /**Can be used by the NPC class to set an NPC to a room*/
    public void setNPC(String npc) {
    }
    /**Changes coordinates of clue*/
    public void moveClue(Clue newClue,int x,int y){
        newClue.setCoords(x* Settings.PIXEL_SIZE,y* Settings.PIXEL_SIZE);
    }
    public void addClue(Clue newClue){
        cluesinroom.add(newClue);
        /**IF statement to remove clue from list of original room if moving from room to room*/
        if (newClue.getRoomID()!=0){

            newClue.setRoomID(this.ID);}
        else{
            newClue.setRoomID(this.ID);
        }
    }
}


