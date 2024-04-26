package src.gui;

import src.board.Square;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {
    private Square square;

    private int x,y;
    public SquarePanel(Square square,int x,int y) {
        this.x = x;
        this.y = y;
        this.square = square;
        // Set the border to replicate the drawn rectangle
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setOpaque(false); // Make the square transparent
    }

    public Square getSquare() {
        return square;
    }
}