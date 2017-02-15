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
    private Clue testClue;
    private Hint testHint;
    private Hint testHint2;


    @Before
    public void setUp() throws Exception {
        this.testClue = new Clue();
        this.testHint = new Hint(testClue);
        this.testHint2 = new Hint(testClue);
        while(testHint.getRelatedNpcIdArray() == testHint2.getRelatedNpcIdArray()){
            this.testHint2 = new Hint(testClue);
        }
    }

    @After
    public void tearDown() throws Exception {
        this.testHint = null;
        this.testClue = null;
        this.testHint2 = null;

    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        assertNotNull(testHint.getRelatedNpcIdArray());
        /**
        * Test the elements are subsets of related  npc id array.
        * */
        for(int id: testHint.getRelatedNpcIdArray()){
            assertTrue("Hint should be subset of related NPC",  testClue.getRelatedNpcIdArray().contains(id,true));
        }


    }

    @Test
    public void getRelatedClue() throws Exception {
        assertNotNull(testHint.getRelatedClue());
        assertEquals(testClue, testHint.getRelatedClue());


    }

    @Test
    public void combine() throws Exception {
        /**
         * test if the elements in different hints can be combined.
         */
        Array<Integer> test = new Array<>();
        test.addAll(testHint.getRelatedNpcIdArray());
        test.addAll(testHint2.getRelatedNpcIdArray());
        testHint.combine(testHint2);
        for(int id:test){
            assertTrue("Combine Failed", testHint.getRelatedNpcIdArray().contains(id,true));
        }


    }

}