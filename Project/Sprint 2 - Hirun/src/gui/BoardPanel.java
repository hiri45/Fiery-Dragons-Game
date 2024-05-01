package gui;

import game.DragonCard;
import game.utils.Shuffle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BoardPanel extends JFrame {
    private static final int INNER_BOARD_SIZE = 350; //used to represent the Dragon Cards
    private static final int CHIT_ROWS = 4;
    private static final int CHIT_COLS = 4;
    private static final int HORIZONTAL_VOLCANO_COLS = 2;
    private static final int HORIZONTAL_VOLCANO_ROWS = 3;
    private static final int VERTICAL_VOLCANO_ROWS = 2;
    private static final int VERTICAL_VOLCANO_COLS = 3;
    private static final GridBagConstraints gbc = new GridBagConstraints();
    public BoardPanel() {
        setLayout(new GridBagLayout()); // The layout manager

        // GridBag constraints for panel
        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.BOTH;

        JPanel gridPanel = new JPanel(new GridLayout(CHIT_ROWS, CHIT_COLS,10,10)); // for the basic game use a grid of 4x4 to represent the dragon cards in the middle
        gridPanel.setPreferredSize(new Dimension(INNER_BOARD_SIZE, INNER_BOARD_SIZE)); // Setting the preferred size of the grid panel

        String[] dragonCardFlipped = {"1 salamander", "2 salamander", "3 salamander", "1 bat",
                "2 bat", "3 bat", "1 spider", "2 spider", "3 spider", "1 babyDragon", "2 babyDragon", "3 babyDragon", "1 pirateDragon",
                "1 pirateDragon", "2 pirateDragon", "2 pirateDragon"};
        // shuffle the original dragonCardFlipped array
        Shuffle dragonCardShuffled = new Shuffle(dragonCardFlipped);
        dragonCardShuffled.shuffleDragon();
        // Load the dragon image
        try {
            BufferedImage dragonImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gui/gameImages/DragonCardImage.png")));
            Image scaledImage = dragonImage.getScaledInstance(250 / CHIT_COLS, -1, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            DragonCard dragonCard = new DragonCard();
            //String [] dragonCardArray = dragonCard.shuffleDragon(); // use to randomise the chit cards on the board
            // Create the 4x4 grid by adding button sub-panels with image on button representing dragon card before flipped
            for (int i = 0; i < CHIT_ROWS * CHIT_COLS; i++) {
                JButton cellPanel = new JButton(icon);
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to each cell
                int finalI = i;
                // for now just having a JLabel with a string when flipped but will represent the icon in future
                cellPanel.addActionListener(_ -> dragonCard.flipDragon(dragonCardFlipped,cellPanel, finalI)); // Add action listener to flip the dragon card
                gridPanel.add(cellPanel); // Add cell panel to the grid
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Add the grid panel to the center of the frame
        add(gridPanel); // The panel is centered by default using GridBagLayout

        // create panels for the volcano cards
        JPanel leftVolcanoPanel1 = new JPanel(new GridLayout(HORIZONTAL_VOLCANO_ROWS, HORIZONTAL_VOLCANO_COLS));
        leftVolcanoPanel1.setPreferredSize(new Dimension(200,200));
        JPanel leftVolcanoPanel2 = new JPanel(new GridLayout(HORIZONTAL_VOLCANO_ROWS, HORIZONTAL_VOLCANO_COLS));
        leftVolcanoPanel2.setPreferredSize(new Dimension(200,200));

        JPanel rightVolcanoPanel1 = new JPanel(new GridLayout(HORIZONTAL_VOLCANO_ROWS,HORIZONTAL_VOLCANO_COLS));
        rightVolcanoPanel1.setPreferredSize(new Dimension(200,200));
        JPanel rightVolcanoPanel2 = new JPanel(new GridLayout(HORIZONTAL_VOLCANO_ROWS,HORIZONTAL_VOLCANO_COLS));
        rightVolcanoPanel2.setPreferredSize(new Dimension(200,200));

        JPanel topVolcanoPanel1 = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        topVolcanoPanel1.setPreferredSize(new Dimension(200,200));
        JPanel topVolcanoPanel2 = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        topVolcanoPanel2.setPreferredSize(new Dimension(200,200));

        JPanel bottomVolcanoPanel1 = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        bottomVolcanoPanel1.setPreferredSize(new Dimension(200,200));
        JPanel bottomVolcanoPanel2 = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        bottomVolcanoPanel2.setPreferredSize(new Dimension(200,200));


        String [] caves = {"bat", "babyDragon", "spider", "salamander"}; // for now just using an array of strings without the actual Cave class
        String [][] volcanoCaveCards = {{"babyDragoon","bat","spider"},{"salamander","spider","bat"},{"spider","salamander","babyDragon"},{"bat","spider","babyDragon"}};
        String [][] nonCaveVolcCard = {{"spider","bat","salamander"},{"babyDragon","salamander","bat"},{"bat","babyDragon","salamander"},{"salamander","babyDragon","spider"}};
        // uses Shuffle class to shuffle the original caves array
        Shuffle caveShuffled = new Shuffle(caves);
        caveShuffled.shuffleDragon();

        createLeftPanel(leftVolcanoPanel1,caves[0]);
        createLeftPanel(leftVolcanoPanel2,"");

        createRightPanel(rightVolcanoPanel1,caves[1]);
        createRightPanel(rightVolcanoPanel2,"");

        // Create card panels for the top volcano panel and bottom volcano panel along with cave in required position
        createTopPanel(topVolcanoPanel1,"");
        createTopPanel(topVolcanoPanel2,caves[2]);

        createBottomPanel(bottomVolcanoPanel1,caves[3]);
        createBottomPanel(bottomVolcanoPanel2,"");



// Representation of the volcano panels, caves and volcano cards as desired using grid bag constraints

        createGridConstraints(1,0,CHIT_COLS/2,1,topVolcanoPanel1);
        createGridConstraints(1+CHIT_ROWS/2,0,CHIT_COLS/2,1,topVolcanoPanel2);
        createGridConstraints(0,1,1,CHIT_ROWS/2,leftVolcanoPanel1);
        createGridConstraints(0,1+CHIT_ROWS/2,1,CHIT_ROWS/2,leftVolcanoPanel2);
        createGridConstraints(1,1,CHIT_COLS,CHIT_ROWS,gridPanel);
        createGridConstraints(CHIT_COLS+1,1,1,CHIT_ROWS/2,rightVolcanoPanel1);
        createGridConstraints(CHIT_COLS+1,1+CHIT_ROWS/2,1,CHIT_ROWS/2,rightVolcanoPanel2);
        createGridConstraints(1,CHIT_ROWS+1,CHIT_COLS/2,1,bottomVolcanoPanel1);
        createGridConstraints(1+CHIT_ROWS/2,CHIT_ROWS+1,CHIT_COLS/2,1,bottomVolcanoPanel2);

// Make sure to validate the layout after adding all panels
        validate();
        repaint();

        pack();

    }
    void createGridConstraints(int gridx, int gridy, int gridWidth, int gridHeight, JPanel panel){
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        add(panel,gbc);
    }

    void createLeftPanel(JPanel volcanoPanel, String caveCreature) {
        for (int row = 0; row < HORIZONTAL_VOLCANO_ROWS; row++) {
            for (int col = 0; col < HORIZONTAL_VOLCANO_COLS; col++) { // Two columns loop
                JPanel cellPanel = new JPanel();
                if (!caveCreature.isEmpty()) {
                    // Conditions to add cavePanel in specific cells
                    if ((row == 1 && col == 0)) {
                        JPanel CavePanel = new JPanel();
                        JLabel leftCaveLabel = new JLabel(caveCreature);
                        CavePanel.setBackground(Color.GRAY);
                        CavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                        CavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                        CavePanel.add(leftCaveLabel);
                        cellPanel.add(CavePanel);
                    }
                    // Add a label conditionally
                    else {
                        // Create labels only for specific cards
                        if (col == 1) {
                            JLabel label = new JLabel();
                            cellPanel.add(label);
                            cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                    }
                }
                if (col == 1) {
                    JLabel label = new JLabel("Card " + (row * 2 + col + 1));
                    cellPanel.add(label);
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                volcanoPanel.add(cellPanel); // Add cellPanel to leftVolcanoPanel
            }

        }
    }
    void createRightPanel(JPanel volcanoPanel, String caveCreature) {
        for (int row = 0; row < HORIZONTAL_VOLCANO_ROWS; row++) {
            for (int col = 0; col < HORIZONTAL_VOLCANO_COLS; col++) { // Two columns loop
                JPanel cellPanel = new JPanel();
                if (!caveCreature.isEmpty()) {
                    // Conditions to add cavePanel in specific cells
                    if ((row == 1 && col == 1)) {
                        JPanel CavePanel = new JPanel();
                        JLabel leftCaveLabel = new JLabel(caveCreature);
                        CavePanel.setBackground(Color.GRAY);
                        CavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                        CavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                        CavePanel.add(leftCaveLabel);
                        cellPanel.add(CavePanel);
                    }
                    // Add a label conditionally
                    else {
                        // Create labels only for specific cards
                        if (col == 0) {
                            JLabel label = new JLabel();
                            cellPanel.add(label);
                            cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                    }
                }
                if (col == 0) {
                    JLabel label = new JLabel("Card " + (row * 2 + col + 1));
                    cellPanel.add(label);
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                volcanoPanel.add(cellPanel); // Add cellPanel to leftVolcanoPanel
            }

        }
    }
    void createTopPanel(JPanel volcanoPanel, String caveCreature) {
        for (int row = 0; row < VERTICAL_VOLCANO_ROWS; row++) {
            for (int col = 0; col < VERTICAL_VOLCANO_COLS; col++) { // Two columns loop
                JPanel cellPanel = new JPanel();
                if (!caveCreature.isEmpty()) {
                    // Conditions to add cavePanel in specific cells
                    if ((row == 0 && col == 1)) {
                        JPanel CavePanel = new JPanel();
                        JLabel leftCaveLabel = new JLabel(caveCreature);
                        CavePanel.setBackground(Color.GRAY);
                        CavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                        CavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                        CavePanel.add(leftCaveLabel);
                        cellPanel.add(CavePanel);
                    }
                    // Add a label conditionally
                    else {
                        // Create labels only for specific cards
                        if (!(row == 0)) {
                            JLabel label = new JLabel();
                            cellPanel.add(label);
                            cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                    }
                }
                if (!(row == 0)) {
                    JLabel label = new JLabel("Card " + (row * 2 + col + 1));
                    cellPanel.add(label);
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                volcanoPanel.add(cellPanel); // Add cellPanel to leftVolcanoPanel
            }

        }
    }
    void createBottomPanel(JPanel volcanoPanel, String caveCreature) {
        for (int row = 0; row < VERTICAL_VOLCANO_ROWS; row++) {
            for (int col = 0; col < VERTICAL_VOLCANO_COLS; col++) { // Two columns loop
                JPanel cellPanel = new JPanel();
                if (!caveCreature.isEmpty()) {
                    // Conditions to add cavePanel in specific cells
                    if ((row == 1 && col == 1)) {
                        JPanel CavePanel = new JPanel();
                        JLabel leftCaveLabel = new JLabel(caveCreature);
                        CavePanel.setBackground(Color.GRAY);
                        CavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                        CavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                        CavePanel.add(leftCaveLabel);
                        cellPanel.add(CavePanel);
                    }
                    // Add a label conditionally
                    else {
                        // this is where the cave creature cards would be added, didnt have time.
                        if (!(row == 1)) {
                            JLabel label = new JLabel();
                            cellPanel.add(label);
                            cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                    }
                }
                // this is where the non cave creature cards would be added, didnt have time.
                if (!(row == 1)) {
                    JLabel label = new JLabel("Card " + (col + 1));
                    cellPanel.add(label);
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
                volcanoPanel.add(cellPanel); // Add cellPanel to leftVolcanoPanel
            }

        }
    }




}


