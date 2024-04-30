package game;

import game.creatures.Creature;

public class Cave {
    private final Creature caveCreature;

    public Cave(Creature caveCreature) {
        this.caveCreature = caveCreature;
    }

    public Creature getCaveCreature() {
        return caveCreature;
    }
}
