package me.lihq.game.people;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class NpcDialogue extends Dialogue{
    private Array<String> failResponseArray;

    public NpcDialogue(NPC person) {
        super(person);

        Json json = new Json();
        JsonValue responseJsonData = dialogueJsonValue.get("failResponse");
        failResponseArray = json.readValue(Array.class, responseJsonData);
    }

    public Array<String> getFailResponseArray() {
        return failResponseArray;
    }
}
