package game.utils;

import game.elements.Platform;
import game.entities.player.Character1;
import game.entities.player.Player;
import game.input.KeyInput;
import game.levels.Level1;
import game.mechanics.Collisions;
import game.mechanics.Gravity;
import game.mechanics.Movement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class GamePanel extends JPanel {

    private final Player player;
    private final Movement movement;
    private Collisions collisions;
    private Gravity gravity;
    private final Timer gameTimer;
    private DebugInfo debugInfo;
    public static int panelHeight;
    public static int panelWidth;
    private Level1 level1;

    public GamePanel() {
        setFocusable(true);

        player = new Character1();
        gravity = new Gravity(player, 1.25);
        movement = new Movement(player, gravity);
        collisions = new Collisions(player, gravity, movement);

        addKeyListener(new KeyInput(movement));

        debugInfo = new DebugInfo();
        add(debugInfo);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                requestFocusInWindow();
                panelHeight = GamePanel.this.getHeight();
                panelWidth = GamePanel.this.getWidth();
                System.out.println(panelHeight);
                player.initializeSize(panelHeight);
                level1 = new Level1(collisions);
            }
        });



        gameTimer = new Timer(8, e -> {
            movement.update(panelWidth);
            gravity.update();
            collisions.checkCollisions();
            repaint();
        });
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(panelWidth != 0 && panelHeight != 0) {
            level1.draw(g);
            player.draw(g);
            debugInfo.updateInfo(movement.isJumpingLeft(), movement.isJumpingRight());
        }
    }
}
