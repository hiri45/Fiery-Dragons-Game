package Panels;

import java.awt.*;
import javax.swing.*;

//the Token that the player will move. Currently distinguished by colours
public class DragonToken extends JComponent {
    private int positionX, positionY;
    private Color color;


    public DragonToken(int positionX, int positionY, Color color) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
        setOpaque(false);
    }

    public void setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        setLocation(positionX, positionY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0,50,50); // Ensure the oval fills the token's component
    }
}