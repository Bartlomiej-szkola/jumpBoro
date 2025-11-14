/*
package game.mechanics.backup2;

import game.entities.Player;

public class Movement {
    private final Player player;
    private final Gravity gravity;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean chargingJump = false;
    private boolean jumping = false;

    private double currentJumpHeight = 0;
    private double verticalSpeed = 0;

    public Movement(Player player, Gravity gravity) {
        this.player = player;
        this.gravity = gravity;
    }

    // === Sterowanie ===
    public void setMovingLeft(boolean movingLeft) { this.movingLeft = movingLeft; }
    public void setMovingRight(boolean movingRight) { this.movingRight = movingRight; }

    // === Skok ===
    public void startChargingJump() {
        if (!jumping && !gravity.isFalling()) {
            chargingJump = true;
            player.setBeforeJumpImage();
        }
    }

    public void releaseJump() {
        if (chargingJump && !jumping && !gravity.isFalling()) {
            chargingJump = false;
            jumping = true;
            verticalSpeed = -currentJumpHeight; // moc skoku zależna od ładowania
            currentJumpHeight = 0;
            player.setJumpingImage();
        }
    }

    // === Aktualizacja ruchu ===
    public void update(int panelWidth) {
        handleHorizontalMovement(panelWidth);
        handleJump();
    }

    private void handleHorizontalMovement(int panelWidth) {
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();

        if (movingLeft) dx -= effectiveSpeed;
        if (movingRight) dx += effectiveSpeed;

        player.move(dx);

        // ograniczenia ruchu do szerokości panelu
        if (player.getX() < 0) player.move(-player.getX());
        if (player.getX() > panelWidth - player.getWidth())
            player.move(panelWidth - player.getWidth() - player.getX());
    }

    private void handleJump() {
        // ładowanie skoku
        if (chargingJump) {
            currentJumpHeight += player.getChargeSpeed();
            if (currentJumpHeight > player.getMaxJumpHeight())
                currentJumpHeight = player.getMaxJumpHeight();
        }

        // faktyczny skok (wznoszenie)
        if (jumping) {
            player.jump(verticalSpeed);
            verticalSpeed += 0.5; // spowolnienie wznoszenia

            // osiągnięcie szczytu lotu
            if (verticalSpeed >= 0) {
                jumping = false;
                player.setJumpingImage(); // gracz w fazie spadania zmienia obrazek
            }
        }
    }

    public boolean isJumping() {
        return jumping;
    }
}


 */