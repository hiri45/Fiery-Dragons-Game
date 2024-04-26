package src.board;

import src.actors.Actor;
import src.actors.DragonToken;
import src.gui.CavePanel;

public class Cave {
    private CreatureType creatureType;
    private int cavePosition;
    private DragonToken caveOwner;

    private CavePanel cavePanel;



    Cave(CreatureType creatureType, int cavePosition){
        this.cavePosition = cavePosition;
        this.creatureType = creatureType;
    }

    public CavePanel getCavePanel() {
        return cavePanel;
    }

    public void setCavePanel(CavePanel cavePanel) {
        this.cavePanel = cavePanel;
    }

    public int getCavePosition() {
        return cavePosition;
    }

    public CreatureType getCreatureType() {
        return creatureType;
    }
    public Actor getCaveOwner(){
        return caveOwner;
    }

    public void setCaveOwner(DragonToken caveOwner) {
        caveOwner.setCave(this);
        this.caveOwner = caveOwner;
    }

    public void setCavePosition(int position){
        cavePosition = position;
    }
}
