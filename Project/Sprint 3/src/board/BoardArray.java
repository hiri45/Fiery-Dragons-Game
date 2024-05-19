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


import src.Creature.*;

import java.util.ArrayList;
import java.util.Collections;

public class BoardArray {

    private ArrayList<VolcanoCard> board = new ArrayList<>();
    private ArrayList<VolcanoCard> caveCards = new ArrayList<>();
    private ArrayList<VolcanoCard> nonCaveCards = new ArrayList<>();

    private ArrayList<DragonCard> dragonCards = new ArrayList<>();

    private ArrayList<Square> squares = new ArrayList<>();

    private ArrayList<Creature> caveCreatures = new ArrayList<>();

    public static BoardArray instance;

    private BoardArray(){
        caveCreatures.add(new BabyDragon());
        caveCreatures.add(new Salamander());
        caveCreatures.add(new Spider());
        caveCreatures.add(new Bat());
        Collections.shuffle(caveCreatures);
    }
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
        BabyDragon babyDragon = new BabyDragon();
        Bat bat = new Bat();
        Salamander salamander = new Salamander();
        Spider spider = new Spider();
        //initialise volcano cards with relevant squares
        //Cave volcano cards
        caveCards.get(0).initialiseSquares(babyDragon, bat, spider);
        caveCards.get(1).initialiseSquares(salamander,spider,bat);
        caveCards.get(2).initialiseSquares(spider,salamander,babyDragon);
        caveCards.get(3).initialiseSquares(bat,spider, babyDragon);

        //non-cave volcano cards
        nonCaveCards.get(0).initialiseSquares(spider ,bat, salamander);
        nonCaveCards.get(1).initialiseSquares(babyDragon, salamander,bat);
        nonCaveCards.get(2).initialiseSquares(bat,babyDragon,salamander);
        nonCaveCards.get(3).initialiseSquares(salamander,babyDragon,spider);
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
    public void setDragonCards(ArrayList<DragonCard> dragonCards) {
        this.dragonCards = dragonCards;
    }

    public ArrayList<Creature> getCaveCreatures() {
        return caveCreatures;
    }

    public void setCaveCreatures(Creature caveCreature) {
        caveCreatures.remove(caveCreature);
    }

    public void resetDragonCards()
    {
        for (DragonCard current : this.dragonCards) {
            if (!current.isFrontVisible()) {
                current.flip();
                current.setFlipped(false);
            }
        }
    }


}
