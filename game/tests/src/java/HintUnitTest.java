package java;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Hint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by PPPPPP on 2017/2/12.
 */
public class HintUnitTest {
    private Clue testclue;
    private Hint testhint;
    private Hint testhint2;

    @Before
    public void setUp() throws Exception {
        this.testclue = new Clue();
        this.testhint = new Hint(testclue);
        this.testhint2 = new Hint(testclue);
        while(testhint.getRelatedNpcIdArray() == testhint2.getRelatedNpcIdArray()){
            this.testhint2 = new Hint(testclue);
        }
    }

    @After
    public void tearDown() throws Exception {
        this.testhint = null;
        this.testclue = null;

    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        assertNotNull(testhint.getRelatedNpcIdArray());
        /**
        * Test the elements are subsets of related  npc id array.
        * */
        for(int id:testhint.getRelatedNpcIdArray()){
            assertTrue("Hint should be subset of related NPC",  testclue.getRelatedNpcIdArray().contains(id,true));
        }


    }

    @Test
    public void getRelatedClue() throws Exception {
        assertNotNull(testhint.getRelatedClue());
        assertEquals(testhint.getRelatedClue(),testclue);


    }

    @Test
    public void combine() throws Exception {
        /**
         * test if the elements in different hints can be combined.
         */
        Array<Integer> test = new Array<>();
        test.addAll(testhint.getRelatedNpcIdArray());
        test.addAll(testhint2.getRelatedNpcIdArray());
        testhint.combine(testhint2);
        for(int id:test){
            assertTrue("Combine Failed", testhint.getRelatedNpcIdArray().contains(id,true));
        }


    }

}