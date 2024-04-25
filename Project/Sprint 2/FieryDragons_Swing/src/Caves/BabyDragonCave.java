package Caves;

import Creature.*;

import javax.swing.*;

public class BabyDragonCave extends Cave{
    private static BabyDragonCave instance;
    public BabyDragonCave() {
        super(new ImageIcon("src/Images/baby dragon resize.png"), new BabyDragon());
    }
    //method to get the instance of the class
    public static BabyDragonCave getInstance() {
        if (instance == null) {
            instance = new BabyDragonCave();
        }
        return instance;
    }
}