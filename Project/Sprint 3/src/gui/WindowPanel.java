/**
 * WindowPanel.java
 *
 * Manages the graphical display of the game board in the Fiery Dragons game.
 * This panel is responsible for creating and arranging the squares, caves, and dragon tokens
 * on the game board. It also manages the positioning and interactive functionality associated
 * with these elements.
 *
 * Author: Alex Ung
 * Last Modified: 5/06/2024
 */

package src.gui;

import src.actors.DragonToken;
import src.board.*;
import src.utils.MovementManager;
import src.utils.PlayerManager;
import src.utils.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

public class WindowPanel extends JPanel {
    private static WindowPanel instance;

    private final int squareSize = 75;   // Size of each square on the board
    private int gridSize = 8;      // Grid size of the board, representing 8x8

    private int offsetX;                 // Horizontal offset for centering the board
    private int offsetY;                 // Vertical offset for centering the board
    private final int caveSize = 75;     // Size of the caves on the board

    private final int cardSize = 60;     // Size of dragon cards
    private final Color caveColor = new Color(128, 64, 0); // Color for caves

    private final int width = 1600;      // Width of the panel
    private final int height = 1500;      // Height of the panel

    private ArrayList<SquarePanel> boardPanels = new ArrayList<>(); // Panels for each square
    private ArrayList<CavePanel> cavePanels = new ArrayList<>();    // Panels for each cave
    private int numberOfPlayers; // Variable to store the number of players
    private Image backgroundImage;
    private SaveLoad saveLoad;
    private ArrayList<DragonTokenPanel> dragonTokenPanels;


    private int volcanoCardCount,squaresPerVC;

    private ArrayList<VolcanoCard> volcanoCards = BoardArray.getInstance().getBoard();

    private ArrayList<Cave> caves = new ArrayList<>();

    private int centerX = width/2;
    private int centerY = height/2;

    private final BoardArray boardArray = BoardArray.getInstance();

    private double radius;

    private ArrayList<Double> angles = new ArrayList<Double>();

    /**
     * Constructor for WindowPanel. Sets up the board by creating squares, caves,
     * and dragon cards, and configures basic panel settings.
     */
    private WindowPanel(int numberOfPlayers,int volcanoCardCount, int squaresPerVC) {
        this.setLayout(null); // Use a null layout manager for absolute positioning
        //this.setBackground(new Color(153,153,153));
        this.setPreferredSize(new Dimension(width, height));
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Calculate offsets to center the board
        int totalBoardWidth = gridSize * squareSize;
        int totalBoardHeight = gridSize * squareSize;
        this.offsetX = centerX - (totalBoardWidth / 2);
        this.offsetY = centerY - (totalBoardHeight / 2);
        this.numberOfPlayers = numberOfPlayers;
        this.volcanoCardCount = volcanoCardCount;
        this.squaresPerVC = squaresPerVC;

        this.gridSize = calculateGridSize(volcanoCardCount,squaresPerVC);
        PlayerManager playerManager = PlayerManager.getInstance(); // Get the singleton instance of PlayerManager
        playerManager.addPlayers(numberOfPlayers); // Add players to the game
        this.dragonTokenPanels = new ArrayList<>(); // Initialize the dragonTokenPanels list

        createSquaresAndCaves();
        placeDragonCardPool();
        placeBeaverWizardCard();
        MovementManager movementManager = MovementManager.getInstance();
        movementManager.setWindowPanel(this);
        displayCurrentPlayer();
        saveLoad = new SaveLoad(this);
        saveButton();
        backgroundImage = new ImageIcon(this.getClass().getResource("/src/Images/magic background.png")).getImage();
        if (backgroundImage == null) {
            System.err.println("Background image not found!");
        }

    }
    public static WindowPanel getInstance(int numberOfPlayers,int volcanoCardCount, int squaresPerVC){
        if(instance == null){
            instance = new WindowPanel(numberOfPlayers,volcanoCardCount,squaresPerVC);
        }
        return instance;
    }

    public static WindowPanel getInstance() {
        if (instance == null) {
            instance = new WindowPanel(2,8,3); // Default to standard board if no number is provided
        }
        return instance;
    }

    public static void resetInstance(int numberOfPlayers) {
        instance = new WindowPanel(numberOfPlayers, instance.volcanoCardCount, instance.squaresPerVC);
    }

    /**
     * Gets the list of square panels that make up the game board.
     * This method allows access to the square panels, which represent each square on the game board.
     *
     * @return An ArrayList of SquarePanel representing each square on the board.
     */
    public ArrayList<SquarePanel> getBoardPanels() {
        return boardPanels;
    }

