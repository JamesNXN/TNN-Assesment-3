//import me.lihq.game.Assets;
//import me.lihq.game.GameMain;
//import me.lihq.game.people.Player;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// * Created by nxn1 on 14/02/2017.
// */
//public class PlayerUnitTest extends GameTester {
//    Player testPlayer;
//    GameMain main;
//
//
//    @Before
//    public void setUp() throws Exception {
//        main = new GameMain();
//        testPlayer = new Player(main.assets.playerJsonData , main.assets.playerSpriteSheet, main);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        main = null;
//        testPlayer = null;
//    }
//
//    @Test
//    public void addToTime() throws Exception {
//        assertEquals(0, testPlayer.getTime());
//
//        testPlayer.addToTime(5);
//
//        assertEquals(5, testPlayer.getTime());
//    }
//
//    @Test
//    public void addToPersonality() throws Exception {
//        //todo add test properly once json sorted out
//
//    }
//
//    @Test
//    public void interact() throws Exception {
//        testPlayer.interact();
//    }
//
//
//    @Test
//    public void getPersonality() throws Exception {
//        //todo add test properly once json sorted out
//    }
//
//    @Test
//    public void getPersonalityLevel() throws Exception {
//        //todo add test properly once json sorted out
//    }
//
//    @Test
//    public void getTime() throws Exception {
//        assertNotNull(testPlayer.getTime());
//
//        assertTrue(testPlayer.getTime() == 0);
//    }
//}