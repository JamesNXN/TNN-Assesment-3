package me.lihq.game.people;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Npc dialogue class contains the dialogue information for the npc
 */
public class NpcDialogue extends Dialogue{

    /**
     * Parameters needed by NpcDialogue object:
     *
     * failResponseArray - the array of possible responses an npc may give when a questioning has failed
     */
    private Array<String> failResponseArray;

    /**
     * Constructor for NpcDialogue object
     * @param person - the npc containing the information needed to construct the object
     */
    public NpcDialogue(Npc person) {
        super(person);

        Json json = new Json();
        JsonValue responseJsonData = dialogueJsonValue.get("failResponses");
        failResponseArray = json.readValue(Array.class, responseJsonData);
    }

    /**
     * Method that returns the failResponseArray
     * @return - returns the failResponseArray
     */
    public Array<String> getFailResponseArray() {
        return failResponseArray;
    }
}
