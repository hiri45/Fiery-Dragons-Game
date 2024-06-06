/**
 * NinjaDragon.java
 *
 * This class defines the NinjaDragon creature, a special type of dragon that
 * implements the SpecialCreature interface. It includes a special action where
 * the NinjaDragon swaps positions with the closest player on the board.
 *
 * Author: Alex Ung
 * Date: 1/06/2024
 */

package src.Creature;

import src.actors.DragonToken;
import src.board.BoardArray;
import src.utils.MovementManager;
import src.utils.PlayerManager;

import javax.swing.*;
import java.util.Objects;

public class NinjaDragon extends Creature implements SpecialCreature{

    /**
     * Constructor for the Creature class.
     *
     */
    public NinjaDragon() {
        super("Ninja Dragon", retrieveImage("/src/Images/Ninja Dragon resize.jpg"), true);
    }

    /**
     * Retrieves the image of the Ninja Dragon.
     *
     * @param dragonAmount The amount of dragons (not used in this implementation).
     * @return The ImageIcon of the Ninja Dragon.
     */
    @Override
    public ImageIcon getDragonImage(int dragonAmount) {
        return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/Ninja Dragon resize.jpg")));
    }

    /**
     * Performs the special action of the Ninja Dragon.
     * This action involves swapping the position of the Ninja Dragon with the closest player
     * on the board, considering the circular nature of the board.
     *
     * @param dragonToken The dragon token that will perform the special action.
     */
    @Override
    public void performSpecialAction(DragonToken dragonToken) {
        PlayerManager playerManager = PlayerManager.getInstance();
        DragonToken closestPlayer = dragonToken;
        int boardSize = BoardArray.getInstance().getSquares().size();
        int temp;
        int closestDistance = boardSize;

        // Find the closest player
        for (DragonToken player:playerManager.getPlayers()){
            temp = circularDistance(dragonToken.getPosition(),player.getPosition(),boardSize);
            if (temp < closestDistance && !dragonToken.equals(player) && !player.isInCave()){
                closestPlayer = player;
                closestDistance = temp;
            }
        }
        // Perform the swap if a closest player is found
        if (closestPlayer != dragonToken){
            if (closestPlayer.getPosition() < dragonToken.getCave().getCavePosition()){
                dragonToken.setCycleTracker(dragonToken.getCycleTracker()-1);
            }
            MovementManager.getInstance().swap(dragonToken,closestPlayer);
        }

    }

    /**
     * Calculates the circular distance between two positions on the board.
     *
     * @param pos1 The first position.
     * @param pos2 The second position.
     * @param boardSize The size of the board.
     * @return The minimum distance considering the circular nature of the board.
     */
    private int circularDistance(int pos1, int pos2, int boardSize) {
        int directDistance = Math.abs(pos1 - pos2);
        int wrapAroundDistance = boardSize - directDistance;
        return Math.min(directDistance, wrapAroundDistance);
    }
}
