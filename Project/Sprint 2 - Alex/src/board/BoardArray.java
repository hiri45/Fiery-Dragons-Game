package src.board;


import java.util.ArrayList;
import java.util.Collections;

public class BoardArray {

    private ArrayList<VolcanoCard> board = new ArrayList<>();
    private ArrayList<VolcanoCard> caveCards = new ArrayList<>();
    private ArrayList<VolcanoCard> nonCaveCards = new ArrayList<>();

    private int volcanoCardCount;

    private int squaresPerVC;
    public static BoardArray instance;

    BoardArray(){

    }

    public ArrayList<VolcanoCard> getBoard() {
        return board;
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
//                board.add(new VolcanoCard(squaresPerVC,true));
                caveCards.add(new VolcanoCard(squaresPerVC,true));
            }
            else{
                nonCaveCards.add(new VolcanoCard(squaresPerVC,false));
//                board.add(new VolcanoCard(squaresPerVC,false));
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

// Determine the maximum size to iterate to
        int maxSize = caveCards.size() + nonCaveCards.size();

        // Alternate between cave and non-cave cards
        for (int i = 0, j = 0, k = 0; i < maxSize; i++) {
            if (i % 2 == 0 && j < caveCards.size()) {
                // On even iterations, add from caveCards if available
                board.add(caveCards.get(j));
                j++;
            } else if (k < nonCaveCards.size()) {
                // On odd iterations, or if caveCards are exhausted, add from nonCaveCards
                board.add(nonCaveCards.get(k));
                k++;
            }
        }
    }

    public void addPosition(int volcanoCardCount, int squaresPerVC){
        //add the starting position to each volcano card
        board.get(0).setStartPosition(0);
        for (int i = 2; i < volcanoCardCount; i += 3){
            board.get(i).setStartPosition(i);

        }
    }

}
