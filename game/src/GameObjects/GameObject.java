package GameObjects;

import java.awt.Graphics;
import java.awt.Shape;

public abstract class GameObject {
    protected int x, y;
    protected int velX = 0, velY = 0;
    protected Shape shape;

    public GameObject(Shape shape) {
        this.shape = shape;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);

    public Shape getShape() {
        return shape;
    }
}
