package gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setSize(1000,1000);
        gamePanel.setTitle("Fiery Dragons");
        gamePanel.setLocationRelativeTo(null); // Center the frame on screen
        gamePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel.setVisible(true);
    }}

