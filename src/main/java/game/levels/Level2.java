package game.levels;

import game.elements.Platform;
import game.mechanics.Collisions;

import javax.swing.*;
import java.util.Objects;

import static game.utils.GamePanel.panelWidth;


public class Level2  extends AbstractLevel {

    public Level2(Collisions collisions) {
        setAssetsDirectory("/Levels/Level2/");
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource(getAssetsDirectory()+"background.png"))).getImage();
        addPlatforms(collisions);
    }

    public void addPlatforms(Collisions collisions) {
        platforms.clear(); // usuwamy poprzednie platformy
        // platformy w różnych miejscach
        platforms.add(new Platform((int)(panelWidth * 0.6), veryHighY, platformLargeWidth, platformHeight, getAssetsDirectory()+"platform.png"));
        platforms.add(new Platform((int)(panelWidth * 0.45), middleY, platformSmallWidth, platformHeight, getAssetsDirectory()+"platform.png"));
        platforms.add(new Platform((int)(panelWidth * 0.1), lowY, platformMediumWidth, platformHeight, getAssetsDirectory()+"platform.png"));
        collisions.setPlatforms(platforms);
    }
}
