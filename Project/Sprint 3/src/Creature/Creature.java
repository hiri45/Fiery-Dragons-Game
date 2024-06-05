package src.Creature;

import javax.swing.*;
import java.util.Objects;

public abstract class Creature {
    private final String name;
    private final ImageIcon image;
    private final boolean isEnemy;

    /**
     * Constructor for the Creature class.
     * @param name the name of the creature
     * @param image the image of the creature
     * @param isEnemy whether the creature is an enemy
     */
    public Creature(String name, ImageIcon image, boolean isEnemy) {
        this.name = name;
        this.image = image;
        this.isEnemy = isEnemy;
    }

    /**
     * Retrieves the image for the creature.
     * @param imagePath the path to the image file
     * @return the image icon
     */
    protected static ImageIcon retrieveImage(String imagePath) {
        return new ImageIcon(Objects.requireNonNull(Creature.class.getResource(imagePath)));
    }

    /**
     * Abstract method to get the dragon image based on the amount.
     * @param dragonAmount the amount of dragons
     * @return the image icon representing the dragon
     */
    public abstract ImageIcon getDragonImage(int dragonAmount);

    /**
     * Gets the name of the creature.
     * @return the name of the creature
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the image of the creature.
     * @return the image of the creature
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Checks if the creature is an enemy.
     * @return true if the creature is an enemy, false otherwise
     */
    public boolean isEnemy() {
        return isEnemy;
    }
    /**
     * when getting the name of the creature type it can be converted into the type of creature it corresponds to
     * @param creatureType name of creature in String format
     * @return creature type corresponding to the string given
     * */
    public static Creature stringToCreature(String creatureType) {
        switch (creatureType) {
            case "BabyDragon":
                return new BabyDragon();
            case "Bat":
                return new Bat();
            case "Salamander":
                return new Salamander();
            case "Spider":
                return new Spider();
            default:
                throw new IllegalArgumentException("Unknown creature type: " + creatureType);
        }
    }
}
