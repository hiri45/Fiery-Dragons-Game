/**
 * DragonToken.java
 *
 * This file defines the DragonToken class, which represents a dragon token in the game.
 * It extends the Actor class with additional attributes and behaviors specific to the dragon tokens,
 * such as cave-related properties and movement functionalities.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.actors;

import src.board.Cave;
import src.gui.DragonTokenPanel;
import src.utils.MovementManager;
import src.utils.PlayerManager;

/**
 * Represents a dragon token in the game, containing specific game mechanics related to the dragon's
 * interaction with the game board, such as movements and cave interactions.
 */
public class DragonToken extends Actor {

    private Cave cave;  // Reference to the cave associated with the dragon token
    private DragonTokenPanel dragonTokenPanel;  // GUI component associated with the dragon token

    private boolean inCave;  // Flag indicating whether the dragon token is currently in the cave
    private int totalSquaresMoved;  // Counter for the total number of squares moved by the token

    private int cycleTracker;
    /**
     * Constructor for the DragonToken class.
     *
     * @param id The unique identifier for the dragon token.
     */
    public DragonToken(int id) {
        super(id);
        inCave = true;  // Tokens start in the cave
        totalSquaresMoved = 0;  // Initial move count is zero
    }

    /**
     * Returns the graphical user interface panel associated with this dragon token.
     *
     * @return The DragonTokenPanel associated with this token.
     */
    public DragonTokenPanel getDragonTokenPanel() {
        return dragonTokenPanel;
    }

    /**
     * Sets the graphical user interface panel for this dragon token.
     *
     * @param dragonTokenPanel The DragonTokenPanel to associate with this token.
     */
    public void setDragonTokenPanel(DragonTokenPanel dragonTokenPanel) {
        this.dragonTokenPanel = dragonTokenPanel;
    }

    /**
     * Gets the cave associated with this dragon token.
     *
     * @return The cave object linked to this token.
     */
    public Cave getCave() {
        return cave;
    }

    /**
     * Sets the cave for this dragon token.
     *
     * @param cave The cave to associate with this token.
     */
    public void setCave(Cave cave) {
        this.cave = cave;
    }

    /**
     * Checks if the dragon token is currently in a cave.
     *
     * @return true if the token is in the cave, false otherwise.
     */
    public boolean isInCave() {
        return inCave;
    }

    /**
     * Retrieves the current value of the cycle tracker for a dragon token.
     * The cycle tracker can be used to monitor how many complete loops a token has made around the board.
     *
     * @return The current cycle tracker value.
     */
    public int getCycleTracker() {
        return cycleTracker;
    }
    /**
     * Sets the cycle tracker value for a dragon token. This method is used to update the cycle tracker,
     * which counts how many complete loops have been made around the board
     *
     * @param cycleTracker The new value to set for the cycle tracker.
     */
    public void setCycleTracker(int cycleTracker) {
        this.cycleTracker = cycleTracker;
    }

    /**
     * Gets the total number of squares moved by this token.
     *
     * @return The total number of squares moved.
     */
    public int getTotalSquaresMoved() {
        return totalSquaresMoved;
    }

    /**
     * Adds the number of squares moved to the total count of squares moved.
     *
     * @param totalSquaresMoved The number of squares to add to the total.
     */
    public void addMovement(int totalSquaresMoved) {
        this.totalSquaresMoved += totalSquaresMoved;
    }

    /**
     * Sets the in-cave status of the dragon token.
     *
     * @param inCave true to place the token in the cave, false to indicate it is outside the cave.
     */
    public void setInCave(boolean inCave) {
        this.inCave = inCave;
    }

    /**
     * Overridden playTurn method from Actor class to implement specific dragon token turn actions.
     * This includes movement decisions and card interactions.
     */
    @Override
    public void playTurn() {
        super.playTurn();  // Call to superclass method in case it has important functionality
        int noPositions = 1;  // Example movement, should be determined by game logic or player input
        MovementManager movementManager = MovementManager.getInstance();
        if (movementManager.canMove(this, noPositions)) {
            movementManager.updatePosition(this, noPositions);
        }
        // Additional game logic such as flipping a dragon card can be implemented here
    }

    /**
     * Moves the dragon token by a specified number of positions.
     *
     * @param noPositions The number of positions to move the token.
     */
    public void move(int noPositions) {
        MovementManager movementManager = MovementManager.getInstance();
        if (movementManager.canMove(this, noPositions)) {
            movementManager.updatePosition(this, noPositions);
        }else {
            PlayerManager.getInstance().updatePlayerTurn();
        }
    }
}
