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
import src.gui.MenuPanel;
import src.gui.WindowPanel;

import java.util.ArrayList;
import java.util.Collections;

public class BoardArray {

    public void setBoard(ArrayList<VolcanoCard> board) {
        this.board = board;
    }

    private ArrayList<VolcanoCard> board = new ArrayList<>();

    private ArrayList<DragonCard> dragonCards = new ArrayList<>();

    private ArrayList<Square> squares = new ArrayList<>();

    private ArrayList<Creature> caveCreatures = new ArrayList<>();


    public static BoardArray instance;

    private int volcanoCardCount, squaresPerVC;
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
        int i = 0;
        int noCaves = 0;
        int alternatingCaveMaxBoardSize = 7;
        if (volcanoCardCount < alternatingCaveMaxBoardSize )
            while (i < volcanoCardCount){
                if (noCaves < 4){
                    board.add(new VolcanoCard(squaresPerVC,true));
                    noCaves++;
                }
                else{
                    board.add(new VolcanoCard(squaresPerVC,false));
                }
                i++;


            }
        else{
            while (i < volcanoCardCount) {
                if (i % 2 == 0 && noCaves < 4){
                    board.add(new VolcanoCard(squaresPerVC,true));
                    noCaves ++;
                }else{
                    board.add(new VolcanoCard(squaresPerVC,false));
                }
                i+= 1;
            }
        }

        initialiseCardsWithCreatures();
    }

    public void initialiseCardsWithCreatures(){
        BabyDragon babyDragon = new BabyDragon();
        Bat bat = new Bat();
        Salamander salamander = new Salamander();
        Spider spider = new Spider();

        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(babyDragon);
        creatures.add(bat);
        creatures.add(salamander);
        creatures.add(spider);

        for (VolcanoCard card: board){
            Collections.shuffle(creatures);
            card.initialiseSquares(creatures);
        }
        for(VolcanoCard volcanoCard: board) {
            squares.addAll(volcanoCard.getSquares());
        }
    }

    /**
     * Sets starting positions for each VolcanoCard and fixes their position.
     */
    public void addPosition(){
        //add the starting position to each volcano card
        int counter = 0;
//        board.get(0).setStartPosition(counter);
//        counter += squaresPerVC;
        //System.out.println(board.size());
        for (int i = 0; i < volcanoCardCount; i ++){
            board.get(i).setStartPosition(counter);
            counter += squaresPerVC;
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

    public void setVolcanoCardCount(int volcanoCardCount) {
        this.volcanoCardCount = volcanoCardCount;
    }

    public void setSquaresPerVC(int squaresPerVC) {
        this.squaresPerVC = squaresPerVC;
    }
}
