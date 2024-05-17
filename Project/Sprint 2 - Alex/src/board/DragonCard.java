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
    private final String back;
    private boolean isFrontVisible = true;
    private final int creatureAmount;
    private final Creature creature;
    private boolean flipped = false;

    public DragonCard(Creature creature, int creatureAmount) {
        this.creature = creature;
        //currently displays the name and amount of creature, but will change to an image in later sprint
        this.back = creature.getName()+ " x"+ creatureAmount;
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

    public void checkFlip() {
        //checks if the card can be flipped, and only flips if it can be
        if(!flipped)
        {
            flipped = true;
            flip();
        }
    }
    public void flip(){
        isFrontVisible = !isFrontVisible;
        updateAppearance();
    }

    //will update appearance of dragon card depending on whether isFrontVisible is true or nah
    private void updateAppearance() {
        removeAll(); // Clear any existing content
        JLabel label;
        if(isFrontVisible)
        {
            label = new JLabel(front);
            add(label);
            revalidate();
            repaint();
        }
        else
        {
            label = new JLabel(back);

            PlayerManager playerManager = PlayerManager.getInstance();
            DragonToken currentPlayer = playerManager.getPlayers().get(playerManager.getPlayerTurn());

            BoardArray boardArray = BoardArray.getInstance();
            WindowPanel windowPanel = WindowPanel.getInstance();

            if(!this.creature.isEnemy())
            {
                if(this.creature.getName() == boardArray.getSquares().get(currentPlayer.getPosition()).getCreature().getName())
                {
                    add(label);
                    revalidate();
                    repaint();
                    currentPlayer.move(this.creatureAmount);
                }
                else
                {
                    add(label);
                    revalidate();
                    repaint();
                    playerManager.updatePlayerTurn();
                    windowPanel.displayCurrentPlayer();

                }
            }
            else
            {
                add(label);
                revalidate();
                repaint();
                currentPlayer.move(-(this.creatureAmount));
                playerManager.updatePlayerTurn();
                windowPanel.displayCurrentPlayer();
            }
        }
    }

    public int getCreatureAmount() {
        return creatureAmount;
    }

    public void setFrontVisible(boolean frontVisible) {
        isFrontVisible = frontVisible;
    }
    public boolean isFrontVisible() {
        return isFrontVisible;
    }

    public Creature getCreature() {
        return creature;
    }

    public boolean isCanFlip() {
        return flipped;
    }

    public void setCanFlip(boolean canFlip) {
        this.flipped = canFlip;
    }
}
