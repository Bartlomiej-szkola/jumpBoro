package game.utils;

import javax.swing.*;
import java.awt.*;

public class CharactersPanel extends JPanel{

    public CharactersPanel(CardLayout cardLayout, JPanel cardPanel, MainMenu mainMenu) {

        JLabel label1 = new JLabel("Test charactersPanel");
        JLabel label = new JLabel("Wybierz postać:");
        JButton char1Button = new JButton("Postać 1");
        JButton char2Button = new JButton("Postać 2");
        JButton backButton = new JButton("Powrót");


        add(label1);

        add(label);
        add(char1Button);
        add(char2Button);

        add(backButton);

        char1Button.addActionListener(e -> {
            mainMenu.setSelectedCharacter(CharacterType.CHARACTER_1);
            JOptionPane.showMessageDialog(this, "Wybrano Postać 1");
        });

        char2Button.addActionListener(e -> {
            mainMenu.setSelectedCharacter(CharacterType.CHARACTER_2);
            JOptionPane.showMessageDialog(this, "Wybrano Postać 2");
        });

        backButton.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
    }
}
