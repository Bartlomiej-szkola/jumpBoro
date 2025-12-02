package game.input;

import game.mechanics.Movement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Movement movement;

    public KeyInput(Movement movement) {
        this.movement = movement;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> movement.setMovingLeft(true);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> movement.setMovingRight(true);
            case KeyEvent.VK_SPACE -> movement.startChargingJump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> movement.setMovingLeft(false);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> movement.setMovingRight(false);
            case KeyEvent.VK_SPACE -> movement.releaseJump();
        }
    }
}
