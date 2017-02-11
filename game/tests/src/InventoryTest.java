package me.lihq.game.models;

import com.badlogic.gdx.utils.Array;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 09/02/2017.
 */
public class InventoryTest {
    private Inventory testInventory;
    private Clue testClue;

    @Before
    public void setUp() throws Exception {
        testInventory = new Inventory();
        testClue = new Clue();

    }

    @After
    public void tearDown() throws Exception {
        testInventory = null;
        testClue = null;

    }

    @Test
    public void getCollectedClues() throws Exception {
        /**
         * base case checks whether the inventory has a collected clue array
         */
        assertNotNull(testInventory.getCollectedClues());

        /**
         * adds test clue to the array
         */
        testInventory.addNewClue(testClue);

        /**
         * checks whether the getCollectedClues returns an array which contains the added test clue
         */
        assertEquals("array does not contain correct clue", testClue, testInventory.getCollectedClues().peek());
    }

    @Test
    public void getMetCharacters() throws Exception {

    }

    @Test
    public void getCollectedHints() throws Exception {

    }

    @Test
    public void addNewClue() throws Exception {

    }

    @Test
    public void checkIfHintExists() throws Exception {

    }

    @Test
    public void addNewCharacter() throws Exception {

    }

    @Test
    public void addNewHint() throws Exception {

    }

}