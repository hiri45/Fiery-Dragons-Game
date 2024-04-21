package gui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final int WINDOW_SIZE = 750;
    private static final int INNER_BOARD_SIZE = 150; //used to represent the Dragon Cards
    private static final int GRID_ROWS = 4;
    private static final int GRID_COLS = 4;

    public GameFrame() {
        setTitle("Fiery Dragons");
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // The layout manager

        JPanel gridPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS)); // for the basic game use a grid of 4x4 to represent the dragon cards in the middle
        gridPanel.setPreferredSize(new Dimension(INNER_BOARD_SIZE, INNER_BOARD_SIZE)); // Setting the preferred size of the grid panel

        // Create the 4x4 grid by adding sub-panels
        for (int i = 0; i < GRID_ROWS * GRID_COLS; i++) {
            JPanel cellPanel = new JPanel();
            cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to each cell
            gridPanel.add(cellPanel); // Add cell panel to the grid
        }

        // Add the grid panel to the center of the frame
        add(gridPanel); // The panel is centered by default using GridBagLayout

        setLocationRelativeTo(null); // Center the frame on screen
    }
}
