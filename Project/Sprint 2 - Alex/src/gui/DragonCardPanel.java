package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragonCardPanel extends JPanel {
    private String cardName;

    public DragonCardPanel(String cardName) {
        this.cardName = cardName;
        this.setBackground(new Color(0, 100, 0)); // Green color for dragon cards
        this.setOpaque(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Show a dialog with a message on click, using the cardName
                JOptionPane.showMessageDialog(DragonCardPanel.this, cardName + " clicked!");
            }
        });
    }

    // You can add more methods here if you need specific functionality for your DragonCardPanel
}