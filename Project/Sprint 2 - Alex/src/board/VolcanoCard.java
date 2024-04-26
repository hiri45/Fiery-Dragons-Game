package src.board;

import src.actors.DragonToken;
import src.utils.PlayerManager;

import java.util.ArrayList;

public class VolcanoCard {
    private int squareCount;
    private boolean hasCave;
    private ArrayList<Square> squares = new ArrayList<>();
    private Cave cave;

    private int startPosition;
    VolcanoCard(int squareCount, boolean hasCave){
        this.squareCount = squareCount;
        this.hasCave = hasCave;


    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void setStartPosition(int startPosition){
        //going clockwise, this is the first position of the first square
        this.startPosition = startPosition;
    }
    public boolean hasCave(){
        return hasCave;
    }

    public void initialiseSquares(CreatureType creature1,CreatureType creature2, CreatureType creature3){
        //give each square a position on the board

        if (squares.size() < squareCount){
            squares.add(new Square(creature1));
            squares.add(new Square(creature2));
            squares.add(new Square(creature3));
        }
        if(this.hasCave){
            initialiseCave();
        }
    }
    public void initialiseCave(){
        int middleSquare = 1;
        //add a new cave
        if (this.hasCave){
            cave = new Cave(squares.get(middleSquare).getCreature(),squares.get(middleSquare).getPosition());

        }
    }
    public void setFixedPositions(){
        int cardPosition = startPosition;
        for (int i = 0; i < squares.size();i++){
            squares.get(i).setPosition(cardPosition);
            cardPosition++;
        }
    }

    public Cave getCave() {
        return cave;
    }
}
