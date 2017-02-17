package me.lihq.game.models;

import com.badlogic.gdx.utils.Array;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HintUnitTest {
    private Clue testClue1, testClue2;
    private Hint testHint1;
    private Hint testHint2;


    @Before
    public void setUp() throws Exception {
        Array<Integer> testArray1 = new Array<>();
        testArray1.addAll(1, 2, 3);
        testClue1 = new Clue(null, null);

        Array<Integer> testArray2 = new Array<>();
        testArray2.addAll(4, 5, 6);
        testClue2 = new Clue(null, null);

        this.testHint1 = new Hint(testClue1);
        this.testHint2 = new Hint(testClue2);
    }

    @After
    public void tearDown() throws Exception {
        this.testHint1 = null;
        this.testClue1 = null;
        this.testHint2 = null;
        this.testClue2 = null;

    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        assertNotNull(testHint1.getRelatedNpcIdArray());
        /**
         * Test the elements are subsets of related  npc id array.
         * */
        for (int id : testHint1.getRelatedNpcIdArray()) {
            assertTrue("Hint should be subset of related NPC", testClue1.getRelatedNpcIdArray().contains(id, true));
        }


    }

    @Test
    public void getRelatedClue() throws Exception {
        assertNotNull(testHint1.getRelatedClue());
        assertEquals(testClue1, testHint1.getRelatedClue());


    }

    @Test
    public void combine() throws Exception {
        /**
         * test if the elements in different hints can be combined.
         */
        Array<Integer> test = new Array<>();
        test.addAll(testHint1.getRelatedNpcIdArray());
        test.addAll(testHint2.getRelatedNpcIdArray());
        testHint1.combine(testHint2);
        for (int id : test) {
            assertTrue("Combine Failed", testHint1.getRelatedNpcIdArray().contains(id, true));
        }


    }

}