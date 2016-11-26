import me.lihq.game.models.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeshuff on 26/11/2016.
 */
public class RoomUnitTests {

    Map map;

    @Before
    public void before()
    {
        map = new Map();
    }

    @Test
    public void testGetTransition()
    {
        System.out.println(map.getRoom(0).getNewRoom(5,5).toString());
        System.out.println(map.getRoom(0).getNewRoom(5,1).toString());
    }

}
