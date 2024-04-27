package Caves;

import Creature.*;

import javax.swing.*;

public class SalamanderCave extends Cave{
    public SalamanderCave(int positionX, int positionY) {
        super(new ImageIcon("src/Images/salamander resize.png"), new Salamander(), positionX, positionY);
    }
}
