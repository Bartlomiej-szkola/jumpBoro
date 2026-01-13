package game.utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CharacterCard extends JPanel {

    public CharacterCard(CharacterType character, MainMenu mainMenu, CharactersPanel parent) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // ZDJĘCIE
        JLabel imgLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(character.standingFile));
            Image img = icon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imgLabel.setText("Brak zdjecia");
        }
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // OPIS
        JLabel speedMultiplierLabel = new JLabel("speedMultiplier: " + character.stats.getSpeedMultiplier());
        JLabel maxJumpHeightLabel = new JLabel("maxJumpHeight: " + character.stats.getMaxJumpHeight());
        JLabel chargeSpeedLabel = new JLabel("chargeSpeed: " + character.stats.getChargeSpeed());
        speedMultiplierLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        maxJumpHeightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        chargeSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // PRZYCISK
        JButton selectButton = new JButton("Wybierz");
        selectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(imgLabel);
        add(Box.createRigidArea(new Dimension(0, 10))); // Odstęp
        add(speedMultiplierLabel);
        add(maxJumpHeightLabel);
        add(chargeSpeedLabel);
        add(Box.createVerticalGlue()); // Elastyczny odstęp
        add(selectButton);

        MouseAdapter selectAction = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainMenu.setSelectedCharacter(character);
                parent.highlightSelection(CharacterCard.this);
            }
        };

        this.addMouseListener(selectAction);
        selectButton.addActionListener(e -> {
            mainMenu.setSelectedCharacter(character);
            parent.highlightSelection(CharacterCard.this);
        });
    }
}