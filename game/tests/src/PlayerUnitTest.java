import me.lihq.game.people.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nxn1 on 14/02/2017.
 */
public class PlayerUnitTest {
    Player testPlayer;


    @Before
    public void setUp() throws Exception {

        testPlayer = new Player(null ,null,null);
    }

    @After
    public void tearDown() throws Exception {
        testPlayer = null;
    }

    @Test
    public void addToTime() throws Exception {
        assertEquals(0, testPlayer.getTime());

        testPlayer.addToTime(5);

        assertEquals(5, testPlayer.getTime());
    }

    @Test
    public void addToPersonality() throws Exception {
        //todo add test properly once json sorted out

    }

    @Test
    public void interact() throws Exception {
        testPlayer.interact();
    }


    @Test
    public void getPersonality() throws Exception {
        //todo add test properly once json sorted out
    }

    @Test
    public void getPersonalityLevel() throws Exception {
        //todo add test properly once json sorted out
    }

    @Test
    public void getTime() throws Exception {
        assertNotNull(testPlayer.getTime());

        assertTrue(testPlayer.getTime() == 0);
    }
}