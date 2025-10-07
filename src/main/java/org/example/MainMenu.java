package org.example;

import javax.swing.*;

public class MainMenu extends JFrame {
    private JPanel mainPanel;

    public MainMenu() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }
}
