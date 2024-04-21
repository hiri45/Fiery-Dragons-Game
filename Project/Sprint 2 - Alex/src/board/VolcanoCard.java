package src.board;

import java.util.ArrayList;

public class VolcanoCard {
    private int squareCount;
    private boolean hasCave;
    private ArrayList<Square> squares;
    private Cave cave;
    VolcanoCard(int squareCount, boolean hasCave){
        this.squareCount = squareCount;
        this.hasCave = hasCave;

        //add a new cave
        if (this.hasCave){
            //cave = new Cave();
        }
    }

    public boolean hasCave(){
        return hasCave;
    }

    public void initialiseSquares(CreatureType creature1,CreatureType creature2, CreatureType creature3){
        if (squares.size() < squareCount){
            squares.add(new Square(creature1));
            squares.add(new Square(creature2));
            squares.add(new Square(creature3));
        }


    }


}
