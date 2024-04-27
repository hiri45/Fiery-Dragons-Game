package Creature;

import javax.swing.*;
//Class to store all information about a specific creature
public abstract class Creature {
    private final ImageIcon image;
    private final String name;
    private final boolean enemy;

    public Creature(String name, ImageIcon image, boolean enemy)
    {
        this.name = name;
        this.image = image;
        this.enemy = enemy;
    }

    public ImageIcon getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}

