package me.lihq.game.models;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.GameTester;
import me.lihq.game.people.NPC;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class InventoryUnitTests extends GameTester {
    /**
     * Start by defining test parameters needed
     */
    private Inventory testInventory;

    private Clue testClue;
    private Clue testClue2;

    private NPC testNPC;
    private NPC testNPC2;

    private Hint testHint;
    private Hint testHint2;

    /**
     * Setup method initialises the objects needed and the object to be tested and throws an exception
     * if they dont initialise properly
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testInventory = new Inventory();

        Array<Integer> testArray1 = new Array<>();
        testArray1.addAll(1, 2, 3);
        testClue = new Clue("testClue1", "description1", testArray1);

        Array<Integer> testArray2 = new Array<>();
        testArray2.addAll(4, 5, 6);
        testClue2 = new Clue("testClue2", "description2", testArray2);

        Json json = new Json();

        JsonValue npcJsonData = new JsonReader().parse(new FileHandle("src/test/resources/assets/testNPC.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        testNPC = new NPC(npcJsonDataArray.get(0));
        testNPC2 = new NPC(npcJsonDataArray.get(1));

        testHint = new Hint(testClue);
        testHint2 = new Hint(testClue2);

    }

    /**
     * Tear Down function ensures that the test parameters are reset after each test is run
     * basically just reset everything to null
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        testInventory = null;

        testClue = null;
        testClue2 = null;

        testNPC = null;
        testNPC2 = null;

        testHint = null;
        testHint2 = null;

    }

    /**
     * Tests follow the format below for every function you want a base case eg in the case of getCollectedClues()
     * ive tested that it doesnt return a null object as the base case
     * <p>
     * then after some change normally involving using the method
     * <p>
     * you then run another assertion to check if the change worked and the method now returns the correct thing
     *
     * @throws Exception
     */
    @Test
    public void getCollectedClues() throws Exception {
        /**
         * base case checks whether the inventory has a collected clue array
         */
        Assert.assertNotNull(testInventory.getCollectedClues());

        /**
         * adds test clue to the array
         */
        testInventory.addNewClue(testClue);

        /**
         * checks whether the getCollectedClues returns an array which contains the added test clue
         */
        Assert.assertEquals("array does not contain correct clue", testClue, testInventory.getCollectedClues().peek());
    }

    @Test
    public void getMetCharacters() throws Exception {

        Assert.assertNotNull(testInventory.getMetCharacters());

        testInventory.addNewCharacter(testNPC);

        Assert.assertEquals("array does not contain correct NPC", testNPC, testInventory.getMetCharacters().peek());

    }

    @Test
    public void getCollectedHints() throws Exception {

        Assert.assertNotNull(testInventory.getCollectedHints());

        testInventory.addNewHint(testHint);

        Assert.assertEquals("array does not contain correct Hint", testHint, testInventory.getCollectedHints().peek());
    }

    @Test
    public void checkIfHintExists() throws Exception {

        testInventory.addNewHint(testHint);

        Assert.assertEquals(true, testInventory.checkIfHintExists(testHint));

    }

    @Test
    public void addNewClue() throws Exception {

        testInventory.addNewClue(testClue);
        Assert.assertEquals("first item not added", testClue, testInventory.getCollectedClues().peek());

        testInventory.addNewClue(testClue2);
        Assert.assertEquals("second item not added", testClue2, testInventory.getCollectedClues().peek());

        //// @TODO I was unsure of how to do the last part, but we need to assert collectedClues contains 2 items

    }

    @Test
    public void addNewCharacter() throws Exception {

        testInventory.addNewCharacter(testNPC);
        Assert.assertEquals("first NPC not added", testNPC, testInventory.getMetCharacters().peek());

        testInventory.addNewCharacter(testNPC2);
        Assert.assertEquals("second NPC not added", testNPC2, testInventory.getMetCharacters().peek());

        //// @TODO I was unsure of how to do the last part, but we need to assert metCharacters contains 2 items

    }

    @Test
    public void addNewHint() throws Exception {

        testInventory.addNewHint(testHint);
        Assert.assertEquals("first Hint not added", testHint, testInventory.getCollectedHints().peek());

        testInventory.addNewHint(testHint2);
        Assert.assertEquals("second Hint not added", testHint2, testInventory.getCollectedHints().peek());

        //// @TODO I was unsure of how to do the last part, but we need to assert collectedHints contains 2 items
    }

}