package Caves;

import Creature.*;

import javax.swing.*;

public class SalamanderCave extends Cave{
    private static SalamanderCave instance;
    public SalamanderCave() {
        super(new ImageIcon("src/Images/salamander resize.png"), new Salamander());
    }
    //method to get the instance of the class
    public static SalamanderCave getInstance() {
        if (instance == null) {
            instance = new SalamanderCave();
        }
        return instance;
    }
}
