package src.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import src.Creature.*;
import src.board.BoardArray;
import src.board.DragonCard;

public class DragonCardPool extends JPanel {

    public Creature[] creatureArray = {new Bat(), new BabyDragon(), new Salamander(), new Spider()};
    public Creature[] enemy = {new PirateDragon(), new PirateDragon()};
    public Creature[] specialCreature = {new NinjaDragon()};
    public ArrayList<DragonCard> dragonCards = new ArrayList<>();
    public BoardArray boardArray = BoardArray.getInstance();

    /**
     * Constructor for the DragonCardPool class.
     */
    public DragonCardPool() {
        setOpaque(false); // Make the Panel transparent (other than the circle)
        setLayout(new GridLayout(4, 4, 10, 10)); // Layout with 4 rows, 4 columns, and gaps
        generateDragonCards();
    }

    /**
     * Generates the dragon cards and adds them to the pool.
     */
    private void generateDragonCards() {
        // Loop to create all type of dragon cards and then randomize before placing them on the board
        //dragonCards = new ArrayList<>();
        for (Creature i : creatureArray) {
            for (int j = 1; j <= 3; j++) {
                DragonCard card = new DragonCard(i, j);
                dragonCards.add(card);
            }
        }
        for (Creature i : enemy) {
            for (int j = 1; j <= 2; j++) {
                DragonCard card = new DragonCard(i, j);
                dragonCards.add(card);
            }
        }

        for (Creature i : specialCreature) {
            for (int j = 0;j<1; j++) {
                DragonCard card = new DragonCard(i, j);
                dragonCards.add(card);
            }
        }

        // Collections.shuffle(dragonCards); // This shuffles the dragon cards arrayList so we can randomize placement of the dragon cards
        BoardArray boardArray = BoardArray.getInstance();
//        Collections.shuffle(dragonCards); // This shuffles the dragon cards arrayList so we can randomize placement of the dragon cards
        boardArray.setDragonCards(dragonCards);
        // Adds dragon cards to dragon card pool panel
        for (DragonCard i : dragonCards) {
            add(i);
        }
    }

    public ArrayList<DragonCard> getDragonCards() {
        return dragonCards;
    }

    public void setDragonCards(ArrayList<DragonCard> dragonCards) {
        this.dragonCards = dragonCards;
        removeAll();
        for (DragonCard card : dragonCards) {
            add(card);
        }
        boardArray.setDragonCards(dragonCards);
        revalidate();
        repaint();
    }


    @Override
    /**
     * Paints the component with a custom background.
     * @param g the Graphics object to protect
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color brownBackground = new Color(51, 0, 0);
        g.setColor(brownBackground);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}

