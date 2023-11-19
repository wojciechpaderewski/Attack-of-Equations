package ui.popups;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Popup {
    protected int width = 400;
    protected int height = 600;
    protected Color borderColor = Color.white;
    protected int windowWidth;
    protected int windowHeight;
    protected int borderSize = 5;

    public Popup(int mapWidth, int mapHeight) {
        this.windowWidth = mapWidth;
        this.windowHeight = mapHeight;
    }

    public void render(Graphics graphics) {
        // draw on center
        int x = windowWidth / 2 - width / 2;
        int y = windowHeight / 2 - height / 2;
        
        graphics.setColor(borderColor);
        graphics.fillRect(x, y, width, height);
        
        drawBackground(graphics);
        graphics.fillRect(x + borderSize, y + borderSize, width - 2 * borderSize, height - 2 * borderSize);
    }

    protected void drawBackground(Graphics graphics) {
        // draw gradient background magenta and black
        Color magenta = new Color(255, 0, 255);
        Color black = new Color(0, 0, 0);
        int x = windowWidth / 2 - width / 2;
        int y = windowHeight / 2 - height / 2;
        GradientPaint gradient = new GradientPaint(x, y, magenta, x + width, y + height, black);
        
        if (graphics instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setPaint(gradient);
        }
    }
}
