package game.utils;

import game.elements.Platform;
import game.entities.player.Character1;
import game.entities.player.Character2;
import game.entities.player.Player;
import game.input.KeyInput;
import game.levels.Level1;
import game.levels.LevelController;
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
    private LevelController levelController;
    private final JFrame parentFrame;


    public GamePanel(CharacterType selectedCharacter, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setFocusable(true);

        switch (selectedCharacter) {
            case CHARACTER_1 -> player = new Character1();
            case CHARACTER_2 -> player = new Character2();
            default -> player = new Character1();
        }

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
                levelController = new LevelController(collisions, player);
            }
        });


        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    showPauseMenu();
                }
            }
        });


        gameTimer = new Timer(8, e -> {
            movement.update();
            gravity.update();
            collisions.checkCollisions();
            if(levelController != null) {
                levelController.updateLevels();
            }
            repaint();
        });
        gameTimer.start();
    }

    private void showPauseMenu(){
        gameTimer.stop();
        String[] options = {"Wznów", "Powrót do Menu", "Wyjdź z gry"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Pauza",
                "Menu Gry",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0 || choice == -1) {
            gameTimer.start();
            requestFocusInWindow();
        } else if (choice == 1) {
            parentFrame.dispose();
            new MainMenu();
        } else if (choice == 2) {
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(panelWidth != 0 && panelHeight != 0) {
            levelController.draw(g);
            player.draw(g);

            debugInfo.updateInfo(movement.isJumpingLeft(), movement.isJumpingRight(), 0, gravity.getGravityVerticalSpeed());
            collisions.drawHitboxes(g);

        }
    }
}
