package src.Creature;

import javax.swing.*;
import java.net.URL;

public class Bat extends Creature {
    public Bat() {
        super("Bat", retrieveImage("/src/Images/bat resize.png"), false);
    }
    @Override
    public ImageIcon getDragonImage(int dragonAmount) {
        // Provide specific implementation for getting dragon image
        // This is just a placeholder implementation
        if(dragonAmount == 1){
            return new ImageIcon(this.getClass().getResource("/src/Images/bat resize.png"));
        }
        else if (dragonAmount == 2){
            return new ImageIcon(this.getClass().getResource("/src/Images/spider resize.png"));
        }
        else{
            return new ImageIcon(this.getClass().getResource("/src/Images/salamander resize.png"));
        }
    }
}
