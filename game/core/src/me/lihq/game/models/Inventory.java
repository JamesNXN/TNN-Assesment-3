package me.lihq.game.models;

import me.lihq.game.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by joeshuff on 20/11/2016.
 */
public class Inventory {

    private List<Item> items = new ArrayList<Item>();

    //Constructor
    public Inventory() {

    }

    /*
        This method will check whether an item is in the inventory or not based on its name

        @param String name - The name of the item to be checks

        @returns boolean - Whether the item exists or not
     */
    public boolean hasItem(String name) {
        return getItems().contains(new Item(name, -1, -1));
    }

    //Returns the list of items in the inventory
    public List<Item> getItems() {
        return this.items;
    }

    //My justification is only the Inventory will hold items, so to save space, keep it in this class
    public class Item {
        String name = "";

        //Stores the location of the item image on the item sprite sheet
        int imageX = -1;
        int imageY = -1;

        public Item(String itemName, int imageX, int imageY) {
            this.name = itemName;

            //* 32 because we can give the position of the image, eg. x=2, y=1. Then it gets the pixel locations by timesing by the pixel size (32 default)
            this.imageX = imageX * Settings.TILE_SIZE;
            this.imageY = imageY * Settings.TILE_SIZE;
        }

        //Returns the name of the item
        public String getName() {
            return this.name;
        }

        //Returns a list with the X and Y pixel coordinates of the item image e.g {x, y}
        public List<Integer> getImageLoc() {
            return Arrays.asList(this.imageX, this.imageY);
        }

        //DO WE WANT SETTERS FOR THESE ATTRIBUTES??????

        /*
            This is a standard Object method that checks if 2 objects are the same.
            It is called in instances such as list.remove(item). item1 == item2 etc

            @param obj - The object it is being compared to

            @returns boolean - Whether the parameter obj is equal to the current object
         */
        @Override
        public boolean equals(Object obj) {

            if (obj instanceof Item) {
                Item other = (Item) obj;

                return other.getName().equals(this.getName());
            } else {
                return false;
            }

        }
    }
}