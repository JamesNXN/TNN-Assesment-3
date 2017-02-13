package java;

import me.lihq.game.models.Vector2Int;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by PPPPPP on 2017/2/13.
 */
public class Vector2IntUnitTest {
    private Vector2Int test2int;
    @Before
    public void setUp() throws Exception {
        test2int = new Vector2Int(1,2);

    }

    @After
    public void tearDown() throws Exception {
        test2int = null;

    }

    @Test
    public void getX() throws Exception {
        assertEquals("x not equal",test2int.getX(),1);

    }



    @Test
    public void getY() throws Exception {
        assertEquals("y not equal",test2int.getY(),2);
    }

    @Test
    public void testToString() throws Exception {
        assertTrue("Doesn't return string",test2int.toString() instanceof String);
        assertEquals("xy not equal",test2int.toString(),"(1,2)");

    }

}