package game.utils;

import javax.swing.*;
import java.util.Objects;

public class Game extends JFrame {

    ImageIcon gameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icon.png")));

    public Game(CharacterType selectedCharacter) {
        setIconImage(gameIcon.getImage());
        setTitle("Jump King 2");
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        add(new GamePanel(selectedCharacter, this));
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
