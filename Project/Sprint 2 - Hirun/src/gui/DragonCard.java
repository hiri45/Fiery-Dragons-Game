package gui;

import javax.swing.*;

public class DragonCard {
    private String dragonCardCreature;
    private boolean flipStatus;
    private int creatureQuant;

    public DragonCard(){
        // these variables will be helpful for later when matching and moving the player
        this.dragonCardCreature = null;
        this.flipStatus = false;
        this.creatureQuant = 0;
    }

    public void setFlipStatus(boolean flipStatus) {
        this.flipStatus = flipStatus;
    }

    public void setCreatureQuant(int creatureQuant) {
        this.creatureQuant = creatureQuant;
    }

    public void setDragonCardCreature(String dragonCardCreature) {
        this.dragonCardCreature = dragonCardCreature;
    }

    public String getDragonCardCreature() {
        return dragonCardCreature;
    }

    public boolean isFlipStatus() {
        return flipStatus;
    }

    public int getCreatureQuant() {
        return creatureQuant;
    }

    void flipDragon(JButton button, int cardIndex) {
        // Set the button's text to the string from the dragonCardFlipped array. This dragonCardFlipped array is only displayed like this for the time being.
        // corresponding to the index of the button that was clicked
        String[] dragonCardFlipped = {"1 salamander","2 salamander", "3 salamander","1 bat",
                "2 bat","3 bat", "1 spider","2 spider", "3 spider", "1 babyDragon","2 babyDragon","3 babyDragon","1 pirateDragon",
                "1 pirateDragon","2 pirateDragon","2 pirateDragon" };
        button.setText(dragonCardFlipped[cardIndex]);
        // Optionally, remove the image icon when the card is flipped
        button.setIcon(null);
        // Revalidate and repaint the button to show the text
        button.revalidate();
        button.repaint();
        setFlipStatus(true);
        splitDragonString(dragonCardFlipped[cardIndex]);
    }

    // this method is used to split the dragonCardFlipped string array to access the creature quant and type individually
    void splitDragonString(String card) {
        String[] parts = card.split(" ", 2); // Split the string into two parts
        if (parts.length == 2) {
            // Parse the number part to an integer
            try {
                setCreatureQuant(Integer.parseInt(parts[0]));
            } catch (NumberFormatException e) {
                // Handle the exception if the number part is not a valid integer
                setCreatureQuant(0);
            }
            setDragonCardCreature(parts[1]);; // The creature name part
        }
    }


}
