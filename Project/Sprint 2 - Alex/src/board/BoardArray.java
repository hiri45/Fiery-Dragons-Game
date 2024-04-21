package src.board;

import src.utils.TurnManager;

import java.util.ArrayList;

public class BoardArray {

    private ArrayList<VolcanoCard> board;
    BoardArray(){

    }
    public static BoardArray instance;
    public static BoardArray getInstance(){
        if(instance == null){
            instance = new BoardArray();
        }
        return instance;
    }

    public void setVolcanoCards(int volcanoCardCount, int squaresPerVC){
        int position = 1;
        //Create a set of volcano cards that will be used for the game board
        for(int i = 0; i<volcanoCardCount; i++){
            board.add(new VolcanoCard(squaresPerVC,i%2==0,position));
            position += 3;
        }

    }
}
