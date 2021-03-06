package me.lihq.game.models;
import me.lihq.game.GameTester;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScoreUnitTest extends GameTester {

    private Score testScore;


    @Before
    public void setUp() throws Exception {
        testScore = Score.getInstance();
        testScore.reset();
    }

    @After
    public void tearDown() throws Exception {
        testScore.reset();
    }


    @Test
    public void addPoints() throws Exception {
        assertNotNull(testScore.getTargetScore());

        testScore.addPoints(30);

        assertEquals(30, testScore.getTargetScore());
    }

    @Test
    public void subPoints() throws Exception {
        assertNotNull(testScore.getTargetScore());

        testScore.subPoints(50);

        assertEquals(-50, testScore.getTargetScore());
    }

    @Test
    public void getPoints() throws Exception {
        assertNotNull(testScore.getCurrentScore());

        testScore.addPoints(30);

        assertEquals(30, testScore.getTargetScore());
    }

    @Test
    public void returnFinalScore() throws Exception {
        assertNotNull(testScore.getCurrentScore());
        testScore.addPoints(100);
        assertEquals(97, testScore.getFinalScore(10));
    }

    @Test
    public void getFailedAccusationCount() throws Exception {
        testScore.failedAccusation();

        assertEquals(1, testScore.getFailedAccusationCount());
    }
}