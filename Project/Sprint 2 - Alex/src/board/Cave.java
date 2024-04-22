package src.board;

import src.actors.DragonToken;

public class Cave {
    private CreatureType creatureType;
    private int cavePosition;
    private DragonToken caveOwner;

    Cave(CreatureType creatureType){
        this.creatureType = creatureType;
    }
    public DragonToken getCaveOwner(){
        return caveOwner;
    }

    public void setCavePosition(int position){
        cavePosition = position;
    }
}
