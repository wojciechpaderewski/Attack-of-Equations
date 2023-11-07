package GameObjects;

import java.awt.Graphics;
import Shapes.Shape;

public abstract class GameObject {
    protected float velX = 0, velY = 0;
    protected Shape shape;

    public GameObject(Shape shape) {
        this.shape = shape;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);
    public boolean isInBounds(int x, int y) {
        return shape.isInBounds(x, y);
    }
}
