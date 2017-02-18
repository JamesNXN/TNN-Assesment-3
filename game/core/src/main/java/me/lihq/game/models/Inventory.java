package me.lihq.game.models;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.people.Npc;

public class Inventory {

    private Array<Clue> collectedClues;
    private Array<Npc> metCharacters;
    private Array<Hint> collectedHints;

    public Inventory () {
        this.collectedClues = new Array<>();
        this.metCharacters = new Array<>();
        this.collectedHints = new Array<>();
    }

    public Array<Clue> getCollectedClues() {return this.collectedClues;}

    public Array<Npc> getMetCharacters() {return this.metCharacters;}

    public Array<Hint> getCollectedHints() {return this.collectedHints;}

    public void addClue(Clue clue) {
        this.collectedClues.add(clue);
        /** player.score.addPoints(100); */ //@TODO Need to fix referencing score from inside inventory.
    }

    public boolean contains(Hint hint) {
        for (Hint hintToCheck: this.collectedHints) {
            if (hintToCheck.getRelatedClue() == hint.getRelatedClue()) {
                return true;
            }
        }
        return false;
    }

    public boolean isWeaponFound(){
        for (Clue clue: this.collectedClues){
            if (clue.getClueType()==ClueType.WEAPON){
                return true;
            }
        }
        return false;
    }

    public void addCharacter(Npc character) {
        this.metCharacters.add(character);
    }

    public void addHint(Hint hint) {
        if (contains(hint) == false) {
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
