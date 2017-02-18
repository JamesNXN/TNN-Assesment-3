package me.lihq.game.people;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.GameTester;
import me.lihq.game.models.Clue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NPCUnitTests extends GameTester {
    private NPC testNPC;
    private Clue testClue;

    @Before
    public void setUp() throws Exception {
        Json json = new Json();
        JsonValue npcJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testNPC.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        TextureAtlas testSprite = new TextureAtlas(GameTester.ASSEST_FOLDER + "colin.pack");

        TextureAtlas clueGlint = new TextureAtlas(GameTester.ASSEST_FOLDER + "clueGlint.pack");
        JsonValue clueJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testClue.json"));
        Array<JsonValue> clueJsonDataArray = json.readValue(Array.class, clueJsonData);

        testClue = new Clue(clueJsonDataArray.get(0), clueGlint);

        testNPC = new NPC(npcJsonDataArray.get(0), testSprite);
    }

    @After
    public void tearDown() throws Exception {
        testNPC = null;
    }

    @Test
    public void getName() throws Exception{
        assertNotNull(testNPC.getName());
        assertEquals("getting the name of the NPC failing", "testNPC1", testNPC.getName());
    }

    @Test
    public void getPersonality() throws Exception{
        assertNotNull(testNPC.getPersonality());
        assertEquals(Personality.AGGRESSIVE, testNPC.getPersonality());
    }

    @Test
    public void isKiller() throws Exception {
        assertNotNull(testNPC.isKiller());
        assertFalse(testNPC.isKiller());
    }

    @Test
    public void setKiller() throws Exception {
        assertFalse(testNPC.isKiller());

        testNPC.setKiller(true);

        assertTrue(testNPC.isKiller());
    }

    @Test
    public void isVictim() throws Exception {
        assertNotNull(testNPC.isVictim());
        assertFalse(testNPC.isVictim());
    }

    @Test
    public void setVictim() throws Exception {
        assertFalse(testNPC.isVictim());

        testNPC.setVictim(true);

        assertTrue(testNPC.isVictim());
    }

    @Test
    public void getExhaustedClues() throws Exception {
        assertNotNull(testNPC.getExhaustedClues());
        assertTrue(testNPC.getExhaustedClues().size == 0);
    }

    @Test
    public void addExhaustedClue() throws Exception {
        assertTrue(testNPC.getExhaustedClues().size == 0);

        testNPC.addExhaustedClue(testClue);

        assertTrue(testNPC.getExhaustedClues().contains(testClue,true) && testNPC.getExhaustedClues().size == 1);
    }

    @Test
    public void getId() throws Exception {
        assertNotNull(testNPC.getId());
        assertEquals("id is not correct", 1, testNPC.getId());
    }

    @Test
    public void getDescription() throws Exception {
        assertNotNull(testNPC.getDescription());
        assertEquals("description is not correct", "test description", testNPC.getDescription());
    }

    @Test
    public void setFalselyAccused() throws Exception {
        assertFalse(testNPC.getFalselyAccused());

        testNPC.setFalselyAccused();

        assertTrue(testNPC.getFalselyAccused());
    }

    @Test
    public void getFalselyAccused() throws Exception {
        assertNotNull(testNPC.getFalselyAccused());
        assertFalse(testNPC.getFalselyAccused());
    }
}

