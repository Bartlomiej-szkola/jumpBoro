package game.utils;

import game.elements.Platform;
import game.entities.Character1;
import game.entities.Player;
import game.input.KeyInput;
import game.mechanics.Collisions;
import game.mechanics.Gravity;
import game.mechanics.Movement;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;

public class GamePanel extends JPanel {
    private final Player player;
    private final Movement movement;
    private Collisions collisions;
    private Gravity gravity;
    private final Timer gameTimer;
    private DebugInfo debugInfo;
    private Map<String, Boolean> info = new HashMap<>();

    // Kamera
    private int cameraY = 0;

    // Predefiniowane levele
    private final java.util.List<Level> levels = new ArrayList<>();
    private int currentLevelIndex = 0;

    public GamePanel() {
        setFocusable(true);

        player = new Character1();
        gravity = new Gravity(player, 0.8);
        movement = new Movement(player, gravity);
        gravity.setMovement(movement);
        collisions = new Collisions(player, gravity, movement);
        addKeyListener(new KeyInput(movement));

        debugInfo = new DebugInfo();
        add(debugInfo);

        initLevels();
        collisions.setPlatforms(levels.get(currentLevelIndex).getPlatforms());

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                player.initializeSize(getHeight());
                initLevels(); // inicjalizacja poziomów po ustaleniu rozmiaru okna
                collisions.setPlatforms(levels.get(currentLevelIndex).getPlatforms());
                requestFocusInWindow();
            }
        });

        // Pętla gry ~120 FPS
        gameTimer = new Timer(8, e -> {
            movement.update(getWidth());
            gravity.update(getWidth());
            collisions.checkCollisions();

            // Kamera w górę
            if (player.getY() - cameraY < getHeight() * 0.3) {
                cameraY -= 5;
            }

            // Kamera w dół (dogania gracza gdy spadnie nisko)
            if (player.getY() - cameraY > getHeight() * 0.7) {
                cameraY += 5;
            }

            // Przełączanie na kolejny poziom (w górę)
            if (cameraY < (currentLevelIndex + 1) * getHeight()) {
                if (currentLevelIndex < levels.size() - 1) {
                    currentLevelIndex++;
                    collisions.setPlatforms(levels.get(currentLevelIndex).getPlatforms());
                }
            }

            // Przełączanie na poprzedni poziom (w dół)
            if (cameraY > currentLevelIndex * getHeight()) {
                if (currentLevelIndex > 0) {
                    currentLevelIndex--;
                    collisions.setPlatforms(levels.get(currentLevelIndex).getPlatforms());
                }
            }

            repaint();
        });
        gameTimer.start();
    }

    // -------------------- INICJALIZACJA LEVELI --------------------
    private void initLevels() {
        levels.clear();

        // Level 1
        java.util.List<Platform> level1Platforms = new ArrayList<>();
        level1Platforms.add(new Platform(0, (int)(getHeight()*0.85), getWidth(), 120));
        level1Platforms.add(new Platform(200, 500, 300, 80, "/platforma.png"));
        level1Platforms.add(new Platform(600, 400, 250, 80, "/platforma.png"));
        Image bg1 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background1.png"))).getImage();
        levels.add(new Level(level1Platforms, bg1));

        // Level 2
        java.util.List<Platform> level2Platforms = new ArrayList<>();
        level2Platforms.add(new Platform(100, 500, 250, 80, "/platforma.png"));
        level2Platforms.add(new Platform(800, 400, 300, 80, "/platforma.png"));
        Image bg2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background2.png"))).getImage();
        levels.add(new Level(level2Platforms, bg2));

        // Level 3
        java.util.List<Platform> level3Platforms = new ArrayList<>();
        level3Platforms.add(new Platform(400, 450, 250, 80, "/platforma.png"));
        level3Platforms.add(new Platform(700, 300, 300, 80, "/platforma.png"));
        Image bg3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background3.png"))).getImage();
        levels.add(new Level(level3Platforms, bg3));
    }

    // -------------------- RYSOWANIE --------------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (levels.isEmpty()) {
            return; // nic nie rysujemy, dopóki nie ma poziomów
        }

        Level currentLevel = levels.get(currentLevelIndex);

        // Tło
        g.drawImage(currentLevel.getBackground(), 0, -cameraY, getWidth(), getHeight(), null);

        // Platformy
        for (Platform p : currentLevel.getPlatforms()) {
            p.draw(g, cameraY);
        }

        // Gracz
        player.draw(g, cameraY);

        // Debug info
        info.clear();
        info.put("isJumpingLeft", movement.isJumpingLeft());
        info.put("isJumpingRight", movement.isJumpingRight());
        debugInfo.updateInfo(info);
    }
}
