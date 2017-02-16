package me.lihq.game.people;

/**
 * Created by Tunc on 16/02/2017.
 */
public class Score {

    private boolean firstAccusation;
    private int points;

    public Score(){
        this.firstAccusation = true;
        this.points = 0;
    }

    

    public void addPoints(int point){
        this.points += point;
    }

    public void subPoints (int point){
        this.points -= point;
    }

    public void failedAccusation(){
        this.firstAccusation = false;
    }



    public int getPoints(){
        return points;
    }

    public int returnScore( int time ){
        if (firstAccusation){
            this.points += 200;
        }
        this.points -= time;  // Time class needs a return function thst is time in integer format

        return points;
    }
}
