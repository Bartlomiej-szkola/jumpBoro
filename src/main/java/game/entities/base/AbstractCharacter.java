package game.entities.base;

public abstract class AbstractCharacter {
    protected int x, y, width, height;

    public abstract void moveX(double dx);
    public abstract void moveY(double dy);

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
