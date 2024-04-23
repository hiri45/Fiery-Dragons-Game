import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import Creature.*;

public class DragonCardPool extends JPanel {

    public Creature[] creatureArray = {new Bat(), new BabyDragon(), new Salamander(), new Spider()};
    public Creature[] enemy = {new PirateDragon(), new PirateDragon()};
    public DragonCardPool() {
        setOpaque(false); // Make the Panel transparent (other than the circle)
        setLayout(new GridLayout(4, 4, 10, 10)); // Layout with 4 rows, 4 columns, and gaps
        generateDragonCards();
    }

    private void generateDragonCards() {

        ArrayList<DragonCard> dragonCards = new ArrayList<>();
        for (Creature i: creatureArray)
        {
            for (int j = 1; j <= 3; j ++)
            {
                DragonCard card = new DragonCard(i.getName() +" x"+j,j);
                dragonCards.add(card);
            }
        }
        for (Creature i: enemy)
        {
            for (int j = 1; j <= 2; j ++)
            {
                DragonCard card = new DragonCard(i.getName() +" x"+j,j);
                dragonCards.add(card);
            }
        }
        Collections.shuffle(dragonCards); // This shuffles the dragon cards arrayList so we can randomise placement of the dragon cards
        for(DragonCard i : dragonCards)
        {
            add(i);
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
