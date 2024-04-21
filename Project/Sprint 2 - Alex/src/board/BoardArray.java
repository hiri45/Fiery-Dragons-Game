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
        //Create a set of volcano cards that will be used for the game board
        for(int i = 0; i<volcanoCardCount; i++){
            board.add(new VolcanoCard(squaresPerVC,i%2==0));
        }

        //initialise volcano cards with relevant squares
        //Cave volcano cards
        board.get(0).initialiseSquares(CreatureType.BABY_DRAGON,CreatureType.BAT,CreatureType.SPIDER);
        board.get(2).initialiseSquares(CreatureType.SALAMANDER,CreatureType.SPIDER,CreatureType.BAT);
        board.get(4).initialiseSquares(CreatureType.SPIDER,CreatureType.SALAMANDER,CreatureType.BABY_DRAGON);
        board.get(6).initialiseSquares(CreatureType.BAT,CreatureType.SPIDER,CreatureType.BABY_DRAGON);

        //non-cave volcano cards
        board.get(1).initialiseSquares(CreatureType.SPIDER,CreatureType.BAT,CreatureType.SALAMANDER);
        board.get(3).initialiseSquares(CreatureType.BABY_DRAGON,CreatureType.SALAMANDER,CreatureType.BAT);
        board.get(5).initialiseSquares(CreatureType.BAT,CreatureType.BABY_DRAGON,CreatureType.SALAMANDER);
        board.get(7).initialiseSquares(CreatureType.SALAMANDER,CreatureType.BABY_DRAGON,CreatureType.SPIDER);


    }
}
