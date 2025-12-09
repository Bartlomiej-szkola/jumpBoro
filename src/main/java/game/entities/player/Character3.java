package game.entities.player;

import game.entities.base.CharacterStats;

public class Character3 extends Player {
    public Character3() {
        super(
                new CharacterStats(3.5, 30, 1.0, 3),
                "/character1standing.png",
                "/character1moving1.png",
                "/character1moving2.png",
                "/character1charging.png",
                "/character1jumping.png"
        );
    }
}