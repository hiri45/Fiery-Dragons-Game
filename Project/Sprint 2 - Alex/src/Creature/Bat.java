package src.Creature;

import javax.swing.*;
import java.util.Objects;

public class Bat extends Creature {

    /**
     * Constructor for the Bat class.
     */
    public Bat() {
        super("Bat", retrieveImage("/src/Images/bat resize.png"), false);
    }

    @Override
    /**
     * Provides a specific implementation for getting the bat image.
     * @param dragonAmount the amount of bats
     * @return the image icon representing the bat
     */
    public ImageIcon getDragonImage(int dragonAmount) {
        if (dragonAmount == 1) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/bat resize.png")));
        } else if (dragonAmount == 2) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/2 bats.png")));
        } else {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/3 bats.png")));
        }
    }
}

