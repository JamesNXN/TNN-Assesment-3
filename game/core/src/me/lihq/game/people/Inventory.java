package me.lihq.game.people;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Hint;

/**
 * Created by User on 08/02/2017.
 */
public class Inventory {

    private Array<Clue> collectedClues;
    private Array<NPC> metCharacters;
    private Array<Hint> collectedHints;

    public Inventory () {
        this.collectedClues = new Array(0);
        this.metCharacters = new Array<>(0);
        this.collectedHints = new Array<>(0);
    }

    public Array<Clue> getCollectedClues() {return this.collectedClues;}

    public Array<NPC> getMetCharacters() {return this.metCharacters;}

    public Array<Hint> getCollectedHints() {return this.collectedHints;}

    public void addNewClue(Clue clue) {
        this.collectedClues.ensureCapacity(1);
        this.collectedClues.add(clue);
    }

    public void addNewCharacter(NPC character) {
        this.metCharacters.ensureCapacity(1);
        this.metCharacters.add(character);
    }

    public void addNewHint(Hint hint) {
        this.collectedHints.ensureCapacity(1);
        this.collectedHints.add(hint);
    }
}
