package me.lihq.game;

import me.lihq.game.people.QuestionStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for PersonalityMeter
 */
public class PersonalityMeterUnitTest {
    private PersonalityMeter testMeter;

    @Before
    public void setUp() throws Exception {
        testMeter = new PersonalityMeter(50);
    }

    @After
    public void tearDown() throws Exception {
        testMeter = null;
    }

    @Test
    public void setMeter() throws Exception {
        /**
         * test neutral question style will not change the meter if meter is 50
         */
        testMeter.setMeter(QuestionStyle.NEUTRAL);
        assertEquals("Should equals to 50",50,testMeter.getMeter());

        /**
         * test friendly question style will increase meter by 5
         */
        testMeter.setMeter(QuestionStyle.NICE);
        assertEquals("Should equals to 55",55,testMeter.getMeter());

        /**
         * test neutral question style will decrease the meter by 5 if meter greater than 50
         */
        testMeter.setMeter(QuestionStyle.NICE);
        testMeter.setMeter(QuestionStyle.NEUTRAL);
        assertEquals("Should equals to 55",55,testMeter.getMeter());

        /**
         * test aggressive question style will decrease the meter by 5
         */
        testMeter.setMeter(QuestionStyle.AGGRESSIVE);
        assertEquals("Should equals to 50",50,testMeter.getMeter());

        /**
         * test neutral question style will increase the meter by 5 if meter less than 50
         */
        testMeter.setMeter(QuestionStyle.AGGRESSIVE);
        testMeter.setMeter(QuestionStyle.AGGRESSIVE);
        testMeter.setMeter(QuestionStyle.NEUTRAL);
        assertEquals("Should equals to 45",45,testMeter.getMeter());

    }

    @Test
    public void getMeter() throws Exception {
        /**
         * test if the getter returns value.
         */
        assertNotNull(testMeter.getMeter());
    }

}