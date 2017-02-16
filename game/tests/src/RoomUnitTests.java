import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import org.junit.Before;
import org.junit.Test;

import me.lihq.game.models.Room;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomUnitTests extends GameTester
{

    private Room testRoom1, testRoom2;

    @Before
    public void before()
    {
        TiledMap testMap1 = new TmxMapLoader().load("assets/testRoom1.tmx");
        testRoom1 = new Room(testMap1);

        TiledMap testMap2 = new TmxMapLoader().load("assets/testRoom2.tmx");
        testRoom2 = new Room(testMap2);
    }

    @Test
    public void testWalkable()
    {
        assertTrue(testRoom1.isWalkableTile(1,1));
        assertTrue(testRoom1.isWalkableTile(3,3));
        assertFalse(testRoom1.isWalkableTile(0,0));
        assertFalse(testRoom1.isWalkableTile(1,2));

        assertTrue(testRoom2.isWalkableTile(1,1));
        assertTrue(testRoom2.isWalkableTile(3,3));
        assertFalse(testRoom2.isWalkableTile(0,0));
        assertFalse(testRoom2.isWalkableTile(1,4));
    }

    @Test
    public void getHidingSpots() {
        assertTrue(testRoom1.getHidingSpots().contains(testRoom1.getRandHidingSpot(),false));
        assertTrue(testRoom2.getHidingSpots().contains(testRoom2.getRandHidingSpot(),false));
    }
}
