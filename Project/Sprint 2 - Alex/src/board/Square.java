package src.board;

public class Square {
    private CreatureType creature;
    private int position;
    Square(CreatureType creature){
        this.creature = creature;

    }

    public void setPosition(int position){
        this.position = position;
    }

    public CreatureType getCreature() {
        return creature;
    }
}
