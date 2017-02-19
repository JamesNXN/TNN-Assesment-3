package me.lihq.game.people;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Abstract class that contains dialogue information
 */
public abstract class Dialogue {
    /**
     * Parameters needed by Dialogue object:
     *
     * dialogueJsonValue - contains the dialogue information obtained through json
     * introduction - contains the string displayed when starting a conversation
     */
    protected JsonValue dialogueJsonValue;
    private String introduction;

    /**
     * Constructor for Dialogue object
     * @param person - person to get dialogue information from
     */
    public Dialogue(AbstractPerson person){
        dialogueJsonValue = person.getJsonData().get("dialogue");
        introduction = dialogueJsonValue.getString("introduction");
    }

    /**
     * Getter used by other classes
     */
    public String getIntroduction(){
        return introduction;
    }
}
