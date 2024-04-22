import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import Creature.*;

public class DragonCardPool extends JPanel {

    public Creature[] creatureArray = {new Bat(), new BabyDragon(), new Salamander(), new Spider()};
    public Creature[] enemy = {new PirateDragon()};
    public DragonCardPool() {
        setOpaque(false); // Make the Panel transparent (other than the circle)
        setLayout(new GridLayout(4, 4, 10, 10)); // Layout with 4 rows, 4 columns, and gaps
        generateDragonCards();
    }

    private void generateDragonCards() {
        int numberOfCards = 12;
        DragonCard[] dragonCards = new DragonCard[numberOfCards];
        int counter = 0;
        for (Creature i: creatureArray)
        {
            for (int j = 1; j < 3; j ++)
            {

                DragonCard card = new DragonCard(new ImageIcon("src/Images/FD logo.png"),i.getName() +" x"+j,j);
                dragonCards[counter] = card;
                counter ++;
                System.out.println(counter);
                System.out.println(dragonCards[counter].getBack());
            }
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
