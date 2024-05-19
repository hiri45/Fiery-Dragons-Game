package src.Creature;

import javax.swing.*;
import java.util.Objects;

public class Salamander extends Creature {

    /**
     * Constructor for the Salamander class.
     */
    public Salamander() {
        super("Salamander", retrieveImage("/src/Images/salamander resize.png"), false);
    }

    @Override
    /**
     * Provides a specific implementation for getting the salamander image.
     * @param dragonAmount the amount of salamanders
     * @return the image icon representing the salamander
     */
    public ImageIcon getDragonImage(int dragonAmount) {
        // Provide specific implementation for getting dragon image
        // This is just a placeholder implementation
        if (dragonAmount == 1) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/salamander resize.png")));
        } else if (dragonAmount == 2) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/2 salamander.png")));
        } else {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/3 salamander.png")));
        }
    }
}

