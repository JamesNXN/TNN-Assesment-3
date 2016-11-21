package me.lihq.game.models;

import me.lihq.game.Settings;

/**
 * Created by vishal on 21/11/2016.
 */
public class Clue {
    private String ClueName;
    private int RoomID;
    private int xcoord;
    private int ycoord;

    public Clue(String Name,int RoomID, int x, int y){
        this.ClueName= Name;
        this.RoomID= RoomID;
        this.xcoord= x* Settings.PIXEL_SIZE;
        this.ycoord= y* Settings.PIXEL_SIZE;
    }
    public String getClueName(){
        return(this.ClueName);
    }
    public void setClueName(String name){
        this.ClueName=name;
    }
    public void setCoords(int x, int y){
        this.xcoord=x* Settings.PIXEL_SIZE;
        this.ycoord=y* Settings.PIXEL_SIZE;
    }
    public int getxCoords(){
        return(this.xcoord);
    }
    public int getyCoords(){
        return(this.ycoord);
    }
    public int getRoomID(){
        return(this.RoomID);
    }
    public void setRoomID(int ID){
        this.RoomID=ID;
    }
}
