package me.lihq.game.models;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.GameTester;
import me.lihq.game.people.NPC;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomUnitTests extends GameTester
{

    private Room testRoom1;
    private Room testRoom2;
    private Clue testClue;
    private NPC testNpc;

    @Before
    public void setUp() throws Exception {
        TextureAtlas arrows = new TextureAtlas(GameTester.ASSEST_FOLDER + "arrows.pack");
        TiledMap testMap1 = new TmxMapLoader().load(GameTester.ASSEST_FOLDER + "testRoom1.tmx");
        testRoom1 = new Room(testMap1, arrows);

        TiledMap testMap2 = new TmxMapLoader().load(GameTester.ASSEST_FOLDER + "testRoom2.tmx");
        testRoom2 = new Room(testMap2, arrows);

        TextureAtlas clueGlint = new TextureAtlas(GameTester.ASSEST_FOLDER + "clueGlint.pack");
        Json json = new Json();
        JsonValue clueJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testClue.json"));
        Array<JsonValue> clueJsonDataArray = json.readValue(Array.class, clueJsonData);
        testClue = new Clue(clueJsonDataArray.get(0), clueGlint);

        JsonValue npcJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testNPC.json"));
        Array<JsonValue> npcJsonDataArray = json.readValue(Array.class, npcJsonData);
        TextureAtlas testSprite = new TextureAtlas(GameTester.ASSEST_FOLDER +"colin.pack");
        testNpc = new NPC(npcJsonDataArray.get(0), testSprite);
    }

    @After
    public void tearDown() throws Exception {
        testRoom1 = null;
        testRoom2 = null;
        testClue = null;
        testNpc = null;
    }

    @Test
    public void testWalkable() throws Exception{
        assertTrue(testRoom1.isWalkableTile(1,1));
        assertTrue(testRoom1.isWalkableTile(3,3));
        assertFalse(testRoom1.isWalkableTile(0,0));
        assertFalse(testRoom1.isWalkableTile(1,2));

        assertTrue(testRoom2.isWalkableTile(1,1));
        assertTrue(testRoom2.isWalkableTile(3,3));
        assertFalse(testRoom2.isWalkableTile(0,0));
        assertFalse(testRoom2.isWalkableTile(1,4));
    }

    @Test
    public void getHidingSpots() throws Exception{
        assertTrue(testRoom1.getHidingSpots().contains(testRoom1.getRandHidingSpot(),false));
        assertTrue(testRoom2.getHidingSpots().contains(testRoom2.getRandHidingSpot(),false));
    }

    @Test
    public void equals() throws Exception{
        assertNotNull(testRoom1.equals(testRoom1));

        assertTrue(testRoom1.equals(testRoom1));

        assertFalse(testRoom1.equals(testRoom2));
        assertFalse(testRoom1.equals(testClue));
    }

    @Test
    public void setMurderRoom() throws Exception {
        assertFalse(testRoom1.isMurderRoom());

        testRoom1.setMurderRoom(true);

        assertTrue(testRoom1.isMurderRoom());
    }

    @Test
    public void isMurderRoom() throws Exception {
        assertNotNull(testRoom1.isMurderRoom());

        assertFalse(testRoom1.isMurderRoom());
    }

    @Test
    public void getID() throws Exception {
        assertNotNull(testRoom1.getID());
        assertEquals("Wrong Id for room", 100, testRoom1.getID());

    }

    @Test
    public void getName() throws Exception {
        assertNotNull(testRoom1.getName());
        assertEquals("Wrong name for room", "Test Room 1", testRoom1.getName());
    }

    @Test
    public void addClue() throws Exception {
        assertTrue(testRoom1.getClueArray().size == 0);

        testRoom1.addClue(testClue);

        assertTrue(testRoom1.getClueArray().contains(testClue,true));
    }

    @Test
    public void addNPC() throws Exception {
        assertTrue(testRoom1.getNpcArray().size == 0);

        testRoom1.addNPC(testNpc);

        assertTrue(testRoom1.getNpcArray().contains(testNpc,true));
    }

    @Test
    public void getRandomLocation() throws Exception {
        assertNotNull(testRoom1.getRandomLocation());

        assertTrue(testRoom1.getRandomLocation() instanceof Vector2Int);
    }

}
