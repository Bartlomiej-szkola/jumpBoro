package game.utils;

import game.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GamePanel extends JPanel {
    private Image background;
    private Player player;

    public GamePanel() {
        setFocusable(true);
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background.png"))).getImage();
        if (background == null) {
            System.out.println("Nie udało się wczytać obrazka!");
        }

        player = new Player();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        player.draw(g);
    }
}
