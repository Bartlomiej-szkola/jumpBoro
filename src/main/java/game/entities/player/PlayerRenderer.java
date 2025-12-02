package game.entities.player;

import java.awt.Graphics;

public class PlayerRenderer {
    private final Player player;

    public PlayerRenderer(Player player) {
        this.player = player;
    }

    public void render(Graphics g) {
        player.draw(g);
    }
}
