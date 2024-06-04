package src.gui;

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
    private Font titleGameFont = new Font("Times New Roman", Font.PLAIN, 90);
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
        titleLabel.setFont(titleGameFont);
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
            gameWindow = WindowPanel.getInstance(numberOfPlayers);
            JScrollPane scrollPane = new JScrollPane(gameWindow);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            frame.add(scrollPane);
        } else if (gameChoice.equals("load")) {
            saveLoad = new SaveLoad(gameWindow);
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

}
