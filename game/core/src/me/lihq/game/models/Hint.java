package me.lihq.game.models;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import me.lihq.game.people.NPC;

import java.util.concurrent.ThreadLocalRandom;

public class Hint {
    private Clue relatedClue;
    private Array<Integer> relatedNpcIdArray = new Array<>(0);


    public Hint(Clue clue) {
        this.relatedClue =  clue;
        Array<Integer> npcArray = new Array<Integer>();
        npcArray.addAll(clue.getRelatedNpcIdArray());
        npcArray.shuffle();
        int subsetSize = MathUtils.random(1, npcArray.size);
        npcArray.setSize(subsetSize);
        this.relatedNpcIdArray = npcArray;
    }

    public Array<Integer> getRelatedNpcIdArray(){
        return relatedNpcIdArray;
    }

    public Clue getRelatedClue() { return relatedClue;}

    public void combine(Hint hint){
        for (int idToBeAdded : hint.getRelatedNpcIdArray()){
            if (!relatedNpcIdArray.contains(idToBeAdded, true)){
                relatedNpcIdArray.add(idToBeAdded);
            }
        }
    }
}
