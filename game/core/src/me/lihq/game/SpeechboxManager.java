package me.lihq.game;

import com.badlogic.gdx.InputMultiplexer;
import me.lihq.game.screen.elements.SpeechBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brookehatton on 21/01/2017.
 */
public class SpeechboxManager
{
    public InputMultiplexer multiplexer;
    private List<SpeechBox> stack = new ArrayList<>();

    public SpeechboxManager()
    {
        multiplexer = new InputMultiplexer();
    }

    public void render()
    {
        if (!this.stack.isEmpty()) {
            this.stack.get(0).render();
        }
    }

    public void update()
    {
        if (!this.stack.isEmpty()) {
            if (this.stack.get(0).timeoutDuration == 0) {
                this.multiplexer.removeProcessor(this.stack.get(0).stage);
                this.stack.remove(0);
            } else {
                this.stack.get(0).update();

            }
        }
        updateInputProcessor();
    }

    public void resize(int width, int height)
    {
        if (!this.stack.isEmpty()) {
            this.stack.get(0).resize(width, height);
        }
    }

    private void updateInputProcessor()
    {
        if (this.multiplexer.getProcessors().size == 0 && !this.stack.isEmpty()) {
            this.multiplexer.addProcessor(this.stack.get(0).stage);
        }
    }

    public void addSpeechBox(SpeechBox speechBox)
    {
        this.stack.add(speechBox);
    }

    public void rmCurrentSpeechBox()
    {
        if (!this.stack.isEmpty()) {
            this.stack.get(0).timeoutDuration = 0;
        }
    }


}
