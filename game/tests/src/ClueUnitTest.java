import me.lihq.game.Assets;
import me.lihq.game.Settings;
import me.lihq.game.models.Clue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by brookehatton on 02/01/2017.
 */
public class ClueUnitTest extends GameTester
{
    public Clue shoe, book, glasses;
    @Before
    public void createClues()
    {
        Assets.load();
        //TODO: use correct assets
        shoe = new Clue("Shoe", "I am a shoe", Assets.getArrowDirection("NORTH"));
        book = new Clue("book", "I am a book", Assets.getArrowDirection("NORTH"));
        glasses = new Clue("glasses", "I am a pair of glasses", Assets.getArrowDirection("NORTH"));
    }

    @Test
    public void testName()
    {
        assertEquals("Name not what was expected", "Shoe", shoe.getName());
        assertEquals("Name not what was expected", "book", book.getName());
        assertEquals("Name not what was expected", "glasses", glasses.getName());
    }

    @Test
    public void testDescription()
    {
        assertEquals("Description not what was expected", "I am a shoe", shoe.getDescription());
        assertEquals("Description not what was expected", "I am a book", book.getDescription());
        assertEquals("Description not what was expected", "I am a pair of glasses", glasses.getDescription());
    }

    @Test
    public void testTileCoordinates()
    {
        shoe.setTileCoordinates(10, 20);
        assertEquals("tile coordinate x not set correctly", 10, shoe.getTileX());
        assertEquals("tile coordinate y not set correctly", 20, shoe.getTileY());
        assertEquals("position coordinate x not being updated from the tile coordiantes", 10 * Settings.TILE_SIZE, shoe.getX(), 0f);
        assertEquals("position coordinate y not being updated from the tile coordiantes", 20 * Settings.TILE_SIZE, shoe.getY(), 0f);
    }

    @Test
    public void testEquality() {
        Clue shoe2 = shoe;
        assertEquals("Equality test failing", shoe, shoe2);
        assertNotEquals("Equality test always true", shoe, book);
    }


}
