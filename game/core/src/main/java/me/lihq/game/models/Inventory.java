package me.lihq.game.models;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.people.Npc;

/**
 * Inventory class is a container that holds clues and hints collected by the player
 * as well as npc's that have been spoken to.
 */
public class Inventory {
    /**
     * Parameters needed for Inventory:
     *
     * collectedClues - an array of Clues containing clues that have been collected by the player
     * metCharacters - an array of Npc's containing the npcs that the player has spoken to
     * collectedHints - an array of hints containing hints that have been collected by the player
     */
    private Array<Clue> collectedClues;
    private Array<Npc> metCharacters;
    private Array<Hint> collectedHints;

    /**
     * Constructor for creating inventory object
     */
    public Inventory () {
        this.collectedClues = new Array<>();
        this.metCharacters = new Array<>();
        this.collectedHints = new Array<>();
    }

    /**
     * Method to check whether a hint about a clue has already been collected
     * @param hint - hint to check
     * @return (boolean) Returns true if the hint has already been collected
     */
    public boolean contains(Hint hint) {
        for (Hint hintToCheck: this.collectedHints) {
            if (hintToCheck.getRelatedClue() == hint.getRelatedClue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check whether a WEAPON type clue has been collected
     * @return (boolean) Returns true if the weapon has been found
     */
    public boolean isWeaponFound(){
        for (Clue clue: this.collectedClues){
            if (clue.getClueType()==ClueType.WEAPON){
                return true;
            }
        }
        return false;
    }

    /**
     * Method for adding clues to the inventory
     * @param clue - clue to be added to inventory
     */
    public void addClue(Clue clue) {
        this.collectedClues.add(clue);
        /** player.score.addPoints(100); */ //@TODO Need to fix referencing score from inside inventory.
    }

    /**
     * Method for adding met npcs to the inventory
     * @param character - npc to be added to inventory
     */
    public void addCharacter(Npc character) {
        this.metCharacters.add(character);
    }

    /**
     * Method to add hints to the inventory
     * the method checks if a hint relating to the same
     * clue as the new hint has already been added and combines them if it has
     * if not it just adds the new hint
     * @param hint - hint to be added to inventory
     */
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

    /**
     * Getters needed for use by other classes
     */
    public Array<Clue> getCollectedClues() {return this.collectedClues;}

    public Array<Npc> getMetCharacters() {return this.metCharacters;}

    public Array<Hint> getCollectedHints() {return this.collectedHints;}

}
