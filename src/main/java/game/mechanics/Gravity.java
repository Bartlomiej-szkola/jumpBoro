package game.mechanics;

import game.entities.Player;

public class Gravity {
    private final Player player;
    private boolean falling = false;
    private double gravityVerticalSpeed = 0;
    private final double gravityForce;
    private Movement movement;

    public Gravity(Player player, double gravityForce) {
        this.player = player;
        this.gravityForce = gravityForce;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public void startFalling() {
        if (!falling) {
            falling = true;
            gravityVerticalSpeed = 0;
        }
    }

    public void stopFalling() {
        if(falling){
            falling = false;
            gravityVerticalSpeed = 0;
            player.setStandingImage();
            if(movement != null){
                movement.clearFallingLeftRight();
            }

        }
    }

    public void update(int panelWidth) {
        if (falling) {
            gravityVerticalSpeed += gravityForce;
            player.moveY(gravityVerticalSpeed);
            if((movement != null) &&  (movement.isFallingLeft() || movement.isFallingRight())){
                movement.handleHorizontalMovementWhileJumping(panelWidth, movement.isFallingLeft(), movement.isFallingRight(), true);
            }
        }
    }

    public boolean isFalling(){return falling;}
    public double getGravityForce(){return gravityForce; }
}
