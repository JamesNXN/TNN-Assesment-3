package me.lihq.game.models;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import me.lihq.game.GameTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClueUnitTest extends GameTester {

    private Clue testClue1;
    private Clue testClue2;

    @Before
    public void setUp() throws Exception {
        TextureAtlas clueGlint = new TextureAtlas(GameTester.ASSEST_FOLDER + "clueGlint.pack");
        Json json = new Json();
        JsonValue clueJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testClue.json"));
        Array<JsonValue> clueJsonDataArray = json.readValue(Array.class, clueJsonData);
        testClue1 = new Clue(clueJsonDataArray.get(0), clueGlint);
        testClue2 = new Clue(clueJsonDataArray.get(1), clueGlint);
    }

    @After
    public void tearDown() throws Exception {
        this.testClue1 = null;
        this.testClue2 = null;

    }


    /**
     * test equals only except same clue.
     */
    @Test
    public void equals() throws Exception {
        assertFalse("Should not be equal", testClue1.equals(testClue2));
        Clue testClue3 = testClue2;
        assertTrue("Should be equal", testClue2.equals(testClue3));
    }

    @Test
    public void getName() throws Exception {
        assertNotNull(testClue1.getName());
        System.out.println(testClue1.getName());
        assertEquals("name doesn't fit", "testClue1", testClue1.getName());

    }

    @Test
    public void getDescription() throws Exception {
        assertNotNull(testClue1.getDescription());
        assertEquals("name doesn't fit", "description1", testClue1.getDescription());


    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        assertNotNull(testClue1.getRelatedNpcIdArray());
        Array<Integer> test = new Array<>(new Integer[]{1, 2, 3});
        assertTrue("ID array doesn't fit", testClue1.getRelatedNpcIdArray().equals(test));

    }

    @Test
    public void isFound() throws Exception {
        assertFalse(testClue1.isFound());
    }

    @Test
    public void setFound() throws Exception {
        assertFalse(testClue1.isFound());

        testClue1.setFound(true);

        assertTrue(testClue1.isFound());
    }

    /**
     * Test if the setTilePosition has set the collisionBox position
     */

    @Test
    public void setTilePosition() throws Exception {
        testClue1.setTilePosition(1, 2);
        assertEquals(new Vector2Int(1, 2), testClue1.getTilePosition());
    }

    /**
     * test if the getTilPostition equals to what has been set.
     */
    @Test
    public void getTilePosition() throws Exception {
        assertNotNull(testClue1.getTilePosition());
        Vector2Int testTileCoordinates = new Vector2Int(1, 2);
        testClue1.setTilePosition(1, 2);
        assertEquals(testTileCoordinates, testClue1.getTilePosition());
    }

    @Test
    public void getCollisionBox() throws Exception {
        assertNotNull(testClue1.getCollisionBox());
    }

}