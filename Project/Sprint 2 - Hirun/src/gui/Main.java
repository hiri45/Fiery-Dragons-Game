package gui;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        BoardPanel boardPanel = new BoardPanel();
        boardPanel.setSize(1000, 1000);
        boardPanel.setTitle("Fiery Dragons");
        boardPanel.setLocationRelativeTo(null); // Center the frame on screen
        boardPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel.setVisible(true);
    }
}

