/*
 * DragonTokenPanel.java
 *
 * Provides a graphical representation of a dragon token within a game. This panel is responsible
 * for rendering a dragon token at a specific location on a graphical interface, handling both the
 * visual representation and the movement logic.
 *
 * Author: Alex Ung
 * Last Modified: 29/04/2024
 */

package src.gui;

import src.actors.DragonToken;
import javax.swing.*;
import java.awt.*;

/**
 * A JPanel subclass designed to display a dragon token, with functionality to handle its
 * movement and visual updates on a game board.
 */
public class DragonTokenPanel extends JPanel {
    private int x;              // X-coordinate of the panel on the board
    private int y;              // Y-coordinate of the panel on the board
    private int offsetX;        // Additional offset for the X-coordinate
    private int offsetY;        // Additional offset for the Y-coordinate
    private int squareSize = 50; // Size of the square in which the token is displayed
    private DragonToken dragonToken; // The dragon token object this panel represents
    private int tokenDiameter = 50;  // The diameter of the token for drawing
    private JLabel label;       // Label displaying the token's ID
    private Color color;        // Color of the token

    /**
     * Constructs a DragonTokenPanel with specified parameters.
     *
     * @param x Initial x-coordinate of the token.
     * @param y Initial y-coordinate of the token.
     * @param dragonToken The dragon token associated with this panel.
     * @param offsetX Horizontal offset for positioning.
     * @param offsetY Vertical offset for positioning.
     * @param color The color of the dragon token.
     */
    public DragonTokenPanel(int x, int y, DragonToken dragonToken, int offsetX, int offsetY, Color color) {
        this.dragonToken = dragonToken;
        this.color = color;
        this.x = x;
        this.y = y;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        setOpaque(false);  // Set panel transparency to false for custom drawing
        label = new JLabel(String.valueOf(dragonToken.getId())); // Initialize label with token ID
        add(label);  // Add label to the panel

        dragonToken.setDragonTokenPanel(this); // Link this GUI component with the corresponding dragon token
    }

    /**
     * Returns the dragon token associated with this panel.
     *
     * @return The dragon token object.
     */
    public DragonToken getDragonToken() {
        return dragonToken;
    }

    /**
     * Overrides the JPanel method to provide the current x-coordinate of the token.
     *
     * @return The current x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Overrides the JPanel method to provide the current y-coordinate of the token.
     *
     * @return The current y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the token.
     *
     * @param x New x-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the token.
     *
     * @param y New y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Updates the position of the token on the board.
     * This method is called to reposition the panel during movement.
     */
    private void updatePosition() {
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Moves the dragon token to a new position specified by newX and newY.
     *
     * @param newX The new x-coordinate for the token.
     * @param newY The new y-coordinate for the token.
     */
    public void moveDragonToken(int newX, int newY) {
        setX(newX);
        setY(newY);
        updatePosition();
        repaint();
    }

    /**
     * Custom paint component for drawing the dragon token.
     * This method is overridden to draw an oval representing the dragon token.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color); // Set color for drawing
        g.fillOval(0, 0, tokenDiameter, tokenDiameter); // Draw an oval based on tokenDiameter
    }
}
