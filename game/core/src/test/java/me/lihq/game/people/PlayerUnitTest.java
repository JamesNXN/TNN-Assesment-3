package me.lihq.game.people;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import me.lihq.game.GameMain;
import me.lihq.game.GameTester;
import me.lihq.game.models.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerUnitTest extends GameTester {
    Player testPlayer;
    GameMain main;


    @Before
    public void setUp() throws Exception {
        JsonValue jsonData = new JsonReader().parse(new FileHandle(GameTester.ASSEST_FOLDER + "testPlayer.json"));
        testPlayer = new Player(jsonData);
        testPlayer.setCurrentRoom(Mockito.mock(Room.class));
    }

    @After
    public void tearDown() throws Exception {
        main = null;
        testPlayer = null;
    }

    @Test
    public void addToPersonality() throws Exception {
        //todo add test properly once json sorted out

    }

    @Test
    public void getPersonality() throws Exception {
        //todo add test properly once json sorted out
    }

    @Test
    public void getPersonalityLevel() throws Exception {
        //todo add test properly once json sorted out
    }
}