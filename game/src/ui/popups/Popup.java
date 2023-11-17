package ui.popups;

import java.awt.Color;
import java.awt.Graphics;

public class Popup {
    protected int width = 400;
    protected int height = 600;
    protected Color backgroundColor = Color.gray;
    protected int windowWidth;
    protected int windowHeight;

    public Popup(int mapWidth, int mapHeight) {
        this.windowWidth = mapWidth;
        this.windowHeight = mapHeight;
    }

    public void render(Graphics graphics) {
        graphics.setColor(backgroundColor);
        // draw on center
        int x = windowWidth / 2 - width / 2;
        int y = windowHeight / 2 - height / 2;
        graphics.fillRect(x, y, width, height);
    }
}
