package Caves;

import Creature.*;

import javax.swing.*;

public class SpiderCave extends Cave{
    public SpiderCave(int positionX, int positionY) {
        super(new ImageIcon("src/Images/spider resize.png"), new Spider(), positionX, positionY);
    }
}
