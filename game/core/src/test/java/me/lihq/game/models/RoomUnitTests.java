package me.lihq.game.models;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import me.lihq.game.GameTester;
import me.lihq.game.models.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomUnitTests extends GameTester
{

    private Room testRoom1;
    private Room testRoom2;

    @Before
    public void setUp() throws Exception {
        TiledMap testMap1 = new TmxMapLoader().load("assetLoader/testRoom1.tmx");
        testRoom1 = new Room(testMap1);

        TiledMap testMap2 = new TmxMapLoader().load("assetLoader/testRoom2.tmx");
        testRoom2 = new Room(testMap2);
    }

    @After
    public void tearDown() throws Exception {
        testRoom1 = null;
        testRoom2 = null;
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
