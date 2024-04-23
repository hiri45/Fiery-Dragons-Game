package gui;

import javax.swing.*;

public class DragonCard {
    private String dragonCard;
    private boolean flipStatus;
    private int creatureQuant;
    public DragonCard(){
        this.dragonCard = "";
    }
    void flipDragon(JButton button, int cardIndex) {
        // Set the button's text to the string from the dragonCardFlipped array
        // corresponding to the index of the button that was clicked
        String[] dragonCardFlipped = {"1 salamandar","2 salamandar", "3 salamandar","1 bat",
                "2 bat","3 bat", "1 spider","2 spider", "3 spider", "1 baby dragon","2 baby dragon","3 baby dragon","1 pirate dragon",
                "1 pirate dragon","2 pirate dragon","2 pirate dragon" };
        button.setText(dragonCardFlipped[cardIndex]);
        // Optionally, remove the image icon when the card is flipped
        button.setIcon(null);
        // Revalidate and repaint the button to show the text
        button.revalidate();
        button.repaint();
    }
}
