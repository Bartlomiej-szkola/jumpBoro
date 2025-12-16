package game.entities.base;

public class CharacterStats {
    private final double speedMultiplier;
    private final double maxJumpHeight;
    private final double chargeSpeed;
    private final int baseSpeed;

    public CharacterStats(double speedMultiplier, double maxJumpHeight, double chargeSpeed, int baseSpeed) {
        this.speedMultiplier = speedMultiplier;
        this.maxJumpHeight = maxJumpHeight;
        this.chargeSpeed = chargeSpeed;
        this.baseSpeed = baseSpeed;
    }

    public double getSpeedMultiplier() { return speedMultiplier; }
    public double getMaxJumpHeight() { return maxJumpHeight; }
    public double getChargeSpeed() { return chargeSpeed; }
    public int getBaseSpeed() { return baseSpeed; }
}
