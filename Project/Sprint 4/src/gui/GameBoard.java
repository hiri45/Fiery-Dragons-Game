/**
 * GameBoard.java
 *
 * Provides the graphical interface for displaying the game board in the Fiery Dragon Board Game.
 * This class extends JPanel and contains methods to initialize and display the game board within a JFrame.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Manages the graphical representation of the game board for the Fiery Dragon Board Game.
 * It initializes and displays the main game window, which contains all the necessary components
 * of the game including squares, tokens, and interactive elements.
 */
public class GameBoard extends JPanel {

    /**
     * Creates and displays the main game window.
     * This method sets up the JFrame that hosts the game panel and configures its properties
     * such as size, close operation, and visibility.
     */
    public void drawGameBoard() {
        JFrame frame = new JFrame("Fiery Dragon Board Game"); // Create a new JFrame with the game's title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
        frame.setPreferredSize(new Dimension(1000, 900));

        MenuPanel menuPanel = MenuPanel.getInstance(frame);

        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(menuPanel.menuButtonPanel, BorderLayout.CENTER);

        frame.pack(); // Size the frame so that all its contents are at or above their preferred sizes
        frame.setVisible(true); // Make the frame visible
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }
}
