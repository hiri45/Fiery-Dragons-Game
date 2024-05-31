package src.Creature;

import src.actors.DragonToken;

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

    }


//    public int dynamicMovement
}
