/*
 * Game.java
 *
 * This file contains the main executable class for the Fiery Dragons board game.
 * It sets up the game environment, initializes game components, and starts the game interface.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src;

// Import necessary classes from other packages
import src.board.BoardArray;
import src.gui.GameBoard;

// Main class for the Fiery Dragons game
public class Game{

    /**
     * The main method is the entry point of the game application.
     * It initializes the game board, players, and starts the game interface.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize the singleton BoardArray instance
        BoardArray boardArray = BoardArray.getInstance();

        // Add volcano cards to the board and configure each with 3 squares
        boardArray.addVolcanoCards(8,3);

        // Shuffle the board setup for randomness and recombine
        boardArray.shuffleAndCombine();

        // Add positions to the board for volcano cards and squares
        boardArray.addPosition(8,3);

        GameBoard gameBoard = new GameBoard();
        gameBoard = new GameBoard();
        gameBoard.drawGameBoard();
    }
}