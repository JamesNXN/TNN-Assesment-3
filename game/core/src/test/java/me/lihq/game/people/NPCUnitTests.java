package me.lihq.game.people;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.GameTester;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Vector2Int;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NPCUnitTests extends GameTester {
    private NPC testNpc;
    private Clue testClue;

    @Before
    public void setUp() throws Exception {
        Json json = new Json();
        JsonValue npcJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testNpc.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        TextureAtlas testSprite = new TextureAtlas(GameTester.ASSEST_FOLDER + "colin.pack");

        TextureAtlas clueGlint = new TextureAtlas(GameTester.ASSEST_FOLDER + "clueGlint.pack");
        JsonValue clueJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testClue.json"));
        Array<JsonValue> clueJsonDataArray = json.readValue(Array.class, clueJsonData);

        testClue = new Clue(clueJsonDataArray.get(0), clueGlint);

        testNpc = new NPC(npcJsonDataArray.get(0), testSprite);
    }

    @After
    public void tearDown() throws Exception {
        testNpc = null;
    }

    @Test
    public void getName() throws Exception{
        assertNotNull(testNpc.getName());
        assertEquals("getting the name of the NPC failing", "testNPC1", testNpc.getName());
    }

    @Test
    public void getPersonality() throws Exception{
        assertNotNull(testNpc.getPersonality());
        assertEquals(Personality.AGGRESSIVE, testNpc.getPersonality());
    }

    @Test
    public void isKiller() throws Exception {
        assertNotNull(testNpc.isKiller());
        assertFalse(testNpc.isKiller());
    }

    @Test
    public void setKiller() throws Exception {
        assertFalse(testNpc.isKiller());

        testNpc.setKiller(true);

        assertTrue(testNpc.isKiller());
    }

    @Test
    public void isVictim() throws Exception {
        assertNotNull(testNpc.isVictim());
        assertFalse(testNpc.isVictim());
    }

    @Test
    public void setVictim() throws Exception {
        assertFalse(testNpc.isVictim());

        testNpc.setVictim(true);

        assertTrue(testNpc.isVictim());
    }

    @Test
    public void getExhaustedClues() throws Exception {
        assertNotNull(testNpc.getExhaustedClues());
        assertTrue(testNpc.getExhaustedClues().size == 0);
    }

    @Test
    public void addExhaustedClue() throws Exception {
        assertTrue(testNpc.getExhaustedClues().size == 0);

        testNpc.addExhaustedClue(testClue);

        assertTrue(testNpc.getExhaustedClues().contains(testClue,true) && testNpc.getExhaustedClues().size == 1);
    }

    @Test
    public void getId() throws Exception {
        assertNotNull(testNpc.getId());
        assertEquals("id is not correct", 1, testNpc.getId());
    }

    @Test
    public void getDescription() throws Exception {
        assertNotNull(testNpc.getDescription());
        assertEquals("description is not correct", "test description", testNpc.getDescription());
    }

    @Test
    public void setFalselyAccused() throws Exception {
        assertFalse(testNpc.getFalselyAccused());

        testNpc.setFalselyAccused();

        assertTrue(testNpc.getFalselyAccused());
    }

    @Test
    public void getFalselyAccused() throws Exception {
        assertNotNull(testNpc.getFalselyAccused());
        assertFalse(testNpc.getFalselyAccused());
    }

    @Test
    public void getTilePosition() throws Exception {
        assertNotNull(testNpc.getTilePosition());
        assertTrue(testNpc.getTilePosition() instanceof Vector2Int);
    }

    @Test
    public void setTilePosition() throws Exception {
        assertTrue(testNpc.getTilePosition().getX() == 0 && testNpc.getTilePosition().getY() == 0);
        testNpc.setTilePosition(1,1);
        assertTrue(testNpc.getTilePosition().getX()==1 && testNpc.getTilePosition().getY()==1);
    }
}

