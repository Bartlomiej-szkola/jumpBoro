/*package game.entities;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PlayerBackup2 {
    private Image playerImage;

    private int x = 0;
    private int y = 0;
    private int playerWidth;
    private int playerHeight;

    // Atrybuty gameplayowe (ustawiane w konstruktorze)
    private final double speedMultiplier;   // mnożnik prędkości ruchu
    private final double maxJumpHeight;        // max wysokość skoku (na przyszłość)
    private final double chargeSpeed;       // szybkość ładowania skoku (na przyszłość)
    private final int baseSpeed = 3;

    private boolean movingLeft = false;
    private boolean movingRight = false;

    // Skok
    private boolean chargingJump = false;  // czy gracz trzyma spację
    private double currentJumpHeight = 0;   // aktualna wysokość skoku
    private boolean jumping = false;        // czy gracz aktualnie się unosi
    private double verticalSpeed = 0;       // prędkość w pionie
    private final double gravity = 0.5;     // stała grawitacja


    public PlayerBackup2(String imagePath, double speedMultiplier, double maxJumpHeight, double chargeSpeed) {
        this.speedMultiplier = speedMultiplier;
        this.maxJumpHeight = maxJumpHeight;
        this.chargeSpeed = chargeSpeed;

        playerImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath))).getImage();
    }

    public void initializeSize(int panelHeight) {
        playerHeight = panelHeight / 10;
        playerWidth = (int) (playerHeight * ((double)
                playerImage.getWidth(null) / playerImage.getHeight(null)));

        y = panelHeight - playerHeight;
    }
    public void draw(Graphics g) {
        g.drawImage(playerImage, x, y, playerWidth, playerHeight, null);
    }

    public void startChargingJump() {
        chargingJump = true;
    }

    public void releaseJump() {
        chargingJump = false;
        if (!jumping) {
            verticalSpeed = -currentJumpHeight; // ujemna, bo y rośnie w dół
            jumping = true;
            currentJumpHeight = 0;
        }
    }


    public void update(int panelWidth, int panelHeight) {
        // RUCH POZIOMY
        double dx = 0;
        double effectiveSpeed = baseSpeed * speedMultiplier;
        if (movingLeft) dx -= effectiveSpeed;
        if (movingRight) dx += effectiveSpeed;
        x += dx;
        if (x < 0) x = 0;
        if (x > panelWidth - playerWidth) x = panelWidth - playerWidth;

        // ŁADOWANIE SKOKU
        if (chargingJump && !jumping) {
            currentJumpHeight += chargeSpeed;
            if (currentJumpHeight > maxJumpHeight) currentJumpHeight = maxJumpHeight;
        }

        // RUCH PIONOWY
        if (jumping) {
            y += verticalSpeed;
            verticalSpeed += gravity; // grawitacja

            // kontakt z podłożem
            if (y > panelHeight - playerHeight) {
                y = panelHeight - playerHeight;
                jumping = false;
                verticalSpeed = 0;
            }
        }
    }


    // kontrola ruchu z KeyListenera
    public void setMovingLeft(boolean movingLeft)  { this.movingLeft = movingLeft; }
    public void setMovingRight(boolean movingRight){ this.movingRight = movingRight; }

    // getters (jeśli będą potrzebne)
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return playerWidth; }
    public int getHeight() { return playerWidth; }

}

 */

