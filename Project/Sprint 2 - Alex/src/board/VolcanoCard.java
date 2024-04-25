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
        int cardPosition = startPosition;
        if (squares.size() < squareCount){
            squares.add(new Square(creature1));
            squares.add(new Square(creature2));
            squares.add(new Square(creature3));
        }
        for (int i = 0; i < squares.size();i++){
            squares.get(i).setPosition(cardPosition);
            cardPosition++;
        }
        if(this.hasCave){
            initialiseCave();
        }
    }
    public void initialiseCave(){
        int middleSquare = 1;
        //add a new cave
        if (this.hasCave){
            for(DragonToken dragonToken: PlayerManager.getInstance().players){
                //if a player doesn't have a cave, allocate the dragonToken the Cave Owner
                if(dragonToken.getCave() == null){

                    cave = new Cave(squares.get(middleSquare).getCreature(),dragonToken);
                    break;
                }

            }

        }
    }

    public Cave getCave() {
        return cave;
    }
}
