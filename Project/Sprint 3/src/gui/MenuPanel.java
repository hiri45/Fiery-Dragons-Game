package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the starting menu for the game with a title and start button for the time being. The start buttons goes to the
 * actual game when pressed which is done through the startGame function and actionListener.
 */
public class MenuPanel extends JPanel {
    private static MenuPanel instance;
    JLabel titleLabel;
    JPanel startButtonPanel;
    JButton startButton;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    private JFrame frame;

    private MenuPanel(JFrame frame) {
        this.frame = frame;
        this.setBounds(150, 100, 600, 150);
        this.setBackground(Color.black);
        titleLabel = new JLabel("Fiery Dragons");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(350, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        this.add(titleLabel);
        startButtonPanel.add(startButton);

    }

    public static MenuPanel getInstance(JFrame frame) {
        if (instance == null) {
            instance = new MenuPanel(frame);
        }
        return instance;
    }
    /**
     * this method is used to open up the WindowPanel which is the game to be played
     */
    private void startGame() {
        frame.getContentPane().removeAll();
        WindowPanel gameWindow = WindowPanel.getInstance();
        JScrollPane scrollPane = new JScrollPane(gameWindow);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Add the JScrollPane to the frame
        frame.add(scrollPane); // Add the scroll pane, not the game panel directly*/
        frame.revalidate();
        frame.repaint();
    }
}
