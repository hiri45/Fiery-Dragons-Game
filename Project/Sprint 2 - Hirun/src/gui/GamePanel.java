package gui;

import game.DragonCard;
import game.utils.Shuffle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GamePanel extends JFrame {
    private static final int INNER_BOARD_SIZE = 300; //used to represent the Dragon Cards
    private static final int CHIT_ROWS = 4;
    private static final int CHIT_COLS = 4;
    private static final int HORIZONTAL_VOLCANO_COLS = 2;
    private static final int HORIZONTAL_VOLCANO_ROWS = 6;
    private static final int VERTICAL_VOLCANO_ROWS = 2;
    private static final int VERTICAL_VOLCANO_COLS = 6;
    public GamePanel() {
        setLayout(new GridBagLayout()); // The layout manager

        // GridBag constraints for panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

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
        JPanel leftVolcanoPanel = new JPanel(new GridLayout(HORIZONTAL_VOLCANO_ROWS, HORIZONTAL_VOLCANO_COLS));
        leftVolcanoPanel.setPreferredSize(new Dimension(200,200));
        JPanel rightVolcanoPanel = new JPanel(new GridLayout(HORIZONTAL_VOLCANO_ROWS,HORIZONTAL_VOLCANO_COLS));
        rightVolcanoPanel.setPreferredSize(new Dimension(200,200));
        JPanel topVolcanoPanel = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        topVolcanoPanel.setPreferredSize(new Dimension(200,200));
        JPanel bottomVolcanoPanel = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        bottomVolcanoPanel.setPreferredSize(new Dimension(200,200));


        String [] caves = {"bat", "babyDragon", "spider", "salamander"}; // for now just using an array of strings without the actual Cave class
        // uses Shuffle class to shuffle the original caves array
        Shuffle caveShuffled = new Shuffle(caves);
        caveShuffled.shuffleDragon();

        // Create card panels for the left volcano panel and right volcano panel along with cave in required position
        for (int row = 0; row < HORIZONTAL_VOLCANO_ROWS; row++) {
            for (int col = 0; col < HORIZONTAL_VOLCANO_COLS; col++) { // Two columns loop
                JPanel cellPanel = new JPanel();

                // Conditions to add cavePanel in specific cells
                if ((row == 1 && col == 0)) {
                    JPanel leftCavePanel = new JPanel();
                    JLabel leftCaveLabel = new JLabel(caves[0]);
                    leftCavePanel.setBackground(Color.GRAY);
                    leftCavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                    leftCavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                    leftCavePanel.add(leftCaveLabel);
                    cellPanel.add(leftCavePanel);
                }
                // Add a label conditionally
                else {
                    // Create labels only for specific cards
                    if (col == 1) {
                        JLabel label = new JLabel("Card " + (row * 2 + col + 1));
                        cellPanel.add(label);
                        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }
                }

                leftVolcanoPanel.add(cellPanel); // Add cellPanel to leftVolcanoPanel
            }

        }

        for (int row = 0; row < HORIZONTAL_VOLCANO_ROWS; row++) {
            for (int col = 0; col < HORIZONTAL_VOLCANO_COLS; col++) { // Two columns loop
                JPanel cellPanel = new JPanel();

                // Conditions to add cavePanel in specific cells
                if ((row == 4 && col == 1)) {
                    JPanel rightCavePanel = new JPanel();
                    JLabel rightCaveLabel = new JLabel(caves[1]);
                    rightCavePanel.setBackground(Color.GRAY);
                    rightCavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                    rightCavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                    rightCavePanel.add(rightCaveLabel);
                    cellPanel.add(rightCavePanel);
                }
                else {
                    if (col == 0) {
                        JLabel label = new JLabel("Card " + (row * 2 + col + 1)); // Create labels for specific cards for now
                        cellPanel.add(label);
                        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }
                }

                rightVolcanoPanel.add(cellPanel); // Add cellPanel to leftVolcanoPanel
            }
        }

        // Create card panels for the top volcano panel and bottom volcano panel along with cave in required position
        for (int row = 0; row < VERTICAL_VOLCANO_ROWS; row++) { // Loop for two rows
            for (int col = 0; col < VERTICAL_VOLCANO_COLS; col++) {
                // Loop for six columns
                JPanel cellPanel = new JPanel();
                if ((row == 0 && col == 4)) {
                    JPanel topCavePanel = new JPanel();
                    JLabel topCaveLabel = new JLabel(caves[2]);
                    topCavePanel.setBackground(Color.GRAY);
                    topCavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                    topCavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                    topCavePanel.add(topCaveLabel);
                    cellPanel.add(topCavePanel);
                }
                // Add a label conditionally, skip the first label of each row
                else {
                    if (!(row == 0)) {// Skip the first column of each row
                        JLabel label = new JLabel("Card " + ((row * 6) + col + 1));
                        cellPanel.add(label);
                        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    }
                }

                topVolcanoPanel.add(cellPanel); // Add cellPanel to topVolcanoPanel
            }
        }

        for (int row = 0; row < VERTICAL_VOLCANO_ROWS; row++) { // Loop for two rows
            for (int col = 0; col < VERTICAL_VOLCANO_COLS; col++) {
                // Loop for six columns
                JPanel cellPanel = new JPanel();
                if ((row == 1 && col == 1)) {
                    JPanel bottomCavePanel = new JPanel();
                    JLabel bottomCaveLabel = new JLabel(caves[3]);
                    bottomCavePanel.setBackground(Color.GRAY);
                    bottomCavePanel.setPreferredSize(new Dimension(100, 100)); // Size of the cavePanel
                    bottomCavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ensure cavePanel has a border
                    bottomCavePanel.add(bottomCaveLabel);
                    cellPanel.add(bottomCavePanel);
                }
                // Add a label conditionally, skip the first label of each row
                else {
                    if (!(row == 1)) {// Skip the first column of each row
                        JLabel label = new JLabel("Card " + (col + 1));
                        cellPanel.add(label);
                        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    }
                }

                bottomVolcanoPanel.add(cellPanel); // Add cellPanel to topVolcanoPanel
            }
        }


// Representation of the volcano panels, caves and volcano cards as desired using grid bag constraints

// Top volcano panel
        gbc.gridx = 1; gbc.gridy = 0; // This should be directly above the central grid
        gbc.gridwidth = CHIT_COLS; gbc.gridheight = 1;
        add(topVolcanoPanel, gbc);

// Left side panel
        gbc.gridx = 0; gbc.gridy = 1; // This should be directly to the left of the central grid
        gbc.gridwidth = 1; gbc.gridheight = CHIT_ROWS;
        add(leftVolcanoPanel, gbc);

// Central grid panel
        gbc.gridx = 1; gbc.gridy = 1; // This is the central grid
        gbc.gridwidth = CHIT_COLS; gbc.gridheight = CHIT_ROWS;
        add(gridPanel, gbc);

// Right side panel
        gbc.gridx = CHIT_COLS + 1; gbc.gridy = 1; // This should be directly to the right of the central grid
        gbc.gridwidth = 1; gbc.gridheight = CHIT_ROWS;
        add(rightVolcanoPanel, gbc);

// Bottom volcano panel
        gbc.gridx = 1; gbc.gridy = CHIT_ROWS + 1; // This should be directly below the central grid
        gbc.gridwidth = CHIT_COLS; gbc.gridheight = 1;
        add(bottomVolcanoPanel, gbc);


// Make sure to validate the layout after adding all panels
        validate();
        repaint();

        pack();




    }



}


