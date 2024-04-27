package Panels;

import Creature.*;

import javax.swing.*;
import java.awt.*;

public class VolcanoCard extends JPanel {
    Creature[] creatures;

    public VolcanoCard(Creature[] creatures) {
        this.creatures = creatures;
//        setLayout(new GridLayout(1, 3, 10, 10));
        for (int i = 0; i < creatures.length; i++)
        {
            add(new JLabel(creatures[i].getImage()));
        }
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }
}
