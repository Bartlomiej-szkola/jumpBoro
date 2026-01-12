package game.levels;

import game.elements.Platform;
import game.entities.interfaces.IDrawable;
import game.entities.player.Player;
import game.mechanics.Collisions;

import java.awt.*;

import static game.utils.GamePanel.panelHeight;
import static game.utils.GamePanel.panelWidth;

public class LevelController implements IDrawable {
    private AbstractLevel level;
    private Collisions collisions;
    private Player player;
    private int currrentLevel;

    public LevelController(Collisions collisions, Player player){
        this.collisions = collisions;
        this.player = player;
        this.level = new Level1(collisions);
        currrentLevel = 1;
    }

    private AbstractLevel loadLevel(int levelNumber) {
        return switch (levelNumber) {
            case 1 -> new Level1(collisions);
            case 2 -> new Level2(collisions);
            case 3 -> new Level3(collisions);
            case 4 -> new Level4(collisions);
            case 5 -> new Level5(collisions);
            default -> new Level1(collisions);
        };
    }

    public void draw(Graphics g) {
        g.drawImage(level.background, 0, 0, panelWidth, panelHeight, null);
        for (Platform p : level.platforms) {
            p.draw(g);
        }
    }

    public void updateLevels(){
        if(player.getFeet().getMinY() <= level.getTopBorder().getMaxY()){
            increaseLevel();
        }

        if(player.getHead().getMaxY() >= level.getBottomBorder().getMinY()){
            reduceLevel();
        }
    }

    private void increaseLevel() {
        currrentLevel++;

        level = loadLevel(currrentLevel);

        player.setY(panelHeight - player.getHeight() - 5);

        System.out.println("Zwiekszono level: " + currrentLevel);
    }


    private void reduceLevel() {
        if (currrentLevel <= 1) return;

        currrentLevel--;

        level = loadLevel(currrentLevel);

        player.setY(5);

        System.out.println("Zmniejszono level: " + currrentLevel);
    }

}
