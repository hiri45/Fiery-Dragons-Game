import Panels.Board;

import javax.swing.*;
import java.awt.*;

// This is the class that creates the Window in which the game will be played
public class GameWindow extends JFrame {
    public GameWindow(){
        int screenWidth = 1900;
        int screenHeight = 1080;
        int players = 2;

        setTitle("Fiery Dragons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setLayout(null);

        // set panels
        add(Board.getInstance(screenWidth,screenHeight,players));

    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new GameWindow().setVisible(true);
        });
    }
}
