package src.Creature;

import javax.swing.*;
import java.util.Objects;

public class PirateDragon extends Creature {

    /**
     * Constructor for the PirateDragon class.
     */
    public PirateDragon() {
        super("PirateDragon", retrieveImage("/src/Images/pirate dragon resize.png"), true);
    }

    @Override
    /**
     * Provides a specific implementation for getting the pirate dragon image.
     * @param dragonAmount the amount of pirate dragons
     * @return the image icon representing the pirate dragon
     */
    public ImageIcon getDragonImage(int dragonAmount) {
        // Provide specific implementation for getting dragon image
        // This is just a placeholder implementation
        if (dragonAmount == 1) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/pirate dragon resize.png")));
        } else if (dragonAmount == 2) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/2 pirate dragon.png")));
        } else {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/3 pirate dragon.png")));
        }
    }
}

