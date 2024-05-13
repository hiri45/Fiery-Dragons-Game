package src.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

import src.Creature.*;
import src.actors.DragonToken;
import src.utils.MovementManager;
import src.utils.PlayerManager;

public class DragonCard extends JPanel {
    private final ImageIcon front = new ImageIcon(Objects.requireNonNull(DragonCard.class.getResource("/src/Images/dragon card resize.png")));
    private final String back;
    private boolean isFrontVisible = true;
    private final int creatureAmount;
    private final Creature creature;

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
                flip();
            }
        });
    }

    public void flip() {
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
        }
        else
        {
            label = new JLabel(back);

            MovementManager movementManager = MovementManager.getInstance();
            PlayerManager playerManager = PlayerManager.getInstance();
            DragonToken currentPlayer = playerManager.getPlayers().get(playerManager.getPlayerTurn());
            if(!this.creature.isEnemy())
            {
                currentPlayer.move(this.creatureAmount);
//                movementManager.forwardsMovement(currentPlayer, this.creatureAmount);
            }
            else
            {
                currentPlayer.move(this.creatureAmount);
//                movementManager.backwardsMovement(currentPlayer, this.creatureAmount);
            }

        }
        add(label);
        revalidate();
        repaint();
    }

    public int getCreatureAmount() {
        return creatureAmount;
    }

    public void setFrontVisible(boolean frontVisible) {
        isFrontVisible = frontVisible;
    }

    public Creature getCreature() {
        return creature;
    }
}
