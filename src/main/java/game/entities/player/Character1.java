package game.entities.player;

import game.entities.base.CharacterStats;
import game.utils.CharacterType;

public class Character1 extends Player {
    public Character1() {
        super(
                CharacterType.CHARACTER_1.stats,
                CharacterType.CHARACTER_1.standingFile,
                CharacterType.CHARACTER_1.moving1File,
                CharacterType.CHARACTER_1.moving2File,
                CharacterType.CHARACTER_1.chargingFile,
                CharacterType.CHARACTER_1.jumpingFile
        );
    }
}