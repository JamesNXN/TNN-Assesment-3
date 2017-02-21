package me.lihq.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * NEW
 * Score class is a singleton class used to keep track of the players score
 */
public class Score extends Actor{
    private static Score instance = new Score();

    /**
     * Parameters needed for score:
     *
     * highScore - The current highscore.
     * currentScore - An integer that contains the player's score at current frame.
     * targetScore - An integer that the current score will eventually reach. Implemented for score increment animation effect.
     * failedAccusationCount - This integer contains the number of times a false accusation has been made.
     *
     */

    private int highScore;
    private int currentScore;
    private int targetScore;

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
        targetScore = currentScore + point;
    }

    public void subPoints(int point) {
        targetScore = currentScore - point;
    }

    /**
     * Getters for use in other classes.
     */

    public int getCurrentScore() {
        return currentScore;
    }

    public int getTargetScore() { return targetScore;}

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
     * This method applies a time penalty to the current score. Time penalty is 30% of seconds taken to clear.
     * If the current score is greater than the high score, the high score is updated.
     *
     * Returns the integer finalScore.
     */

    public int getFinalScore(int time){
        int finalScore = (int) (targetScore - time * 0.3f);
        if (isHighScore(finalScore)) {
            Preferences pref = Gdx.app.getPreferences("pref");
            pref.putInteger("highScore", finalScore);
            pref.flush();
        }
        return finalScore;
    }

    public boolean isHighScore(int score){
        return score > highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    @Override
    public void act(float delta) {
        if (currentScore > targetScore){
            currentScore -= 5;
        }
        else if (currentScore < targetScore){
            currentScore += 5;
        }
    }
}
