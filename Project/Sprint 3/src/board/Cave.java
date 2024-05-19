/**
 * Cave.java
 *
 * This file defines the Cave class, which represents a cave in the board game.
 * A cave is associated with a specific creature type and has a position on the board.
 * It can also be owned by a dragon token, symbolizing the dragon residing in that cave.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.board;

import src.Creature.Creature;
import src.actors.Actor;
import src.actors.DragonToken;
import src.gui.CavePanel;

import javax.swing.*;

/**
 * Represents a cave on the game board. Each cave has a unique creature type and
 * a position, and can be owned by a dragon token.
 */
public class Cave {
    private Creature creatureType;  // The type of creature associated with the cave
    private int cavePosition;           // The board position of the cave
    private DragonToken caveOwner;      // The dragon token that owns or resides in this cave

    private CavePanel cavePanel;        // GUI component associated with this cave

    /**
     * Constructs a Cave with a specified creature type and position.
     *
     * @param creature The type of creature associated with the cave.
     * @param cavePosition The position of the cave on the board.
     */
    Cave(Creature creature, int cavePosition) {
        this.creatureType = creature;
        this.cavePosition = cavePosition;
    }

    /**
     * Returns the GUI panel associated with this cave.
     *
     * @return The CavePanel instance.
     */
    public CavePanel getCavePanel() {
        return cavePanel;
    }

    /**
     * Sets the GUI panel for this cave.
     *
     * @param cavePanel The CavePanel to associate with this cave.
     */
    public void setCavePanel(CavePanel cavePanel) {
        this.cavePanel = cavePanel;
    }

    /**
     * Gets the position of the cave on the board.
     *
     * @return The cave's position as an integer.
     */
    public int getCavePosition() {
        return cavePosition;
    }

    /**
     * Gets the creature type associated with this cave.
     *
     * @return The Creature of the cave.
     */
    public Creature getCreatureType() {
        return creatureType;
    }
    public ImageIcon getCreatureImage() {return creatureType.getImage();}

    /**
     * Gets the dragon token that owns or resides in this cave.
     *
     * @return The DragonToken that owns the cave, or null if it's unoccupied.
     */
    public Actor getCaveOwner() {
        return caveOwner;
    }

    /**
     * Sets the dragon token that owns or resides in this cave.
     * This method also sets this cave as the dragon token's cave.
     *
     * @param caveOwner The DragonToken to set as the owner of the cave.
     */
    public void setCaveOwner(DragonToken caveOwner) {
        caveOwner.setCave(this);  // Link the owner to this cave
        this.caveOwner = caveOwner;
    }

    /**
     * Sets a new position for this cave on the game board.
     *
     * @param position The new position of the cave.
     */
    public void setCavePosition(int position) {
        cavePosition = position;
    }
}
