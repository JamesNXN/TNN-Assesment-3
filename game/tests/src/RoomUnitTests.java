import me.lihq.game.GameMain;
import me.lihq.game.living.AbstractPerson;
import me.lihq.game.living.Player;
import me.lihq.game.models.Map;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static me.lihq.game.models.Room.*;
import static org.junit.Assert.*;

/**
 * Created by joeshuff on 26/11/2016.
 */
public class RoomUnitTests extends GameTester
{

    Map map;
    Player p;

    @Before
    public void before()
    {
        map = new Map();
        p = new Player("name", "player.png");
    }

    @Test
    public void testGetTransition()
    {
        assertEquals(2, map.getRoom(0).getNewRoom(17, 17).newRoom);
        assertEquals(new Vector2Int(1, 5), map.getRoom(0).getNewRoom(17, 17).to);
        assertEquals(null, map.getRoom(0).getNewRoom(5, 1));
    }

    @Test
    public void testAddTransition()
    {
        map.getRoom(0).addTransition(new Transition().setFrom(5, 5).setTo(1, 5, 5, AbstractPerson.Direction.EAST));
        assertEquals(1, map.getRoom(0).getNewRoom(5, 5).newRoom);
    }

    @Test
    public void testWalkable()
    {
        assertEquals(true, map.getRoom(0).isWalkableTile(21, 20));
        assertEquals(false, map.getRoom(0).isWalkableTile(20, 20));
    }

    @Test
    public void testTrigger()
    {
        assertEquals(true, map.getRoom(0).isTriggerTile(11, 1));
        assertEquals(false, map.getRoom(0).isTriggerTile(11, 2));
    }

    @Test
    public void testMapRotation()
    {
        assertEquals("SOUTH", map.getRoom(0).getMatRotation(11, 1));
        assertEquals("EAST", map.getRoom(0).getMatRotation(18, 2));
    }

    @Test
    public void testHasTransition()
    {
        assertEquals(null, map.getRoom(0).getNewRoom(17, 18));
    }
}
