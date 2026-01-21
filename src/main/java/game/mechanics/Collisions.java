package game.mechanics;

import game.entities.player.Player;
import game.elements.Platform;
import game.utils.SoundManager;

import java.awt.*;
import java.util.List;

public class Collisions {    private final Player player;
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
        player.setFeet(new Rectangle(player.getX(), player.getY() + player.getHeight(), player.getWidth(), 2));
        player.setBelowFeet(new Rectangle(player.getX(), player.getY() + player.getHeight() + 1, player.getWidth(), 2));
        player.setHead(new Rectangle(player.getX(), player.getY(), player.getWidth(), 2));
        player.setLeftSide(new Rectangle(
                player.getX(),
                player.getY() + 15,
                2,
                player.getHeight() - 60
        ));

        player.setRightSide(new Rectangle(
                player.getX() + player.getWidth(),
                player.getY() + 15,
                2,
                player.getHeight() - 60
        ));

        standingOnPlatform(player.getFeet(), player.getBelowFeet());
        touchingPlatformByHead(player.getHead());
        touchingPlatformByLeftSide(player.getLeftSide());
        touchingPlatformByRightSide(player.getRightSide());
    }

    private void standingOnPlatform(Rectangle feet, Rectangle belowFeet){
        if (platforms == null || platforms.isEmpty()) return;

        boolean standingOnPlatform = false;

        for (Platform p : platforms) {
            if (player.getFeet().intersects(p.getBounds())) {

                // ustawiamy gracza na platformie
                player.setY(p.getBounds().y - player.getHeight());

                // MOMENT LĄDOWANIA (tylko wtedy ustawiamy obrazek stania)
                if (gravity.isFalling() || movement.isJumping()) {
                    player.forceSetStanding();  // <-- kluczowa zmiana

                    if(gravity.getGravityVerticalSpeed() > 50){ // do dostosowania
                        SoundManager.playSound("fall");
                    }
                    else{
                        SoundManager.playSound("land");
                    }
                }

                gravity.stopFalling();
                currentPlatform = p;
                standingOnPlatform = true;
                break;
            }
        }

        // jeśli zszedł/spadł z platformy
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
                System.out.println("Kolizja głową");
                movement.setJumping(false);
                gravity.startFalling();
                SoundManager.playSound("bump");
                break;
            }
        }
    }

    private void touchingPlatformByLeftSide(Rectangle leftSide) {
        if (platforms == null || platforms.isEmpty()) return;

        for (Platform p : platforms) {
            if (leftSide.intersects(p.getBounds())) {
                System.out.println("Kolizja z lewej");
                movement.setJumpingLeft(false);
                movement.setJumpingRight(true);
                SoundManager.playSound("bump");
                break;
            }
        }
    }

    private void touchingPlatformByRightSide(Rectangle rightSide) {
        if (platforms == null || platforms.isEmpty()) return;

        for (Platform p : platforms) {
            if (rightSide.intersects(p.getBounds())) {
                System.out.println("Kolizja z prawej");
                movement.setJumpingRight(false);
                movement.setJumpingLeft(true);
                SoundManager.playSound("bump");
                break;
            }
        }
    }

    /**--------------------------------------------------------------------------------------------
     ------------------------------------RYSOWANIE HITBOXÓW----------------------------------------
     --------------------------------------------------------------------------------------------- */

    public void drawHitboxes(Graphics g) {
        if (player == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2)); // grubsza linia dla hitboxów

        // Hitbox stóp
        g2.setColor(Color.GREEN);
        g2.draw(player.getFeet());

        // Hitbox pod stopami
        g2.setColor(Color.YELLOW);
        g2.draw(player.getBelowFeet());

        // Hitbox głowy
        g2.setColor(Color.RED);
        g2.draw(player.getHead());

        // Hitbox lewego boku
        g2.setColor(Color.BLUE);
        g2.draw(player.getLeftSide());

        // Hitbox prawego boku
        g2.setColor(Color.MAGENTA);
        g2.draw(player.getRightSide());

        // Hitboxy platform
        if (platforms != null) {
            g2.setColor(Color.ORANGE);
            for (Platform p : platforms) {
                g2.draw(p.getBounds());
            }
        }
    }
}
