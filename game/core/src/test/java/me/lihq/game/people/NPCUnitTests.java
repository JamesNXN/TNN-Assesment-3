package me.lihq.game.people;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.GameTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NPCUnitTests extends GameTester {
    private NPC testNPC;

    @Before
    public void setUp() throws Exception {
        Json json = new Json();
        JsonValue npcJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testNPC.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        testNPC = new NPC(npcJsonDataArray.get(0));
    }

    @After
    public void tearDown() throws Exception {
        testNPC = null;
    }

    @Test
    public void getName() {
        assertEquals("getting the name of the NPC failing", "testNPC1", testNPC.getName());
    }

    @Test
    public void getPersonality() {
        assertEquals(Personality.AGGRESSIVE, testNPC.getPersonality());
    }


}

