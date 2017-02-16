package me.lihq.game.models;
import me.lihq.game.GameTester;
import me.lihq.game.people.Score;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScoreUnitTest extends GameTester {

    private Score testScore;


    @Before
    public void setUp() throws Exception {
        testScore = new Score();
    }

    @After
    public void tearDown() throws Exception {
        testScore = null;
    }


    @Test
    public void addPoints() throws Exception {
        assertNotNull(testScore.getPoints());

        testScore.addPoints(30);

        assertEquals(30, testScore.getPoints());
    }

    @Test
    public void subPoints() throws Exception {
        assertNotNull(testScore.getPoints());

        testScore.subPoints(50);

        assertEquals(-50, testScore.getPoints());
    }

    @Test
    public void failedAccusation() throws Exception {
        assertNotNull(testScore.getAccusation());

        testScore.failedAccusation();

        assertEquals(false, testScore.getAccusation());
    }

    @Test
    public void getPoints() throws Exception {
        assertNotNull(testScore.getPoints());

        testScore.addPoints(30);

        assertEquals(30, testScore.getPoints());

        testScore.subPoints(50);

        assertEquals(-20, testScore.getPoints());
    }

    @Test
    public void getAccusation() throws Exception {
        assertNotNull(testScore.getAccusation());

        testScore.failedAccusation();

        assertEquals(false, testScore.getAccusation());
    }

    @Test
    public void returnScore() throws Exception {
        assertNotNull(testScore.getPoints());
        assertNotNull(testScore.getAccusation());

        assertEquals(210, testScore.returnScore(-10));
    }


}