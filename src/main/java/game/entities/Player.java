package game.entities;

import game.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Player {
    private Image playerCurrentImage;
    private final Image playerImageStandingRight;
    private final Image playerImageStandingLeft;
    private final Image playerImageJumpingRight;
    private final Image playerImageJumpingLeft;
    private final Image playerImageBeforeJumpRight;
    private final Image playerImageBeforeJumpLeft;
    private int x = 0;
    private int y = 0;
    private int width;
    private int height;
    public boolean isFacingRight = true;

    // Gameplay attributes
    private final double speedMultiplier;
    private final double maxJumpHeight;
    private final double chargeSpeed;
    private final int baseSpeed = 3;

    public Player(String imagePath, String imagePathJumping, String imagePathBeforeJump, double speedMultiplier, double maxJumpHeight, double chargeSpeed) {
        this.speedMultiplier = speedMultiplier;
        this.maxJumpHeight = maxJumpHeight;
        this.chargeSpeed = chargeSpeed;

        playerCurrentImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        playerImageStandingRight = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        playerImageStandingLeft = ImageUtils.flipHorizontally(playerImageStandingRight);
        playerImageJumpingRight = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePathJumping))).getImage();
        playerImageJumpingLeft = ImageUtils.flipHorizontally(playerImageJumpingRight);
        playerImageBeforeJumpRight = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePathBeforeJump))).getImage();
        playerImageBeforeJumpLeft = ImageUtils.flipHorizontally(playerImageBeforeJumpRight);
    }

    public void initializeSize(int panelHeight) {
        height = panelHeight / 7;
        width = (int) (height * ((double) playerCurrentImage.getWidth(null) / playerCurrentImage.getHeight(null)));
        y = panelHeight - height - 130;
    }

    public void draw(Graphics g) {
        g.drawImage(playerCurrentImage, x, y, width, height, null);
    }

    // Metody do ruchu (enkapsulacja)
    public void moveX(double dx) {
        x += dx;
    }

    public void moveY(double dy) {
        y += dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }



    public void faceLeft(){ // DO POPRAWY (po zmianie zdjęcia stanu gracza, ten jest skierowany od razu w prawo, po za tym to chyba dość mało efektowny sposów i lepiej bedzie przy włączaniu gdy przypisać wszystkiie opcje zdjęcia do różnych zmiennychi potem tylko tylko przypisywac do currentImage)
        if(isFacingRight){
            isFacingRight = false;
        }
    }

    public void faceRight(){ // DO POPRAWY (po zmianie zdjęcia stanu gracza, ten jest skierowany od razu w prawo, po za tym to chyba dość mało efektowny sposów i lepiej bedzie przy włączaniu gdy przypisać wszystkiie opcje zdjęcia do różnych zmiennychi potem tylko tylko przypisywac do currentImage)
        if(!isFacingRight){
            isFacingRight = true;
        }
    }


    public void setStandingImage(){
        if (isFacingRight && playerCurrentImage!=playerImageStandingRight) playerCurrentImage = playerImageStandingRight;
        if (!isFacingRight && playerCurrentImage!=playerImageStandingLeft) playerCurrentImage = playerImageStandingLeft;
    }

    public void setJumpingImage(){
        if (isFacingRight && playerCurrentImage!=playerImageJumpingRight) playerCurrentImage = playerImageJumpingRight;
        if (!isFacingRight && playerCurrentImage!=playerImageJumpingLeft) playerCurrentImage = playerImageJumpingLeft;
    }

    public void setBeforeJumpImage(){
        if (isFacingRight && playerCurrentImage!=playerImageBeforeJumpRight) playerCurrentImage = playerImageBeforeJumpRight;
        if (!isFacingRight && playerCurrentImage!=playerImageBeforeJumpLeft) playerCurrentImage = playerImageBeforeJumpLeft;
    }

    // Gettery
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public double getSpeedMultiplier() { return speedMultiplier; }
    public double getMaxJumpHeight() { return maxJumpHeight; }
    public double getChargeSpeed() { return chargeSpeed; }
    public int getBaseSpeed() { return baseSpeed; }
}
