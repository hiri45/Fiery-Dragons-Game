import javax.swing.*;
import java.awt.*;

// This is the class that creates the Window in which the game will be played
public class GameWindow extends JFrame {
    public GameWindow(){
        int screenWidth = 1900;
        int screenHeight = 1080;

        // set panels
        JPanel boardPanel = new JPanel();
        Color darkGreenBackground = new Color(0,153,0);
        boardPanel.setBackground(Color.white);
        boardPanel.setBounds(0,0,screenWidth,screenHeight);
        boardPanel.setLayout(null);

        // To make sure the dragonpool is always in the middle
        int DragonPoolDiameter = (screenWidth/4); //Dynamic dragon pool size so that it always scales with screen size
        int DragonCardPoolX = (screenWidth - DragonPoolDiameter) / 2;
        int DragonCardPoolY = (screenHeight - DragonPoolDiameter) / 2;

        // Create a circular panel and add it to the boardPanel
        DragonCardPool DragonCardPool = new DragonCardPool();
        DragonCardPool.setBounds(DragonCardPoolX, DragonCardPoolY, DragonPoolDiameter, DragonPoolDiameter);
        add(DragonCardPool);

        // Add 24 squares around the DragonCardPool
        addSquaresAroundCircle(boardPanel, DragonCardPoolX, DragonCardPoolY, DragonPoolDiameter);

//        JPanel boardControllerPanel = new JPanel();
//        boardControllerPanel.setBackground(Color.white);
//        boardControllerPanel.setBounds(0,0,screenWidth - boardPanelWidth, screenHeight);



        //set game logo
        ImageIcon gameLogo = new ImageIcon("src/Images/FD logo.png");
        JLabel gameName = new JLabel();
        gameName.setIcon(gameLogo);
        gameName.setBounds(0,55,50,50);




        setTitle("Fiery Dragons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        add(boardPanel);
        boardPanel.add(gameName);
        setLayout(null);

    }
    private void addSquaresAroundCircle(JPanel panel, int centerX, int centerY, int diameter) {
        int radius = diameter / 2 + 150; // slightly outside the circle
        int squareSize = 50; // size of each square
        double angleStep = 360.0 / 24;

        for (int i = 0; i < 24; i++) {
            double angle = Math.toRadians(angleStep * i);
            int x = (int) (centerX + radius * Math.cos(angle) + diameter / 2 - squareSize / 2); // adjust x position
            int y = (int) (centerY + radius * Math.sin(angle) + diameter / 2 - squareSize / 2); // adjust y position

            JPanel square = new JPanel();
            square.setBackground(Color.gray);
            square.setBounds(x, y, squareSize, squareSize);
            panel.add(square);
        }
    }

}
