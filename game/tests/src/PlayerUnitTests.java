import me.lihq.game.Settings;
import me.lihq.game.people.Player;
import me.lihq.game.models.Room;
import org.junit.Before;
import org.junit.Test;

import static me.lihq.game.people.AbstractPerson.*;
import static org.junit.Assert.*;

/**
 * Created by joeshuff on 22/11/2016.
 */
public class PlayerUnitTests extends GameTester
{
    Player p = null;

    @Before
    public void before()
    {
        p = new Player("Test Name", "player.png", 0, 0);
        p.setRoom(new Room(0, "testMap.tmx", "Test Map"));
    }

    @Test
    public void testPlayername()
    {
        assertEquals("Fail - Not returning correct playername", p.getName(), "Test Name");
    }

    @Test
    public void testPlayerPersonality()
    {
        //Personality Level is default 50
        p.addToPersonality(100);
        //Should have surpassed the maximum of 100. Then changed to the maximum, 100
        assertEquals("Fail - Personality not Upper Capped", 100, p.getPersonalityLevel());
        assertEquals(Personality.NICE, p.getPersonality());

        p.addToPersonality(-50);
        //Reduce back to 50
        assertEquals("Fail - Personality not reduced to 50", 50, p.getPersonalityLevel());
        assertEquals(Personality.NEUTRAL, p.getPersonality());

        p.addToPersonality(-100);
        //Gone below 0, should move it back up to 0
        assertEquals("Fail - Personality not Lower Capped", 0, p.getPersonalityLevel());
        assertEquals(Personality.AGGRESSIVE, p.getPersonality());
    }


    /**
     * This tests if the move function is working correctly.
     * The player move function is in terms of tiles so an increase of 1 is actually 1 tile width.
     */
    @Test
    public void doesPlayerMove()
    {
        p.setTileCoordinates(0, 0);
        p.setAnimTime(0f);
        assertEquals(0, p.getX(), 0.0f);
        assertEquals(0, p.getY(), 0.0f);

        p.move(Direction.NORTH);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(Settings.TILE_SIZE, p.getY(), 0.0f);

        p.move(Direction.EAST);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(Settings.TILE_SIZE, p.getX(), 0.0f);
        assertEquals(Settings.TILE_SIZE, p.getY(), 0.0f);

        p.move(Direction.SOUTH);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(Settings.TILE_SIZE, p.getX(), 0.0f);
        assertEquals(0, p.getY(), 0.0f);

        p.move(Direction.WEST);
        p.update();
        p.pushCoordinatesToSprite();

        assertEquals(0, p.getX(), 0.0f);
        assertEquals(0, p.getY(), 0.0f);


    }

}
