package src.gui;

import src.board.Cave;

import javax.swing.*;
import java.awt.*;

public class CavePanel extends JPanel {
    private int x;
    private int y;
    private Cave cave;
    private JLabel creatureLabel;
    public CavePanel(int x, int y, Cave cave,Color bgColor) {
        if (cave == null) {
            throw new IllegalArgumentException("Cave cannot be null");
        }
        this.setBackground(bgColor);
        this.setOpaque(true); // Caves are not transparent
        this.cave = cave;
        creatureLabel = new JLabel(cave.getCreatureType().toString());
        add(creatureLabel);
        this.x = x;
        this.y = y;
    }
}
