package src.Creature;

import javax.swing.*;
import java.util.Objects;

public class BabyDragon extends Creature {

    /**
     * Constructor for the BabyDragon class.
     */
    public BabyDragon() {
        super("BabyDragon", retrieveImage("/src/Images/baby dragon resize.png"), false);
    }

    @Override
    /**
     * Provides a specific implementation for getting the dragon image.
     * @param dragonAmount the amount of dragons
     * @return the image icon representing the dragon
     */
    public ImageIcon getDragonImage(int dragonAmount) {
        if (dragonAmount == 1) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/baby dragon resize.png")));
        } else if (dragonAmount == 2) {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/2 baby dragon.png")));
        } else {
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/3 baby dragon.png")));
        }
    }
}

