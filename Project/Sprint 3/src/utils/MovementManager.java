/**
 * File: MovementManager.java
 *
 * Description:
 * Manages the logic for moving dragon tokens on the game board within the Fiery Dragons game.
 * This class is responsible for checking move validity, updating token positions, and managing
 * the interaction between game rules and user actions.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */
package src.utils;

import src.actors.DragonToken;
import src.board.BoardArray;
import src.board.DragonCard;
import src.gui.WindowPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MovementManager {
    private WindowPanel windowPanel;
    private static MovementManager instance;

    public BoardArray boardArray = BoardArray.getInstance();


    /**
     * Retrieves the single instance of MovementManager, creating it if it does not yet exist.
     * This method implements the Singleton design pattern to ensure that only one instance
     * of MovementManager is used throughout the game.
     *
     * @return The single instance of MovementManager.
     */
    public static MovementManager getInstance() {
        if (instance == null) {
            instance = new MovementManager();
        }
        return instance;
    }

    /**
     * Sets the WindowPanel that this manager will interact with.
     *
     * @param windowPanel The WindowPanel to be used for game interactions.
     */
    public void setWindowPanel(WindowPanel windowPanel) {
        this.windowPanel = windowPanel;
    }

    /**
     * Determines if a dragon token can move the specified number of positions on the board.
     *
     * @param dragonToken The dragon token attempting to move.
     * @param noPositions The number of positions to move.
     * @return true if the move is possible, false otherwise.
     */
    public boolean canMove(DragonToken dragonToken, int noPositions) {
        PlayerManager playerManager = PlayerManager.getInstance();
        int newPosition = (dragonToken.getPosition() + noPositions) % windowPanel.getBoardPanels().size();


        //If the current token is in the cave, remove one position from the number of positions to move
        //because cave and square in front both have the same position
        if (dragonToken.isInCave()) {
            newPosition = dragonToken.getPosition() + noPositions - 1;
        } else if (this.canWin(dragonToken, noPositions)) {

            this.endGame(dragonToken);
        }
        for (DragonToken player : playerManager.getPlayers()) {
            if (player != dragonToken && player.getPosition() == newPosition && (!player.isInCave() || dragonToken.isInCave())) {
                return false;// Block move if another token is in the target position.
            }
        }
        return noPositions >= 0 || dragonToken.getCave().getCavePosition() >= noPositions;// Block backward movement into the cave if not aligned with the cave entry rules.

    }

    public boolean canWin(DragonToken dragonToken, int noPositions) {
        int newPosition = dragonToken.getPosition() + noPositions;
        int boardSize = boardArray.getSquares().size();
        int tempVar = (newPosition) % (boardSize);

        if (tempVar <= 2 && newPosition >= (boardSize - 1)) {
            newPosition = tempVar;

        }
        if (noPositions < 0) {
            return false;
        }
        if (newPosition == dragonToken.getCave().getCavePosition() + 1 && !dragonToken.isInCave()) {
            return true;
        }

        return false;
    }

    /**
     * Updates the position of a dragon token based on the number of positions to move.
     * Handles wrapping around the board and interactions with caves.
     *
     * @param dragonToken The dragon token to update.
     * @param noPositions The number of positions to move the token.
     */
    public void updatePosition(DragonToken dragonToken, int noPositions) {
        if (canWin(dragonToken, noPositions)) {
            windowPanel.moveToken(dragonToken.getDragonTokenPanel(), dragonToken.getStartingPosition());
        } else {
            int newPosition;
            if (noPositions > 0) {
                newPosition = forwardsMovement(dragonToken, noPositions);
            } else {
                newPosition = backwardsMovement(dragonToken, noPositions);
            }
            dragonToken.setPosition(newPosition);
            dragonToken.addMovement(noPositions);

            // Update UI
            windowPanel.moveToken(dragonToken.getDragonTokenPanel(), noPositions);
        }


    }

    /**
     * Calculates the new position of a dragon token when moving backwards on the board.
     * This method ensures that movement is wrapped around correctly when reaching the start of the board array.
     * Additionally, it handles special conditions when the dragon token starts in the cave.
     *
     * @param dragonToken The dragon token whose position is being updated.
     * @param noPositions The number of positions to move backwards (negative value).
     * @return The new position of the dragon token after accounting for backwards movement and wrap-around.
     */
    public int forwardsMovement(DragonToken dragonToken, int noPositions) {
        int newPosition = dragonToken.getPosition() + noPositions;

        int boardSize = boardArray.getSquares().size();
        int tempVar = (newPosition) % (boardSize);

        int currentPosition = dragonToken.getPosition();
        // Reset position if wrapping around the board.
        if (tempVar <= 2 && newPosition >= (boardSize - 1)) {
            newPosition = tempVar;
            if (dragonToken.getCycleTracker() < 1) {
                dragonToken.setCycleTracker(dragonToken.getCycleTracker() + 1);
            }
        } else {
            //if the player's position is still in their cave move out of the cave
            if (dragonToken.isInCave()) {
                newPosition = currentPosition;
                if (noPositions > 1) {
                    newPosition = currentPosition + noPositions - 1; // Adjust for movement out of the cave.
                }
            }
        }
        return newPosition;
    }

    /**
     * Calculates the new position for a dragon token moving backwards on the game board.
     * This method ensures correct handling when the token wraps around the start of the board,
     * and accounts for tokens starting their movement from a cave.
     *
     * @param dragonToken The dragon token whose position is being updated.
     * @param noPositions The number of positions to move backwards (negative value).
     * @return The new position on the board, adjusted for backward movement and wrap-around.
     */

    public int backwardsMovement(DragonToken dragonToken, int noPositions) {
        int cavePos = dragonToken.getCave().getCavePosition();
        int boardSize = boardArray.getSquares().size();
        int newPosition = dragonToken.getPosition() + noPositions;

        //if the player is already in the cave, don't move backwards anymore
        if (dragonToken.isInCave()) {
            return cavePos;
        }
        if (newPosition < 0) {
            //Makes sure the player can't have negative cycles
            if (dragonToken.getCycleTracker() - 1 < 0) {
                return cavePos - 1;
            }
            dragonToken.setCycleTracker(dragonToken.getCycleTracker() - 1);
            return boardSize + newPosition;
        }

        return newPosition;
    }

    public void endGame(DragonToken dragonToken) {
        windowPanel.moveToken(dragonToken.getDragonTokenPanel(), dragonToken.getStartingPosition());

        // Create the JOptionPane with a custom "OK" button
        Object[] options = {"OK"};
        int result = JOptionPane.showOptionDialog(
                null,
                "GG Player " + dragonToken.getId() + " has won the game!",
                "Game Over",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Check if the "OK" button was pressed
        if (result == JOptionPane.OK_OPTION) {
            // Exit the application
            System.exit(0);
        }
    }
}