package game.entities;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Player {
    private int x = 900, y = 900;
    private Image image;

    public Player() {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/player.png"))).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
