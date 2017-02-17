package me.lihq.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Score {
    private static Score instance = new Score();

    private int highScore;
    private int currentScore;

    private int failedAccusationCount;

    private Score() {
        highScore = Gdx.app.getPreferences("pref").getInteger("highScore", 0);
        currentScore = 0;
        failedAccusationCount = 0;
    }

    public static Score getInstance(){
        return instance;
    }


    public void addPoints(int point) {
        currentScore += point;
    }

    public void subPoints(int point) {
        currentScore -= point;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void reset() {
        currentScore = 0;
        failedAccusationCount = 0;
    }

    public void failedAccusation(){
        failedAccusationCount++;
    }

    public int getFailedAccusationCount() {
        return failedAccusationCount;
    }

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
