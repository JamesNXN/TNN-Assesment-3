package me.lihq.game.people;

import com.badlogic.gdx.utils.JsonValue;

public abstract class Dialogue {
    protected JsonValue dialogueJsonValue;
    private String introduction;

    public Dialogue(AbstractPerson person){
        dialogueJsonValue = person.getJsonData().get("dialogue");
        introduction = dialogueJsonValue.getString("introduction");
    }

    public String getIntroduction(){
        return introduction;
    }
}
