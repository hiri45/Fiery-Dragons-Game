package src.board;

import java.util.ArrayList;

public class VolcanoCard {
    private int squareCount;
    private boolean hasCave;
    private ArrayList<Square> squares;
    private Cave cave;
    VolcanoCard(int squareCount, boolean hasCave,int position){
        this.squareCount = squareCount;
        this.hasCave = hasCave;

        //add a new cave
        if (this.hasCave){
            cave = new Cave();
        }
    }

    public boolean hasCave(){
        return hasCave;
    }


}
