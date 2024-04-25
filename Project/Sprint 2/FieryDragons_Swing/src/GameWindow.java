import Creature.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// This is the class that creates the Window in which the game will be played
public class GameWindow extends JFrame {
    public GameWindow(){
        int screenWidth = 1900;
        int screenHeight = 1080;

        setTitle("Fiery Dragons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setLayout(null);

        // set panels
        Board boardPanel = new Board(screenWidth, screenHeight,4);
        add(boardPanel);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new GameWindow().setVisible(true);
        });
    }
}
