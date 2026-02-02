package game.levels;

import game.elements.Platform;
import game.mechanics.Collisions;

import javax.swing.*;
import java.util.Objects;

import static game.utils.GamePanel.panelWidth;


public class Level3  extends AbstractLevel {

    public Level3(Collisions collisions) {
        setAssetsDirectory("/Levels/Level3/");
        background = new ImageIcon(Objects.requireNonNull(getClass().getResource(getAssetsDirectory()+"background.png"))).getImage();
        addPlatforms(collisions);
    }

    public void addPlatforms(Collisions collisions) {
        platforms.clear(); // usuwamy poprzednie platformy
        // platformy w różnych miejscach
        platforms.add(new Platform((int)(panelWidth * 0.7), highY, platformLargeWidth, platformHeight, getAssetsDirectory()+"platform.png"));
        platforms.add(new Platform((int)(panelWidth * 0.4), lowY, platformSmallWidth, platformHeight, getAssetsDirectory()+"platform.png"));
        collisions.setPlatforms(platforms);
    }
}
