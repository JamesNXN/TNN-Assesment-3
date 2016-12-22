import me.lihq.game.models.Map;
import me.lihq.game.models.Vector2Int;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

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
        assertEquals(2, map.getRoom(0).getNewRoom(17, 17).newRoom);
        assertEquals(new Vector2Int(1, 5), map.getRoom(0).getNewRoom(17, 17).to);
        assertEquals(null, map.getRoom(0).getNewRoom(5, 1));
    }

}
