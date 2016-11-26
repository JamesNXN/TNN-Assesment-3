package me.lihq.game.living;

import me.lihq.game.Settings;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.models.Inventory;

/**
 * Created by brookehatton on 18/11/2016.
 */
public class Player extends AbstractPerson
{

    //The personality will be a percent score (0-100) 50 being neutral etc etc
    private int personalityLevel = 50;

    private Inventory inventory = new Inventory();

    private int score = 0;

    private String name = "Dr Professor Lecture Module Leader Sir Colin Runciman-Sensei-Sama PhD Hons. GCSEs";

    public Player(String name, String imgSrc)
    {
        super(imgSrc);
        this.name = name;
    }

    /*
        This method will change the players personality by the given amount.
        It will cap the personality between 0 and 100.

        If the change takes it out of these bounds, it will change it to the min or max.

        @param int change - The amount to change by, can be positive or negative
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

    public void move(int dx, int dy)
    {
        this.position.x += dx;
        this.position.y += dy;

        this.setPosition(this.position.x * Settings.TILE_SIZE, this.position.y * Settings.TILE_SIZE);
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
}
