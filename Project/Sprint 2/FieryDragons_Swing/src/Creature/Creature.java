package Creature;

import javax.swing.*;

public abstract class Creature {
    private final ImageIcon image;
    private final String name;

    public Creature(String name, ImageIcon image)
    {
        this.name = name;
        this.image = image;
    }

    public ImageIcon getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}

