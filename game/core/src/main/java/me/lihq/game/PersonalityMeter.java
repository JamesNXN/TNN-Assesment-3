package me.lihq.game;

import me.lihq.game.people.QuestionStyle;

/**
 * PersonalityMeter which will affect the way that player can question.
 */
public class PersonalityMeter {
    /**
     * Parameters needed for PersonalityMeter:
     * meter - an integer % value of the players personality
     */
    private int meter;


    public PersonalityMeter(int initialMeter) {
        this.meter = initialMeter;
    }

    public void setMeter(QuestionStyle questionStyle) {
        switch (questionStyle){
            case AGGRESSIVE:
                this.meter += 10;
                if (meter > 100)
                    meter = 100;
                break;
            case NICE:
                this.meter -= 10;
                if (meter < 0)
                    meter = 0;
                break;
            case NEUTRAL:
                if (this.meter > 50){
                    this.meter -= 10;
                }
                if (this.meter <50){
                    this.meter += 10;
                }
                else {
                    break;
                }
                break;
        }
    }

    public int getMeter() {
        return meter;
    }
}