    /**
     * Overrides the getWidth method to provide the predefined width of the panel.
     * This method is used to determine the width of the WindowPanel for layout calculations.
     *
     * @return The width of the panel.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Overrides the getHeight method to provide the predefined height of the panel.
     * This method is used to determine the height of the WindowPanel for layout calculations.
     *
     * @return The height of the panel.
     */
    @Override
    public int getHeight() {
        return height;
    }


    private int calculateGridSize(int volcanoCardCount, int squaresPerVC) {
        return (int) Math.ceil(Math.sqrt(volcanoCardCount * squaresPerVC)) + 2;

    }

    /**
     * Creates and arranges the squares and caves on the game board.
     * This method sets up the visual representation of the game board by arranging SquarePanel and CavePanel
     * instances according to the game's layout specifications. It also adds functionality for the placement
     * of DragonToken representations based on the game's rules.
     */
    private void createSquaresAndCaves() {
        int totalSquares = volcanoCardCount*squaresPerVC;
        BoardArray boardArray = BoardArray.getInstance();
        ArrayList<Square> squares = boardArray.getSquares();
        int index = 0;

        // Calculate the radius based on the number of squares and square size
        radius = (squareSize * totalSquares) / (2 * Math.PI) + 20;
        if (radius < 300){
            radius = 300;
        }
        for (int i = 0; i < totalSquares; i++, index++) {
            // Calculate angle for each square
            double angle = 2 * Math.PI * i / totalSquares;
            angles.add(angle);
            // Calculate x and y position based on angle and radius
            int x = (int) (centerX + radius * Math.cos(angle) - squareSize / 2);
            int y = (int) (centerY + radius * Math.sin(angle) - squareSize / 2);

            addSquare(squares, index, x, y);
            System.out.println(index+ ","+ x + ","+ y);
        }

        // Check the total number of squares drawn
        System.out.println("Total squares drawn: " + boardPanels.size());

        // Call method to add creature labels to the squares.
        addCreatureLabels();

        // Retrieve all volcano cards to determine which ones have caves.
        for (VolcanoCard card: volcanoCards){
            if(card.hasCave()){
                caves.add(card.getCave());
            }
        }

        drawCaveForCard();
        // Retrieve the list of dragon tokens from the player manager and position them.
        PlayerManager playerManager = PlayerManager.getInstance();
        ArrayList<DragonToken> dragonTokens = playerManager.getPlayers();

        //add the dragonTokens onto the gui
        // Coordinates and offsets for the tokens
        for (int i = 0; i < numberOfPlayers; i++) {
                addDragonToken(dragonTokens.get(i), cavePanels.get(i).getX(), cavePanels.get(i).getY());
            }
    }
    private void addSquare(ArrayList<Square> squares, int index, int x, int y) {
        if (index >= squares.size()) return; // Ensure index is within bounds
        SquarePanel squarePanel = new SquarePanel(squares.get(index), x, y);
        squarePanel.setBounds(x, y, squareSize, squareSize);
        boardPanels.add(squarePanel);
        this.add(squarePanel);
    }

    private void drawCaveForCard() {
            for (Cave cave:caves){
                int caveX = 0;
                int caveY = 0;

                // Determine the position for the cave relative to the selected square
                double angle = angles.get(cave.getCavePosition());

                // Calculate x and y position based on angle and radius
                caveX = (int) (centerX + (radius+75) * Math.cos(angle) - squareSize / 2);
                caveY = (int) (centerY + (radius+75) * Math.sin(angle) - squareSize / 2);

                addCave(cave, caveX, caveY);
                System.out.println("cave: " + cave.getCavePosition() + ","+ caveX + ","+ caveY);
            }
    }


    private boolean isWithinBounds(int x, int y) {
        // Adjust the bounds checking logic as needed based on your board size and layout
        int boardWidth = getWidth();
        int boardHeight = getHeight();
        return x >= 0 && x < boardWidth && y >= 0 && y < boardHeight;
    }
    /**
     * Adds a CavePanel to the game board at a specified position.
     *
     * @param cave The Cave object representing the game logic for a specific cave.
     * @param x    The x-coordinate where the cave should be placed on the board.
     * @param y    The y-coordinate where the cave should be placed on the board.
     */
    private void addCave(Cave cave, int x, int y) {
        // Create a new CavePanel using the specified cave and color for background.
        CavePanel cavePanel = new CavePanel(x,y,cave,caveColor);
        // Associate the graphical cave panel with the logical cave object.
        cave.setCavePanel(cavePanel);
        // Add the cave panel to the list of cave panels.
        cavePanels.add(cavePanel);
        // Set the position and size of the cave panel on the game board.
        cavePanel.setBounds(x, y, caveSize, caveSize);
        // Add the cave panel to this WindowPanel, making it visible on the game board.
        this.add(cavePanel);
    }

