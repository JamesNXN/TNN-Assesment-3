import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.NPC;
import me.lihq.game.models.Room;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by brookehatton on 02/01/2017.
 */
public class NPCUnitTests extends GameTester
{
    public NPC colin;
    public Room room;

    @Before
    public void makeNPC()
    {
        room = new Room(0, "testRoom0.tmx", "Test Room 0");
        colin = new NPC("bob", "colin.png", 1,1, room, false, "Colin.JSON");
    }

    @Test
    public void testGetName() {
        assertEquals("getting the name of the NPC failing", "bob", colin.getName());
    }

    @Test
    public void testPersonality() {
        assertEquals(AbstractPerson.Personality.NICE, colin.getPersonality());
    }


}

