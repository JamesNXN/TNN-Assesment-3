import com.badlogic.gdx.utils.Array;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Inventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 09/02/2017.
 */
public class InventoryUnitTests extends GameTester{
    /**
     * Start by defining test parameters needed
     */
    private Inventory testInventory;
    private Clue testClue;

    /**
     * Setup method initialises the objects needed and the object to be tested and throws an exception
     * if they dont initialise properly
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        testInventory = new Inventory();
        testClue = new Clue();

    }

    /**
     * Tear Down function ensures that the test parameters are reset after each test is run
     * basically just reset everything to null
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        testInventory = null;
        testClue = null;

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