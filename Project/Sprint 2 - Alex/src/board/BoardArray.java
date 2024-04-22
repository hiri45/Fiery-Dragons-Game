package src.board;

import src.utils.TurnManager;

import java.util.ArrayList;
import java.util.Collections;

public class BoardArray {

    private ArrayList<VolcanoCard> board;
    private ArrayList<VolcanoCard> caveCards;
    private ArrayList<VolcanoCard> nonCaveCards;

    private int volcanoCardCount;

    private int squaresPerVC;
    public static BoardArray instance;

    BoardArray(){

    }

    public static BoardArray getInstance(){
        if(instance == null){
            instance = new BoardArray();
        }
        return instance;
    }

    public void addVolcanoCards(int volcanoCardCount, int squaresPerVC){
        //Create a set of volcano cards that will be used for the game board
        for(int i = 0; i<volcanoCardCount; i++){
            if(i%2==0){
                caveCards.add(new VolcanoCard(squaresPerVC,true));
            }
            else{
                nonCaveCards.add(new VolcanoCard(squaresPerVC,true));
            }

        }

        //initialise volcano cards with relevant squares
        //Cave volcano cards
        caveCards.get(0).initialiseSquares(CreatureType.BABY_DRAGON,CreatureType.BAT,CreatureType.SPIDER);
        caveCards.get(1).initialiseSquares(CreatureType.SALAMANDER,CreatureType.SPIDER,CreatureType.BAT);
        caveCards.get(2).initialiseSquares(CreatureType.SPIDER,CreatureType.SALAMANDER,CreatureType.BABY_DRAGON);
        caveCards.get(3).initialiseSquares(CreatureType.BAT,CreatureType.SPIDER,CreatureType.BABY_DRAGON);

        //non-cave volcano cards
        nonCaveCards.get(0).initialiseSquares(CreatureType.SPIDER,CreatureType.BAT,CreatureType.SALAMANDER);
        nonCaveCards.get(1).initialiseSquares(CreatureType.BABY_DRAGON,CreatureType.SALAMANDER,CreatureType.BAT);
        nonCaveCards.get(2).initialiseSquares(CreatureType.BAT,CreatureType.BABY_DRAGON,CreatureType.SALAMANDER);
        nonCaveCards.get(3).initialiseSquares(CreatureType.SALAMANDER,CreatureType.BABY_DRAGON,CreatureType.SPIDER);


    }

    public void shuffleAndCombine(){
        //This only shuffles the cards in a fixed location, but should be okay for first sprint.
        Collections.shuffle(caveCards);
        Collections.shuffle(nonCaveCards);

        // Combine the lists, alternating between cave and non-cave cards
        for (int i = 0; i < Math.max(caveCards.size(), nonCaveCards.size()); i++) {
            if (i < caveCards.size()) {
                board.add(caveCards.get(i));
            }
            if (i < nonCaveCards.size()) {
                board.add(nonCaveCards.get(i));
            }
        }
    }

    public void addPosition(int volcanoCardCount, int squaresPerVC){
        //add the starting position to each volcano card
        for (int i = 0; i < volcanoCardCount*squaresPerVC; i += 3){
            board.get(i).setStartPosition(i);

        }
    }

}
