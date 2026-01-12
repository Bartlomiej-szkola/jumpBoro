package game.levels;

import game.elements.Platform;
import game.mechanics.Collisions;

import javax.swing.*;
import java.util.Objects;

import static game.utils.GamePanel.panelWidth;


public class Level1  extends AbstractLevel {

    public Level1(Collisions collisions) {
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background.png"))).getImage();
        addPlatforms(collisions);
    }

    public void addPlatforms(Collisions collisions) {
        platforms.clear(); // usuwamy poprzednie platformy
        // ziemia
        platforms.add(new Platform(0, groundY, panelWidth, groundHeight));
        // platformy w różnych miejscach
        platforms.add(new Platform((int)(panelWidth * 0.2), veryHighY, platformLargeWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.5), highY, platformMediumWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.7), lowHigherY, platformSmallWidth, platformHeight, "/platforma.png"));
        collisions.setPlatforms(platforms);
    }
}
