package game.utils;

import game.entities.base.CharacterStats;

public enum CharacterType {
    CHARACTER_1(
            3.5,
            30,
            1.0,
            "/character1.png",
            "/character1.png",
            "/character1.png",
            "/character1beforejump.png",
            "/character1jumping.png"
    ),
    CHARACTER_2(
            3.5,
            30,
            1.0,
            "/character2.png",
            "/character2moving1.png",
            "/character2moving2.png",
            "/character2beforejump.png",
            "/character2jumping.png"
    ),
    CHARACTER_3(
            3.5,
            30,
            1.0,
            "/character1standing.png",
            "/character1moving1.png",
            "/character1moving2.png",
            "/character1charging.png",
            "/character1jumping.png"
    ),
    CHARACTER_4(
            3.5,
            30,
            1.0,
            "/character1standing.png",
            "/character1moving1.png",
            "/character1moving2.png",
            "/character1charging.png",
            "/character1jumping.png"
    ),
    CHARACTER_5(
            3.5,
            30,
            1.0,
            "/character1standing.png",
            "/character1moving1.png",
            "/character1moving2.png",
            "/character1charging.png",
            "/character1jumping.png"
    ),
    CHARACTER_6(
            3.5,
            30,
            1.0,
            "/character1standing.png",
            "/character1moving1.png",
            "/character1moving2.png",
            "/character1charging.png",
            "/character1jumping.png"
    );


    public final CharacterStats stats;
    public final String standingFile, moving1File, moving2File, chargingFile, jumpingFile;

    CharacterType(double speedMultiplier, double maxJumpHeight, double chargeSpeed, String standingFile, String moving1File, String moving2File, String chargingFile, String jumpingFile) {
        this.stats = new CharacterStats(speedMultiplier, maxJumpHeight, chargeSpeed);

        this.standingFile = standingFile;
        this.moving1File = moving1File;
        this.moving2File = moving2File;
        this.chargingFile = chargingFile;
        this.jumpingFile = jumpingFile;
    }
}