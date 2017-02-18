package me.lihq.game.people;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class PlayerDialogue extends Dialogue {
    private Array<String> niceQuestionArray;
    private Array<String> neutralQuestionArray;
    private Array<String> aggressiveQuestionArray;

    public PlayerDialogue(Player person) {
        super(person);

        JsonValue questionJsonData = dialogueJsonValue.get("question");
        Json json = new Json();
        niceQuestionArray = json.readValue(Array.class, String.class, questionJsonData.get("NICE"));
        neutralQuestionArray = json.readValue(Array.class, String.class, questionJsonData.get("NEUTRAL"));
        aggressiveQuestionArray = json.readValue(Array.class, String.class, questionJsonData.get("AGGRESSIVE"));
    }

    public Array<String> getNiceQuestionArray() {
        return niceQuestionArray;
    }

    public Array<String> getNeutralQuestionArray() {
        return neutralQuestionArray;
    }

    public Array<String> getAggressiveQuestionArray() {
        return aggressiveQuestionArray;
    }
}
