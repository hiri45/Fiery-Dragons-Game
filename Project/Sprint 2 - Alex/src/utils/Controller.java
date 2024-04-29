/**
 * File: Controller.java
 *
 * Description:
 * Manages the game's user interface interactions and the state transitions based on player actions.
 * The Controller class serves as a mediator between the game logic encapsulated by various game
 * elements (like DragonToken) and the graphical user interface managed through the GameBoard.
 *
 * Author: Alex Ung
 * Date: 29/04/2024
 */
package src.utils;

import src.actors.DragonToken;
import src.gui.GameBoard;

/**
 * Controls various game actions and manages the interface between the game logic and the GUI.
 *
 * This class encapsulates methods to manage the game board and perform operations that affect
 * the game state, such as updating positions of dragon tokens and managing the visibility state
 * of dragon cards.
 */
public class Controller {
    private GameBoard gameBoard;

    /**
     * Unflips all dragon cards on the game board.
     *
     * This method is intended to reset the visibility state of dragon cards to their default state,
     * but currently does not implement any specific functionality.
     */
    public void unflipDragonCards() {
        // Placeholder for method implementation
    }

    /**
     * Updates the position of a specific dragon token on the game board.
     *
     * This method should contain logic to update the position of a dragon token based on the game's rules,
     * but currently does not implement any specific functionality.
     *
     * @param dragonToken The dragon token whose position needs to be updated.
     */
    public void updateDragonTokenPosition(DragonToken dragonToken) {
        // Placeholder for method implementation
    }

    /**
     * Creates and displays the game board.
     *
     * This method initializes a new game board and makes it visible. It acts as the entry point
     * for rendering the initial game interface.
     */
    public void createGameBoard() {
        gameBoard = new GameBoard();
        gameBoard.drawGameBoard(); // Invoke the method to set up and display the game board.
    }
}
