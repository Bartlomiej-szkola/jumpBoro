package game.mechanics;

import game.entities.player.Player;
import game.elements.Platform;

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
        player.setFeet(new Rectangle(player.getX()+2, player.getY() + player.getHeight(), player.getWidth()-4, 2));
        player.setBelowFeet(new Rectangle(player.getX()+2, player.getY() + player.getHeight() + 2, player.getWidth()-4, 2));
        player.setHead(new Rectangle(player.getX()+2, player.getY(), player.getWidth()-4, 2));
        player.setLeftSide(new Rectangle(
                player.getX(),
                player.getY() + 50,
                2,
                player.getHeight() - 60
        ));

        player.setRightSide(new Rectangle(
                player.getX() + player.getWidth(),
                player.getY() + 50,
                2,
                player.getHeight() - 60
        ));

        touchingPlatformByHead(player.getHead());
        touchingPlatformByLeftSide(player.getLeftSide());
        touchingPlatformByRightSide(player.getRightSide());
        standingOnPlatform(player.getFeet(), player.getBelowFeet());
        /// sprawdzaniue predkosci vertykalnej (ujemna = wznoszenie, dodatnia = opadanie)
        /// System.out.println(movement.getJumpVerticalSpeed());
    }

    private void standingOnPlatform(Rectangle feet, Rectangle belowFeet){
        if (platforms == null || platforms.isEmpty()) return;

        boolean standingOnPlatform = false;

        for (Platform p : platforms) {
            // KLUCZ: Sprawdzamy kolizję stóp TYLKO gdy gracz opada (verticalSpeed >= 0)
            // To zapobiega teleportacji na górę, gdy uderzasz w platformę z boku lub od dołu
            if (player.getFeet().intersects(p.getBounds()) && movement.getJumpVerticalSpeed() >= 0) {

                player.setY(p.getBounds().y - player.getHeight());

                if (gravity.isFalling() || movement.isJumping()) {
                    player.moveY(-(player.getBaseHeight()-player.getHeight()));
                    player.forceSetStanding();
                }

                gravity.stopFalling();
                movement.setJumping(false);
                movement.setJumpVerticalSpeed(0); // Resetujemy prędkość pionową
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

                if (movement.getJumpVerticalSpeed() > 10 || movement.getJumpVerticalSpeed() < -10)
                    movement.setJumpVerticalSpeed(0);

                /**movement.setJumping(false);
                gravity.startFalling();*/
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
                /// Jesli gracz sie wznosi i wtedy uderzy bokiem w platforme to zystuje na predkosci
                if (movement.getJumpVerticalSpeed() < 0)
                    movement.setJumpVerticalSpeed(movement.getJumpVerticalSpeed()-5);
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
                /// Jesli gracz sie wznosi i wtedy uderzy bokiem w platforme to zystuje na predkosci
                if (movement.getJumpVerticalSpeed() < 0)
                    movement.setJumpVerticalSpeed(movement.getJumpVerticalSpeed()-5);
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
