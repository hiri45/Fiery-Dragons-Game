package src.gui;

import javax.swing.*;
import java.awt.*;

public class CavePanel extends JPanel {
    public CavePanel(Color bgColor) {
        this.setBackground(bgColor);
        this.setOpaque(true); // Caves are not transparent
    }
}
