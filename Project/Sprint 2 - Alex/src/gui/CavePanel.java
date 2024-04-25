package src.gui;

import src.board.Cave;

import javax.swing.*;
import java.awt.*;

public class CavePanel extends JPanel {
    private Cave cave;
    private JLabel creatureLabel;
    public CavePanel(Cave cave,Color bgColor) {
        this.setBackground(bgColor);
        this.setOpaque(true); // Caves are not transparent
        this.cave = cave;
        creatureLabel = new JLabel(cave.getCreatureType().toString());
        add(creatureLabel);

    }
}
