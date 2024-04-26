package src.actors;

import src.board.Cave;
import src.gui.DragonTokenPanel;
import src.utils.MovementManager;

public class DragonToken extends Actor{

    private Cave cave;
    private DragonTokenPanel dragonTokenPanel;

    private boolean inCave;

    private int totalSquaresMoved;

    public DragonToken(int id){
        super(id);
        inCave = true;
        totalSquaresMoved = 0;
    }

    public DragonTokenPanel getDragonTokenPanel() {
        return dragonTokenPanel;
    }

    public void setDragonTokenPanel(DragonTokenPanel dragonTokenPanel) {
        this.dragonTokenPanel = dragonTokenPanel;
    }

    public Cave getCave(){
        return cave;
    }

    public void setCave(Cave cave){
        this.cave = cave;
    }

    public boolean isInCave() {
        return inCave;
    }

    public int getTotalSquaresMoved() {
        return totalSquaresMoved;
    }

    public void addMovement(int totalSquaresMoved) {
        this.totalSquaresMoved += totalSquaresMoved;
    }

    public void setInCave(boolean inCave) {
        this.inCave = inCave;
    }

    @Override
    public void playTurn() {
        super.playTurn();
        int noPositions = 1;
        MovementManager movementManager = MovementManager.getInstance();
        if(movementManager.canMove(this,noPositions)){
            movementManager.updatePosition(this,noPositions);
        }
        // flip a dragon card. Call the isMatch() function
            //success! call the canMove() function
            // move 2 squares

    }
    public void move1(){
        int noPositions = 1;
        MovementManager movementManager = MovementManager.getInstance();
        if(movementManager.canMove(this,noPositions)){
            movementManager.updatePosition(this,noPositions);
        }
    }

    public void move2(){
        int noPositions = 2;
        MovementManager movementManager = MovementManager.getInstance();
        if(movementManager.canMove(this,noPositions)){
            movementManager.updatePosition(this,noPositions);
        }
    }

    public void move3(){
        int noPositions = 3;
        MovementManager movementManager = MovementManager.getInstance();
        if(movementManager.canMove(this,noPositions)){
            movementManager.updatePosition(this,noPositions);
        }
    }
}
