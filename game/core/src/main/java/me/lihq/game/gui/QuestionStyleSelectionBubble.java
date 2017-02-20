package me.lihq.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import me.lihq.game.GameWorld;
import me.lihq.game.models.Clue;
import me.lihq.game.people.Personality;

public class QuestionStyleSelectionBubble extends SpeechBubble{
    public QuestionStyleSelectionBubble(GameWorld gameWorld, Clue selectedClue, Skin skin) {
        super(gameWorld.getPlayer(), skin);

        TextButton aggressiveButton = new TextButton("Aggressive", skin, "buttonBubble");
        aggressiveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameWorld.getInteraction().question(selectedClue, Personality.AGGRESSIVE);
            }
        });

        TextButton neutralButton = new TextButton("Neutral", skin, "buttonBubble");
        neutralButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameWorld.getInteraction().question(selectedClue, Personality.NEUTRAL);
            }
        });

        TextButton niceButton = new TextButton("Nice", skin, "buttonBubble");
        niceButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameWorld.getInteraction().question(selectedClue, Personality.NICE);
            }
        });


        switch (gameWorld.getPlayer().getPersonality()){
            case NICE:
                addButton(niceButton);
                addButton(neutralButton);
                break;

            case NEUTRAL:
                addButton(niceButton);
                addButton(neutralButton);
                addButton(aggressiveButton);
                break;

            case AGGRESSIVE:
                addButton(neutralButton);
                addButton(aggressiveButton);
                break;
        }
    }

    private void addButton(TextButton textButton){
        getContentTable().add(textButton).row();
    }
}
