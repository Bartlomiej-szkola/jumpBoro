package game.mechanics;

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
    private boolean fallingLeft = false;
    private boolean fallingRight = false;

    private double currentJumpHeight = 0;
    private double jumpVerticalSpeed = 0;

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

    private void handleHorizontalMovement(int panelWidth) {
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();

        // blokada ruchu w locie tylko podczas ładowania skoku i wznoszenia
        if (chargingJump || jumping || gravity.isFalling()) {
            if (movingLeft) player.setFacingLeft();
            if (movingRight) player.setFacingRight();
            return;
        }

        // ruch w powietrzu podczas opadania
        if (gravity.isFalling()) {
            if (fallingLeft) dx -= effectiveSpeed;
            if (fallingRight) dx += effectiveSpeed;
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

    private void handleJump(int panelWidth) {
        // ładowanie skoku
        if (chargingJump) {
            currentJumpHeight += player.getChargeSpeed();
            if (currentJumpHeight > player.getMaxJumpHeight())
                currentJumpHeight = player.getMaxJumpHeight();

            jumpingLeft = movingLeft;
            jumpingRight = movingRight;
            player.setBeforeJumpImage();
        }

        // faktyczny skok (wznoszenie)
        if (jumping) {
            player.setJumpingImage();
            player.moveY(jumpVerticalSpeed);
            jumpVerticalSpeed += gravity.getGravityForce();
            handleHorizontalMovementWhileJumping(panelWidth, jumpingLeft, jumpingRight, false);

            // ruch w locie podczas wznoszenia
            double dx = 0;
            double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();
            if (jumpingLeft) dx -= effectiveSpeed;
            if (jumpingRight) dx += effectiveSpeed;
            player.moveX(dx);

            // ograniczenia ekranu
            if (player.getX() < 0) player.moveX(-player.getX());
            if (player.getX() > panelWidth - player.getWidth())
                player.moveX(panelWidth - player.getWidth() - player.getX());

            // koniec wznoszenia → zaczynamy spadanie
            if (jumpVerticalSpeed >= 0) {
                jumping = false;
                fallingLeft = jumpingLeft;
                fallingRight = jumpingRight;
                jumpingLeft = false;
                jumpingRight = false;
            }
        }
    }

    public void handleHorizontalMovementWhileJumping(int panelWidth, boolean mLeft, boolean mRight, boolean isFalling) {
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();
        if (isFalling) effectiveSpeed *= 0.8;

        if (jumpingLeft) dx -= effectiveSpeed;
        if (jumpingRight) dx += effectiveSpeed;

        if (mLeft) dx -= effectiveSpeed;
        if (mRight) dx += effectiveSpeed;

        player.moveX(dx);

        if (player.getX() < 0) { // po lewej
            player.moveX(-player.getX());
        }
        if (player.getX() > panelWidth - player.getWidth()) { // po prawej
            player.moveX(panelWidth - player.getWidth() - player.getX());
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

    public boolean isFallingLeft() {
        return fallingLeft;
    }

    public boolean isFallingRight() {
        return fallingRight;
    }

    public void clearFallingLeftRight(){
        fallingLeft = false;
        fallingRight = false;
    }

    public void update(int panelWidth) {
        handleHorizontalMovement(panelWidth);
        handleJump(panelWidth);

        // ustawienie obrazu postaci na ziemi
        if (!jumping && !chargingJump && !gravity.isFalling()) {
            if (movingLeft || movingRight) player.setMovingImage();
            else player.setStandingImage();
        }
    }
}
