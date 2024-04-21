package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameFrame extends JFrame {
    private static final int WINDOW_SIZE = 1000;
    private static final int INNER_BOARD_SIZE = 400; //used to represent the Dragon Cards
    private static final int GRID_ROWS = 4;
    private static final int GRID_COLS = 4;
    public GameFrame() {
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setLayout(new GridBagLayout()); // The layout manager

        JPanel gridPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS,10,10)); // for the basic game use a grid of 4x4 to represent the dragon cards in the middle
        gridPanel.setPreferredSize(new Dimension(INNER_BOARD_SIZE, INNER_BOARD_SIZE)); // Setting the preferred size of the grid panel

        // Load the dragon image
        try {
            BufferedImage dragonImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gui/GameImages/DragonCardImage.png")));
            Image scaledImage = dragonImage.getScaledInstance(250 / GRID_COLS, -1, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            // Create the 4x4 grid by adding button sub-panels with image on button representing dragon card before flipped
            for (int i = 0; i < GRID_ROWS * GRID_COLS; i++) {
                JButton cellPanel = new JButton(icon);
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to each cell
                gridPanel.add(cellPanel); // Add cell panel to the grid
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Add the grid panel to the center of the frame
        add(gridPanel); // The panel is centered by default using GridBagLayout

        setLocationRelativeTo(null); // Center the frame on screen

    }

}

