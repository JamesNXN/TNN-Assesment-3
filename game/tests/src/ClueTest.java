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
 * Created by PPPPPP on 2017/2/12.
 */
public class ClueTest {

    private Clue cluetest;
    private Clue clue2;

    @Before
    public void setUp() throws Exception {
        this.cluetest = new Clue();
        this.clue2 = new Clue("test2");
    }

    @After
    public void tearDown() throws Exception {
        this.cluetest = null;
        this.clue2 = null;
    }


    /**
     *test equals only except same clue.
     */
    @Test
    public void equals() throws Exception {
        assertTrue("Should not be equal",!cluetest.equals(clue2));
        clue2 = new Clue();
        assertTrue("Should be equal",cluetest.equals(clue2));
        String teststring = "String";
        assertTrue("Should not be equal",!cluetest.equals(teststring));
    }

    @Test
    public void getName() throws Exception {
        System.out.println(cluetest.getName());
        assertEquals("name doesn't fit","test",cluetest.getName());

    }

    @Test
    public void getDescription() throws Exception {
        assertEquals("name doesn't fit","test",cluetest.getDescription());


    }

    @Test
    public void getRelatedNpcIdArray() throws Exception {
        assertNotNull(cluetest.getRelatedNpcIdArray());
        Array<Integer> test = new Array<>(new Integer[]{1,2,3,4});
        assertTrue("ID array doesn't fit",cluetest.getRelatedNpcIdArray().equals(test));

    }

    /**
     * Test if the setTilePosition has set the collisionBox position
     */

    @Test
    public void setTilePosition() throws Exception {
        cluetest.setTilePosition(1,2);
        Rectangle  testbox= new Rectangle(0,0,0,0);
        assertNotEquals(testbox,cluetest.getCollisionBox());

    }

    /**
     * test if the getTilPostition equals to what has been set.
     */
    @Test
    public void getTilePosition() throws Exception{
        Vector2Int testtileCoordinates = new Vector2Int(1,2);
        cluetest.setTilePosition(1,2);
        assertEquals(testtileCoordinates,cluetest.getTilePosition());
    }

    @Test
    public void getCollisionBox() throws Exception {
        assertNotNull(cluetest.getCollisionBox());

    }

}