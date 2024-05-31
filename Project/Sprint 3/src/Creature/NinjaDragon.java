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
        int temp;
        int closestDistance = BoardArray.getInstance().getSquares().size();
        for (DragonToken player:playerManager.getPlayers()){
            temp = Math.abs(dragonToken.getPosition() - player.getPosition());
            if (temp < closestDistance && dragonToken != player && !player.isInCave()){
                closestPlayer = player;
                closestDistance = player.getPosition();
            }
        }
        if (closestPlayer != dragonToken){
            MovementManager.getInstance().swap(dragonToken,closestPlayer);
        }

    }
}
