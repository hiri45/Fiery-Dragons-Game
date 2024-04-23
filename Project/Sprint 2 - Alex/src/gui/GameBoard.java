package src.gui;

import javax.swing.*;

public class GameBoard extends JPanel {

    public static void main(String[] args) {
    }

    public void drawGameBoard(){
        JFrame frame = new JFrame("Interactive Game Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowPanel gameWindow = new WindowPanel();
        frame.add(gameWindow);
        frame.pack();
        frame.setVisible(true);
    }
}
