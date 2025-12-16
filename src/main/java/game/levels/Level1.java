package game.levels;

import game.elements.Platform;
import game.entities.interfaces.IDrawable;
import game.mechanics.Collisions;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static game.utils.GamePanel.panelHeight;
import static game.utils.GamePanel.panelWidth;


public class Level1 implements levelsVariables, IDrawable {

    private Image background;

    private final java.util.List<Platform> platforms = new java.util.ArrayList<>();

    public Level1(Collisions collisions) {
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background.png"))).getImage();
        addPlatforms(collisions);
    }

    void addPlatforms(Collisions collisions) {
        platforms.clear(); // usuwamy poprzednie platformy przy resize
        // -------------------
        // Zmienne pomocnicze dla platform
        // -------------------


        // -------------------
        // Tworzenie platform
        // -------------------
        // ziemia
        platforms.add(new Platform(0, groundY, panelWidth, groundHeight));
        // platformy w różnych miejscach
        platforms.add(new Platform((int)(panelWidth * 0.2), veryHighY, platformLargeWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.5), highY, platformMediumWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.7), lowHigherY, platformSmallWidth, platformHeight, "/platforma.png"));
        collisions.setPlatforms(platforms);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, panelWidth, panelHeight, null);
        for (Platform p : platforms) {
            p.draw(g);
        }
    }
}
