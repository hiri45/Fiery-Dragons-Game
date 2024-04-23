package src.gui;

import src.board.BoardArray;
import src.board.Square;
import src.board.VolcanoCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

    private int offsetX;
    private int offsetY;
    private final int caveSize = 75;

    private final int cardSize = 60;
    private final Color caveColor = new Color(128, 64, 0); // Brown color for caves

    private final int width = 1000;
    private final int height = 900;

    private ArrayList<JPanel> boardPanels = new ArrayList<>();

    public WindowPanel() {
        this.setLayout(null); // Use a null layout manager for absolute positioning
        this.setPreferredSize(new Dimension(width, height));
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Calculate offsets to center the board
        int totalBoardWidth = gridSize * squareSize;
        int totalBoardHeight = gridSize * squareSize;
        this.offsetX = centerX - (totalBoardWidth / 2);
        this.offsetY = centerY - (totalBoardHeight / 2);

        createSquaresAndCaves();
        createDragonCards();
    }

    private void createSquaresAndCaves() {
        BoardArray boardArray = BoardArray.getInstance();


        for (int i = 1; i < gridSize - 1; i++) {
            int x = offsetX + i * squareSize;
            int y = offsetY;
            SquarePanel square = new SquarePanel();
            square.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(square);
        }

        // Add right edge squares
        for (int j = 1; j < gridSize - 1; j++) {
            int x = offsetX + (gridSize - 1) * squareSize;
            int y = offsetY + j * squareSize;
            SquarePanel square = new SquarePanel();
            square.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(square);
        }

        // Add bottom edge squares in reverse order
        for (int i = gridSize - 2; i > 0; i--) {
            int x = offsetX + i * squareSize;
            int y = offsetY + (gridSize - 1) * squareSize;
            SquarePanel square = new SquarePanel();
            square.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(square);
        }

        // Add left edge squares in reverse order
        for (int j = gridSize - 2; j > 0; j--) {
            int x = offsetX;
            int y = offsetY + j * squareSize;
            SquarePanel square = new SquarePanel();
            square.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(square);
        }
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<VolcanoCard> volcanoCards = boardArray.getBoard();
        //go through the list of volcanoCards
        for(int i = 0; i < volcanoCards.size();i++){

            //get the array of squares inside each volcano card
            ArrayList<Square> volcanoCardSquares = volcanoCards.get(i).getSquares();

            //get the animals associated with each square
            for (int j = 0; j < volcanoCardSquares.size(); j++){
                String label = volcanoCardSquares.get(j).ui();
                JLabel creatureLabel = new JLabel(label);
                labels.add(creatureLabel);


            }
        }
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);

        //add all the labels to their appropriate panel
        for (int k = 0; k < this.boardPanels.size();k++){
            if(k%3 == 0){
                rand = new Random();
                r = rand.nextFloat();
                g = rand.nextFloat();
                b = rand.nextFloat();
                randomColor = new Color(r, g, b);
            }
            JLabel label = labels.get(k);
            boardPanels.get(k).setOpaque(true);
            boardPanels.get(k).setBackground(randomColor);
            boardPanels.get(k).add(label);

            //add each boardPanel to the current windowPanel
            this.add(boardPanels.get(k));
        }

        //place first cave down on the map starting from the left side of the board, and then place the rest of the caves down
            VolcanoCard card = volcanoCards.get(0);
            if (card.hasCave()) {
                addCave(offsetX - caveSize, offsetY + (gridSize / 2 - 1) * squareSize - squareSize); // Left cave top
                addCave(offsetX + (gridSize / 2) * squareSize + squareSize, offsetY - caveSize); //top cave right
                addCave(offsetX + (gridSize + 1) * squareSize - squareSize,offsetY + (gridSize / 2 - 1) * squareSize + 2 * squareSize); // right cave bottom
                addCave(offsetX + (gridSize / 2) * squareSize - 2 * squareSize, offsetY + gridSize * squareSize); // bottom cave left

            }else{
                addCave(offsetX - caveSize,offsetY + (gridSize / 2 - 1) * squareSize + 2 * squareSize);// left cave bottom
                addCave(offsetX + (gridSize / 2) * squareSize - 2 * squareSize, offsetY - caveSize); // top cave left
                addCave(offsetX + (gridSize + 1) * squareSize - squareSize, offsetY + (gridSize / 2 - 1) * squareSize - squareSize); // right cave top
                addCave(offsetX + (gridSize / 2) * squareSize + 1 * squareSize, offsetY + gridSize * squareSize); // bottom cave right
            }
    }

    private void addCave(int x, int y) {
        CavePanel cave = new CavePanel(caveColor);
        cave.setBounds(x, y, caveSize, caveSize);
        this.add(cave);
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
