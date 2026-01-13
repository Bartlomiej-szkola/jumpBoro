package game.utils;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private CharacterType selectedCharacter = CharacterType.CHARACTER_1;

    public MainMenu() {

        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        JPanel mainPanel = new MainPanel(cardLayout, cardPanel, this);
        JPanel charactersPanel = new CharactersPanel(cardLayout, cardPanel, this);

        cardPanel.add(mainPanel, "mainPanel");
        cardPanel.add(charactersPanel, "charactersPanel");
        add(cardPanel);
        cardLayout.show(cardPanel, "mainPanel");

        setVisible(true);
    }

    public CharacterType getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(CharacterType character) {
        this.selectedCharacter = character;
    }
}
