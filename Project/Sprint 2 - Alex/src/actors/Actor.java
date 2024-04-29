/**
 * Actor.java
 *
 * This file contains the definition of the Actor class, which represents a player or character
 * in the game. It encapsulates the properties and behaviors common to all actors, such as
 * position handling and turn management.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.actors;

// Import necessary classes
import src.board.Cave;

/**
 * Represents a game actor, such as a player or any character that can take turns and has a position.
 */
public class Actor {
    // Fields to hold the starting position and current position of the actor
    private int startingPosition;
    private int position;

    // Unique identifier for the actor
    private int id;

    // Flag to indicate whether it is this actor's turn
    private boolean turn;

    /**
     * Constructor for Actor class.
     *
     * @param id The unique identifier for this actor.
     */
    Actor(int id) {
        this.id = id;
    }

    /**
     * Returns the current position of the actor on the board.
     *
     * @return The current position as an integer.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the current position of the actor on the board.
     *
     * @param position The new position of this actor.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Returns the unique identifier of the actor.
     *
     * @return The ID as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this actor.
     *
     * @param id The new ID for this actor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Placeholder method for actors to perform their turn actions.
     * This should be overridden in subclasses to implement specific behavior.
     */
    public void playTurn() {
        // This method should be overridden in subclass
    }

    /**
     * Sets the starting position for this actor.
     *
     * @param startingPosition The starting position on the game board.
     */
    public void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }

    /**
     * Sets whether it is currently this actor's turn.
     *
     * @param turn true if it is this actor's turn, false otherwise.
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}