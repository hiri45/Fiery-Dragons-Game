import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    int dragonCardPoolX;
    int dragonCardPoolY;
    int dragonPoolSideLength;
    public Board(int screenWidth, int screenHeight) {
        setBounds(0,0,screenWidth,screenHeight);
        Color darkGreenBackground = new Color(0,75,0);
        setBackground(darkGreenBackground);
        setLayout(null);

        this.dragonPoolSideLength = (screenWidth/4); //Dynamic dragon pool size so that it always scales with screen size
        this.dragonCardPoolX = (screenWidth - dragonPoolSideLength) / 2;
        this.dragonCardPoolY = (screenHeight - dragonPoolSideLength) / 2;

        placeVolcano(3);
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

        for (int i = 0; i < x; i++) {
            int offset = i * (volcanoSideLength + gap);


            VolcanoCard topVolcanoCard = new VolcanoCard();
            topVolcanoCard.setBounds(this.dragonCardPoolX + offset, this.dragonCardPoolY - volcanoSideLength/x, volcanoSideLength, volcanoSideLength/x);
            topVolcanoCard.setLayout(new GridLayout(1,3,10,10));
            add(topVolcanoCard);

            VolcanoCard bottomVolcanoCard = new VolcanoCard();
            bottomVolcanoCard.setBounds(this.dragonCardPoolX + offset, this.dragonCardPoolY + effectiveLength, volcanoSideLength, volcanoSideLength/x);
            add(bottomVolcanoCard);
            bottomVolcanoCard.setLayout(new GridLayout(1,3,10,10));

            VolcanoCard leftVolcanoCard = new VolcanoCard();
            leftVolcanoCard.setBounds(this.dragonCardPoolX - volcanoSideLength/x, this.dragonCardPoolY + offset, volcanoSideLength/x, volcanoSideLength);
            leftVolcanoCard.setLayout(new GridLayout(3,1,10,10));
            add(leftVolcanoCard);

            VolcanoCard rightVolcanoCard = new VolcanoCard();
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
