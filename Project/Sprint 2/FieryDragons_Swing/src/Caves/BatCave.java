package Caves;

import Creature.*;

import javax.swing.*;

public class BatCave extends Cave{
    public BatCave(int positionX, int positionY) {
        super(new ImageIcon("src/Images/bat resize.png"), new Bat(), positionX, positionY);
    }
}
