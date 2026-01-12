package game.entities.player;

import game.entities.base.CharacterStats;

public class Character2 extends Player {
    public Character2() {
        super(
                new CharacterStats(3.5, 30, 1.0, 3),
                "/character2.png",
                "/character2moving1.png",
                "/character2moving2.png",
                "/character2beforejump.png",
                "/character2jumping.png"
        );
    }
}