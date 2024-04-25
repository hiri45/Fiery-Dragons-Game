package Caves;

import Creature.*;

import javax.swing.*;

public class BatCave extends Cave{
    private static BatCave instance;
    public BatCave() {
        super(new ImageIcon("src/Images/bat resize.png"), new Bat());
    }
    //method to get the instance of the class
    public static BatCave getInstance() {
        if (instance == null) {
            instance = new BatCave();
        }
        return instance;
    }
}
