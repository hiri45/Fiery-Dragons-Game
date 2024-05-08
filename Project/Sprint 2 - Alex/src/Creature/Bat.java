package src.Creature;

import javax.swing.*;
import java.net.URL;

public class Bat extends Creature {
    public Bat() {
        super("Bat", retrieveImage("/src/Images/bat resize.png"), false);
    }
}
