package src.gui;

import src.actors.Actor;
import src.actors.DragonToken;
import javax.swing.*;
import java.awt.*;

public class DragonTokenPanel extends JPanel {
    private int x;
    private int y;

    private int offsetX;
    private int offsetY;
    private int squareSize = 50;
    private DragonToken dragonToken;
    private int tokenDiameter = 300;  // Diameter of the dragon token
    private JLabel label;

    public DragonTokenPanel(int x,int y,DragonToken dragonToken,int offsetX, int offsetY) {
        this.dragonToken = dragonToken;
        this.x = x;
        this.y = y;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        setOpaque(false);  // Make the JPanel transparent
        label = new JLabel(String.valueOf(dragonToken.getId()));
        add(label);

    }

    public DragonToken getDragonToken() {
        return dragonToken;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    private void updatePosition() {
        setLocation(this.getX() * squareSize + offsetX, this.getY() * squareSize + offsetY);
    }

    public void moveDragonToken(int newX, int newY) {
        this.setX(newX);
        this.setY(newY);
        updatePosition();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, 50, 50);
    }
}
