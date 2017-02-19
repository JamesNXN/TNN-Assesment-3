package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import me.lihq.game.GameMain;
import me.lihq.game.people.AbstractPerson;

public class ConversationSpeechBubble extends SpeechBubble {
    /**
     * Constructs a speech bubble for conversation
     * @param speakingPerson the character that is speaking
     * @param dialogue the dialogue line
     * @param skin skin to use for table construction
     */
    public ConversationSpeechBubble(AbstractPerson speakingPerson, String dialogue, Skin skin) {
        super(speakingPerson, skin);

        nameLabel = new Label(speakingPerson.getName(), skin, "speechName");

        Label dialogueLabel = new Label(dialogue, skin, "speechDialogue");
        dialogueLabel.setWrap(true);
        dialogueLabel.setAlignment(Align.center);
        contentTable.add(dialogueLabel).align(Align.top).width(250);
    }
}
