/**
 * Square.java
 *
 * Represents a single square on the game board. Each square is associated with a specific
 * CreatureType, which can influence the gameplay dynamics based on the creature present at
 * this square. This class provides methods to access and modify the square's properties.
 *
 * Author: Alex Ung
 * Date: 29/04/2024
 */

package src.board;

import src.Creature.Creature;

import javax.swing.*;

/**
 * Represents a square on the game board, with each square characterized by a creature.
 * Squares are fundamental elements of the board setup, influencing player movement
 * and interactions based on the creature type they contain.
 */
public class Square {
    private Creature creature; // The creature associated with this square
    private int position;          // The position of this square on the board

    /**
     * Constructs a Square with a specified creature.
     *
     * @param creature The creature type associated with this square.
     */
    Square(Creature creature) {
        this.creature = creature;
    }

    /**
     * Sets the position of this square on the board.
     *
     * @param position The position to set for this square.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Returns the position of this square on the board.
     *
     * @return The integer position of this square.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the creature type associated with this square.
     *
     * @return The CreatureType of the creature on this square.
     */
    public Creature getCreature() {
        return creature;
    }

    /**
     * Provides a string representation of the creature on this square,
     * typically used for UI display or debugging.
     *
     * @return A string representing the creature type.
     */
    public ImageIcon ui() {
        return this.creature.getImage();
    }
}
