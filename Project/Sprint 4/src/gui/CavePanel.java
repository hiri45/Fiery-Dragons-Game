/**
 * CavePanel.java
 *
 * This class extends JPanel to create a visual representation of a cave on the game board.
 * It is responsible for displaying the type of creature associated with a specific cave,
 * using background colors and labels to enhance the visual experience for players.
 *
 * Author: Alex Ung
 * Date: 29/04/2024
 */

package src.gui;

import src.board.Cave;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a graphical panel for displaying a cave in the game.
 * Each cave panel is associated with a specific cave object and shows the creature type
 * within that cave.
 */
public class CavePanel extends JPanel {
    private int x;            // The x-coordinate of the panel
    private int y;            // The y-coordinate of the panel
    private Cave cave;        // The cave object associated with this panel
    private JLabel creatureLabel; // Label to display the creature type in the cave

    /**
     * Constructs a CavePanel at specified coordinates with a specific cave.
     *
     * @param x The x-coordinate where the panel is located.
     * @param y The y-coordinate where the panel is located.
     * @param cave The cave object this panel is representing.
     * @param bgColor The background color for the panel.
     * @throws IllegalArgumentException if the provided cave object is null.
     */
    public CavePanel(int x, int y, Cave cave, Color bgColor) {
        if (cave == null) {
            throw new IllegalArgumentException("Cave cannot be null");
        }
        this.x = x;
        this.y = y;
        this.cave = cave;

        // Set the appearance of the panel
        this.setBackground(bgColor);
        this.setOpaque(true); // Ensures the background color is not transparent

        // Create and add a label displaying the creature type from the cave
        creatureLabel = new JLabel(cave.getCreatureImage());
        this.add(creatureLabel);
        refreshCavePanel();
    }

    /**
     * Returns the x-coordinate of this panel.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this panel.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    public Cave getCave() {
        return cave;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    /**
     * refreshes the cave panels when loading up a game to ensure that the cave panels reflect the creatures on the save text file
     * */
    public void refreshCavePanel() {
        this.removeAll();
        creatureLabel = new JLabel(cave.getCreatureImage());
        this.x = getX();
        this.y = getY();
        this.add(creatureLabel);
        this.revalidate();
        this.repaint();
    }
}
