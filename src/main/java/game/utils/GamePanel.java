package game.utils;

import game.elements.Platform;
import game.entities.Character1;
import game.entities.Character2;
import game.entities.Character3;
import game.entities.Character4;
import game.entities.Character5;
import game.entities.Character6;
import game.entities.Player;
import game.input.KeyInput;
import game.mechanics.Collisions;
import game.mechanics.Gravity;
import game.mechanics.Movement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class GamePanel extends JPanel {
    private final Image background;
    private final Player player;
    private final Movement movement;
    private Collisions collisions;
    private Gravity gravity;
    private final Timer gameTimer;
    private final java.util.List<Platform> platforms = new java.util.ArrayList<>();
    private DebugInfo debugInfo;

    public GamePanel() {
        setFocusable(true);
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background.png"))).getImage();

        player = new Character1();
        gravity = new Gravity(player, 0.8);
        movement = new Movement(player, gravity);
        gravity.setMovement(movement);
        collisions = new Collisions(player, gravity, movement);
        addKeyListener(new KeyInput(movement));

        debugInfo = new DebugInfo();
        add(debugInfo);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) { // wykonanie metod pobierających wielkość okna (po ustaleniu wielkości okna przez jave)
                player.initializeSize(getHeight());
                addPlatforms();
                requestFocusInWindow();
            }
        });

        collisions.setPlatforms(platforms);

        // Pętla gry ~ 120 FPS
        gameTimer = new Timer(8, e -> {
            movement.update(getWidth());
            gravity.update(getWidth());
            collisions.checkCollisions();
            repaint();
        });
        gameTimer.start();
    }

    void addPlatforms(){ // dodanie platform
        platforms.add(new Platform(0, (int) (getHeight()*0.85), getWidth(), 120));
        platforms.add(new Platform(200, 500, 300, 80, "/platforma.png"));
        platforms.add(new Platform(600, 400, 250, 80, "/platforma.png"));
        platforms.add(new Platform(1000, 300, 300, 80, "/platforma.png"));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null); // rysowanie tła
        for (Platform p : platforms) { // rysowanie platform
            p.draw(g);
        }
        player.draw(g); // rysowanie gracza
        debugInfo.updateInfo(movement.isJumpingLeft(), movement.isJumpingRight());
    }
}
