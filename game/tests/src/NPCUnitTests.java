import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.people.AbstractPerson;
import me.lihq.game.people.NPC;
import me.lihq.game.models.Room;
import me.lihq.game.people.Personality;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NPCUnitTests extends GameTester
{
    public NPC testNPC;

    @Before
    public void makeNPC()
    {
        Json json = new Json();
        JsonValue npcJsonData = new JsonReader().parse(new FileHandle("testNPC.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        testNPC = new NPC(npcJsonDataArray.get(0));
    }

    @Test
    public void testGetName() {
        assertEquals("getting the name of the NPC failing", "testNPC1", testNPC.getName());
    }

    @Test
    public void testPersonality() {
        assertEquals(Personality.AGGRESSIVE, testNPC.getPersonality());
    }


}

