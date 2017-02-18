package me.lihq.game.models;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.GameTester;
import me.lihq.game.people.NPC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryUnitTests extends GameTester {
    /**
     * Start by defining test parameters needed
     */
    private Inventory testInventory;

    private Clue testClue1;
    private Clue testClue2;
    private Clue testWeapon;

    private NPC testNPC1;
    private NPC testNPC2;

    private Hint testHint1;
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
        Json json = new Json();
        TextureAtlas clueGlint = new TextureAtlas(GameTester.ASSEST_FOLDER + "clueGlint.pack");
        JsonValue clueJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testClue.json"));
        Array<JsonValue> clueJsonDataArray = json.readValue(Array.class, clueJsonData);

        testClue1 = new Clue(clueJsonDataArray.get(0), clueGlint);

        testClue2 = new Clue(clueJsonDataArray.get(1), clueGlint);

        testWeapon = new Clue(clueJsonDataArray.get(2), clueGlint);


        JsonValue npcJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testNPC.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        testNPC1 = new NPC(npcJsonDataArray.get(0));
        testNPC2 = new NPC(npcJsonDataArray.get(1));

        testHint1 = new Hint(testClue1);
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

        testClue1 = null;
        testClue2 = null;
        testWeapon = null;

        testNPC1 = null;
        testNPC2 = null;

        testHint1 = null;
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
        assertNotNull(testInventory.getCollectedClues());

        /**
         * adds test clue to the array
         */
        testInventory.addClue(testClue1);

        /**
         * checks whether the getCollectedClues returns an array which contains the added test clue
         */
        assertEquals("array does not contain correct clue", testClue1, testInventory.getCollectedClues().peek());
    }

    @Test
    public void getMetCharacters() throws Exception {

        assertNotNull(testInventory.getMetCharacters());

        testInventory.addCharacter(testNPC1);

        assertEquals("array does not contain correct NPC", testNPC1, testInventory.getMetCharacters().peek());

    }

    @Test
    public void getCollectedHints() throws Exception {

        assertNotNull(testInventory.getCollectedHints());

        testInventory.addHint(testHint1);

        assertEquals("array does not contain correct Hint", testHint1, testInventory.getCollectedHints().peek());
    }

    @Test
    public void checkIfHintExists() throws Exception {

        testInventory.addHint(testHint1);

        assertEquals(true, testInventory.contains(testHint1));

    }

    @Test
    public void addNewClue() throws Exception {

        testInventory.addClue(testClue1);
        assertEquals("first item not added", testClue1, testInventory.getCollectedClues().peek());

        testInventory.addClue(testClue2);
        assertEquals("second item not added", testClue2, testInventory.getCollectedClues().peek());

        assertEquals(2, testInventory.getCollectedClues().size);

    }

    @Test
    public void addNewCharacter() throws Exception {

        testInventory.addCharacter(testNPC1);
        assertEquals("first NPC not added", testNPC1, testInventory.getMetCharacters().peek());

        testInventory.addCharacter(testNPC2);
        assertEquals("second NPC not added", testNPC2, testInventory.getMetCharacters().peek());

        assertEquals(2, testInventory.getMetCharacters().size);

    }

    @Test
    public void addNewHint() throws Exception {

        testInventory.addHint(testHint1);
        assertEquals("first Hint not added", testHint1, testInventory.getCollectedHints().peek());

        testInventory.addHint(testHint2);
        assertEquals("second Hint not added", testHint2, testInventory.getCollectedHints().peek());

        assertEquals(2, testInventory.getCollectedHints().size);
    }

    @Test
    public void checkIfWeaponFound() throws Exception {
        assertFalse(testInventory.isWeaponFound());

        testInventory.addClue(testWeapon);

        assertTrue(testInventory.isWeaponFound());
    }
}