package src.gui;

import src.actors.Actor;
import src.actors.DragonToken;
import src.board.BoardArray;
import src.board.Cave;
import src.board.Square;
import src.board.VolcanoCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.valueOf;

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

        // Add top edge squares
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
//                String position = valueOf(volcanoCardSquares.get(j).getPosition());
//                JLabel positionLabel = new JLabel(position);
//                labels.add(positionLabel);
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
        ArrayList<Cave> caves = new ArrayList<>();
        for (VolcanoCard card: volcanoCards){
            if(card.hasCave()){
                caves.add(card.getCave());
            }
        }
        //place first cave down on the map starting from the left side of the board, and then place the rest of the caves down
        VolcanoCard card = volcanoCards.get(0);

        if (card.hasCave()) {
            addCave(caves.get(0),offsetX + (gridSize / 2) * squareSize - 2 * squareSize, offsetY - caveSize);//top left cave
            addCave(caves.get(1),offsetX + (gridSize + 1) * squareSize - squareSize, offsetY + (gridSize / 2 - 1) * squareSize - squareSize);//right top cave
            addCave(caves.get(2),offsetX + (gridSize / 2) * squareSize + 1 * squareSize, offsetY + gridSize * squareSize);//bottom left right cave
            addCave(caves.get(3),offsetX - caveSize,offsetY + (gridSize / 2 - 1) * squareSize + 2 * squareSize); //left bottom cave
        }else{
            addCave(caves.get(0),offsetX + (gridSize / 2) * squareSize + squareSize, offsetY - caveSize); //top cave right
            addCave(caves.get(1),offsetX + (gridSize + 1) * squareSize - squareSize,offsetY + (gridSize / 2 - 1) * squareSize + 2 * squareSize); // right cave bottom
            addCave(caves.get(2),offsetX + (gridSize / 2) * squareSize - 2 * squareSize, offsetY + gridSize * squareSize); // bottom cave left
            addCave(caves.get(3),offsetX - caveSize, offsetY + (gridSize / 2 - 1) * squareSize - squareSize); // Left cave top
        }
    addDragonToken(new DragonToken(1),offsetX + (gridSize / 2) * squareSize - 2 * squareSize,offsetY - caveSize);
    }

    private void addCave(Cave cave, int x, int y) {
        CavePanel cavePanel = new CavePanel(x,y,cave,caveColor);
        cavePanel.setBounds(x, y, caveSize, caveSize);
        this.add(cavePanel);
    }

    private void addDragonToken(DragonToken dragonToken, int x, int y){
        DragonTokenPanel dragonTokenPanel = new DragonTokenPanel(x,y,dragonToken, offsetX, offsetY);
        dragonTokenPanel.setBounds(x,y,50,50);
        this.add(dragonTokenPanel,0);

        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(e -> moveToken(dragonTokenPanel));
        moveButton.setBounds(50, 800, 100, 30);  // Position the move button
        this.add(moveButton);

        dragonTokenPanel.setLocation(x * squareSize + offsetX, y * squareSize + offsetY);
    }

    private void moveToken(DragonTokenPanel dragonTokenPanel) {
        // Determine the next position in a clockwise movement
        int currentX = dragonTokenPanel.getX();
        int currentY = dragonTokenPanel.getY();
        System.out.println(currentX);
        System.out.println(currentY);
        // Example of moving right on the top row
        if (currentY == 0 && currentX < gridSize - 1) {
            dragonTokenPanel.moveDragonToken(currentX + 1, currentY);
        } else if (currentX == gridSize - 1 && currentY < gridSize - 1) {  // Move down on the right column
            dragonTokenPanel.moveDragonToken(currentX, currentY + 1);
        } else if (currentY == gridSize - 1 && currentX > 0) {  // Move left on the bottom row
            dragonTokenPanel.moveDragonToken(currentX - 1, currentY);
        } else if (currentX == 0 && currentY > 0) {  // Move up on the left column
            dragonTokenPanel.moveDragonToken(currentX, currentY - 1);
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
