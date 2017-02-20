package me.lihq.game.models;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import me.lihq.game.NpcManager;

/**
 * This class defines hints that are given to the player upon successfully questioning
 * an npc about a clue.
 */
public class Hint {
    /**
     * Parameters needed for hint:
     *
     * relatedClue - hint gives information about the npc that the related clue could point to
     * relatedNpcIdArray - a random subset of the possible npc's the clue could point to as defined by the json info
     */
    private Clue relatedClue;
    private Array<Integer> relatedNpcIdArray = new Array<>(0);

    /**
     * Constructor for the hint object it generates a random subset of the possible npc's that the related clue
     * points to then assigns that subset to the relatedNpcIdArray
     * @param clue - clue that was used to obtain the hint
     */
    public Hint(Clue clue) {
        this.relatedClue =  clue;
        Array<Integer> npcArray = new Array<>();
        npcArray.addAll(clue.getRelatedNpcIdArray());
        npcArray.shuffle();
        int subsetSize = MathUtils.random(1, npcArray.size);
        npcArray.setSize(subsetSize);
        this.relatedNpcIdArray = npcArray;
    }

    /**
     * Combine method is used to combine hints once the player questions multiple people about the same clue it combines
     * the randomly generated subsets of relatedNpcId's such that no duplicates occur this allows the player to collect
     * more information about a clue by questioning more people about it
     * @param hint - hint to be combined
     */
    public void combine(Hint hint){
        for (int idToBeAdded : hint.getRelatedNpcIdArray()){
            if (!relatedNpcIdArray.contains(idToBeAdded, true)){
                relatedNpcIdArray.add(idToBeAdded);
            }
        }
    }

    public String getRelatedNpcNames(){
        String relatedNpcNames = "";

        for (int i = 0; i < relatedNpcIdArray.size; i++){
            if (i == relatedNpcIdArray.size - 1){
                relatedNpcNames += NpcManager.instance.getNpc(relatedNpcIdArray.get(i)).getName();
            }
            else{
                relatedNpcNames += NpcManager.instance.getNpc(relatedNpcIdArray.get(i)).getName() + ", ";
            }
        }
        return relatedNpcNames;
    }

    /**
     *Getters needed for use by other classes
     */
    public Array<Integer> getRelatedNpcIdArray(){return relatedNpcIdArray;}

    public Clue getRelatedClue() { return relatedClue;}
}
