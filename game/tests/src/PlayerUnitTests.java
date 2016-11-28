import me.lihq.game.Settings;
import me.lihq.game.living.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeshuff on 22/11/2016.
 */
public class PlayerUnitTests extends GameTest
{

    Player p = null;

    @Before
    public void before()
    {
        p = new Player("Test Name", "../core/assets/player.png");
    }

    @Test
    public void testPlayername()
    {
        assertEquals("Fail - Not returning correct playername", p.getPlayername(), "Test Name");
    }

    @Test
    public void testPlayerPersonality()
    {
        //Personality Level is default 50
        p.addToPersonality(100);
        //Should have surpassed the maximum of 100. Then changed to the maximum, 100
        assertEquals("Fail - Personality not Upper Capped", p.getPersonality(), 100);

        p.addToPersonality(-50);
        //Reduce back to 50
        assertEquals("Fail - Personality not reduced to 50", p.getPersonality(), 50);

        p.addToPersonality(-100);
        //Gone below 0, should move it back up to 0
        assertEquals("Fail - Personality not Lower Capped", p.getPersonality(), 0);
    }


    /**
     * This tests if the move function is working correctly.
     * The player move function is in terms of tiles so an increase of 1 is actually 1 tile width.
     */
    @Test
    public void doesPlayerMove()
    {
        p.setPosition(0,0);
        p.move(1,1);
        assertEquals(p.getX(), Settings.TILE_SIZE, 0.0f);
        assertEquals(p.getY(), Settings.TILE_SIZE, 0.0f);

        p.move(1,1);
        assertEquals(p.getX(), 2*Settings.TILE_SIZE, 0.0f);
        assertEquals(p.getY(), 2*Settings.TILE_SIZE, 0.0f);

        p.move(-2,-2);
        assertEquals(p.getX(), 0f, 0.0f);
        assertEquals(p.getY(), 0f, 0.0f);
    }

}
