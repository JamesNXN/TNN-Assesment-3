import me.lihq.game.models.Map;
import me.lihq.game.models.Vector2Int;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeshuff on 26/11/2016.
 */
public class RoomUnitTests extends GameTester
{

    Map map;

    @Before
    public void before()
    {
        map = new Map();
    }

    @Test
    public void testGetTransition()
    {
        assertEquals(1, map.getRoom(0).getNewRoom(5,5).newRoom);
        assertEquals(new Vector2Int(2, 2), map.getRoom(0).getNewRoom(5,5).to);
        assertEquals(null, map.getRoom(0).getNewRoom(5, 1));
    }

}
