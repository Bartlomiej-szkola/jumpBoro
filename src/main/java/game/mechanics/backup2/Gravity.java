/*
package game.mechanics.backup2;

import game.entities.Player;

public class Gravity {
    private final Player player;
    private boolean falling = false;
    private double verticalSpeed = 0;
    private final double gravityForce;

    public Gravity(Player player, double gravityForce) {
        this.player = player;
        this.gravityForce = gravityForce;
    }

    public void startFalling() {
        if (!falling) {
            falling = true;
            verticalSpeed = 0;
        }
    }

    public void stopFalling() {
        falling = false;
        verticalSpeed = 0;
        player.setStandingImage();
    }

    public boolean isFalling() {
        return falling;
    }

    public void update() {
        if (!falling) return;

        verticalSpeed += gravityForce;
        player.jump(verticalSpeed);
    }
}

 */
