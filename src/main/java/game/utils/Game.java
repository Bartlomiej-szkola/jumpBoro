package game.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Game extends JFrame {

    ImageIcon gameIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icon.png")));

    public Game(){
        setIconImage(gameIcon.getImage());
        setTitle("Jump King 2");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setSize(1920, 1080); // Psuje się na większej rozdzielczości (1440p)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        add(new GamePanel());
        setVisible(true);
        setLocationRelativeTo(null);
    }
}