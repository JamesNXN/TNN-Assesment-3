import me.lihq.game.models.Inventory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joeshuff on 23/11/2016.
 */
public class InventoryUnitTests
{

    Inventory inv;

    @Before
    public void before()
    {
        inv = new Inventory();
        inv.addItem(new Inventory.Item("Test Item", 1, 1));
    }

    @Test
    public void testHasItem()
    {
        assertEquals("Fail - Cannot Find Inventory Item by Name", inv.hasItem("Test Item"), true);
        assertEquals("Fail - Cannot Find Inventory Item by Item", inv.hasItem(new Inventory.Item("Test Item", 1, 1)), true);
    }

}
