package game.levels;

import game.elements.Platform;
import game.mechanics.Collisions;

import javax.swing.*;
import java.util.Objects;

import static game.utils.GamePanel.panelWidth;


public class Level10 extends AbstractLevel {

    public Level10(Collisions collisions) {
        setAssetsDirectory("/Levels/Level10/");
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource(getAssetsDirectory()+"background.png"))).getImage();
        addPlatforms(collisions);
    }

    public void addPlatforms(Collisions collisions) {
        platforms.clear(); // usuwamy poprzednie platformy
        // platformy w różnych miejscach
        platforms.add(new Platform((int)(panelWidth * 0.7), veryHighY, platformLargeWidth, platformHeight, getAssetsDirectory()+"platform.png"));
        platforms.add(new Platform((int)(panelWidth * 0.2), lowHigherY, platformSmallWidth, platformHeight, getAssetsDirectory()+"platform.png"));
        collisions.setPlatforms(platforms);
    }
}
