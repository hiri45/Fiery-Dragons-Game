package src.gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameBoard {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(1000,1000));
        frame.setVisible(true);
        frame.setTitle("Fiery Dragon");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
