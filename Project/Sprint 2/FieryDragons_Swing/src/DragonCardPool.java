import javax.swing.*;
import java.awt.*;

public class DragonCardPool extends JPanel {

    public DragonCardPool() {
        setOpaque(false); // Make the Panel transparent (other than the circle)

        setLayout(new GridLayout(4, 4, 10, 10)); // Layout with 4 rows, 4 columns, and gaps
        generateDragonCards();
    }

    private void generateDragonCards() {
        int numberOfCards = 16;
        for (int i = 0; i < numberOfCards; i++) {
            DragonCard card = new DragonCard(new ImageIcon("src/Images/FD logo.png"), new ImageIcon("path/to/back.jpg"));
            add(card);

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color brownBackground = new Color(54, 34, 4);
        g.setColor(brownBackground);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
