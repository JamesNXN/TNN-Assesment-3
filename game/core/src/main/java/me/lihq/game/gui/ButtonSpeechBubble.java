package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;
import me.lihq.game.GameWorld;
import me.lihq.game.people.AbstractPerson;

public class ButtonSpeechBubble extends SpeechBubble{
    private TextButton questionButton;
    private TextButton accuseButton;
    private TextButton ignoreButton;

    public ButtonSpeechBubble(AbstractPerson speakingPerson, Skin skin, GameWorld gameWorld) {
        super(speakingPerson, skin);

        questionButton = new TextButton("Question", skin, "buttonBubble");
        getContentTable().add(questionButton).row();

        accuseButton = new TextButton("Accuse", skin, "buttonBubble");
        getContentTable().add(accuseButton).row();

        ignoreButton = new TextButton("Ignore", skin, "buttonBubble");
        ignoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameWorld.getConversationManager().setFinished(true);
            }
        });
        getContentTable().add(ignoreButton).row();
    }
}
