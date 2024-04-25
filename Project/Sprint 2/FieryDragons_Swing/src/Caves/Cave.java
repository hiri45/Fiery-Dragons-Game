package Caves;

import javax.swing.*;
import java.awt.*;
import Creature.*;

public abstract class Cave extends JPanel{
    private final Creature creature;
    private final ImageIcon image;


    public Cave(ImageIcon image,  Creature creature)
    {
        this.image = image;
        this.creature = creature;
        initialiseCave();
    }

    private void initialiseCave()
    {
        this.setBackground(Color.LIGHT_GRAY);  // Set a visible color
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  // Set a visible border

        // Optionally, add a label or other components
//        JLabel label = new JLabel(image);
        JLabel label = new JLabel(creature.getName()+"Cave");
        this.add(label);
    }

    public Creature getCreature() {
        return creature;
    }
}
