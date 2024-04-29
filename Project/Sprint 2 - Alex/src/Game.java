/*
 * Game.java
 *
 * This file contains the main executable class for the Fiery Dragons board game.
 * It sets up the game environment, initializes game components, and starts the game interface.
 *
 * Author: Alex Ung
 * lasd modified: 29/04/2024
 */

package src;

// Import necessary classes from other packages
import src.board.BoardArray;
import src.board.DragonCardArray;
import src.utils.Controller;
import src.utils.PlayerManager;

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

        // Initialize the player count and configure players
        int playerCount = 4; // Number of players
        PlayerManager playerManager = PlayerManager.getInstance(); // Get the singleton instance of PlayerManager
        playerManager.addPlayers(playerCount); // Add players to the game

        // Initialize the array of dragon cards, which are used as chit cards in the game
        DragonCardArray dragonCardArray = DragonCardArray.getInstance();

        // Initialize the UI controller
        Controller uiController = new Controller();
        // Create and display the game board using the UI controller
        uiController.createGameBoard();
    }
}