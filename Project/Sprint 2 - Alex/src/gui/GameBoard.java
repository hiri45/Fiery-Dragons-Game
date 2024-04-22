package src.gui;

import javax.swing.*;

class GameBoard extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interactive Game Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new WindowPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
