package game.specialCreatures;

public abstract class SpecialCreature {

    private final String specialCreatureType;
    public SpecialCreature(String specialCreatureType){
        this.specialCreatureType = specialCreatureType;
    }

    public String getSpecialCreatureType() {
        return specialCreatureType;
    }
}
