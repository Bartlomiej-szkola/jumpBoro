package game.utils;

import javax.swing.*;
import java.util.Objects;

public class Game extends JFrame {

    ImageIcon gameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icon.png")));

    public Game(CharacterType selectedCharacter) {
        setIconImage(gameIcon.getImage());
        setTitle("Jump King 2");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        add(new GamePanel(selectedCharacter, this));
        setVisible(true);
        setLocationRelativeTo(null);

        // Załadowanie dźwięków
        SoundManager.loadSound("jump", "jump.wav");
        SoundManager.loadSound("land", "land.wav");
        SoundManager.loadSound("bump", "bump.wav");
        SoundManager.loadSound("fall", "fall.wav");
    }
}
