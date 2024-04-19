import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// This is the class that creates the Window in which the game will be played
public class GameWindow extends JFrame {
    public GameWindow(){
        int screenWidth = 1600;
        int screenHeight = 900;

        // set panels
        JPanel boardPanel = new JPanel();
        Color darkGreenBackground = new Color(0,153,0);
        boardPanel.setBackground(darkGreenBackground);
        int boardPanelWidth = (int) (0.7 * screenWidth);
        boardPanel.setBounds(0,0,boardPanelWidth,screenHeight);
        boardPanel.setLayout(null);

        // To make sure the dragonpool is always in the middle
        int DragonPoolDiameter = (int) (boardPanelWidth/2.5); //Dynamic dragon pool size so that it always scales with screen size
        int DragonCardPoolX = (boardPanelWidth - DragonPoolDiameter) / 2;
        int DragonCardPoolY = (screenHeight - DragonPoolDiameter) / 2;

        // Create a circular panel and add it to the boardPanel
        CircularPanel DragonCardPool = new CircularPanel();
        DragonCardPool.setBounds(DragonCardPoolX, DragonCardPoolY, DragonPoolDiameter, DragonPoolDiameter);
        boardPanel.add(DragonCardPool);

        JPanel boardControllerPanel = new JPanel();
        boardControllerPanel.setBackground(Color.white);
        boardControllerPanel.setBounds(0,0,screenWidth - boardPanelWidth, screenHeight);



        //set game logo
        ImageIcon gameLogo = new ImageIcon("src/Images/FD logo.png");
        JLabel gameName = new JLabel();
        gameName.setIcon(gameLogo);
        gameName.setBounds(0,55,50,50);




        setTitle("Fiery Dragons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenWidth, screenHeight);
//        setLocationRelativeTo(null);
        add(boardPanel);
        boardPanel.add(gameName);
        setLayout(null);

    }
    // Class to create a cirular Panel for the dragon cards
    private class CircularPanel extends JPanel {

        public CircularPanel(){
            setOpaque(false); //This is to make the Panel transparent (other than the circle)

        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Color brownBackground = new Color(54,34,4);
            g.setColor(brownBackground);
            g.fillOval(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
