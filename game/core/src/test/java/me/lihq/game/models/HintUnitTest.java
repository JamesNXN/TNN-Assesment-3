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

public class HintUnitTest extends GameTester{
    private Clue testClue1, testClue2;
    private Hint testHint1;
    private Hint testHint2;


    @Before
    public void setUp() throws Exception {
        TextureAtlas clueGlint = new TextureAtlas(GameTester.ASSET_FOLDER + "clueGlint.pack");
        Json json = new Json();
        JsonValue clueJsonData = new JsonReader().parse(new FileHandle(GameTester.ASSET_FOLDER + "testClue.json"));
        Array<JsonValue> clueJsonDataArray = json.readValue(Array.class, clueJsonData);
        testClue1 = new Clue(clueJsonDataArray.get(0), clueGlint);
        testClue2 = new Clue(clueJsonDataArray.get(1), clueGlint);

        this.testHint1 = new Hint(testClue1);
        this.testHint2 = new Hint(testClue2);
    }

    @After
    public void tearDown() throws Exception {
        this.testHint1 = null;
        this.testClue1 = null;
        this.testHint2 = null;
        this.testClue2 = null;

    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        assertNotNull(testHint1.getRelatedNpcIdArray());
        /**
         * Test the elements are subsets of related  npc id array.
         * */
        for (int id : testHint1.getRelatedNpcIdArray()) {
            assertTrue("Hint should be subset of related Npc", testClue1.getRelatedNpcIdArray().contains(id, true));
        }


    }

    @Test
    public void getRelatedClue() throws Exception {
        assertNotNull(testHint1.getRelatedClue());
        assertEquals(testClue1, testHint1.getRelatedClue());


    }

    @Test
    public void combine() throws Exception {
        /**
         * test if the elements in different hints can be combined.
         */
        Array<Integer> test = new Array<>();
        test.addAll(testHint1.getRelatedNpcIdArray());
        test.addAll(testHint2.getRelatedNpcIdArray());
        testHint1.combine(testHint2);
        for (int id : test) {
            assertTrue("Combine Failed", testHint1.getRelatedNpcIdArray().contains(id, true));
        }


    }

}