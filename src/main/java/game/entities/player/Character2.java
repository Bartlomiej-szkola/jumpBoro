package game.entities.player;

import game.entities.base.CharacterStats;

public class Character2 extends Player {
    public Character2() {
        super("/character1.png", new CharacterStats(3.5, 30, 1.0, 3));
    }
}