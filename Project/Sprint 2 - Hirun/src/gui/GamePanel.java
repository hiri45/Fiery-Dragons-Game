package gui;

import game.DragonCard;

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
    private static final int HORIZONTAL_VOLCANO_COLS = 1;
    private static final int HORIZONTAL_VOLCANO_ROWS = 6;
    private static final int VERTICAL_VOLCANO_ROWS = 1;
    private static final int VERTICAL_VOLCANO_COLS = 6;
    public GamePanel() {
        setLayout(new GridBagLayout()); // The layout manager

        // GridBag constraints for panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        JPanel gridPanel = new JPanel(new GridLayout(CHIT_ROWS, CHIT_COLS,10,10)); // for the basic game use a grid of 4x4 to represent the dragon cards in the middle
        gridPanel.setPreferredSize(new Dimension(INNER_BOARD_SIZE, INNER_BOARD_SIZE)); // Setting the preferred size of the grid panel

        // Load the dragon image
        try {
            BufferedImage dragonImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gui/gameImages/DragonCardImage.png")));
            Image scaledImage = dragonImage.getScaledInstance(250 / CHIT_COLS, -1, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            DragonCard dragonCard = new DragonCard();
            String [] dragonCardArray = dragonCard.shuffleDragon(); // use to randomise the chit cards on the board
            // Create the 4x4 grid by adding button sub-panels with image on button representing dragon card before flipped
            for (int i = 0; i < CHIT_ROWS * CHIT_COLS; i++) {
                JButton cellPanel = new JButton(icon);
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to each cell
                int finalI = i;
                // for now just having a JLabel with a string when flipped but will represent the icon in future
                cellPanel.addActionListener(_ -> dragonCard.flipDragon(dragonCardArray,cellPanel, finalI)); // Add action listener to flip the dragon card
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
        leftVolcanoPanel.setPreferredSize(new Dimension(100,100));
        JPanel rightVolcanoPanel = new JPanel(new GridLayout(HORIZONTAL_VOLCANO_ROWS,HORIZONTAL_VOLCANO_COLS));
        rightVolcanoPanel.setPreferredSize(new Dimension(100,100));
        JPanel topVolcanoPanel = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        topVolcanoPanel.setPreferredSize(new Dimension(100,100));
        JPanel bottomVolcanoPanel = new JPanel(new GridLayout(VERTICAL_VOLCANO_ROWS, VERTICAL_VOLCANO_COLS));
        bottomVolcanoPanel.setPreferredSize(new Dimension(100,100));

        // still figuring out a way to implement these caves onto the board
        JPanel leftCavePanel = new JPanel();
        leftCavePanel.setPreferredSize(new Dimension(100, 50)); // Ensure size is enough to see it
        leftCavePanel.setBackground(Color.GRAY);
        //JPanel leftCavePanel = new JPanel(new GridLayout(1,1));
        //leftCavePanel.setPreferredSize(new Dimension(100, 100));
        JPanel rightCavePanel = new JPanel();
        rightCavePanel.setPreferredSize(new Dimension(100, 50));
        rightCavePanel.setBackground(Color.GRAY);
        JPanel topCavePanel = new JPanel();
        topCavePanel.setPreferredSize(new Dimension(50, 100));
        topCavePanel.setBackground(Color.GRAY);
        JPanel bottomCavePanel = new JPanel();
        bottomCavePanel.setPreferredSize(new Dimension(50, 100));
        bottomCavePanel.setBackground(Color.GRAY);



        // Create horizontal panels (left and right)
        for (int i = 0; i < HORIZONTAL_VOLCANO_ROWS; i++) {
            JLabel leftCellPanel = new JLabel();
            leftCellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            //leftCellPanel.add(leftCavePanel, BorderLayout.WEST);
            leftVolcanoPanel.add(leftCellPanel);

            JLabel rightCellPanel = new JLabel();
            rightCellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            rightVolcanoPanel.add(rightCellPanel);
        }
        // create vertical panels (top and bottom)
        for (int i = 0; i < VERTICAL_VOLCANO_COLS; i++) {
            JLabel topCellPanel = new JLabel();
            topCellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            topVolcanoPanel.add(topCellPanel);

            JLabel bottomCellPanel = new JLabel();
            bottomCellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            bottomVolcanoPanel.add(bottomCellPanel);
        }


// Representation of the volcano panels as desired using grid bag constraints

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


        // right cave panel
        gbc.gridx = -1; // Align with the central grid horizontally
        gbc.gridy = 1; // Place directly above the central grid (one unit up)
        gbc.gridwidth = 1;
        gbc.gridheight = 1; // Only one unit tall
        add(rightCavePanel, gbc);

// Bottom Cave Panel
        gbc.gridx = 1; // Align with the central grid horizontally
        gbc.gridy = -1; // Place directly below the central grid
        gbc.gridwidth = 1;
        gbc.gridheight = 1; // Only one unit tall
        add(bottomCavePanel, gbc);

// Make sure to validate the layout after adding all panels
        validate();
        repaint();

        pack();




    }



}


