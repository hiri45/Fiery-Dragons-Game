package game.creatures;

public abstract class Creature {
    private final String creatureType; // used to represent type of animal it is

    public Creature(String creatureType){
        this.creatureType = creatureType;
    }

    public String getCreatureType() {
        return creatureType;
    }

}
