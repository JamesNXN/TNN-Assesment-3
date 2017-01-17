import me.lihq.game.living.NPC;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by brookehatton on 02/01/2017.
 */
public class NPCUnitTests extends GameTester
{
    public NPC bob;

    @Before
    public void makeNPC()
    {
        bob = new NPC("bob", "player.png", 1,1,1, false);
    }

    @Test
    public void testGetName() {
        assertEquals("getting the name of the NPC failing", "bob", bob.getName());
    }




}
