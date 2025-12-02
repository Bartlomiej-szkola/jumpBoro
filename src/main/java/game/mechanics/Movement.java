package game.mechanics;

import game.entities.base.CharacterState;
import game.entities.player.Player;

public class Movement {
    private final Player player;
    private final Gravity gravity;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean chargingJump = false;
    private boolean jumping = false;
    private boolean jumpingLeft = false;
    private boolean jumpingRight = false;

    private double currentJumpHeight = 0;
    private double jumpVerticalSpeed = 0;

    public Movement(Player player, Gravity gravity) {
        this.player = player;
        this.gravity = gravity;
    }

    // ------------------------- STEROWANIE LEWO-PRAWO -------------------------
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    private void handleHorizontalMovement(int panelWidth) {
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();

        if (chargingJump || jumping) return; // blokada ruchu podczas ładowania i skoku

        if (movingLeft) dx -= effectiveSpeed;
        if (movingRight) dx += effectiveSpeed;

        player.moveX(dx);

        if (dx != 0) {
            player.setStandingImage(); // w praktyce ustawia MOVING przez moveX
        } else {
            player.setStandingImage();
        }

        // ograniczenia ekranu
        if (player.getX() < 0) {
            player.moveX(-player.getX());
        }
        if (player.getX() > panelWidth - player.getWidth()) {
            player.moveX(panelWidth - player.getWidth() - player.getX());
        }
    }

    private void handleHorizontalMovementWhileJumping(int panelWidth) {
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();

        if (jumpingLeft) dx -= effectiveSpeed;
        if (jumpingRight) dx += effectiveSpeed;

        player.moveX(dx);

        if (player.getX() < 0) {
            player.moveX(-player.getX());
        }
        if (player.getX() > panelWidth - player.getWidth()) {
            player.moveX(panelWidth - player.getWidth() - player.getX());
        }
    }

    // ------------------------- SKOK -------------------------
    private void handleJump(int panelWidth) {
        if (chargingJump) {
            currentJumpHeight += player.getChargeSpeed();

            if (currentJumpHeight > player.getMaxJumpHeight()) {
                currentJumpHeight = player.getMaxJumpHeight();
            }

            jumpingLeft = movingLeft;
            jumpingRight = movingRight;
            player.setBeforeJumpImage(); // ustawia stan CHARGING
        }

        if (jumping) {
            player.moveY(jumpVerticalSpeed);
            jumpVerticalSpeed += gravity.getGravityForce();
            handleHorizontalMovementWhileJumping(panelWidth);

            if (jumpVerticalSpeed >= 0) {
                jumping = false;
                jumpingLeft = false;
                jumpingRight = false;
                player.setStandingImage(); // powrót do STANDING po zakończeniu skoku
            }
        }
    }

    // ------------------------- GETTERY/SETTERY -------------------------
    public boolean isJumping() { return jumping; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }
    public void setJumpingLeft(boolean jumpingLeft) { this.jumpingLeft = jumpingLeft; }
    public void setJumpingRight(boolean jumpingRight) { this.jumpingRight = jumpingRight; }

    // ------------------------- ŁADOWANIE SKOKU -------------------------
    public void startChargingJump() {
        if (!jumping) {
            chargingJump = true;
            player.setBeforeJumpImage(); // stan CHARGING
        }
    }

    public void releaseJump() {
        if (chargingJump && !jumping) {
            chargingJump = false;
            jumping = true;
            jumpVerticalSpeed = -(currentJumpHeight * gravity.getGravityForce());
            currentJumpHeight = 0;
            player.setJumpingImage(); // stan JUMPING
        }
    }

    // ------------------------- AKTUALIZACJA -------------------------
    public void update(int panelWidth) {
        handleHorizontalMovement(panelWidth);
        handleJump(panelWidth);

        // jeśli gracz nie skacze ani nie ładuje skoku, ustaw STANDING/MOVING
        if (!jumping && !chargingJump) {
            if (movingLeft || movingRight) {
                player.setStandingImage(); // w praktyce MOVING
            } else {
                player.setStandingImage(); // STANDING
            }
        }
    }

    public boolean isJumpingLeft() { return jumpingLeft; }
    public boolean isJumpingRight() { return jumpingRight; }
}
