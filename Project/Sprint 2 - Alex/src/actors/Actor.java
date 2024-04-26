package src.actors;

import src.board.Cave;

public class Actor {
    private int startingPosition;

    private int position;
    private int id;

    private boolean turn;

    Actor(int id){
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void playTurn(){

    }

    public void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
