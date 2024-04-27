package Caves;

import Creature.*;

import javax.swing.*;

public class BabyDragonCave extends Cave{
    public BabyDragonCave(int positionX, int positionY) {
        super(new ImageIcon("src/Images/bat resize.png"), new BabyDragon(), positionX, positionY);
    }

}