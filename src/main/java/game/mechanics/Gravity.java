package game.mechanics;

import game.entities.player.Player;
import game.utils.GamePanel;

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
            System.out.println("stopFalling()");
            falling = false;
            gravityVerticalSpeed = 0;
        }
    }

    public void update() {
        if (falling) {
            double scale = (GamePanel.panelHeight > 0) ? (double)GamePanel.panelHeight / 1440.0 : 1.0; // 1440p bo tak dziala dobrze

            System.out.println("Działanie grawitacji");
            gravityVerticalSpeed += gravityForce * scale;
            player.moveY(gravityVerticalSpeed);
        }
    }

    public boolean isFalling(){ return falling; }
    public double getGravityForce(){ return gravityForce; }

    public double getGravityVerticalSpeed() {
        return gravityVerticalSpeed;
    }
}
