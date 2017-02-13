package java;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.models.Clue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by PPPPPP on 2017/2/12.
 */
public class ClueTest {

    private Clue cluetest;

    @Before
    public void setUp() throws Exception {
        this.cluetest = new Clue();
    }

    @After
    public void tearDown() throws Exception {
        this.cluetest = null;
    }


    @Test
    public void equals() throws Exception {


    }

    @Test
    public void getName() throws Exception {
        System.out.println(cluetest.getName());
        assertEquals("name doesn't fit",cluetest.getName(),"test");

    }

    @Test
    public void getDescription() throws Exception {
        assertEquals("name doesn't fit",cluetest.getDescription(),"test");


    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        Array<Integer> test = new Array<>(new Integer[]{1,2,3,4});
        assertTrue("ID array doesn't fit",cluetest.getRelatedNpcIdArray().equals(test));

    }

    @Test
    public void getCollisionBox() throws Exception {

    }

}