package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharactersPanel extends JPanel {

    public CharactersPanel(CardLayout cardLayout, JPanel cardPanel) {

        JLabel label1 = new JLabel("Test charactersPanel");
        JButton charactersBackButton = new JButton("Powrót");

        add(label1);
        add(charactersBackButton);

        charactersBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "mainPanel");
            }
        });
    }
}
