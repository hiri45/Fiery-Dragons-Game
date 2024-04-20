package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class DragonCard extends JPanel {

    private final int squareSize = 75;
    private final int gridSize = 8;
    private final List<Rectangle> squares = new ArrayList<>();
    private final List<Boolean> revealText = new ArrayList<>();
    private final String[] texts = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};

    public DragonCard() {
        // Calculate positions and add to list
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int x = 125 + squareSize + (squareSize * i);
                int y = 150 + squareSize + (squareSize * j);
                squares.add(new Rectangle(x, y, squareSize, squareSize));
                revealText.add(false);
            }
        }

        // Add mouse listener to detect clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                for (int i = 0; i < squares.size(); i++) {
                    if (squares.get(i).contains(clickPoint)) {
                        revealText.set(i, !revealText.get(i)); // Toggle reveal text state
                        repaint(); // Repaint to update the view
                        break;
                    }
                }
            }
        });

        setPreferredSize(new Dimension(1000, 900));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw squares
        for (int i = 0; i < squares.size(); i++) {
            Rectangle square = squares.get(i);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fill(square);
            g2d.setColor(Color.BLACK);
            g2d.draw(square);

            if (revealText.get(i)) {
                g2d.drawString(texts[i], square.x + 20, square.y + squareSize / 2);
            }
        }
    }
}
