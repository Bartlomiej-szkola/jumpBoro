package game.entities.player;

import game.entities.base.CharacterStats;

public class Character1 extends Player {
    public Character1() {
        super(
                new CharacterStats(3.5, 30, 1.0, 3),
                "/character1.png",
                "/character1.png",
                "/character1.png",
                "/character1beforejump.png",
                "/character1jumping.png"
        );
    }
}
