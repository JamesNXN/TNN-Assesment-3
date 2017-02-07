package me.lihq.game.models;

import com.badlogic.gdx.utils.Array;
import me.lihq.game.people.NPC;

/**
 * Created by PPPPPP on 2017/2/7.
 */
public class Hint {
    private Clue relatedClue;
    private Array<NPC> relatedNPClist;


    public Hint(Clue clue) {
        this.relatedClue =  clue;
        this.relatedNPClist = clue.getRelatedNPC();
    }

    public Array<NPC> getRelatedNPClist(){
        return relatedNPClist;
    }

    public void combine(Hint hint){
        for(NPC npc: hint.getRelatedNPClist()){
            if (!hint.getRelatedNPClist().contains(npc, true)) {
                relatedNPClist.add(npc);
            }
        }
    }
}
