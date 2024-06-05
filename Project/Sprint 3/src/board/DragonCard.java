package src.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

import src.Creature.*;
import src.actors.DragonToken;
import src.gui.WindowPanel;
import src.utils.MovementManager;
import src.utils.PlayerManager;

public class DragonCard extends JPanel {
    private final ImageIcon front = new ImageIcon(Objects.requireNonNull(DragonCard.class.getResource("/src/Images/dragon card resize.png")));
    private final ImageIcon back;
    private boolean isFrontVisible = true;
    private boolean flipped = false;
    private final int creatureAmount;
    private final Creature creature;

    /**
     * Constructor for the DragonCard class.
     * @param creature the creature associated with the card
     * @param creatureAmount the amount of creatures
     */
    public DragonCard(Creature creature, int creatureAmount) {
        this.creature = creature;
        //currently displays the name and amount of creature, but will change to an image in later sprint
        this.back = creature.getDragonImage(creatureAmount); //creature.getName()+ " x"+ creatureAmount;
        this.creatureAmount = creatureAmount;
        setLayout(new BorderLayout());
        updateAppearance();
        //when mouse is clicked on panel, card will flip
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkFlip();
            }
        });
    }

    /**
     * Method to flip the card.
     */
    public void flip() {
        isFrontVisible = !isFrontVisible;
        updateAppearance();
    }

    public boolean isFlipped() {
        if (flipped) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Method to check if the card should flip.
     */
    public void checkFlip() {
        if (!flipped) {
            flipped = true;
            flip();
        }
    }

    /**
     * Method to update the appearance of the card.
     */
    private void updateAppearance() {
        removeAll(); // Clear any existing content
        JLabel label;
        if (isFrontVisible) {
            label = new JLabel(front);
            add(label);
            revalidate();
            repaint();
        } else {
            label = new JLabel(back);
            PlayerManager playerManager = PlayerManager.getInstance();
            DragonToken currentPlayer = playerManager.getPlayers().get(playerManager.getPlayerTurn());

            BoardArray boardArray = BoardArray.getInstance();
            WindowPanel windowPanel = WindowPanel.getInstance();

            if (!this.creature.isEnemy()) {
                if (currentPlayer.isInCave() && this.creature.getName().equals(currentPlayer.getCave().getCreatureType().getName())) {
                    add(label);
                    revalidate();
                    repaint();
                    currentPlayer.move(this.creatureAmount);
                } else if (!currentPlayer.isInCave() && this.creature.getName().equals(boardArray.getSquares().get(currentPlayer.getPosition()).getCreature().getName())) {
                    add(label);
                    revalidate();
                    repaint();
                    currentPlayer.move(this.creatureAmount);
                } else {
                    add(label);
                    revalidate();
                    repaint();
                    playerManager.updatePlayerTurn();
                    playerManager.handOverTurn();
                    windowPanel.displayCurrentPlayer();
                }
            } else {
                add(label);
                revalidate();
                repaint();
                if(this.creature instanceof SpecialCreature){
                    ((SpecialCreature) this.creature).performSpecialAction(currentPlayer);
                }else{
                    currentPlayer.move(-(this.creatureAmount));
                }
                playerManager.updatePlayerTurn();
                playerManager.handOverTurn();
                windowPanel.displayCurrentPlayer();
            }
        }
    }

    /**
     * Getter for creatureAmount.
     * @return the amount of creatures
     */
    public int getCreatureAmount() {
        return creatureAmount;
    }

    /**
     * Setter for isFrontVisible.
     * @param frontVisible visibility status of the front side of the card
     */
    public void setFrontVisible(boolean frontVisible) {
        isFrontVisible = frontVisible;
    }

    /**
     * Getter for isFrontVisible.
     * @return true if the front side of the card is visible, false otherwise
     */
    public boolean isFrontVisible() {
        return isFrontVisible;
    }

    /**
     * Getter for creature.
     * @return the creature associated with the card
     */
    public Creature getCreature() {
        return creature;
    }

    /**
     * Setter for flipped.
     * @param flipped flip status of the card
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}

