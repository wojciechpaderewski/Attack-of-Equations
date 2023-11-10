package GameObjects.Enemies;

import java.awt.Color;
import java.awt.Rectangle;

import GameObjects.Texture;

public class EnemyTexture implements Texture {
    private Rectangle rect;
    private int width, height;
    private Color color;

    EnemyTexture(Rectangle rect) {
        this.rect = rect;
        this.color = Color.GREEN;
        this.width = (int) rect.getWidth();
        this.height = (int) rect.getHeight();
    }

    public void tick() {

    }

    public void render(java.awt.Graphics graphics) {
        graphics.setColor(color);
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        graphics.fillRect(x, y, width, height);
    }
}
