package src.board;

import src.actors.DragonToken;
import src.gui.WindowPanel;
import src.utils.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BeaverWizardCard extends JPanel {
    private final ImageIcon front = new ImageIcon(Objects.requireNonNull(DragonCard.class.getResource("/src/Images/beaverwizard resize.png")));
    private final ArrayList<DragonToken> used = new ArrayList<DragonToken>();

    public BeaverWizardCard() {
        JLabel label = new JLabel(front);
        add(label);
        revalidate();
        repaint();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {randomMove(); }});
    }
    public void randomMove() {
        PlayerManager playerManager = PlayerManager.getInstance();
        DragonToken currentPlayer = playerManager.getPlayers().get(playerManager.getPlayerTurn());

        boolean canUse = true;
        for (DragonToken i : used) {
            if (currentPlayer == i) {
                canUse = false;
                break;
            }
        }
        if(!canUse){
            JOptionPane.showMessageDialog(null,"Don't be selfish! Player has already used Beaver Wizards power!","Move Blocked",JOptionPane.PLAIN_MESSAGE);
        }
        else{
            used.add(currentPlayer);
            Random rand= new Random();

            int randomNumber = rand.nextInt(5)+1;
            currentPlayer.move(randomNumber);
            //playerManager.updatePlayerTurn(); //un comment if you want player turn to change after using card. Currently will not
            WindowPanel.getInstance().showPlayerTurnPopup("You have used the Beaver Wizards power and have moved " +
                    (randomNumber) + " spots!");
            BoardArray.getInstance().resetDragonCards();
        }



    }



    @Override
    /**
     * Paints the component with a custom background.
     * @param g the Graphics object to protect
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color brownBackground = new Color(51, 0, 0);
        g.setColor(brownBackground);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
