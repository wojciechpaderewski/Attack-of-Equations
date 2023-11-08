package GameObjects.Player;

import java.awt.Color;
import java.awt.Graphics;

import GameObjects.Texture;
import Interface.PowerLevel;
import Shapes.Rect;

public class PlayerTexture extends Texture {
    private Rect rect;
    private PowerLevel powerLevel;

    public PlayerTexture(Rect rect, PowerLevel powerLevel) {
        this.rect = rect;
        this.color = Color.white;
        this.powerLevel = powerLevel;
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        // draw current power level on center of rect
        graphics.setColor(Color.black);
        String powerLevelString = Integer.toString(powerLevel.getCurrentPowerLevel());
        graphics.setFont(graphics.getFont().deriveFont(20f));
        graphics.drawString(powerLevelString, rect.getX() + rect.getWidth() / 2 - 6, rect.getY() + rect.getHeight() / 2 + 6);
    }
}
