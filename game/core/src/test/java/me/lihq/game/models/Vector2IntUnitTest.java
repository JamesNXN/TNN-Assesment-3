import me.lihq.game.models.Vector2Int;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by PPPPPP on 2017/2/13.
 */
public class Vector2IntUnitTest {

    private Vector2Int test2Int;

    @Before
    public void setUp() throws Exception {
        test2Int = new Vector2Int(1,2);

    }

    @After
    public void tearDown() throws Exception {
        test2Int = null;

    }

    @Test
    public void getX() throws Exception {
        assertNotNull(test2Int.getX());
        assertEquals("x not equal", test2Int.getX(),1);

    }



    @Test
    public void getY() throws Exception {
        assertNotNull(test2Int.getY());
        assertEquals("y not equal", test2Int.getY(),2);
    }

    @Test
    public void testToString() throws Exception {
        assertTrue("Doesn't return string", test2Int.toString() instanceof String);
        assertEquals("xy not equal", test2Int.toString(),"(1,2)");

    }

}