/*
package game.utils.backup2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {

        JPanel mainPanel = new JPanel();
        JButton playButton = new JButton("Graj");
        JButton charactersButton = new JButton("Postacie");

        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPanel);
        setSize(800, 600);

        mainPanel.add(playButton);
        mainPanel.add(charactersButton);

        setVisible(true);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game gameWindow = new Game();
                dispose();
            }
        });

        charactersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("C");
            }
        });
    }

}
*/

