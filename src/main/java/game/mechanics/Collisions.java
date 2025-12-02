package game.mechanics;

import game.entities.player.Player;
import game.elements.Platform;

import java.awt.*;
import java.util.List;

public class Collisions {
    private final Player player;
    private final Gravity gravity;
    private final Movement movement;
    private List<Platform> platforms;
    private Platform currentPlatform = null;

    public Collisions(Player player, Gravity gravity, Movement movement) {
        this.player = player;
        this.gravity = gravity;
        this.movement = movement;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public void checkCollisions() {

        Rectangle feet = new Rectangle(player.getX(), player.getY() + player.getHeight(), player.getWidth(), 2);
        Rectangle belowFeet = new Rectangle(player.getX(), player.getY() + player.getHeight() + 1, player.getWidth(), 2);
        Rectangle head = new Rectangle(player.getX(), player.getY(), player.getWidth(), 2);
        Rectangle leftSide = new Rectangle(
                player.getX(),
                player.getY() + 15,
                2,
                player.getHeight() - 30
        );

        Rectangle rightSide = new Rectangle(
                player.getX() + player.getWidth(),
                player.getY() + 15,
                2,
                player.getHeight() - 30
        );

        standingOnPlatform(feet, belowFeet);
        touchingPlatformByHead(head);
        touchingPlatformByLeftSide(leftSide);
        touchingPlatformByRightSide(rightSide);
    }

    private void standingOnPlatform(Rectangle feet, Rectangle belowFeet){
        if (platforms == null || platforms.isEmpty()) return;

        boolean standingOnPlatform = false;

        for (Platform p : platforms) {
            if (feet.intersects(p.getBounds())) {
                player.setY(p.getBounds().y - player.getHeight());
                gravity.stopFalling();
                player.setStandingImage();
                currentPlatform = p;
                standingOnPlatform = true;
                break;
            }
        }

        if (!standingOnPlatform) {
            if (currentPlatform != null && !movement.isJumping()) {
                if (!belowFeet.intersects(currentPlatform.getBounds())) {
                    gravity.startFalling();
                    currentPlatform = null;
                }
            }
        }
    }

    private void touchingPlatformByHead(Rectangle head){
        if (platforms == null || platforms.isEmpty()) return;

        for (Platform p : platforms) {
            if (head.intersects(p.getBounds())) {
                movement.setJumping(false);
                gravity.startFalling();
                break;
            }
        }
    }

    private void touchingPlatformByLeftSide(Rectangle leftSide) {
        if (platforms == null || platforms.isEmpty()) return;

        for (Platform p : platforms) {
            if (leftSide.intersects(p.getBounds())) {
                movement.setJumpingLeft(false);
                movement.setJumpingRight(true);
                break;
            }
        }
    }

    private void touchingPlatformByRightSide(Rectangle rightSide) {
        if (platforms == null || platforms.isEmpty()) return;

        for (Platform p : platforms) {
            if (rightSide.intersects(p.getBounds())) {
                movement.setJumpingRight(false);
                movement.setJumpingLeft(true);
                break;
            }
        }
    }
}
