import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import me.lihq.game.people.AbstractPerson;
import me.lihq.game.models.Room;
import me.lihq.game.models.Vector2Int;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class RoomUnitTests extends GameTester
{

    Room testRoom1, testRoom2;

    @Before
    public void before()
    {
        TiledMap testMap1 = new TmxMapLoader().load("testRoom1.tmx");
        testRoom1 = new Room(testMap1);

        TiledMap testMap2 = new TmxMapLoader().load("testRoom2.tmx");
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
}
