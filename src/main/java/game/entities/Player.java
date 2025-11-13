package game.entities;

import game.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Player {
    private Image playerImage;
    private Image playerImageStanding;
    private Image playerImageJumping;
    private Image playerImageBeforeJump;
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

        playerImageStanding = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
        playerImageJumping = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePathJumping))).getImage();
        playerImageBeforeJump = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePathBeforeJump))).getImage();
        playerImage = playerImageStanding;
    }

    public void initializeSize(int panelHeight) {
        height = panelHeight / 7;
        width = (int) (height * ((double) playerImage.getWidth(null) / playerImage.getHeight(null)));
        y = panelHeight - height - 130;
    }

    public void draw(Graphics g) {
        g.drawImage(playerImage, x, y, width, height, null);
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


    public void setStandingImage(){
        if(playerImage != playerImageStanding){
            playerImage = playerImageStanding;
        }
    }

    public void setJumpingImage(){
        if(playerImage != playerImageJumping) {
            playerImage = playerImageJumping;
        }
    }

    public void setBeforeJumpImage(){
        if(playerImage != playerImageBeforeJump) {
            playerImage = playerImageBeforeJump;
        }
    }

    public void faceLeft(){ // DO POPRAWY (po zmianie zdjęcia stanu gracza, ten jest skierowany od razu w prawo, po za tym to chyba dość mało efektowny sposów i lepiej bedzie przy włączaniu gdy przypisać wszystkiie opcje zdjęcia do różnych zmiennychi potem tylko tylko przypisywac do currentImage)
        if(isFacingRight){
            isFacingRight = false;
            playerImage = ImageUtils.flipHorizontally(playerImage);
        }
    }

    public void faceRight(){ // DO POPRAWY (po zmianie zdjęcia stanu gracza, ten jest skierowany od razu w prawo, po za tym to chyba dość mało efektowny sposów i lepiej bedzie przy włączaniu gdy przypisać wszystkiie opcje zdjęcia do różnych zmiennychi potem tylko tylko przypisywac do currentImage)
        if(!isFacingRight){
            isFacingRight = true;
            playerImage = ImageUtils.flipHorizontally(playerImage);
        }
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
