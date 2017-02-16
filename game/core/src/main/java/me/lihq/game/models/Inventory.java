package me.lihq.game.models;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.models.Clue;
import me.lihq.game.models.Hint;
import me.lihq.game.people.NPC;

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

    public boolean checkIfHintExists(Hint hint) {
        for (Hint hintToCheck: this.collectedHints) {
            if (hintToCheck.getRelatedClue() == hint.getRelatedClue()) {
                return true;
            }
        }
        return false;
    }

    public void addNewCharacter(NPC character) {
        this.metCharacters.ensureCapacity(1);
        this.metCharacters.add(character);
    }

    public void addNewHint(Hint hint) {
        if (checkIfHintExists(hint) == false) {
            this.collectedHints.ensureCapacity(1);
            this.collectedHints.add(hint);
        } else {
            for (Hint hintToUpdate: this.collectedHints) {
                if (hintToUpdate == hint) {
                    hintToUpdate.combine(hint);
                }
            }
        }
    }
}