    /**
     * Adds a DragonTokenPanel to the game board and sets up control buttons for token movement.
     *
     * This method creates a DragonTokenPanel for a given dragon token at a specified position on the board,
     * and adds interactive buttons that allow the player to move the dragon token in various ways during gameplay.
     *
     * @param dragonToken The DragonToken object that needs a graphical representation.
     * @param x           The x-coordinate where the dragon token should be placed on the board.
     * @param y           The y-coordinate where the dragon token should be placed on the board.
     */
    private void addDragonToken(DragonToken dragonToken, int x, int y) {
        // Create a new DragonTokenPanel with specified position, token data, and default color.
        DragonTokenPanel dragonTokenPanel = new DragonTokenPanel(x+squareSize/2, y, dragonToken, offsetX, offsetY, Color.red);
        dragonTokenPanel.setBounds(x+squareSize/2, y, 50, 50); // Set size and position of the token panel.
        this.add(dragonTokenPanel, 0); // Add the token panel to this WindowPanel at the lowest z-index.

    }
    /**
     * Manages the movement of a dragon token on the game board.
     *
     * This method determines the direction and magnitude of the movement based on the input parameter
     * and delegates to specific methods for forward or backward movement. It updates the position of the
     * dragon token accordingly.
     *
     * @param dragonTokenPanel The graphical panel representing the dragon token whose position is to be updated.
     * @param noPosition       The number of positions to move the dragon token. A positive value moves the token forward,
     *                         while a negative value moves it backward.
     */
    public void moveToken(DragonTokenPanel dragonTokenPanel, int noPosition) {

        // Retrieve the current position of the dragon token from its panel.
        int newPosition = dragonTokenPanel.getDragonToken().getPosition();
        System.out.println(newPosition);
        System.out.println(dragonTokenPanel.getDragonToken().getCave().getCavePosition());
        // Check if the movement is forward (positive number of positions).
        if (noPosition > 0) {
            // Handle forward movement by delegating to the appropriate method.
            move(dragonTokenPanel,newPosition);
//            forwardsMovement(dragonTokenPanel, newPosition);
        } else {
            // Handle backward movement by delegating to the appropriate method.
            backwardsMovement(dragonTokenPanel, newPosition);
        }

    }

    /**
     * Manages backward movement of a dragon token on the game board, including handling special cases
     * like moving into a cave.
     *
     * This method determines if backward movement should place the dragon token inside its cave or just update
     * its position on the board. It handles logic specific to cave entry and adjustments of token position when moving backwards.
     *
     * @param dragonTokenPanel The graphical panel representing the dragon token.
     * @param newPosition      The calculated new position for the dragon token, assuming backward movement.
     */
    public void backwardsMovement(DragonTokenPanel dragonTokenPanel, int newPosition){
        // Retrieve the position and graphical coordinates of the cave associated with the dragon token.
        int cavePos = dragonTokenPanel.getDragonToken().getCave().getCavePosition();
        int caveX = dragonTokenPanel.getDragonToken().getCave().getCavePanel().getX();
        int caveY = dragonTokenPanel.getDragonToken().getCave().getCavePanel().getY();
        int cycles = dragonTokenPanel.getDragonToken().getCycleTracker();

        // Check if the new position is behind the cave's position and the token is not already in the cave.
        if(newPosition < cavePos && !dragonTokenPanel.getDragonToken().isInCave() && cycles == 0){
            // Move the dragon token into the cave and update its state to reflect it is inside the cave.
            dragonTokenPanel.moveDragonToken(caveX, caveY);
            dragonTokenPanel.getDragonToken().setPosition(cavePos);
            dragonTokenPanel.getDragonToken().setInCave(true);
        } else if (newPosition == cavePos && dragonTokenPanel.getDragonToken().isInCave()) {
            // If the token is in the cave and attempts to move backwards to the same cave position, do nothing.
        }
        else {
            // If not moving into the cave, update the position normally.
            move(dragonTokenPanel, newPosition);
        }


    }

