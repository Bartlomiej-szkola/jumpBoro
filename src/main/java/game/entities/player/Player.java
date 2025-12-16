package game.entities.player;

import game.entities.base.AbstractCharacter;
import game.entities.base.CharacterStats;
import game.entities.base.Facing;
import game.entities.base.CharacterState;
import game.entities.interfaces.IDrawable;
import game.entities.interfaces.IJumpable;
import game.entities.interfaces.IMovable;
import game.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Player extends AbstractCharacter implements IMovable, IJumpable, IDrawable {

    private Image playerCurrentImage;
    private final CharacterStats stats;

    private Facing facing = Facing.RIGHT;
    private CharacterState state = CharacterState.STANDING;

    private Image standingLeft, standingRight;
    private Image movingLeft1, movingLeft2, movingRight1, movingRight2;
    private Image chargingLeft, chargingRight;
    private Image jumpingLeft, jumpingRight;

    private long lastAnimationTime = 0;
    private boolean toggleFrame = false;

    public Player(CharacterStats stats,
                  String standingFile,
                  String moving1File,
                  String moving2File,
                  String chargingFile,
                  String jumpingFile) {

        this.stats = stats;
        loadImages(standingFile, moving1File, moving2File, chargingFile, jumpingFile);
        playerCurrentImage = standingRight;
    }

    private void loadImages(String standingFile, String moving1File, String moving2File,
                            String chargingFile, String jumpingFile) {

        standingRight = new ImageIcon(Objects.requireNonNull(getClass().getResource(standingFile))).getImage();
        movingRight1 = new ImageIcon(Objects.requireNonNull(getClass().getResource(moving1File))).getImage();
        movingRight2 = new ImageIcon(Objects.requireNonNull(getClass().getResource(moving2File))).getImage();
        chargingRight = new ImageIcon(Objects.requireNonNull(getClass().getResource(chargingFile))).getImage();
        jumpingRight = new ImageIcon(Objects.requireNonNull(getClass().getResource(jumpingFile))).getImage();

        standingLeft = ImageUtils.flipHorizontally(standingRight);
        movingLeft1 = ImageUtils.flipHorizontally(movingRight1);
        movingLeft2 = ImageUtils.flipHorizontally(movingRight2);
        chargingLeft = ImageUtils.flipHorizontally(chargingRight);
        jumpingLeft = ImageUtils.flipHorizontally(jumpingRight);
    }

    public void initializeSize(int panelHeight) {
        height = panelHeight / 7;
        width = (int) (height * ((double) playerCurrentImage.getWidth(null) / playerCurrentImage.getHeight(null)));
        y = panelHeight - height - 130;
    }

    @Override
    public void draw(Graphics g) {
        updateAnimation();
        g.drawImage(playerCurrentImage, x, y, width, height, null);
    }

    @Override
    public void moveX(double dx) {
        x += dx;

        if (dx < 0) facing = Facing.LEFT;
        else if (dx > 0) facing = Facing.RIGHT;

        // NIE nadpisujemy ruchu jeśli skaczemy lub ładujemy skok
        if (dx != 0 && state != CharacterState.JUMPING && state != CharacterState.CHARGING) {
            state = CharacterState.MOVING;
        }
    }

    @Override
    public void moveY(double dy) {
        y += dy;
    }

    @Override
    public double getMaxJumpHeight() {
        return stats.getMaxJumpHeight();
    }

    @Override
    public void jump() {
        y -= stats.getMaxJumpHeight();
        state = CharacterState.JUMPING;
        setJumpingImage();
    }

    public void setFacingLeft() {
        facing = Facing.LEFT;
    }

    public void setFacingRight() {
        facing = Facing.RIGHT;
    }


    public void setStandingImage() {
        if (state == CharacterState.JUMPING) return;
        if (state == CharacterState.CHARGING) return;

        state = CharacterState.STANDING;
        playerCurrentImage = (facing == Facing.RIGHT) ? standingRight : standingLeft;
    }

    public void setMovingImage() {
        if (state == CharacterState.JUMPING) return;
        if (state == CharacterState.CHARGING) return;

        state = CharacterState.MOVING;
        // obrazek zmienia updateAnimation()
    }

    public void setBeforeJumpImage() {
        state = CharacterState.CHARGING;
        playerCurrentImage = (facing == Facing.RIGHT) ? chargingRight : chargingLeft;
    }

    public void setJumpingImage() {
        state = CharacterState.JUMPING;
        playerCurrentImage = (facing == Facing.RIGHT) ? jumpingRight : jumpingLeft;
    }

    public void forceSetStanding() {
        state = CharacterState.STANDING; // omijamy blokady
        playerCurrentImage = (facing == Facing.RIGHT) ? standingRight : standingLeft;
    }


    public void updateAnimation() {
        // animujemy TYLKO chodzenie
        if (state != CharacterState.MOVING) return;

        long now = System.currentTimeMillis();
        if (now - lastAnimationTime > 150) {
            toggleFrame = !toggleFrame;
            lastAnimationTime = now;
        }

        if (facing == Facing.RIGHT) {
            playerCurrentImage = toggleFrame ? movingRight1 : movingRight2;
        } else {
            playerCurrentImage = toggleFrame ? movingLeft1 : movingLeft2;
        }
    }

    public CharacterStats getStats() { return stats; }
    public Image getCurrentImage() { return playerCurrentImage; }
    public double getSpeedMultiplier() { return stats.getSpeedMultiplier(); }
    public double getChargeSpeed() { return stats.getChargeSpeed(); }
    public int getBaseSpeed() { return stats.getBaseSpeed(); }
    public Facing getFacing() { return facing; }
    public CharacterState getState() { return state; }
}
