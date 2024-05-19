/**
 * SquarePanel.java
 *
 * Extends JPanel to represent a square on the game board. Each square is associated with a specific
 * game square object that includes details such as the creature type it contains. This panel
 * visually represents the square.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.gui;

import src.board.Square;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a graphical component that displays a square from the game board.
 * Each SquarePanel is associated with a specific Square object, which it visually represents.
 */
public class SquarePanel extends JPanel {
    private Square square; // The game logic square this panel represents
    private int x;         // X-coordinate of the panel's position
    private int y;         // Y-coordinate of the panel's position

    /**
     * Constructs a SquarePanel with the specified Square object and position.
     *
     * @param square The Square object this panel will represent.
     * @param x The x-coordinate where the panel is located.
     * @param y The y-coordinate where the panel is located.
     */
    public SquarePanel(Square square, int x, int y) {
        this.square = square;
        this.x = x;
        this.y = y;

        // Visual customization of the panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Outline the square with a black border
        setOpaque(false); // Set the square's background to be transparent
    }

    /**
     * Returns the Square object associated with this panel.
     *
     * @return The Square object represented by this panel.
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Provides the x-coordinate of this panel's position on the game board.
     *
     * @return The x-coordinate.
     */
    public int getXCoordinate() {
        return x;
    }

    /**
     * Provides the y-coordinate of this panel's position on the game board.
     *
     * @return The y-coordinate.
     */
    public int getYCoordinate() {
        return y;
    }
}
