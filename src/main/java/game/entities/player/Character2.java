package game.entities.player;

import game.entities.base.CharacterStats;
import game.utils.CharacterType;

public class Character2 extends Player {
    public Character2() {
        super(
                CharacterType.CHARACTER_2.stats,
                CharacterType.CHARACTER_2.standingFile,
                CharacterType.CHARACTER_2.moving1File,
                CharacterType.CHARACTER_2.moving2File,
                CharacterType.CHARACTER_2.chargingFile,
                CharacterType.CHARACTER_2.jumpingFile
        );
    }
}