package src.gui;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {
    public SquarePanel() {
        // Set the border to replicate the drawn rectangle
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setOpaque(false); // Make the square transparent
    }

}