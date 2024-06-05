package src.gui;

import src.board.BoardArray;
import src.utils.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the starting menu for the game with a title and start button for the time being. The start buttons goes to the
 * actual game when pressed which is done through the startGame function and actionListener.
 */
public class MenuPanel extends JPanel{
    private static MenuPanel instance;
    private JLabel titleLabel;
    JPanel menuButtonPanel;
    private JButton startButton,loadButton;
    private Font titleGameont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font basicFont = new Font("Times New Roman", Font.PLAIN, 30);
    private String gameChoice;
    private SaveLoad saveLoad;
    private WindowPanel gameWindow;
    private JFrame frame;

    private MenuPanel(JFrame frame) {
        this.frame = frame;
        this.setBounds(150, 100, 600, 150);
        this.setBackground(Color.black);
        titleLabel = new JLabel("Fiery Dragons");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleGameont);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameChoice = e.getActionCommand();
                switch (gameChoice) {
                    case "start":
                        startGame("start");
                        break;
                    case "load":
                        startGame("load");
                        break;
                }
            }
        };

        menuButtonPanel = new JPanel();
        menuButtonPanel.setBounds(350, 400, 200, 100);
        menuButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(basicFont);
        startButton.addActionListener(actionListener);
        startButton.setActionCommand("start");

        loadButton = new JButton("LOAD");
        loadButton.setBackground(Color.black);
        loadButton.setForeground(Color.white);
        loadButton.setFont(basicFont);
        loadButton.addActionListener(actionListener);
        loadButton.setActionCommand("load");

        this.add(titleLabel);
        menuButtonPanel.add(startButton);
        menuButtonPanel.add(loadButton);

    }
    /**
     * ensures that theres only a single instance of the menu panel
     * */
    public static MenuPanel getInstance(JFrame frame) {
        if (instance == null) {
            instance = new MenuPanel(frame);
        }
        return instance;
    }
    /**
     * this method is used to open up the WindowPanel which is the game to be played, you can either choose to start a new game
     * or load up a saved version of the game
     */
    private void startGame(String gameChoice) {
        frame.getContentPane().removeAll();
        if (gameChoice.equals("start")) {
            // Add the JScrollPane to the frame for a new game
            int numberOfPlayers = popupForNumberOfPlayers();
            int vCCount = popupForVolcanoCardCount();
            int squaresPerVC = popupForSquaresPerVC();

            BoardArray boardArray = BoardArray.getInstance();
            boardArray.setVolcanoCardCount(vCCount);
            boardArray.setSquaresPerVC(squaresPerVC);

            boardArray.addVolcanoCards(vCCount,squaresPerVC);
            boardArray.addPosition();

            gameWindow = WindowPanel.getInstance(numberOfPlayers,vCCount,squaresPerVC);

            JScrollPane scrollPane = new JScrollPane(gameWindow);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            frame.add(scrollPane);
        } else if (gameChoice.equals("load")) {
            saveLoad = new SaveLoad();
            saveLoad.loadGame();
            gameWindow = saveLoad.getWindowPanel();
            JScrollPane scrollPane = new JScrollPane(gameWindow);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            // Add the JScrollPane to the frame after loading the game
            frame.add(scrollPane);
        }

        frame.revalidate();
        frame.repaint();
    }

    public int popupForNumberOfPlayers() {
        String input = JOptionPane.showInputDialog(this, "How many players? (1-4):", "Player Setup", JOptionPane.QUESTION_MESSAGE);
        int numberOfPlayers = 0;
        try {
            numberOfPlayers = Integer.parseInt(input);
            JOptionPane.showMessageDialog(this, "To confirm, the number of players is: " + numberOfPlayers);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "This game only supports up to 4 players", "Error", JOptionPane.ERROR_MESSAGE);
            return popupForNumberOfPlayers(); // Retry until valid input is given
        }
        return numberOfPlayers;
    }

    public int popupForVolcanoCardCount() {
        int numberOfVC = 0;
        boolean validInput = false;

        while (!validInput) {
            String input = JOptionPane.showInputDialog(null, "How many players Volcano Cards?:", "Board Setup", JOptionPane.QUESTION_MESSAGE);
            try {
                numberOfVC = Integer.parseInt(input);

                if (numberOfVC >= 6 && numberOfVC <= 12) {
                    validInput = true;
                    JOptionPane.showMessageDialog(null, "To confirm, the number of volcano cards is: " + numberOfVC);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 6 and 12", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 6 and 12", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return numberOfVC;
    }
    public int popupForSquaresPerVC() {
        int numberOfSquaresPerVC = 0;
        boolean validInput = false;

        while (!validInput) {
            String input = JOptionPane.showInputDialog(null, "How many squares per volcano card:", "Volcano Card Setup", JOptionPane.QUESTION_MESSAGE);
            try {
                numberOfSquaresPerVC = Integer.parseInt(input);
                if (numberOfSquaresPerVC >= 3 && numberOfSquaresPerVC <= 5) {
                    validInput = true;
                    JOptionPane.showMessageDialog(null, "To confirm, the number of squares per volcano card is: " + numberOfSquaresPerVC);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 3 and 5", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 3 and 5", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return numberOfSquaresPerVC;
    }
}
