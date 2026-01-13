package game.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CharactersPanel extends JPanel {
    private List<CharacterCard> cards = new ArrayList<>();

    public CharactersPanel(CardLayout cardLayout, JPanel cardPanel, MainMenu mainMenu) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz postać", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel cardsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 50));

        for (CharacterType type : CharacterType.values()) {
            CharacterCard card = new CharacterCard(type, mainMenu, this);
            cards.add(card);
            cardsContainer.add(card);
        }

        add(new JScrollPane(cardsContainer), BorderLayout.CENTER);

        JButton backButton = new JButton("Powrót do menu");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
        add(backButton, BorderLayout.SOUTH);
    }

    public void highlightSelection(CharacterCard selected) {
        for (CharacterCard card : cards) {
            if (card == selected) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.YELLOW, 4),
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));
            } else {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK, 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }
        }
    }
}