package src.board;

import src.actors.Actor;
import src.actors.DragonToken;

public class Cave {
    private CreatureType creatureType;
    private int cavePosition;
    private DragonToken caveOwner;



    Cave(CreatureType creatureType, DragonToken caveOwner){
        this.creatureType = creatureType;
        this.caveOwner = caveOwner;
        caveOwner.setCave(this);
    }
    public CreatureType getCreatureType() {
        return creatureType;
    }
    public Actor getCaveOwner(){
        return caveOwner;
    }

    public void setCavePosition(int position){
        cavePosition = position;
    }
}
