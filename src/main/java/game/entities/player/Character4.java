package game.entities.player;

import game.entities.base.CharacterStats;
import game.utils.CharacterType;

public class Character4 extends Player {
    public Character4() {
        super(
                CharacterType.CHARACTER_4.stats,
                CharacterType.CHARACTER_4.standingFile,
                CharacterType.CHARACTER_4.moving1File,
                CharacterType.CHARACTER_4.moving2File,
                CharacterType.CHARACTER_4.chargingFile,
                CharacterType.CHARACTER_4.jumpingFile
        );
    }
}