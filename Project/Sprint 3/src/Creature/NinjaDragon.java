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

    @Override
    public ImageIcon getDragonImage(int dragonAmount) {
        return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/src/Images/Ninja Dragon resize.jpg")));
    }

    @Override
    public void performSpecialAction(DragonToken dragonToken) {
        PlayerManager playerManager = PlayerManager.getInstance();
        DragonToken closestPlayer = dragonToken;
        int boardSize = BoardArray.getInstance().getSquares().size();
        int temp;
        int closestDistance = boardSize;
        for (DragonToken player:playerManager.getPlayers()){
            temp = circularDistance(dragonToken.getPosition(),player.getPosition(),boardSize);
            if (temp < closestDistance && !dragonToken.equals(player) && !player.isInCave()){
                closestPlayer = player;
                closestDistance = temp;
            }
        }
        if (closestPlayer != dragonToken){
            MovementManager.getInstance().swap(dragonToken,closestPlayer);
        }

    }
    private int circularDistance(int pos1, int pos2, int boardSize) {
        int directDistance = Math.abs(pos1 - pos2);
        int wrapAroundDistance = boardSize - directDistance;
        return Math.min(directDistance, wrapAroundDistance);
    }
}
