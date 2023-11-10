package GameObjects.Players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import GameObjects.Texture;
import Interface.PowerLevel;


public class PlayerTexture implements Texture {
    private Rectangle rect;
    private PowerLevel powerLevel;
    private int width, height;
    private Color color;

    public PlayerTexture(Rectangle rect, PowerLevel powerLevel) {
        this.rect = rect;
        this.color = Color.white;
        this.powerLevel = powerLevel;
        this.width = (int) rect.getWidth();
        this.height = (int) rect.getHeight();
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        graphics.fillRect(x, y, width, height);
        // draw current power level on center of rect
        graphics.setColor(Color.black);
        String powerLevelString = Integer.toString(powerLevel.getCurrentPowerLevel());
        graphics.setFont(graphics.getFont().deriveFont(20f));
        graphics.drawString(powerLevelString, x + width / 2 - 5, y + height / 2 + 5);
    }
}
