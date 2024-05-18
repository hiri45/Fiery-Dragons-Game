package src.Creature;

import javax.swing.*;

public class PirateDragon extends Creature{
    public PirateDragon() {
        super("PirateDragon", retrieveImage("/src/Images/FD logo.png"), true);
    }
    @Override
    public ImageIcon getDragonImage(int dragonAmount) {
        // Provide specific implementation for getting dragon image
        // This is just a placeholder implementation
        if(dragonAmount == 1){
            return new ImageIcon(this.getClass().getResource("/src/Images/skull.png"));
        }
        else{
            return new ImageIcon(this.getClass().getResource("/src/Images/2 skull.png"));
        }
    }
}
