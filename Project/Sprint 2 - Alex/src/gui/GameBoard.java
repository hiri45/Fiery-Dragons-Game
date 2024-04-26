package src.gui;

import src.utils.MovementManager;

import javax.swing.*;

public class GameBoard extends JPanel {

    public static void main(String[] args) {
    }

    public void drawGameBoard(){
        JFrame frame = new JFrame("FieryDragon Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowPanel gameWindow = new WindowPanel();
        frame.add(gameWindow);
        frame.pack();
        frame.setVisible(true);
    }
}
