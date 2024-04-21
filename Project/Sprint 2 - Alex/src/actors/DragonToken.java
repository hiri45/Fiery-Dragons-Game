package src.actors;

import src.board.Cave;

public class DragonToken {
    private boolean turn;
    private int startingPosition;
    private Cave cave;

    public DragonToken() {

    }
    public void playTurn(){
        boolean continueTurn;
    }

    public void setCave(Cave cave){
        this.cave = cave;
    }

    public Cave getCave(){
        return cave;
    }
}
