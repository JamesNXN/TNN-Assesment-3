import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import me.lihq.game.Settings;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Vector2Int;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 2017/2/12.Clue Unit Test
 */
public class ClueUnitTest {

    private Clue clueTest;
    private Clue clue2;

    @Before
    public void setUp() throws Exception {
        this.clueTest = new Clue();
        this.clue2 = new Clue("test2");
    }

    @After
    public void tearDown() throws Exception {
        this.clueTest = null;
        this.clue2 = null;
    }


    /**
     *test equals only except same clue.
     */
    @Test
    public void equals() throws Exception {
        assertTrue("Should not be equal",!clueTest.equals(clue2));
        clue2 = new Clue();
        assertTrue("Should be equal", clueTest.equals(clue2));
        String teststring = "String";
        assertTrue("Should not be equal",!clueTest.equals(teststring));
    }

    @Test
    public void getName() throws Exception {
        assertNotNull(clueTest.getName());
        System.out.println(clueTest.getName());
        assertEquals("name doesn't fit","test", clueTest.getName());

    }

    @Test
    public void getDescription() throws Exception {
        assertNotNull(clueTest.getDescription());
        assertEquals("name doesn't fit","test", clueTest.getDescription());


    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        assertNotNull(clueTest.getRelatedNpcIdArray());
        Array<Integer> test = new Array<>(new Integer[]{1,2,3,4});
        assertTrue("ID array doesn't fit", clueTest.getRelatedNpcIdArray().equals(test));

    }

    /**
     * Test if the setTilePosition has set the collisionBox position
     */

    @Test
    public void setTilePosition() throws Exception {
        clueTest.setTilePosition(1,2);
        Rectangle  testbox= new Rectangle(0,0,0,0);
        assertNotEquals(testbox, clueTest.getCollisionBox());

    }

    /**
     * test if the getTilPostition equals to what has been set.
     */
    @Test
    public void getTilePosition() throws Exception{
        assertNotNull(clueTest.getTilePosition());
        Vector2Int testtileCoordinates = new Vector2Int(1,2);
        clueTest.setTilePosition(1,2);
        assertEquals(testtileCoordinates, clueTest.getTilePosition());
    }

    @Test
    public void getCollisionBox() throws Exception {
        /**
         * Base case
         */
        assertNotNull(clueTest.getCollisionBox());

        Rectangle  testbox= new Rectangle();
        assertEquals("box0000 not equal",testbox.setSize(Settings.TILE_SIZE), clueTest.getCollisionBox());
        clueTest.setTilePosition(1,2);

        assertNotEquals("collisionBox size not changed",testbox, clueTest.getCollisionBox());

    }

}