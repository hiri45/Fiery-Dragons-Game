import Creature.Creature;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VolcanoCard extends JPanel {
    public VolcanoCard()
    {
        setLayout(new GridLayout(1,3,10,10));
        add(new Label("hi"));
        add(new Label("test"));
        add(new Label("test"));
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }
}
