package me.lihq.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Score class is a singleton class used to keep track of the players score
 */
public class Score {
    private static Score instance = new Score();

    /**
     * Parameters needed for score:
     *
     * highScore - The current highscore.
     * currentScore - An integer that contains the player's current score.
     * failedAccusationCount - This integer contains the number of times a false accusation has been made.
     *
     */

    private int highScore;
    private int currentScore;

    private int failedAccusationCount;

    /**
     * Score constructor
     */
    private Score() {
        highScore = Gdx.app.getPreferences("pref").getInteger("highScore", 0);
        currentScore = 0;
        failedAccusationCount = 0;
    }



    /**
     * The methods addPoints and subPoints increase or decrease currentScore.
     *
     * @param point - The number of points to be added/subtracted form score.
     */

    public void addPoints(int point) {
        currentScore += point;
    }

    public void subPoints(int point) {
        currentScore -= point;
    }

    /**
     * Getters for use in other classes.
     */

    public int getCurrentScore() {
        return currentScore;
    }

    public static Score getInstance(){
        return instance;
    }

    public int getFailedAccusationCount() {
        return failedAccusationCount;
    }

    /**
     * This method is called if the player wishes to restart the game to reset the score.
     */

     public void reset() {
        currentScore = 0;
      failedAccusationCount = 0;
    }

    /**
     * This method is called when the player falsely accuses an Npc to increase the failed accusation count.
     */

    public void failedAccusation(){
        failedAccusationCount++;
    }

    /**
     * @param time - The time it took to solve the murder.
     *
     * This method applies a time penalty to the current score.
     * If the current score is greater than the high score, the high score is updated.
     *             
     * Returns the integer finalScore.
     */

    public int returnFinalScore(int time ){
        int finalScore = currentScore - time;
        if (finalScore > highScore){
            Preferences pref = Gdx.app.getPreferences("pref");
            pref.putInteger("highScore", finalScore);
            pref.flush();
        }

        return finalScore;
    }
}
