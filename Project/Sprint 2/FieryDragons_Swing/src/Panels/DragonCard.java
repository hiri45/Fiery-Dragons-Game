package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Creature.*;

class DragonCard extends JPanel {
    private final ImageIcon front = new ImageIcon("src/Images/dragon card resize.png");
    private final String back;
    private boolean isFrontVisible = true;
    private final int creatureAmount;
    private final Creature creature;

    public DragonCard(Creature creature, int creatureAmount) {
        this.creature = creature;
        this.back = creature.getName()+ " x"+ creatureAmount;
        this.creatureAmount = creatureAmount;
        setLayout(new BorderLayout());
        updateAppearance();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flip();
            }
        });
    }

    public void flip() {
        isFrontVisible = !isFrontVisible;
        updateAppearance();
    }

    private void updateAppearance() {
        removeAll(); // Clear any existing content
        JLabel label;
        if(isFrontVisible)
        {
            label = new JLabel(front);
        }
        else
        {
            label = new JLabel(back);
        }
//        JLabel label = new JLabel(isFrontVisible ? front : back);
        add(label);
        revalidate();
        repaint();
    }

    public int getCreatureAmount() {
        return creatureAmount;
    }


    public boolean isFrontVisible() {
        return isFrontVisible;
    }

    public void setFrontVisible(boolean frontVisible) {
        isFrontVisible = frontVisible;
    }


    public Creature getCreature() {
        return creature;
    }
}
