package me.lihq.game.people;

/**
 * These are the possible personalities of the person
 * The ranges are inclusive.
 */

public enum Personality {
    NICE(0, 44),
    NEUTRAL(45, 55),
    AGGRESSIVE(56, 100);

    private int minRange;
    private int maxRange;

    Personality(int minRange, int maxRange){
        this.minRange = minRange;
        this.maxRange = maxRange;
    }


    public boolean isInRange(int personalityLevel){
        return personalityLevel <= maxRange && personalityLevel >= minRange;
    }
}
