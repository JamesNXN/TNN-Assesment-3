package me.lihq.game.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.lihq.game.Assets;
import me.lihq.game.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the inventory which is where the player goes to see what items they have found so far.
 */
public class Inventory
{

    private List<Item> items = new ArrayList<Item>();

    //Constructor
    public Inventory()
    {

    }

    public void addItem(Item item)
    {
        if (hasItem(item.getName())) {
            //Increase stack size vs not adding?
        } else {
            items.add(item);
        }
    }

    /*
        This method will check whether an item is in the inventory or not based on its name

        @param String name - The name of the item to be checks

        @returns boolean - Whether the item exists or not
     */
    public boolean hasItem(String name)
    {
        return hasItem(new Item(name, "", -1, -1));
    }

    public boolean hasItem(Item item)
    {
        return getItems().contains(item);
    }

    //Returns the list of items in the inventory
    public List<Item> getItems()
    {
        return this.items;
    }

    //My justification is only the Inventory will hold items, so to save space, keep it in this class
    public static class Item
    {
        String name = "";
        String description = "";

        TextureRegion textureRegion;

        public Item(String itemName, String description, int imageX, int imageY)
        {
            this.name = itemName;
            this.description = description;

            //* 32 because we can give the position of the image, eg. x=2, y=1. Then it gets the pixel locations by timesing by the pixel size (32 default)
            //TODO: Needs UNCOMMENTING when we have items assets
            //textureRegion = new TextureRegion(Assets.items, imageX * Settings.TILE_SIZE, imageY * Settings.TILE_SIZE, Settings.TILE_SIZE, Settings.TILE_SIZE);
        }

        //Returns the name of the item
        public String getName()
        {
            return this.name;
        }

        public String getDescription()
        {
            return description;
        }

        /*
            This is a standard Object method that checks if 2 objects are the same.
            It is called in instances such as list.remove(item). item1 == item2 etc

            @param obj - The object it is being compared to

            @returns boolean - Whether the parameter obj is equal to the current object
         */
        @Override
        public boolean equals(Object obj)
        {

            if (obj instanceof Item) {
                Item other = (Item) obj;

                return other.getName().equals(this.getName());
            } else {
                return false;
            }

        }
    }
}