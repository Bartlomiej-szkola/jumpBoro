package game.utils;

import game.elements.Platform;
import java.awt.*;
import java.util.List;

public class Level {
    private final List<Platform> platforms;
    private final Image background;
    private final Color backgroundColor;

    // Konstruktor z obrazkiem
    public Level(List<Platform> platforms, Image background) {
        this.platforms = platforms;
        this.background = background;
        this.backgroundColor = null;
    }

    // Konstruktor z kolorem
    public Level(List<Platform> platforms, Color backgroundColor) {
        this.platforms = platforms;
        this.background = null;
        this.backgroundColor = backgroundColor;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public Image getBackground() {
        return background;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
