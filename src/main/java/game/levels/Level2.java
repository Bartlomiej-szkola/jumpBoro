package game.levels;

import game.elements.Platform;
import game.mechanics.Collisions;

import javax.swing.*;
import java.util.Objects;

import static game.utils.GamePanel.panelWidth;


public class Level2  extends AbstractLevel {

    public Level2(Collisions collisions) {
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background2.png"))).getImage();
        addPlatforms(collisions);
    }

    public void addPlatforms(Collisions collisions) {
        platforms.clear(); // usuwamy poprzednie platformy
        // platformy w różnych miejscach
        platforms.add(new Platform((int)(panelWidth * 0.6), veryHighY, platformLargeWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.1), lowY, platformMediumWidth, platformHeight, "/platforma.png"));
        platforms.add(new Platform((int)(panelWidth * 0.4), middleY, platformSmallWidth, platformHeight, "/platforma.png"));
        collisions.setPlatforms(platforms);
    }
}
