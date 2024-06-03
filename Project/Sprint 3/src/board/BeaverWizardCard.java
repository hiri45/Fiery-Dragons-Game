package src.board;

import src.actors.DragonToken;
import src.utils.PlayerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BeaverWizardCard extends JPanel {
    private final ImageIcon front = new ImageIcon(Objects.requireNonNull(DragonCard.class.getResource("/src/Images/dragon card resize.png")));
    private ArrayList<DragonToken> used = new ArrayList<DragonToken>();

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
        for (DragonToken i : used){
            if(currentPlayer == i){
                //dont play
            }
            else{
                Random rand= new Random();

                int randomNumber = rand.nextInt(5)+1;
                currentPlayer.move(randomNumber);
            }
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