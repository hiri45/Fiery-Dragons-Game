package src.Creature;

import javax.swing.*;

public class Spider extends Creature{
    public Spider() {
        super("Spider", retrieveImage("/src/Images/spider resize.png"), false);
    }
    @Override
    public ImageIcon getDragonImage(int dragonAmount) {
        // Provide specific implementation for getting dragon image
        // This is just a placeholder implementation
        if(dragonAmount == 1){
            return new ImageIcon(this.getClass().getResource("/src/Images/spider resize.png"));
        }
        else if (dragonAmount == 2){
            return new ImageIcon(this.getClass().getResource("/src/Images/2 spider.png"));
        }
        else{
            return new ImageIcon(this.getClass().getResource("/src/Images/3 spider.png"));
        }
    }
}
