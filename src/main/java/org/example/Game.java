package org.example;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game(){
        setTitle("Jump King 2");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setResizable(false);
        setVisible(true);
    }
}
