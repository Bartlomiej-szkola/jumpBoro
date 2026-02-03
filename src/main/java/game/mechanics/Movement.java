package game.mechanics;

import game.entities.base.CharacterState;
import game.entities.base.Facing;
import game.entities.player.Player;
import game.utils.GamePanel;

import static game.utils.GamePanel.panelWidth;

public class Movement {
    private final Player player;
    private final Gravity gravity;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean chargingJump = false;
    private boolean jumping = false;
    private boolean jumpingLeft = false;
    private boolean jumpingRight = false;
    private boolean fallingLeft = false;
    private boolean fallingRight = false;

    private double currentJumpHeight = 0;
    private double jumpVerticalSpeed = 0;

    private final double BASE_SPEED = 3;
    private final double BASE_WINDOW_HEIGHT = 1440.0;
    // Predkosc 3 dla 1440p byla ok

    public Movement(Player player, Gravity gravity) {
        this.player = player;
        this.gravity = gravity;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public double getJumpVerticalSpeed() {
        return jumpVerticalSpeed;
    }

    public void setJumpVerticalSpeed(double jumpVerticalSpeed) {
        this.jumpVerticalSpeed = jumpVerticalSpeed;
    }

    private double getScale() {
        return (GamePanel.panelHeight > 0) ? GamePanel.panelHeight / BASE_WINDOW_HEIGHT : 1.0;
    }

    private void handleHorizontalMovement() {
        double dx = 0;
        double dynamicBaseSpeed = BASE_SPEED * getScale();
        double effectiveSpeed = dynamicBaseSpeed  * player.getSpeedMultiplier();

        // blokada ruchu w locie tylko podczas ładowania skoku i wznoszenia
        if (chargingJump || jumping) {
            if (movingLeft && !player.getState().equals(CharacterState.JUMPING)) player.setFacingLeft();
            if (movingRight && !player.getState().equals(CharacterState.JUMPING)) player.setFacingRight();
            //System.out.println("Jumping: " + jumping);
            return;
        }

        // ruch w powietrzu podczas opadania
        if (gravity.isFalling()) {
            if (movingLeft) dx -= effectiveSpeed;
            if (movingRight) dx += effectiveSpeed;
        } else { // normalny ruch po ziemi
            if (movingLeft) dx -= effectiveSpeed;
            if (movingRight) dx += effectiveSpeed;
        }

        player.moveX(dx);

        // ograniczenia ekranu
        if (player.getX() < 0) player.moveX(-player.getX());
        if (player.getX() > panelWidth - player.getWidth())
            player.moveX(panelWidth - player.getWidth() - player.getX());
    }

    private void handleJump() {
        double scale = getScale();
        double dynamicBaseSpeed = BASE_SPEED * scale;

        // ładowanie skoku
        if (chargingJump) {
            // Predkosc ladowania skoku
            currentJumpHeight += player.getChargeSpeed() * scale;
            double maxScaledJump = player.getMaxJumpHeight() * scale;

            if (currentJumpHeight > maxScaledJump)
                currentJumpHeight = maxScaledJump;

            jumpingLeft = movingLeft;
            jumpingRight = movingRight;
            player.setBeforeJumpImage();
        }

        // faktyczny skok (wznoszenie)
        if (jumping) {
            player.setJumpingImage();
            player.moveY(jumpVerticalSpeed);

            jumpVerticalSpeed += gravity.getGravityForce() * scale;

            // ruch w locie podczas wznoszenia
            double dx = 0;
            double effectiveSpeed = dynamicBaseSpeed * player.getSpeedMultiplier();
            if (jumpingLeft) dx -= effectiveSpeed*1.5;
            if (jumpingRight) dx += effectiveSpeed*1.5;
            player.moveX(dx);

            // ograniczenia ekranu
            if (player.getX() < 0) player.moveX(-player.getX());
            if (player.getX() > panelWidth - player.getWidth())
                player.moveX(panelWidth - player.getWidth() - player.getX());

            // koniec wznoszenia → zaczynamy spadanie
            if (jumpVerticalSpeed >= 0) {/**
                jumping = false;
                fallingLeft = jumpingLeft;
                fallingRight = jumpingRight;
                jumpingLeft = false;
                jumpingRight = false;*/
            }
        }
    }

    public boolean isJumping() { return jumping; }
    public boolean isJumpingLeft() { return jumpingLeft; }
    public boolean isJumpingRight() { return jumpingRight; }
    // ----------------- DODATKOWE SETTERY -----------------
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setJumpingLeft(boolean jumpingLeft) {
        this.jumpingLeft = jumpingLeft;
    }

    public void setJumpingRight(boolean jumpingRight) {
        this.jumpingRight = jumpingRight;
    }


    public void startChargingJump() {
        if (!jumping) {
            chargingJump = true;
            player.setBeforeJumpImage();
        }
    }

    public void releaseJump() {
        if (chargingJump && !jumping) {
            chargingJump = false;
            jumping = true;
            jumpVerticalSpeed = -(currentJumpHeight * gravity.getGravityForce());
            currentJumpHeight = 0;
            player.setJumpingImage();
        }
    }

    public void update() {
        handleHorizontalMovement();
        handleJump();

        // ustawienie obrazu postaci na ziemi
        if (!jumping && !chargingJump && !gravity.isFalling()) {
            if (movingLeft || movingRight) player.setMovingImage();
            else player.setStandingImage();
        }
    }
}
