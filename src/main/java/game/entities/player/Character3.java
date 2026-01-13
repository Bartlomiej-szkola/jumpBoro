package game.entities.player;

import game.entities.base.CharacterStats;
import game.utils.CharacterType;

public class Character3 extends Player {
    public Character3() {
        super(
                CharacterType.CHARACTER_3.stats,
                CharacterType.CHARACTER_3.standingFile,
                CharacterType.CHARACTER_3.moving1File,
                CharacterType.CHARACTER_3.moving2File,
                CharacterType.CHARACTER_3.chargingFile,
                CharacterType.CHARACTER_3.jumpingFile
        );
    }
}