package me.lihq.game.people;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.GameTester;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Inventory;
import me.lihq.game.models.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerUnitTest extends GameTester {
    private Player testPlayer;
    private Npc testNpc;
    private Clue testClue;


    @Before
    public void setUp() throws Exception {
        JsonValue jsonData = new JsonReader().parse(new FileHandle(GameTester.ASSET_FOLDER + "testPlayer.json"));
        TextureAtlas testSprite = new TextureAtlas(GameTester.ASSET_FOLDER + "colin.pack");
        testPlayer = new Player(jsonData, testSprite);
        testPlayer.setCurrentRoom(Mockito.mock(Room.class));

        Json json = new Json();
        JsonValue npcJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSET_FOLDER + "testNPC.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        testNpc = new Npc(npcJsonDataArray.get(0), testSprite);

        TextureAtlas clueGlint = new TextureAtlas(GameTester.ASSET_FOLDER + "clueGlint.pack");
        JsonValue clueJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSET_FOLDER + "testClue.json"));
        Array<JsonValue> clueJsonDataArray = json.readValue(Array.class, clueJsonData);
        testClue = new Clue(clueJsonDataArray.get(0), clueGlint);
    }

    @After
    public void tearDown() throws Exception {
        testPlayer = null;
    }


    @Test
    public void getPersonality() throws Exception {
        assertNotNull(testPlayer.getPersonality());
        assertEquals("test player does not have expected personality", Personality.NEUTRAL, testPlayer.getPersonality());

        testPlayer.getPersonalityMeter().setMeter(QuestionStyle.AGGRESSIVE);
        assertEquals("test player does not have expected personality", Personality.AGGRESSIVE, testPlayer.getPersonality());

        testPlayer.getPersonalityMeter().setMeter(QuestionStyle.NICE);
        testPlayer.getPersonalityMeter().setMeter(QuestionStyle.NICE);
        assertEquals("test player does not have expected personality", Personality.NICE, testPlayer.getPersonality());
    }

    @Test
    public void getInventory() throws Exception{
        assertNotNull(testPlayer.getInventory());
        assertTrue(testPlayer.getInventory() instanceof Inventory);
    }

}