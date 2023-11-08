package GameObjects;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Texture {
    protected Color color = Color.white;

    public abstract void render(Graphics graphics);
    public abstract void tick();
}
