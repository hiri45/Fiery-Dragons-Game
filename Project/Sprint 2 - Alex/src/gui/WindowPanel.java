package src.gui;

import src.actors.Actor;
import src.actors.DragonToken;
import src.board.BoardArray;
import src.board.Cave;
import src.board.Square;
import src.board.VolcanoCard;
import src.utils.MovementManager;
import src.utils.PlayerManager;

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

    private ArrayList<SquarePanel> boardPanels = new ArrayList<>();
    private ArrayList<CavePanel> cavePanels = new ArrayList<>();

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
        MovementManager movementManager = MovementManager.getInstance();
        movementManager.setWindowPanel(this);
    }

    public ArrayList<SquarePanel> getBoardPanels() {
        return boardPanels;
    }

    private void createSquaresAndCaves() {
        BoardArray boardArray = BoardArray.getInstance();
        ArrayList<Square> squares = boardArray.getSquares();
        int index = 0;

// Add top edge squares (left to right)
        for (int i = 1; i < gridSize - 1; i++, index++) {
            int x = offsetX + i * squareSize;
            int y = offsetY;
            SquarePanel squarePanel = new SquarePanel(squares.get(index),x,y);
            squarePanel.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(squarePanel);
            this.add(squarePanel);
        }

// Add right edge squares (top to bottom)
        for (int j = 1; j < gridSize - 1; j++, index++) {
            int x = offsetX + (gridSize - 1) * squareSize;
            int y = offsetY + j * squareSize;
            SquarePanel squarePanel = new SquarePanel(squares.get(index),x,y);
            squarePanel.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(squarePanel);
            this.add(squarePanel);
        }

// Add bottom edge squares (right to left)
        for (int i = gridSize - 2; i > 0; i--, index++) {
            int x = offsetX + i * squareSize;
            int y = offsetY + (gridSize - 1) * squareSize;
            SquarePanel squarePanel = new SquarePanel(squares.get(index),x,y);
            squarePanel.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(squarePanel);
            this.add(squarePanel);
        }

// Add left edge squares (bottom to top)
        for (int j = gridSize - 2; j > 0; j--, index++) {
            int x = offsetX;
            int y = offsetY + j * squareSize;
            SquarePanel squarePanel = new SquarePanel(squares.get(index),x,y);
            squarePanel.setBounds(x, y, squareSize, squareSize);
            boardPanels.add(squarePanel);
            this.add(squarePanel);
        }

        addcreatureLabels();

        ArrayList<VolcanoCard> volcanoCards = boardArray.getBoard();

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
        PlayerManager playerManager = PlayerManager.getInstance();
        ArrayList<DragonToken> dragonTokens = playerManager.getPlayers();

        addDragonToken(dragonTokens.get(0),offsetX + (gridSize / 2) * squareSize - 2 * squareSize, offsetY - caveSize,50,750);
        addDragonToken(dragonTokens.get(1),offsetX + (gridSize + 1) * squareSize - squareSize,offsetY + (gridSize / 2 - 1) * squareSize - squareSize,50,780);
        addDragonToken(dragonTokens.get(2),offsetX + (gridSize / 2) * squareSize + 1 * squareSize, offsetY + gridSize * squareSize,50,810);
        addDragonToken(dragonTokens.get(3),offsetX - caveSize,offsetY + (gridSize / 2 - 1) * squareSize + 2 * squareSize,50,840);


    }

    private void addCave(Cave cave, int x, int y) {
        CavePanel cavePanel = new CavePanel(x,y,cave,caveColor);
        cave.setCavePanel(cavePanel);
        cavePanels.add(cavePanel);
        cavePanel.setBounds(x, y, caveSize, caveSize);
        this.add(cavePanel);
    }

    private void addDragonToken(DragonToken dragonToken, int x, int y,int buttonX, int buttonY){
        DragonTokenPanel dragonTokenPanel = new DragonTokenPanel(x,y,dragonToken, offsetX, offsetY,Color.red);
        dragonTokenPanel.setBounds(x,y,50,50);
        this.add(dragonTokenPanel,0);

        //Add move buttons for temporarily moving dragon tokens
        JButton moveButton = new JButton("Move1");
        moveButton.addActionListener(e -> dragonToken.move(1));
        moveButton.setBounds(buttonX, buttonY, 100, 30);  // Position the move button
        this.add(moveButton);

        JButton moveButton2 = new JButton("Move2");
        moveButton2.addActionListener(e -> dragonToken.move(2));
        moveButton2.setBounds(buttonX+100, buttonY, 100, 30);  // Position the move button
        this.add(moveButton2);

        JButton moveButton3 = new JButton("move3");
        moveButton3.addActionListener(e -> dragonToken.move(3));
        moveButton3.setBounds(buttonX+ 200, buttonY, 100, 30);  // Position the move button
        this.add(moveButton3);

        JButton moveButton4 = new JButton("moveBackwards1");
        moveButton4.addActionListener(e -> dragonToken.move(-1));
        moveButton4.setBounds(buttonX+ 300, buttonY, 100, 30);  // Position the move button
        this.add(moveButton4);

        JButton moveButton5 = new JButton("moveBackwards2");
        moveButton5.addActionListener(e -> dragonToken.move(-2));
        moveButton5.setBounds(buttonX+ 400, buttonY, 100, 30);  // Position the move button
        this.add(moveButton5);

    }
    public void moveToken(DragonTokenPanel dragonTokenPanel, int noPosition) {
        // Determine the next position in a clockwise movement
        int newPosition = dragonTokenPanel.getDragonToken().getPosition();


        if(noPosition > 0){
            forwardsMovement(dragonTokenPanel,newPosition);
        }else{
            backwardsMovement(dragonTokenPanel,newPosition);
        }
    }

    public void forwardsMovement(DragonTokenPanel dragonTokenPanel, int newPosition){
        //move the token forwards as normal
        move(dragonTokenPanel,newPosition);

    }
    public void backwardsMovement(DragonTokenPanel dragonTokenPanel, int newPosition){
        /*
        Handles the logic for moving dragon tokens backwards
         */
        int cavePos =  dragonTokenPanel.getDragonToken().getCave().getCavePosition();
        int caveX = dragonTokenPanel.getDragonToken().getCave().getCavePanel().getX();
        int caveY = dragonTokenPanel.getDragonToken().getCave().getCavePanel().getY();
        //If the token is supposed to move behind the cave, move the token into the cave
        if(newPosition < cavePos && !dragonTokenPanel.getDragonToken().isInCave()){
            dragonTokenPanel.moveDragonToken(caveX,caveY);
            dragonTokenPanel.getDragonToken().setPosition(cavePos);
            dragonTokenPanel.getDragonToken().setInCave(true);
        }else if(newPosition == cavePos && dragonTokenPanel.getDragonToken().isInCave()){
            //if the token is in the cave, but there is backwards movement, don't do anything

        }
        else{
            //otherwise move backwards as normal
            move(dragonTokenPanel,newPosition);
        }
    }

    public void move(DragonTokenPanel dragonTokenPanel, int newPosition) {
        /*
        Visually move the jpanel to match the position of the token in the backend
         */
        for (SquarePanel squarePanel : boardPanels) {
            if (newPosition == squarePanel.getSquare().getPosition()) {
                dragonTokenPanel.moveDragonToken(squarePanel.getX(), squarePanel.getY());
                dragonTokenPanel.getDragonToken().setInCave(false);
                break;
            }
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

    public void addcreatureLabels(){
        BoardArray boardArray = BoardArray.getInstance();
        ArrayList<Square> squares = boardArray.getSquares();
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<VolcanoCard> volcanoCards = boardArray.getBoard();
        //go through the list of volcanoCards
            //get the animals associated with each square
        for (int j = 0; j < squares.size(); j++){
            String label = squares.get(j).ui();
            JLabel creatureLabel = new JLabel(label);
            labels.add(creatureLabel);
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
    }

}
