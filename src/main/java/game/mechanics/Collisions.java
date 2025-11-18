package game.mechanics;

import game.entities.Player;
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
        this.platforms = platforms; //przypisanie platform do listy
    }

//------------------------------------------ WYKONYWANA CYKLICZNIE --------------------------------------
    public void checkCollisions() {

        Rectangle feet = new Rectangle(player.getX(), player.getY() + player.getHeight(), player.getWidth(), 2);
        Rectangle belowFeet = new Rectangle(player.getX(), player.getY() + player.getHeight() + 1, player.getWidth(), 2);
        Rectangle head = new Rectangle(player.getX(), player.getY(), player.getWidth(), 2);
        Rectangle leftSide = new Rectangle(
                player.getX(),      // 2 px w lewo od gracza
                player.getY() + 15,      // odcięcie 2 px od góry
                2,                      // szerokość ścianki
                player.getHeight() - 30 // wysokość pomniejszona o 2 px z góry i dołu
        );

        Rectangle rightSide = new Rectangle(
                player.getX() + player.getWidth(), // prawa krawędź gracza
                player.getY() + 15,                 // +2 px od góry
                2,                                 // szerokość prawej ścianki
                player.getHeight() - 30             // -2 px z góry i dołu
        );

        standingOnPlatform(feet, belowFeet);
        touchingPlatformByHead(head);
        touchingPlatformByLeftSide(leftSide);
        touchingPlatformByRightSide(rightSide);
    }

    //--------------------------------------------------------------------------------------------------------
//-------------------------------------------KOLIZJE Z PLATFORMAMI----------------------------------------
    private void standingOnPlatform(Rectangle feet, Rectangle belowFeet){
        if (platforms == null || platforms.isEmpty()) return;

        boolean standingOnPlatform = false;

        for (Platform p : platforms) {
            if (feet.intersects(p.getBounds())) {
                // Gracz stoi na platformie
                player.setY(p.getBounds().y - player.getHeight());
                gravity.stopFalling();// wyłączenie grawitacji na platformach
                player.setStandingImage();
                currentPlatform = p;
                standingOnPlatform = true;
                break;
            }
        }

        // Jeśli gracz już nie dotyka platformy
        if (!standingOnPlatform) {
            if (currentPlatform != null && !movement.isJumping()) {
                // sprawdzamy, czy nadal jest nad platformą

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
        for (Platform p : platforms) {
            if (leftSide.intersects(p.getBounds())) {
                movement.setJumpingLeft(false);
                movement.setJumpingRight(true);
                break;
            }
        }
    }

    private void touchingPlatformByRightSide(Rectangle leftSide) {
        for (Platform p : platforms) {
            if (leftSide.intersects(p.getBounds())) {
                movement.setJumpingRight(false);
                movement.setJumpingLeft(true);
                break;
            }
        }
    }
//--------------------------------------------------------------------------------------------------------

}
