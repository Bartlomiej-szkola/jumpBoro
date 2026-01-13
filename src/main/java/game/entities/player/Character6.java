package game.entities.player;

import game.entities.base.CharacterStats;
import game.utils.CharacterType;

public class Character6 extends Player {
    public Character6() {
        super(
                CharacterType.CHARACTER_6.stats,
                CharacterType.CHARACTER_6.standingFile,
                CharacterType.CHARACTER_6.moving1File,
                CharacterType.CHARACTER_6.moving2File,
                CharacterType.CHARACTER_6.chargingFile,
                CharacterType.CHARACTER_6.jumpingFile
        );
    }
}