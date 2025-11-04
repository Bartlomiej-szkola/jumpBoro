package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {

        JPanel mainPanel = new JPanel();
        JButton playButton = new JButton("Graj");
        JButton charactersButton = new JButton("Postacie");

        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        mainPanel.add(playButton);
        mainPanel.add(charactersButton);

        JPanel charactersPanel = new JPanel();
        JLabel label1 = new JLabel("Test charactersPanel");
        charactersPanel.add(label1);

        cardPanel.add(mainPanel, "mainPanel");
        cardPanel.add(charactersPanel, "charactersPanel");
        add(cardPanel);
        cardLayout.show(cardPanel, "mainPanel");

        setVisible(true);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game gameWindow = new Game();
                dispose();
            }
        });

        charactersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("C");
                cardLayout.show(cardPanel, "charactersPanel");
            }
        });
    }

}

