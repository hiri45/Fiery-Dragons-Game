/*
 * DragonCardPanel.java
 *
 * This class extends JPanel to create a visual representation of a dragon card in the game.
 * It is designed to provide interactive functionality, such as responding to mouse clicks,
 * and display specific information about the dragon card.
 *
 * Author: Alex Ung
 * Date: 29/04/2024
 */

package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a graphical panel for displaying a dragon card in the game.
 * This panel is interactive, responding to mouse clicks to provide feedback about the card.
 */
public class DragonCardPanel extends JPanel {
    private String cardName; // The name of the dragon card displayed on this panel

    /**
     * Constructs a DragonCardPanel with a specified name for the card.
     * The panel is set up with a green background and a mouse listener to handle click events.
     *
     * @param cardName The name of the dragon card, used to identify it and provide interactive feedback.
     */
    public DragonCardPanel(String cardName) {
        this.cardName = cardName;

        // Set the appearance of the panel
        this.setBackground(new Color(0, 100, 0)); // Dark green color for dragon cards
        this.setOpaque(true); // Ensures the background color is visible

        // Add a mouse listener to provide interactivity
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action to perform on mouse click: show a dialog box with the card name
                JOptionPane.showMessageDialog(DragonCardPanel.this,
                        cardName + " clicked!",
                        "Card Interaction", // Title for the dialog box
                        JOptionPane.INFORMATION_MESSAGE); // Message type
            }
        });
    }

}
