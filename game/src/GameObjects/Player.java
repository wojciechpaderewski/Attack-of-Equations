package GameObjects;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
    static int width = 32, height = 32;
    static Color color = Color.white;

    public Player(int x, int y) {
        super(new Shapes.Rect(x, y, width, height));
    }

    public void tick() {
        shape.setX(shape.getX() + (int) velX);
        shape.setY(shape.getY() + (int) velY);
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(shape.getX(), shape.getY(), 32, 32);
    }
}