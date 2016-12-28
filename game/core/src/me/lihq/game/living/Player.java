package me.lihq.game.living;

import me.lihq.game.GameMain;
import me.lihq.game.models.Inventory;
import me.lihq.game.models.Room;

/**
 * This class defines the player that the person playing the game will be represented by.
 */
public class Player extends AbstractPerson
{

    public Boolean move = false;
    //The personality will be a percent score (0-100) 50 being neutral etc etc
    private int personalityLevel = 50;
    private Inventory inventory = new Inventory();
    private int score = 0;
    private String name;

    public Player(String name, String imgSrc)
    {
        super(imgSrc);
        this.name = name;

        setTileCoordinates(15, 5);
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
     * This Moves the player to a new tile.
     *
     * @param dir the direction that the player should move in.
     */
    public void move(Direction dir)
    {
        if (this.state != PersonState.STANDING) {
            return;
        }

        if (getRoom().isTriggerTile(this.tileCoordinates.x, this.tileCoordinates.y) && dir.toString().equals(getRoom().getMatRotation(this.tileCoordinates.x, this.tileCoordinates.y)))
        {
            GameMain.me.getNavigationScreen().initialiseRoomChange();
            return;
        }

        if (!getRoom().isWalkableTile(this.tileCoordinates.x + dir.getDx(), this.tileCoordinates.y + dir.getDy())) {
            setDirection(dir);
            return;
        }

        initialiseMove(dir);
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


    /**
     * This method takes a room & its coordinates, then moves the player to that specific room
     * at the defined coordinates.
     *
     * @param newRoom - The room to change to
     * @param newX - The X coordinate to move to
     * @param newY - The Y coordinate to move to
     */
    public void changeRoom(Room newRoom, int newX, int newY)
    {
        setRoom(newRoom);

        this.setTileCoordinates(newX, newY);
    }
}
