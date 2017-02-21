package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * NEW
 * Actor that keeps track of game play time. It should pause while in pause screen.
 */

public class Time extends Actor {
    private static Time instance = new Time();

    private float time;
    private boolean paused = false;

    private Time() {
        this.time = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!paused){
            this.time += delta;
        }
    }

    public static Time getInstance() {
        return instance;
    }

    public float getTime(){
        return this.time;
    }

    public void setPaused(boolean pause){
        this.paused = pause;
    }

    public void reset(){
        time = 0;
        paused = false;
    }
}
