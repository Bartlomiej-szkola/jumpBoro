package game.levels;

import game.elements.Platform;
import game.entities.interfaces.IDrawable;

import java.awt.*;

import static game.utils.GamePanel.panelHeight;
import static game.utils.GamePanel.panelWidth;

public abstract class AbstractLevel implements levelsVariables {
    Image background;
    java.util.List<Platform> platforms = new java.util.ArrayList<>();
    private Rectangle topBorder;
    private Rectangle bottomBorder;
    private String assetsDirectory;

    public AbstractLevel(){
        topBorder = new Rectangle(0, 2, panelWidth, 2);
        bottomBorder = new Rectangle(0, panelHeight, panelWidth, 2);
    }

    public Rectangle getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(Rectangle topBorder) {
        this.topBorder = topBorder;
    }

    public Rectangle getBottomBorder() {
        return bottomBorder;
    }

    public void setBottomBorder(Rectangle bottomBorder) {
        this.bottomBorder = bottomBorder;
    }

    public String getAssetsDirectory() {
        return assetsDirectory;
    }

    public void setAssetsDirectory(String assetsDirectory) {
        this.assetsDirectory = assetsDirectory;
    }
}
