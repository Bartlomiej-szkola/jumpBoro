package game.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class CharactersPanel extends JPanel {
    private List<CharacterCard> cards = new ArrayList<>();
    private JPanel cardsContainer;
    private JScrollPane scrollPane;

    public CharactersPanel(CardLayout cardLayout, JPanel cardPanel, MainMenu mainMenu) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Wybierz postać", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        cardsContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));

        for (CharacterType type : CharacterType.values()) {
            CharacterCard card = new CharacterCard(type, mainMenu, this);
            cards.add(card);
            cardsContainer.add(card);
        }

        scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20); // Płynny scroll

        // Zmiana rozmiaru przy zmianie rozmiaru okna
        scrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateContainerSize();
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JButton backButton = new JButton("Powrót do menu");
        backButton.setPreferredSize(new Dimension(200, 40));
        bottomPanel.add(backButton);

        backButton.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
        add(bottomPanel, BorderLayout.SOUTH);

        if (!cards.isEmpty()) {
            highlightSelection(cards.get(0));
        }
    }

    private void updateContainerSize() {
        int width = scrollPane.getViewport().getWidth();
        if (width <= 0) return;

        // Pobierz wymiary pojedynczej karty (muszą być zgodne z CharacterCard)
        // do poprawy
        int cardWidth = 180;
        int cardHeight = 280;
        int hGap = 30;
        int vGap = 30;

        int cardsPerRow = Math.max(1, width / (cardWidth + hGap));
        int numRows = (int) Math.ceil((double) cards.size() / cardsPerRow);
        int totalHeight = numRows * (cardHeight + vGap) + vGap;

        cardsContainer.setPreferredSize(new Dimension(width, totalHeight));
        cardsContainer.revalidate();
    }

    public void highlightSelection(CharacterCard selected) {
        for (CharacterCard card : cards) {
            card.setSelected(card == selected);
        }
    }
}