import me.lihq.game.people.AbstractPerson;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import org.junit.Before;
import org.junit.Test;

import static me.lihq.game.models.Room.*;
import static org.junit.Assert.*;

/**
 * Created by joeshuff on 26/11/2016.
 */
public class RoomUnitTests extends GameTester
{

    Room room0, room1;


    @Before
    public void before()
    {

        room0 = new Room(0, "testRoom0.tmx", "Test Room 0");
        room1 = new Room(1, "testRoom1.tmx", "Test Room 1");

        room0.addTransition(new Room.Transition().setFrom(0, 4).setTo(room1, 0,0, AbstractPerson.Direction.EAST));
    }

    @Test
    public void testGetTransition()
    {
        assertEquals(room1, room0.getTransitionData(0, 4).getNewRoom());
        assertEquals("Test Room 1", room0.getTransitionData(0, 4).getNewRoom().getName());
        assertEquals(new Vector2Int(0, 0), room0.getTransitionData(0, 4).newTileCoordinates);
        assertEquals(null, room0.getTransitionData(0, 0));
        assertEquals(AbstractPerson.Direction.EAST, room0.getTransitionData(0, 4).newDirection);
    }

    @Test
    public void testAddTransition()
    {
        room1.addTransition(new Transition().setFrom(0, 0).setTo(room0, 0, 4, AbstractPerson.Direction.NORTH));
        assertEquals(room0, room1.getTransitionData(0, 0).getNewRoom());
    }

    @Test
    public void testWalkable()
    {
        assertEquals(true, room0.isWalkableTile(0, 0));
        assertEquals(false, room0.isWalkableTile(0, 1));
        assertEquals(false, room0.isWalkableTile(-10, -5));
    }

    @Test
    public void testTrigger()
    {
        assertEquals(true, room0.isTriggerTile(0, 4));
        assertEquals(false, room0.isTriggerTile(3, 3));
    }

    @Test
    public void testMatRotation()
    {
        assertEquals("NORTH", room0.getMatRotation(0, 4));
        assertEquals("SOUTH", room1.getMatRotation(0, 0));
    }

}
