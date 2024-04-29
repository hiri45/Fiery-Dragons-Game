/*
 * DragonCardArray.java
 *
 * Manages a collection of dragon cards in a centralized location using the singleton design pattern.
 * This ensures that there is only one consistent view of the dragon cards throughout the game,
 * simplifying management and access to these cards. The class is responsible for initializing and
 * storing all the dragon cards used during the game.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.board;

import java.util.ArrayList;

/**
 * Manages the collection of dragon cards used in the game. This class implements the singleton
 * pattern to ensure a single instance of the dragon card array throughout the game's lifecycle.
 */
public class DragonCardArray {
    // List to store the dragon cards
    private ArrayList<DragonCardArray> dragonCardArray;

    // The single instance of DragonCardArray for singleton pattern
    private static DragonCardArray instance;

    // Constant representing the number of dragon cards to be created
    private static final int dragonCardCount = 16;

    /**
     * Private constructor to prevent instantiation outside of the getInstance method,
     * ensuring this class adheres to the singleton pattern.
     */
    private DragonCardArray() {
        dragonCardArray = new ArrayList<>();
    }

    /**
     * Provides global access to the single instance of the DragonCardArray. If the instance
     * does not exist, it initializes a new instance.
     *
     * @return The single, static instance of DragonCardArray.
     */
    public static DragonCardArray getInstance() {
        if (instance == null) {
            instance = new DragonCardArray();
        }
        return instance;
    }

    /**
     * Creates and stores a predefined number of dragon cards in the dragon card array.
     * This method populates the array with dragon card objects, ready to be used in the game.
     */
    public void createDragonCards() {
        for (int i = 0; i < dragonCardCount; i++) {
            // Assuming the existence of a DragonCard class, uncomment the following line
            // to add new DragonCard instances to the array.
            // dragonCardArray.add(new DragonCard());
        }
    }
}
