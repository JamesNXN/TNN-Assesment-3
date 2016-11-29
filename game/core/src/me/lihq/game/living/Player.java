package me.lihq.game.living;

import me.lihq.game.GameMain;
import me.lihq.game.Settings;
import me.lihq.game.models.Inventory;
import me.lihq.game.models.Room;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class Player extends AbstractPerson
{

    //The personality will be a percent score (0-100) 50 being neutral etc etc
    private int personalityLevel = 50;

    private Inventory inventory = new Inventory();

    private int score = 0;

    private String name;

    private Room currentRoom;

    public Player(String name, String imgSrc)
    {
        super(imgSrc);
        this.name = name;
    }

    /**
     * This method will change the players personality by the given amount.
     * It will cap the personality between 0 and 100.
     * <p>
     * If the change takes it out of these bounds, it will change it to the min or max.
     *
     * @param change - The amount to change by, can be positive or negative
     */
    public void addToPersonality(int change)
    {
        personalityLevel = personalityLevel + change;

        if (personalityLevel < 0) {
            personalityLevel = 0;
        } else if (personalityLevel > 100) {
            personalityLevel = 100;
        }
    }


    /**
     * This moves the player to a new tile
     * @param dx the amount of tiles to move in the x direction
     * @param dy the amout of tiles to move in the y direction
     */
    @Override
    public void move(int dx, int dy)
    {
        if (!currentRoom.isWalkableTile(tileCoordinates.getX() + dx, tileCoordinates.getY() + dy)) {return;}

        this.setTileCoordinates(tileCoordinates.x + dx, tileCoordinates.y + dy);
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    public String getPlayername()
    {
        return this.name;
    }

    public int getPersonality()
    {
        return this.personalityLevel;
    }

    public void changeRoom(int roomID, int newX, int newY)
    {
        changeRoom(GameMain.me.gameMap.getRoom(roomID), newX, newY);
    }

    public void changeRoom(Room newRoom, int newX, int newY)
    {
        currentRoom = newRoom;

        this.setTileCoordinates(newX, newY);
    }

    public void setRoom(Room room)
    {
        this.currentRoom = room;
    }

    public Room getRoom()
    {
        return this.currentRoom;
    }
}
