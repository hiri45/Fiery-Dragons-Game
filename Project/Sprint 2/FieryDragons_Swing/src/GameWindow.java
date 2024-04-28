import Panels.Board;

import javax.swing.*;
import java.awt.*;

// This is the class that creates the Window in which the game will be played
public class GameWindow extends JFrame {
    public GameWindow(){
        //set screen size
        int screenWidth = 1600;
        int screenHeight = 900;
        //record number of players. will be dynamically entered in later stages of development
        int players = 2;

        setTitle("Fiery Dragons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setLayout(null);

        // create instance of board and add to frame
        add(Board.getInstance(screenWidth,screenHeight,players));

    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new GameWindow().setVisible(true);
        });
    }
}
