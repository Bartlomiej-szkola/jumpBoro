/*
package game.utils.backup2;

import game.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class GamePanel extends JPanel {
    private Image background;
    private Player player;
    private final Timer gameTimer;

    public GamePanel() {
        setFocusable(true);
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background.png"))).getImage();

        // Game loop ~60 FPS (16 ms)
        gameTimer = new Timer(8, e -> {
            if (player != null) {
                player.update(getWidth(), getHeight());
            }
            repaint();
        });
        gameTimer.start();

        // Key handling
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (player == null) return;
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setMovingLeft(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setMovingRight(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    player.startChargingJump();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (player == null) return;
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setMovingLeft(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setMovingRight(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    player.releaseJump();
                }
            }

        });

        player = new Player("/player.png", 3.5 , 20 , 1.0 );
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                player.initializeSize(getHeight());
                requestFocusInWindow();
            }
        });

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        player.draw(g);

    }
}
*/