import me.lihq.game.models.Player;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by joeshuff on 22/11/2016.
 */
public class PlayerUnitTests {

    @Test
    public void testPlayerFunctionsCorrect()
    {
        Player p = new Player("Test Name");
        assertEquals("Fail - Not returning correct playername", p.getPlayername(), "Test Name");

        //Personality Level is default 0
        p.changePersonality(100);
        //Should have surpassed the maximum of 100. Then changed to the maximum, 100
        assertEquals("Fail - Personality not Upper Capped", p.getPersonality(), 100);

        p.changePersonality(-50);
        //Reduce back to 50
        assertEquals("Fail - Personality not reduced to 50", p.getPersonality(), 50);

        p.changePersonality(-100);
        //Gone below 0, should move it back up to 0
        assertEquals("Fail - Personality not Lower Capped", p.getPersonality(), 0);
    }

}
