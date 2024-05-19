package src.Creature;

import javax.swing.*;
import java.util.Objects;

public class BabyDragon extends Creature{
    public BabyDragon() {
        super("BabyDragon",retrieveImage("/src/Images/baby dragon resize.png"),false);
    }
    @Override
    public ImageIcon getDragonImage(int dragonAmount) {
        // Provide specific implementation for getting dragon image
        // This is just a placeholder implementation
        if(dragonAmount == 1){
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/baby dragon resize.png")));
        }
        else if (dragonAmount == 2){
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/2 baby dragon.png")));
        }
        else{
            return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/3 baby dragon.png")));
        }
    }
}