    /**
     * Updates the graphical position of a dragon token to reflect its new logical position.
     *
     * This method scans through all square panels on the game board to find the square that matches
     * the new position of the dragon token. Once found, it updates the graphical location of the
     * dragon token to align with the square's location on the board.
     *
     * @param dragonTokenPanel The graphical panel representing the dragon token that needs to be moved.
     * @param newPosition      The new logical position of the dragon token determined by game logic.
     */
    public void move(DragonTokenPanel dragonTokenPanel, int newPosition) {
        // Iterate over all square panels on the board to find the one that matches the new position.
        for (SquarePanel squarePanel : boardPanels) {
            // Check if the square's position matches the dragon token's new logical position.
                if (newPosition == squarePanel.getSquare().getPosition()) {
                // Move the dragon token panel to the square's graphical coordinates.
                dragonTokenPanel.moveDragonToken(squarePanel.getX(), squarePanel.getY());
                // Update the token's state to indicate it is not inside a cave.
                dragonTokenPanel.getDragonToken().setInCave(false);
                break;  // Exit the loop once the correct panel is found and updated.
            }
        }
    }
    /**
     *places the DragonCard Pool in the panel. In this case it will be the main Game Panel -> WindowPanel
     */
    private void placeDragonCardPool()
    {
        int dragonPoolSideLength = (int) radius; //Dynamic dragon pool size so that it always scales with screen size
        int dragonCardPoolX = (width - dragonPoolSideLength) / 2;
        int dragonCardPoolY = (height - dragonPoolSideLength) / 2;
        DragonCardPool dragonCardPool = new DragonCardPool();
        dragonCardPool.setBounds(dragonCardPoolX, dragonCardPoolY, dragonPoolSideLength, dragonPoolSideLength);
        add(dragonCardPool);
    }

    private void placeBeaverWizardCard(){
        BeaverWizardCard beaverWizardCard = new BeaverWizardCard();
        beaverWizardCard.setBounds(width -150, 50, 100,100);
        add(beaverWizardCard);
    }


    /**
     * Adds creature labels to each square panel on the game board.
     *
     * This method retrieves the list of squares from the BoardArray singleton and creates a label for each square
     * based on the creature type it holds. These labels are then added to their corresponding square panels
     * and visually updated with random background colors to distinguish the squares.
     */
    public void addCreatureLabels() {
        // Retrieve the singleton instance of BoardArray to access squares and volcano cards.

        ArrayList<Square> squares = boardArray.getSquares();
        ArrayList<JLabel> labels = new ArrayList<>();

        // Create labels for each square based on the creature type it contains.
        for (int j = 0; j < squares.size(); j++) {
            ImageIcon labelImage = squares.get(j).ui();  // Retrieves the creature type in string format from each square.
            JLabel creatureLabel = new JLabel(labelImage);  // Create a label with the creature type text.
            labels.add(creatureLabel);  // Add the newly created label to the list of labels.
        }

        // Initialize a random color generator for setting random background colors.
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);  // Create an initial random color.

        // Assign each label to its corresponding square panel and set a random background color.
        for (int k = 0; k < this.boardPanels.size(); k++) {
            if (k % squaresPerVC == 0) {  // Change the color every time a new vc is shown
                rand = new Random();  // Reinitialize random number generator.
                r = rand.nextFloat();
                g = rand.nextFloat();
                b = rand.nextFloat();
                randomColor = new Color(r, g, b);  // Assign a new random color.
            }

            JLabel label = labels.get(k);  // Get the label corresponding to the current square panel.
            boardPanels.get(k).setOpaque(true);  // Set the panel to opaque to ensure the background color shows.
            boardPanels.get(k).setBackground(randomColor);  // Set the background color of the panel.
            boardPanels.get(k).add(label);  // Add the label to the square panel.

            // Finally, add the updated square panel to the WindowPanel for display.
            this.add(boardPanels.get(k));
        }

    }
    /**
     * Displays current player on the window panel (top left) so that the player knows whose turn it is
     */
    public void displayCurrentPlayer()
    {
        PlayerManager playerManager = PlayerManager.getInstance();
        DragonToken currentPlayer = playerManager.getPlayers().get(playerManager.getPlayerTurn());
        JPanel currentPlayerDisplay = new JPanel();
        currentPlayerDisplay.setBounds(100,100,150,50);
        JLabel label = new JLabel("Current Player: "+ currentPlayer.getId());
        currentPlayerDisplay.add(label);
        this.add(currentPlayerDisplay);
    }

    /**
     * A button on the game board which will implement a save function to save the current configuration of the game
     */
    public void saveButton()
    {
        JButton saveButton = new JButton();
        saveButton.setBounds(750,100,150,50);
        JLabel label = new JLabel("Save Game");
        saveButton.add(label);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveLoad.saveGame();
                JOptionPane.showMessageDialog(null, "Game Saved!");
            }
        });
        this.add(saveButton);
    }

    /**Method to get a specific DragonTokenPanel by index*/
    public DragonTokenPanel getDragonTokenPanel(int index) {
        return dragonTokenPanels.get(index);
    }

    /**Method to get all DragonTokenPanels*/
    public ArrayList<DragonTokenPanel> getDragonTokenPanels() {
        return dragonTokenPanels;
    }

    /**
     * displays message when player turn has ended
     */
    public void showPlayerTurnPopup(String message) {
        JOptionPane.showMessageDialog(this, message, "End of Turn ;(", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public ArrayList<CavePanel> getCavePanels() {
        return cavePanels;
    }
}
