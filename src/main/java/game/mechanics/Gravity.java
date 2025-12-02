package game.mechanics;

import game.entities.player.Player;

public class Gravity {
    private final Player player;
    private boolean falling = false;
    private double gravityVerticalSpeed = 0;
    private final double gravityForce;

    public Gravity(Player player, double gravityForce) {
        this.player = player;
        this.gravityForce = gravityForce;
    }

    public void startFalling() {
        if (!falling) {
            falling = true;
            gravityVerticalSpeed = 0;
        }
    }

    public void stopFalling() {
        if (falling) {
            falling = false;
            gravityVerticalSpeed = 0;
        }
    }

    public void update() {
        if (falling) {
            gravityVerticalSpeed += gravityForce;
            player.moveY(gravityVerticalSpeed);
        }
    }

    public boolean isFalling(){ return falling; }
    public double getGravityForce(){ return gravityForce; }
}
