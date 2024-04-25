package src.gui;

import src.actors.Actor;
import src.actors.DragonToken;
import javax.swing.*;
import java.awt.*;

public class DragonTokenPanel extends JPanel {
    private DragonToken dragonToken;
    private int tokenDiameter = 300;  // Diameter of the dragon token
    private JLabel label;

    public DragonTokenPanel(DragonToken dragonToken) {
        this.dragonToken = dragonToken;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);  // Make the JPanel not transparent
        label = new JLabel(String.valueOf(dragonToken.getId()));
        add(label);
    }


//    public void moveToken(int x, int y) {
//        // Update the dragon token's position
//        dragonToken.setX(x);
//        dragonToken.setY(y);
//        repaint();  // Repaint the panel to reflect the new position
//    }
}
