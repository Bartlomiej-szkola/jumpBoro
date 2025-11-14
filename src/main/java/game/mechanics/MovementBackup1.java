/*
* package game.mechanics;

import game.elements.Platform;
import java.util.List;

import game.entities.Player;

import java.awt.*;

public class Movement {
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean chargingJump = false;
    private double currentJumpHeight = 0;
    private boolean jumping = false;
    private double verticalSpeed = 0;
    private final double gravity = 0.5;

    private List<Platform> platforms;

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    private final Player player;

    public Movement(Player player) {
        this.player = player;
    }

    public void startChargingJump() {
        chargingJump = true;
        player.setBeforeJumpImage();
    }

    public void releaseJump() {
        chargingJump = false;
        if (!jumping) {
            verticalSpeed = -currentJumpHeight;
            jumping = true;
            currentJumpHeight = 0;
        }
    }

    public void update(int panelWidth, int panelHeight) {
        // RUCH POZIOMY
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();
        if (movingLeft) dx -= effectiveSpeed;
        if (movingRight) dx += effectiveSpeed;
        player.move(dx);

        if (player.getX() < 0) player.move(-player.getX());
        if (player.getX() > panelWidth - player.getWidth())
            player.move(panelWidth - player.getWidth() - player.getX());

        // ŁADOWANIE SKOKU
        if (chargingJump && !jumping) {
            currentJumpHeight += player.getChargeSpeed();
            if (currentJumpHeight > player.getMaxJumpHeight()) currentJumpHeight = player.getMaxJumpHeight();
        }

        // RUCH PIONOWY
        if (jumping) {
            player.jump(verticalSpeed);
            verticalSpeed += gravity;
            player.setJumpingImage();
            //STANIĘCIE NA ZIEMI
            if (player.getY() > panelHeight - player.getHeight() - 120) {
                player.setY(panelHeight - player.getHeight() - 120);
                jumping = false;
                verticalSpeed = 0;
                player.setStandingImage();
            }
        }

        // SPRAWDZENIE KOLIZJI Z PLATFORMAMI
        if (jumping && verticalSpeed > 0) { // gracz spada
            Rectangle playerFeet = new Rectangle(
                    player.getX(),
                    player.getY() + player.getHeight(),
                    player.getWidth(),
                    2
            );

            for (Platform platform : platforms) {
                if (playerFeet.intersects(platform.getBounds())) {

                    // ustaw gracza na górze platformy
                    player.setY(platform.getBounds().y - player.getHeight());

                    jumping = false;
                    verticalSpeed = 0;
                    player.setStandingImage();
                }
            }
        }


    }

    // Kontrola ruchu z KeyListenera
    public void setMovingLeft(boolean movingLeft) { this.movingLeft = movingLeft; }
    public void setMovingRight(boolean movingRight) { this.movingRight = movingRight; }
}

* */