package game.creatures;

import javax.swing.*;

public abstract class Creature {
    private final String creatureType; // used to represent type of animal it is
    //private final ImageIcon creatureIcon; // to be implemented in later sprints

    public Creature(String creatureType){
        this.creatureType = creatureType;

    }

    public String getCreatureType() {
        return creatureType;
    }

}
