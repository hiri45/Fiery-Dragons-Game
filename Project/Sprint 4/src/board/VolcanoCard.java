/**
 * VolcanoCard.java
 *
 * Represents a Volcano Card in the game that can contain multiple squares, each with a unique creature.
 * These cards can optionally contain a cave, adding complexity and variety to the gameplay.
 * The class provides methods for initializing squares and caves and managing their positions on the card.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.board;

import src.Creature.Creature;
import src.Creature.Salamander;
import src.actors.DragonToken;
import src.utils.PlayerManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a card on the game board which includes multiple squares and potentially a cave.
 * Each square on a Volcano Card can have a creature associated with it, affecting the gameplay.
 */
public class VolcanoCard {
    private int squareCount;               // Number of squares on this card
    private boolean hasCave;               // Indicates if this card contains a cave
    private ArrayList<Square> squares;     // List of squares on this card
    private Cave cave;                     // Cave associated with this card, if any

    public int getStartPosition() {
        return startPosition;
    }

    private int startPosition;             // Position of the first square on the card

    /**
     * Constructs a VolcanoCard with specified number of squares and cave presence.
     *
     * @param squareCount The number of squares on the card.
     * @param hasCave Whether the card has a cave.
     */
    public VolcanoCard(int squareCount, boolean hasCave) {
        this.squareCount = squareCount;
        this.hasCave = hasCave;
        this.squares = new ArrayList<>();
    }

    /**
     * Returns the list of squares on this VolcanoCard.
     *
     * @return An ArrayList of squares.
     */
    public ArrayList<Square> getSquares() {
        return squares;
    }

    /**
     * Sets the starting position for the first square on this card.
     *
     * @param startPosition The board position of the first square.
     */
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    /**
     * Checks whether this card contains a cave.
     *
     * @return true if there is a cave, false otherwise.
     */
    public boolean hasCave() {
        return hasCave;
    }

    /**
     * Initializes the squares on this card with specific creature types.
     * Each square is created with a designated creature.
     *
     * @param creatures a list of possible creatures for a square.
     */
    public void initialiseSquares(ArrayList<Creature> creatures) {
        int counter = 0;
        for (int i=0;i<squareCount; i++){
            if (counter == creatures.size()){
                counter = 0;
            }
            squares.add(new Square(creatures.get(counter)));
            counter++;
        }
    }

    /**
     * Initializes a cave on this card if it is supposed to have one.
     * The cave is associated with the creature and position of the middle square.
     */
    public void initialiseCave() {
        BoardArray boardArray = BoardArray.getInstance();
        Random random = new Random();
        int min = 0;
        int max = squareCount-1;
        int randomNumber = random.nextInt(max);
        if (this.hasCave && cave == null) {
            Creature creature = boardArray.getCaveCreatures().get(0);
            cave = new Cave(creature, squares.get(randomNumber).getPosition());

            boardArray.setCaveCreatures(creature);

        }
    }

    /**
     * Sets fixed positions for each square on the card and initializes the cave if necessary.
     * Positions are set starting from `startPosition` and incrementing sequentially.
     */
    public void setFixedPositions() {
        int cardPosition = startPosition;
        for (Square square : squares) {
            square.setPosition(cardPosition++);
        }
        if (this.hasCave) {
            initialiseCave();
        }
    }

    /**
     * Gets the cave associated with this card, if any.
     *
     * @return The cave object or null if no cave is associated.
     */
    public Cave getCave() {
        return cave;
    }
}
