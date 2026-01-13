package game.entities.player;

import game.entities.base.CharacterStats;
import game.utils.CharacterType;

public class Character5 extends Player {
    public Character5() {
        super(
                CharacterType.CHARACTER_5.stats,
                CharacterType.CHARACTER_5.standingFile,
                CharacterType.CHARACTER_5.moving1File,
                CharacterType.CHARACTER_5.moving2File,
                CharacterType.CHARACTER_5.chargingFile,
                CharacterType.CHARACTER_5.jumpingFile
        );
    }
}