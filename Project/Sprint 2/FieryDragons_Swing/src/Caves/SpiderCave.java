package Caves;

import Creature.*;

import javax.swing.*;

public class SpiderCave extends Cave{
    private static SpiderCave instance;
    public SpiderCave() {
        super(new ImageIcon("src/Images/spider resize.png"), new Spider());
    }
    //method to get the instance of the class
    public static SpiderCave getInstance() {
        if (instance == null) {
            instance = new SpiderCave();
        }
        return instance;
    }
}
