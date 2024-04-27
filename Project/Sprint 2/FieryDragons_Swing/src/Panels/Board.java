package Panels;

import Caves.*;
import Creature.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Board extends JPanel {
    /*
    Board attributes that store everything that should belong on the board. Changes do need to be made to make Board
    not the central class of the game by adding managers to handle some parts of the game seperately. Will be added in
    further sprints
    */
    private static Board instance;
    private final int players;
    private final int dragonCardPoolX;
    private final int dragonCardPoolY;
    private final int dragonPoolSideLength;
    //At the moment the array holds a list of Creature. This should be changed to hold a list of all Volcano Cards instead
    public ArrayList<Creature[]> normalVolcanoCards = new ArrayList<>();
    public ArrayList<Creature[]> caveVolcanoCards = new ArrayList<>();

    private BatCave batCave;
    private SpiderCave spiderCave;
    private SalamanderCave salamanderCave;
    private BabyDragonCave babyDragonCave;

    private Board(int screenWidth, int screenHeight, int players) {
        //set attributes
        this.players = players;
        //calculate location for Dragon Card Pool so that it is in the centre of screen
        this.dragonPoolSideLength = (screenWidth/4); //Dynamic dragon pool size so that it always scales with screen size
        this.dragonCardPoolX = (screenWidth - dragonPoolSideLength) / 2;
        this.dragonCardPoolY = (screenHeight - dragonPoolSideLength) / 2;

        //Create all sets of volcano Cards and store them
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
        caveVolcanoCards.add(set5);
        caveVolcanoCards.add(set6);
        caveVolcanoCards.add(set7);
        caveVolcanoCards.add(set8);

        //Set background for Board
        setBounds(0,0,screenWidth,screenHeight);
        Color darkGreenBackground = new Color(0,75,0);
        setBackground(darkGreenBackground);
        setLayout(null);

        //create and place the volcano and dragon cards
        placeVolcano(8);
        placeDragonCardPool();

        //Store different type of tokens
        DragonToken[] tokens = {
                new DragonToken(0, 0, Color.RED),
                new DragonToken(0, 0, Color.GREEN),
                new DragonToken(0, 0, Color.BLUE),
                new DragonToken(0, 0, Color.YELLOW)
        };
        Cave[] caves = {
                babyDragonCave,
                salamanderCave,
                batCave,
                spiderCave
        };
        //displays tokens on caves depending on how many players are playing
        //tokens start opposite ends if 2 players to ensure even distance
        for (int i = 1; i <= players; i++)
        {
            placeTokenInCave(tokens[i-1], caves[i-1]);
        }



    }
    //adds volcano Card to board
    private void setupVolcanoCard(VolcanoCard card, int x, int y, int width, int height, int rows, int cols) {
        card.setBounds(x, y, width, height);
        card.setLayout(new GridLayout(rows, cols, 10, 10));
        add(card);
        }

    //adds volcano cards to board as well. Used for adding volcano Cards that have a cave attached to them
    //takes the card to be added and its location (x,y) and sizing as parameters. rows and cols are taken to distinguish how the grid of that card is split up
    private void setupVolcanoCard(VolcanoCard card, int x, int y, int width, int height, int rows, int cols, String panelPosition) {
        card.setBounds(x, y, width, height);
        card.setLayout(new GridLayout(rows, cols, 10, 10));
        add(card);

        // Calculate dimensions for the panel based on the card's grid size
        int gridWidth = width / cols;  // Assuming cols is the number of horizontal divisions
        int gridHeight = height / rows;  // Assuming rows is the number of vertical divisions

        // Initialize panel coordinates based on the chosen position
        //reassigned variable to show reference to inputs and how they are used. Easier to inspect.
        int panelX = x;
        int panelY = y;

        //adds cave to a cave volcano card and positions it correctly
        switch (panelPosition.toLowerCase()) {
            case "top":
                panelX = x + gridWidth;
                panelY = y - gridHeight;
                batCave = new BatCave(panelX, panelY);
                batCave.setBounds(panelX, panelY, gridWidth, gridHeight);
                add(batCave);
                break;
            case "bottom":
                panelX = x + gridWidth;
                panelY = y + height;
                spiderCave = new SpiderCave(panelX, panelY);
                spiderCave.setBounds(panelX,panelY, gridWidth, gridHeight);
                add(spiderCave);
                break;
            case "left":
                panelX = x - gridWidth;
                panelY = y + gridHeight;
                babyDragonCave = new BabyDragonCave(panelX, panelY);
                babyDragonCave.setBounds(panelX,panelY, gridWidth, gridHeight);
                add(babyDragonCave);
                break;
            case "right":
                panelX = x + width;
                panelY = y + gridHeight;
                salamanderCave = new SalamanderCave(panelX,panelY);
                salamanderCave.setBounds(panelX,panelY, gridWidth, gridHeight);
                add(salamanderCave);
                break;
        }
    }
    private void placeVolcano(int totalVolcanoCards) {
        //I have made the parameter the total amount of cards as I believe this will make it more simple to expand in the future. It may seem redundant now
        totalVolcanoCards = totalVolcanoCards / 4; // Divide by amount of sides which is 4. This is how many cards are on each side of the board
        int gap = 10; // Gap between cards.
        int effectiveLength = dragonPoolSideLength; // set like this in case effectiveLength needs an extension in the future. Also, easier to understand this section of the code
        Collections.shuffle(normalVolcanoCards);
        Collections.shuffle(caveVolcanoCards);

        int volcanoSideLength = (effectiveLength - (totalVolcanoCards - 1) * gap) / totalVolcanoCards;
        int tileHeight = volcanoSideLength / totalVolcanoCards;
        int tileWidth = volcanoSideLength / totalVolcanoCards;

        /*
         Place the 4 cave cards as the first volcano card of each side.
         Right and Top cards generate from opposite directions to Left and Bottom.
         This ensures that, even though the cave card is the first card generated on that side,
         the distance between each cave volcano card is the same
        */

        setupVolcanoCard(new VolcanoCard(caveVolcanoCards.get(0)), dragonCardPoolX + effectiveLength - volcanoSideLength, dragonCardPoolY - tileHeight, volcanoSideLength, tileHeight, 1, 3, "top");
        setupVolcanoCard(new VolcanoCard(caveVolcanoCards.get(1)), dragonCardPoolX, dragonCardPoolY + effectiveLength, volcanoSideLength, tileHeight, 1, 3,"bottom");
        setupVolcanoCard(new VolcanoCard(caveVolcanoCards.get(2)), dragonCardPoolX - tileWidth, dragonCardPoolY, tileWidth, volcanoSideLength, 3, 1,"left");
        setupVolcanoCard(new VolcanoCard(caveVolcanoCards.get(3)), dragonCardPoolX + effectiveLength, dragonCardPoolY + effectiveLength - volcanoSideLength, tileWidth, volcanoSideLength, 3, 1,"right");

        // Place normal volcano cards around the dragon card pool
        for (int i = 0; i < totalVolcanoCards - 1; i++) {
            int offset = (i + 1) * (volcanoSideLength + gap);

            setupVolcanoCard(new VolcanoCard(normalVolcanoCards.get(3 * i)), dragonCardPoolX + effectiveLength - offset - volcanoSideLength, dragonCardPoolY - tileHeight, volcanoSideLength, tileHeight, 1, 3);
            setupVolcanoCard(new VolcanoCard(normalVolcanoCards.get(3 * i + 1)), dragonCardPoolX + offset, dragonCardPoolY + effectiveLength, volcanoSideLength, tileHeight, 1, 3);
            setupVolcanoCard(new VolcanoCard(normalVolcanoCards.get(3 * i + 2)), dragonCardPoolX - tileWidth, dragonCardPoolY + offset, tileWidth, volcanoSideLength, 3, 1);
            setupVolcanoCard(new VolcanoCard(normalVolcanoCards.get(3 * i + 3)), dragonCardPoolX + effectiveLength, dragonCardPoolY + effectiveLength - offset - volcanoSideLength, tileWidth, volcanoSideLength, 3, 1);
        }
    }
    //creates and adds Dragon Card Pool to board
    private void placeDragonCardPool()
    {
        DragonCardPool dragonCardPool = new DragonCardPool();
        dragonCardPool.setBounds(dragonCardPoolX, dragonCardPoolY, dragonPoolSideLength, dragonPoolSideLength);
        add(dragonCardPool);
    }
    //places token in cave. This method may be changed to a more universal one for panels in general.
    public void placeTokenInCave(DragonToken token, Cave cave) {
        cave.setToken(token);
    }

    //singleton method for Board. As only one board should exist at a time, board should only have on instance at a time
    public static Board getInstance(int screenWidth, int screenHeight, int players) {
        if (instance == null) {
            instance = new Board(screenWidth, screenHeight, players);
        }
        return instance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
