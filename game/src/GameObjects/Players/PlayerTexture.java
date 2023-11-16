package GameObjects.Players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import GameObjects.Texture;
import ui.Score;


public class PlayerTexture implements Texture {
    private Rectangle rect;
    private Score score;
    private int width, height;
    private Color color;

    public PlayerTexture(Rectangle rect, Score powerLevel) {
        this.rect = rect;
        this.color = Color.white;
        this.score = powerLevel;
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
        String powerLevelString = Integer.toString(score.getCurrentScore());
        graphics.setFont(graphics.getFont().deriveFont(20f));
        int stringWidth = graphics.getFontMetrics().stringWidth(powerLevelString);
        int stringHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(powerLevelString, x + width / 2 - stringWidth / 2, y + height / 2 + stringHeight / 2);
    }
}
