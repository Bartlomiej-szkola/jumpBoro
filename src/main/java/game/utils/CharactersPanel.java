package game.utils;

import javax.swing.*;
import java.awt.*;

public class CharactersPanel extends JPanel{

    public CharactersPanel(CardLayout cardLayout, JPanel cardPanel) {

        JLabel label1 = new JLabel("Test charactersPanel");
        JButton backButton = new JButton("Powrót");

        add(label1);
        add(backButton);

        backButton.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
    }
}
