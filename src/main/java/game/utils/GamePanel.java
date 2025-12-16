package game.utils;

import game.elements.Platform;
import game.entities.player.Character1;
import game.entities.player.Player;
import game.input.KeyInput;
import game.mechanics.Collisions;
import game.mechanics.Gravity;
import game.mechanics.Movement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;
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
    private Map<String, Boolean> info = new HashMap<>();

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
            public void componentResized(ComponentEvent e) {
                player.initializeSize(getHeight());
                addPlatforms();  // dynamiczne platformy po zmianie rozmiaru
                requestFocusInWindow();
            }
        });

        collisions.setPlatforms(platforms);

        gameTimer = new Timer(8, e -> {
            movement.update(getWidth());
            gravity.update(getWidth());
            collisions.checkCollisions();
            repaint();
        });
        gameTimer.start();
    }

    void addPlatforms() {
        platforms.clear(); // usuwamy poprzednie platformy przy resize

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // -------------------
        // Zmienne pomocnicze dla platform
        // -------------------
        int groundHeight = (int)(panelHeight * 0.15); // wysokość platformy ziemi
        int platformHeight = (int)(panelHeight * 0.08); // wszystkie inne platformy

        int platformSmallWidth = (int)(panelWidth * 0.15);
        int platformMediumWidth = (int)(panelWidth * 0.2);
        int platformLargeWidth = (int)(panelWidth * 0.25);
        int platformExtraLargeWidth = (int)(panelWidth * 0.3);


        int groundY = panelHeight - groundHeight;
        int veryHighY = (int)(panelHeight * 0.8);
        int highY = (int)(panelHeight * 0.7);
        int middleHighY = (int)(panelHeight * 0.6);
        int middleY = (int)(panelHeight * 0.5);
        int middleLowY = (int)(panelHeight * 0.4);
        int LowHigherY = (int)(panelHeight * 0.3);
        int LowY = (int)(panelHeight * 0.2);
        int VeryLowY = (int)(panelHeight * 0.1);

        // -------------------
        // Tworzenie platform
        // -------------------
        // ziemia
        platforms.add(new Platform(0, groundY, panelWidth, groundHeight));
        // platformy w różnych miejscach
        platforms.add(new Platform((int)(panelWidth * 0.2), veryHighY, platformLargeWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.5), middleHighY, platformMediumWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.7), highY, platformSmallWidth, platformHeight, "/platforma.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        for (Platform p : platforms) {
            p.draw(g);
        }
        player.draw(g);
        debugInfo.updateInfo(movement.isJumpingLeft(), movement.isJumpingRight());
    }
}
