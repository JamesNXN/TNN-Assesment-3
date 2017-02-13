
import me.lihq.game.models.Door;

import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by TD on 13/02/2017.
 */
public class DoorUnitTest extends GameTester{
    /**
     * Start by defining test parameters needed
     */
    private Vector2Int tileCord;
    public Direction direction;


    /**
     * Setup method initialises the objects needed and the object to be tested and throws an exception
     * if they dont initialise properly
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        tileCord = new Vector2Int(2,4);
        direction = new Direction(0,1);


    }

    /**
     * Tear Down function ensures that the test parameters are reset after each test is run
     * basically just reset everything to null
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        testInventory = null;

    }

    /**
     * Tests follow the format below for every function you want a base case eg in the case of getCollectedClues()
     * ive tested that it doesnt return a null object as the base case
     *
     * then after some change normally involving using the method
     *
     * you then run another assertion to check if the change worked and the method now returns the correct thing
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
        testInventory.addNewClue(testClue);

        /**
         * checks whether the getCollectedClues returns an array which contains the added test clue
         */
        assertEquals("array does not contain correct clue", testClue, testInventory.getCollectedClues().peek());
    }

    @Test
    public void getMetCharacters() throws Exception {

        assertNotNull(testInventory.getMetCharacters());

        testInventory.addNewCharacter(testNPC);

        assertEquals("array does not contain correct NPC",testNPC, testInventory.getMetCharacters().peek());

    }

    @Test
    public void getCollectedHints() throws Exception {

        assertNotNull(testInventory.getCollectedHints());

        testInventory.addNewHint(testHint);

        assertEquals("array does not contain correct Hint", testHint, testInventory.getCollectedHints().peek());
    }

    @Test
    public void checkIfHintExists() throws Exception {

        testInventory.addNewHint(testHint);

        assertEquals(true, testInventory.checkIfHintExists(testHint));

    }

    @Test
    public void addNewClue() throws Exception {

        testInventory.addNewClue(testClue);
        assertEquals("first item not added",testClue, testInventory.getCollectedClues().peek());

        testInventory.addNewClue(testClue2);
        assertEquals("second item not added",testClue2, testInventory.getCollectedClues().peek());

        //// @TODO I was unsure of how to do the last part, but we need to assert collectedClues contains 2 items

    }

    @Test
    public void addNewCharacter() throws Exception {

        testInventory.addNewCharacter(testNPC);
        assertEquals("first NPC not added",testNPC, testInventory.getMetCharacters().peek());

        testInventory.addNewCharacter(testNPC2);
        assertEquals("second NPC not added",testNPC2, testInventory.getMetCharacters().peek());

        //// @TODO I was unsure of how to do the last part, but we need to assert metCharacters contains 2 items

    }

    @Test
    public void addNewHint() throws Exception {

        testInventory.addNewHint(testHint);
        assertEquals("first Hint not added",testHint, testInventory.getCollectedHints().peek());

        testInventory.addNewHint(testHint2);
        assertEquals("second Hint not added",testHint2, testInventory.getCollectedHints().peek());

        //// @TODO I was unsure of how to do the last part, but we need to assert collectedHints contains 2 items
    }

}