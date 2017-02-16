package me.lihq.game;

import me.lihq.game.people.QuestionStyle;

/**
 * PesonalityMeter which will affect the way that player can question.
 */
public class PersonalityMeter {
    private int meter;

    public PersonalityMeter() {
        this.meter = 50;
    }

    public void setMeter(QuestionStyle questionStyle) {
        switch (questionStyle){
            case FRIENDLY:
                this.meter += 5;
                break;
            case AGGRESSIVELY:
                this.meter -= 5;
                break;
            case NEUTRAL:
                if (this.meter > 50){
                    this.meter -= 5;
                }
                if (this.meter <50){
                    this.meter += 5;
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
