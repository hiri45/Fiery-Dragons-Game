import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Game extends JPanel{
    public Game() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        // Draw the game board here
    }

}
