package src.gui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {

    private final int squareSize = 75;
    private final int gridSize = 8;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private final int offsetX = 125;
    private final int offsetY = 150;
    private final int caveSize = 75;

    private final int cardSize = 60;
    private final Color caveColor = new Color(128, 64, 0); // Brown color for caves

    private final int width = 1000;
    private final int height = 900;

    public WindowPanel() {
        this.setLayout(null); // Use a null layout manager for absolute positioning
        this.setPreferredSize(new Dimension(width, height));
        createSquaresAndCaves();
        createDragonCards();
    }

    private void createSquaresAndCaves() {
        // Create edge squares
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // Conditions to skip the corners and only create the edges
                if ((i == 0 || i == gridSize - 1 || j == 0 || j == gridSize - 1)
                        && !(i == 0 && j == 0)
                        && !(i == 0 && j == gridSize - 1)
                        && !(i == gridSize - 1 && j == 0)
                        && !(i == gridSize - 1 && j == gridSize - 1)) {
                    int x = offsetX + i * squareSize;
                    int y = offsetY + j * squareSize;
                    SquarePanel square = new SquarePanel();
                    square.setBounds(x, y, squareSize, squareSize);
                    this.add(square);
                }
            }
        }

        // Create caves
        addCave(offsetX - caveSize, offsetY + (gridSize / 2 - 1) * squareSize - squareSize); // Left cave
        addCave(offsetX + (gridSize + 1) * squareSize - squareSize, offsetY + (gridSize / 2 - 1) * squareSize + 2 * squareSize); // Right cave
        addCave(offsetX + (gridSize / 2) * squareSize + squareSize, offsetY - caveSize); // Top cave
        addCave(offsetX + (gridSize / 2) * squareSize - 2 * squareSize, offsetY + gridSize * squareSize); // Bottom cave
    }

    private void addCave(int x, int y) {
        CavePanel cave = new CavePanel(caveColor);
        cave.setBounds(x, y, caveSize, caveSize);
        this.add(cave);
    }

    // Inner class for square panels
    private class SquarePanel extends JPanel {
        public SquarePanel() {
            // Set the border to replicate the drawn rectangle
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.setOpaque(false); // Make the square transparent
        }
    }

    // Inner class for cave panels
    private class CavePanel extends JPanel {
        public CavePanel(Color bgColor) {
            this.setBackground(bgColor);
            this.setOpaque(true); // Caves are not transparent
        }
    }
    private void createDragonCards() {
        // Define the number of cards horizontally and vertically
        int cardsPerRow = 4;
        int cardRows = 4; // For a 4x4 grid of cards

        // Calculate total width and height needed for all cards including gaps between them
        int totalCardsWidth = cardsPerRow * cardSize + (cardsPerRow - 1) * 5; // Adjust the gap width as needed
        int totalCardsHeight = cardRows * cardSize + (cardRows - 1) * 5; // Adjust the gap height as needed

        // Calculate starting position to center the cards
        int startX = (this.getWidth() - totalCardsWidth) / 2;
        int startY = (this.getHeight() - totalCardsHeight) / 2;

        // Create and position the dragon cards
        for (int i = 0; i < cardRows * cardsPerRow; i++) {
            String cardName = "Dragon Card " + (i + 1);
            DragonCardPanel card = new DragonCardPanel(cardName);

            int row = i / cardsPerRow; // Row position of the card
            int col = i % cardsPerRow; // Column position of the card

            int x = startX + col * (cardSize + 5); // x position considering the gap
            int y = startY + row * (cardSize + 5); // y position considering the gap

            card.setBounds(x, y, cardSize, cardSize);
            this.add(card);
        }
    }


}
