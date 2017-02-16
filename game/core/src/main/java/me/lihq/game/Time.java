package me.lihq.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created on 2017/2/15.
 */
public class Time extends Actor {
    private float time;
    private boolean paused = false;

    public Time() {
        this.time = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!paused){
            this.time += delta;
        }
    }

    public float getTime(){
        return this.time;
    }

    public void setPaused(){
        this.paused = true;
    }
}
