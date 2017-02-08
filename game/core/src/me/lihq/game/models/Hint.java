package me.lihq.game.models;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.people.NPC;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by PPPPPP on 2017/2/7.
 */
public class Hint {
    private Clue relatedClue;
    private Array<NPC> relatedNPClist = new Array<NPC>(0);


    public Hint(Clue clue) {
        this.relatedClue =  clue;
        int subsetSize = ThreadLocalRandom.current().nextInt(1, clue.getRelatedNPC().size + 1);
        while (this.relatedNPClist.size != subsetSize) {
            NPC randomNPC = clue.getRelatedNPC().random();
            if (!this.relatedNPClist.contains(randomNPC, true)) {
                this.relatedNPClist.ensureCapacity(1);
                this.relatedNPClist.add(randomNPC);
            }
        }
    }

    public Array<NPC> getRelatedNPClist(){
        return this.relatedNPClist;
    }

    public Clue getRelatedClue() { return this.relatedClue;}

    public void combine(Hint hint){
        for(NPC npc: hint.getRelatedNPClist()){
            if (!hint.getRelatedNPClist().contains(npc, true)) {
                this.relatedNPClist.add(npc);
            }
        }
    }
}
