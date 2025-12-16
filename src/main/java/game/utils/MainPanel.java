package game.utils;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(CardLayout cardLayout, JPanel cardPanel, MainMenu mainMenu) {

        JButton playButton = new JButton("Graj");
        JButton charactersButton = new JButton("Postacie");

        add(playButton);
        add(charactersButton);

        playButton.addActionListener(e -> {
            new Game();
            mainMenu.dispose();
        });

        charactersButton.addActionListener(e -> cardLayout.show(cardPanel, "charactersPanel"));
    }
}
