import Creature.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Board extends JPanel {
    int dragonCardPoolX;
    int dragonCardPoolY;
    int dragonPoolSideLength;
    ArrayList<Creature[]> normalVolcanoCards = new ArrayList<>();
    ArrayList<Creature[]> caveVolcanoCards = new ArrayList<>();

    public Board(int screenWidth, int screenHeight) {

        this.dragonPoolSideLength = (screenWidth/4); //Dynamic dragon pool size so that it always scales with screen size
        this.dragonCardPoolX = (screenWidth - dragonPoolSideLength) / 2;
        this.dragonCardPoolY = (screenHeight - dragonPoolSideLength) / 2;

        Creature[] set1 = {new Spider(), new Bat(), new Salamander()};
        Creature[] set2 = {new BabyDragon(), new Salamander(), new Bat()};
        Creature[] set3 = {new Bat(), new BabyDragon(), new Salamander()};
        Creature[] set4 = {new Salamander(), new BabyDragon(), new Spider()};
        Creature[] set5 = {new BabyDragon(), new Bat(), new Spider()};
        Creature[] set6 = {new Salamander(), new Spider(), new Bat()};
        Creature[] set7 = {new Spider(), new Salamander(), new BabyDragon()};
        Creature[] set8 = {new Bat(), new Spider(), new BabyDragon()};

        normalVolcanoCards.add(set1);
        normalVolcanoCards.add(set2);
        normalVolcanoCards.add(set3);
        normalVolcanoCards.add(set4);
        normalVolcanoCards.add(set5);
        normalVolcanoCards.add(set6);
        normalVolcanoCards.add(set7);
        normalVolcanoCards.add(set8);

        setBounds(0,0,screenWidth,screenHeight);
        Color darkGreenBackground = new Color(0,75,0);
        setBackground(darkGreenBackground);
        setLayout(null);


        placeVolcano(2);
        placeDragonCardPool();
    }

    private void placeVolcano(int x)
    {
        // Define dimensions and spacing for the squares (tiles) around the dragon card pool
        int gap = 10; // Gap between panels
        int effectiveLength = dragonPoolSideLength; // Panels plus gaps must fill the entire side length

        // Calculate the side length of each volcano tile based on the number of gaps (x - 1) and the total number of panels (x)
        int volcanoSideLength = (effectiveLength - (x - 1) * gap) / x;

        // Correcting starting points to align with dragon card pool
        Collections.shuffle(normalVolcanoCards);
        for (int i = 0; i < x; i++) {
            int offset = i * (volcanoSideLength + gap);


            VolcanoCard topVolcanoCard = new VolcanoCard(normalVolcanoCards.get(3*i));
            topVolcanoCard.setBounds(this.dragonCardPoolX + offset, this.dragonCardPoolY - volcanoSideLength/x, volcanoSideLength, volcanoSideLength/x);
            topVolcanoCard.setLayout(new GridLayout(1,3,10,10));
            add(topVolcanoCard);

            VolcanoCard bottomVolcanoCard = new VolcanoCard(normalVolcanoCards.get(3*i+1));
            bottomVolcanoCard.setBounds(this.dragonCardPoolX + offset, this.dragonCardPoolY + effectiveLength, volcanoSideLength, volcanoSideLength/x);
            add(bottomVolcanoCard);
            bottomVolcanoCard.setLayout(new GridLayout(1,3,10,10));

            VolcanoCard leftVolcanoCard = new VolcanoCard(normalVolcanoCards.get(3*i+1));
            leftVolcanoCard.setBounds(this.dragonCardPoolX - volcanoSideLength/x, this.dragonCardPoolY + offset, volcanoSideLength/x, volcanoSideLength);
            leftVolcanoCard.setLayout(new GridLayout(3,1,10,10));
            add(leftVolcanoCard);

            VolcanoCard rightVolcanoCard = new VolcanoCard(normalVolcanoCards.get(3*i+1));
            rightVolcanoCard.setBounds(this.dragonCardPoolX + effectiveLength, this.dragonCardPoolY + offset, volcanoSideLength/x, volcanoSideLength);
            rightVolcanoCard.setLayout(new GridLayout(3,1,10,10));
            add(rightVolcanoCard);
        }
    }

    private void placeDragonCardPool()
    {
        DragonCardPool dragonCardPool = new DragonCardPool();
        dragonCardPool.setBounds(dragonCardPoolX, dragonCardPoolY, dragonPoolSideLength, dragonPoolSideLength);
        add(dragonCardPool);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
