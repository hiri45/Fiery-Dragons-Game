package src.gui;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {

    private final int squareSize = 75;
    private final int gridSize = 8;

    private final int offsetX = 125;
    private final int offsetY = 150;
    private final int caveSize = 75;
    private final Color caveColor = new Color(128, 64, 0); // Brown color for caves
    Panel(){
        this.setPreferredSize(new Dimension(1000,900));
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
                    int x = offsetX + i * squareSize;
                    int y = offsetY + j * squareSize;
                    graphics.drawRect(x, y, squareSize, squareSize);
                }
            }
        }
        int caveOffsetX = offsetX - caveSize; // Cave positioned to the left of the board
        int caveOffsetY = offsetY + (gridSize / 2 - 1) * squareSize;
        drawCave(graphics, caveOffsetX, caveOffsetY - squareSize); // Left cave
        drawCave(graphics, caveOffsetX + (gridSize + 1) * squareSize, caveOffsetY + 2*squareSize); // Right cave
        drawCave(graphics, caveOffsetX + (gridSize / 2) * squareSize + 2* squareSize, offsetY - caveSize); // Top cave
        drawCave(graphics, caveOffsetX + (gridSize / 2) * squareSize - squareSize, offsetY + gridSize * squareSize); // Bottom cave

    }
    private void drawCave(Graphics2D graphics, int x, int y) {
        graphics.setColor(caveColor);
        graphics.fillRect(x, y, 75, 75); // Draw the cave slightly above the square
        graphics.setColor(Color.BLACK); // Reset color for drawing other elements
    }
}
