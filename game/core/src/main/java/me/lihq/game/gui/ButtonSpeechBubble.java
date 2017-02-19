package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import me.lihq.game.gui.buttons.QuestionButton;

public class ButtonSpeechBubble extends SpeechBubble{
    private Gui gui;
    private QuestionButton questionButton;

    public ButtonSpeechBubble(Skin skin, Gui gui) {
        super(skin);

        questionButton = new QuestionButton(skin, gui);
    }
}
