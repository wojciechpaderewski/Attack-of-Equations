package ui;

import java.awt.Color;
import java.awt.Graphics;

public class GameMap {
    private int width, height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void renderMap(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);
    }
}
