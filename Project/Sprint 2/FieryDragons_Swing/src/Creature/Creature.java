package Creature;

import Caves.Cave;

import javax.swing.*;
import java.net.URL;

//Class to store all information about a specific creature including graphics, name and enemy status
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
    public static ImageIcon retrieveImage(String location) {
        URL imgLocation = Cave.class.getResource(location);
        if (imgLocation != null) {
            return new ImageIcon(imgLocation);
        } else {
            System.err.println("cannot find");
            return null;
        }
    }

    public ImageIcon getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}

