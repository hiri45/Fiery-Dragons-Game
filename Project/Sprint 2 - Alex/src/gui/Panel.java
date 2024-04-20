package src.gui;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {

    private final int squareSize = 75;
    private final int gridSize = 8;
    Panel(){
        this.setPreferredSize(new Dimension(1000,750));
    }
    public void paint(Graphics g){
        Graphics2D graphics = (Graphics2D) g;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // Conditions to skip the corners and only draw the edges
                if ((i == 0 || i == gridSize - 1 || j == 0 || j == gridSize - 1) // Check if it's on the perimeter
                        && !(i == 0 && j == 0) // Skip top-left corner
                        && !(i == 0 && j == gridSize - 1) // Skip bottom-left corner
                        && !(i == gridSize - 1 && j == 0) // Skip top-right corner
                        && !(i == gridSize - 1 && j == gridSize - 1)) { // Skip bottom-right corner
                    int x = 100 + i * squareSize;
                    int y = 50 + j * squareSize;
                    graphics.drawRect(x, y, squareSize, squareSize);
                }
            }
        }


    }
}
