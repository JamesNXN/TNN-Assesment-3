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
    public ConversationSpeechBubble(AbstractPerson speakingPerson, String dialogue, Skin skin){
        super(skin);

        setTransform(true);
        align(Align.top);

        Label nameLabel = new Label(speakingPerson.getName(), skin, "speechName");

        Label dialogueLabel = new Label(dialogue, skin, "speechDialogue");
        dialogueLabel.setWrap(true);
        dialogueLabel.setAlignment(Align.center);
        contentTable.add(dialogueLabel).align(Align.top).width(250);

        pack();
        //height increment to account for speech bubble tail
        setHeight(contentTable.getHeight() + 100);

        nameTable.add(nameLabel).align(Align.left).expandX();
        nameTable.setSize(getWidth(), nameLabel.getHeight());
        nameTable.setPosition(20, getTop() - 10, Align.topLeft);
        addActor(nameTable);

        setOrigin(getWidth()/2, getHeight()/2);

        switch (speakingPerson.getDirection()){
            case EAST:
            case SOUTH:
                setBackground(skin.getDrawable("bubble-lower-right"));
                setPosition(50, GameMain.GAME_HEIGHT/2, Align.bottomLeft);
                break;

            case WEST:
            case NORTH:
                setBackground(skin.getDrawable("bubble-lower-left"));
                setPosition(GameMain.GAME_WIDTH - 50, GameMain.GAME_HEIGHT/2, Align.bottomRight);
                break;

        }
        setScale(0);
    }
}
