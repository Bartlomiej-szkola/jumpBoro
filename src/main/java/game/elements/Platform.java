package game.elements;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Platform {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Image image;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        image = null;
    }

    public Platform(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        image = new ImageIcon(Objects.requireNonNull(
                getClass().getResource(imagePath)
        )).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
