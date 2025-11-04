package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    public MainPanel(CardLayout cardLayout, JPanel cardPanel, MainMenu mainMenu) {

        JButton playButton = new JButton("Graj");
        JButton charactersButton = new JButton("Postacie");

        add(playButton);
        add(charactersButton);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game gameWindow = new Game();
                mainMenu.dispose();
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
