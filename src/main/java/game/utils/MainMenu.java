package game.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {

        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        JPanel mainPanel = new MainPanel(cardLayout, cardPanel, this);
        JPanel charactersPanel = new CharactersPanel(cardLayout, cardPanel);

        cardPanel.add(mainPanel, "mainPanel");
        cardPanel.add(charactersPanel, "charactersPanel");
        add(cardPanel);
        cardLayout.show(cardPanel, "mainPanel");

        setVisible(true);
    }

}

