/*
package game.mechanics.backup2;

import game.elements.Platform;
import game.entities.Player;

import java.awt.*;
import java.util.List;

public class Collisions {
    private final Player player;
    private final Gravity gravity;
    private List<Platform> platforms;
    private Platform currentPlatform = null;

    public Collisions(Player player, Gravity gravity) {
        this.player = player;
        this.gravity = gravity;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public void checkCollisions() {
        if (platforms == null || platforms.isEmpty()) return;

        boolean standingOnPlatform = false;
        Rectangle feet = new Rectangle(player.getX(), player.getY() + player.getHeight(), player.getWidth(), 2);

        for (Platform p : platforms) {
            if (feet.intersects(p.getBounds())) {
                // Gracz stoi na platformie
                player.setY(p.getBounds().y - player.getHeight());
                gravity.stopFalling();
                player.setStandingImage();
                currentPlatform = p;
                standingOnPlatform = true;
                break;
            }
        }

        // Jeśli gracz już nie dotyka platformy
        if (!standingOnPlatform) {
            if (currentPlatform != null) {
                // sprawdzamy, czy nadal jest nad platformą
                Rectangle belowFeet = new Rectangle(player.getX(), player.getY() + player.getHeight() + 1, player.getWidth(), 2);
                if (!belowFeet.intersects(currentPlatform.getBounds())) {
                    gravity.startFalling();
                    currentPlatform = null;
                }
            } else {
                gravity.startFalling();
            }
        }
    }
}
 */
