/**
 * File: BoardArray.java
 *
 * Description:
 * Manages the board layout for the Fiery Dragons game, including the creation, shuffling,
 * and organization of VolcanoCards which may or may not contain caves. This class ensures
 * that the board is set up correctly with an even distribution of cave and non-cave cards.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */
package src.board;


import java.util.ArrayList;
import java.util.Collections;

public class BoardArray {

    private ArrayList<VolcanoCard> board = new ArrayList<>();
    private ArrayList<VolcanoCard> caveCards = new ArrayList<>();
    private ArrayList<VolcanoCard> nonCaveCards = new ArrayList<>();

    private ArrayList<Square> squares = new ArrayList<>();

    public static BoardArray instance;

    /**
     * Singleton instance access method.
     * @return Single instance of BoardArray.
     */
    public static BoardArray getInstance(){
        if(instance == null){
            instance = new BoardArray();
        }
        return instance;
    }
    /**
     * Creates and initializes VolcanoCards with or without caves, and assigns them creatures.
     * @param volcanoCardCount Total number of volcano cards to create.
     * @param squaresPerVC Number of squares each VolcanoCard will hold.
     */
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
        // Initialize volcano cards with creatures
        initialiseCardsWithCreatures();
    }
    private void initialiseCardsWithCreatures(){
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




    /**
     * Shuffles cave and non-cave cards separately and combines them into the main board array.
     */
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
        for(VolcanoCard volcanoCard: this.getBoard()){
            squares.addAll(volcanoCard.getSquares());
        }
    }
    /**
     * Sets starting positions for each VolcanoCard and fixes their position.
     * @param volcanoCardCount Total number of volcano cards.
     * @param squaresPerVC Number of squares per volcano card.
     */
    public void addPosition(int volcanoCardCount, int squaresPerVC){
        //add the starting position to each volcano card
        int counter = 0;
        board.get(0).setStartPosition(counter);
        counter += 3;
        for (int i = 1; i < volcanoCardCount; i ++){
            board.get(i).setStartPosition(counter);
            counter += 3;

        }

        for (int i = 0; i < volcanoCardCount; i ++){
            board.get(i).setFixedPositions();
        }


    }
    /**
     * Returns the list of squares across all volcano cards on the board.
     * @return ArrayList of squares.
     */
    public ArrayList<Square> getSquares() {
        return squares;
    }
    public ArrayList<VolcanoCard> getBoard() {
        return board;
    }


}
