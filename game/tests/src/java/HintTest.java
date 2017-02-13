package java;

import me.lihq.game.models.Clue;
import me.lihq.game.models.Hint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Tunc on 12/02/2017.
 */
public class HintTest {
    private Hint testHint;
    private Clue testClue;

    @Before
    public void setUp() throws Exception {
        testClue = new Clue();
        testHint = new Hint(testClue);

    }

    @After
    public void tearDown() throws Exception {
        testClue = null;
        testHint = null;


    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {

        assertNotNull(testHint.getRelatedNpcIdArray());

    }



    @Test
    public void getRelatedClue() throws Exception {
        assertEquals(testClue, testHint.getRelatedClue());

    }

    //// @TODO Unsure on test for combine method atm. Will add later.

}