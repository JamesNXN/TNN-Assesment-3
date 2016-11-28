import me.lihq.game.GameMain;
import me.lihq.game.models.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeshuff on 26/11/2016.
 */
public class RoomUnitTests extends GameTest {

    Map map;

    @Before
    public void before()
    {
        map = new Map();
    }

    @Test
    public void testGetTransition()
    {
        assertEquals("[2, 1, 1]", map.getRoom(0).getNewRoom(5,5).toString());
        assertEquals(null, map.getRoom(0).getNewRoom(5, 1));
    }

}
