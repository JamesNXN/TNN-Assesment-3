package me.lihq.game.people;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * NEW
 * PlayerDialogue class contains the dialogue that a player may use in game
 */
public class PlayerDialogue extends Dialogue {
    /**
     * Parameters needed by PlayerDialogue:
     *
     * niceQuestionArray - array of dialogue strings for nice questioning style
     * neutralQuestionArray - array of dialogue strings for neutral questioning style
     * aggressiveQuestionArray - array of dialogue strings for aggressive questioning style
     */

    private Array<String> niceQuestionArray;
    private Array<String> neutralQuestionArray;
    private Array<String> aggressiveQuestionArray;

    /**
     * Constructor for building player dialogue object using information from player
     * @param person - the player to obtain dialogue information from
     */
    public PlayerDialogue(Player person) {
        super(person);

        JsonValue questionJsonData = dialogueJsonValue.get("question");
        Json json = new Json();
        niceQuestionArray = json.readValue(Array.class, String.class, questionJsonData.get("NICE"));
        neutralQuestionArray = json.readValue(Array.class, String.class, questionJsonData.get("NEUTRAL"));
        aggressiveQuestionArray = json.readValue(Array.class, String.class, questionJsonData.get("AGGRESSIVE"));
    }

    /**
     * Getters for use in other classes
     */

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
