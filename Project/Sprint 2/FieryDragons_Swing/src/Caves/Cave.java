package Caves;

import javax.swing.*;
import java.awt.*;
import Creature.*;
import Panels.DragonToken;

public abstract class Cave extends JPanel{
    private final Creature creature;
    private final ImageIcon image;
    private int positionX, positionY;
    private DragonToken token;


    public Cave(ImageIcon image,  Creature creature, int positionX, int positionY)
    {
        this.image = image;
        this.creature = creature;
        this.positionX = positionX;
        this.positionY = positionY;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 100));
        initialiseCave();
    }

    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

    public void setToken(DragonToken token) {
        this.token = token;
        add(token, BorderLayout.CENTER);
        validate();
        repaint();
    }


    private void initialiseCave()
    {
        this.setBackground(Color.LIGHT_GRAY);  // Set a visible color

//        JLabel label = new JLabel(image);
        JLabel label = new JLabel(creature.getName()+"Cave");
        this.add(label, BorderLayout.SOUTH);
    }

    public Creature getCreature() {
        return creature;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
