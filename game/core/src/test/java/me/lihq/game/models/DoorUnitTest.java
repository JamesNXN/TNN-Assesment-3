import me.lihq.game.GameTester;
import me.lihq.game.models.Door;
import me.lihq.game.models.Vector2Int;
import me.lihq.game.people.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoorUnitTest extends GameTester {

    private Door testDoor;
    private Vector2Int testVec;


    @Before
    public void setUp() throws Exception {
        testDoor = new Door(Direction.EAST, 1, new Vector2Int(0, 2));
        testVec = new Vector2Int();   //// (0,0) by default
    }

    @After
    public void tearDown() throws Exception {
        testDoor = null;
        testVec = null;
    }


    @Test
    public void getCollisionBox() throws Exception {
        assertNotNull(testDoor.getCollisionBox());
    }

    @Test
    public void getTilePosition() throws Exception {
        assertNotNull(testDoor.getTilePosition());
        assertNotEquals(testDoor.getTilePosition(), testVec);
    }

    @Test
    public void getDirection() throws Exception {
        assertNotNull(testDoor.getDirection());
    }

    @Test
    public void getConnectedRoomId() throws Exception {
        assertNotNull(testDoor.getConnectedRoomId());
    }

    @Test
    public void setTilePosition() throws Exception {
        testDoor.setTilePosition(0, 0);
        assertNotNull(testDoor.getTilePosition());
        assertEquals(testDoor.getTilePosition(), testVec);
    }


}