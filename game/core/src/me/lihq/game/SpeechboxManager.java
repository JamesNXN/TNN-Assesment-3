package me.lihq.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.screen.elements.SpeechBox;

/**
 * This is to control the order that SpeechBoxes are shown in and controls flow of displaying the SpeechBoxes
 */
public class SpeechboxManager
{
    public boolean isPressed;

    /**
     * This is the stack of SpeechBox's that are to be displayed
     */
    private Array<SpeechBox> stack = new Array<>();

    public void nextSpeech(){
        if (this.stack.size != 0){
            this.stack.removeIndex(0);
        }
    }

    /**
     * This method is called once a tick
     */
    public void update()
    {
        if (this.stack.size != 0) {
            SpeechBox top = this.stack.get(0);
            if(!top.haveButtons() && Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                if(!isPressed)nextSpeech();
                isPressed = true;
            }
            else {
                isPressed = false;
            }
        }
    }

    /**
     * This method adds a SpeechBox to the stack
     * @param speechBox - The SpeechBox to add to the stack
     */
    public void addSpeechBox(SpeechBox speechBox)
    {
        this.stack.add(speechBox);
    }

    /**
     * This method removes the current speechBox from the stack and progresses onto the next one if
     * there is one
     */
    public void removeCurrentSpeechBox()
    {
        /**
        if (!this.stack.isEmpty()) {
            this.stack.get(0).timeoutDuration = 0;
        }
         **/
        nextSpeech();
    }
}
