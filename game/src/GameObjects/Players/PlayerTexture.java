package GameObjects.Players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import GameObjects.Texture;
import ui.Score;


public class PlayerTexture implements Texture {
    private Rectangle rect;
    private Score score;
    private int width, height;
    private BufferedImage playerImg;

    public PlayerTexture(Rectangle rect, Score powerLevel) {
        this.rect = rect;
        this.score = powerLevel;
        this.width = (int) rect.getWidth();
        this.height = (int) rect.getHeight();
        this.playerImg = importImg();
    }

    private BufferedImage importImg() {
        InputStream is = getClass().getResourceAsStream("../../assets/player.png");
        try {
            BufferedImage img = javax.imageio.ImageIO.read(is);
            return img;
        } catch (Exception e) {
            System.out.println("Error importing player.png");
            return null;
        }
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        graphics.drawImage(playerImg, x, y, null);
        // draw current power level on center of rect
        graphics.setColor(Color.WHITE);
        String powerLevelString = Integer.toString(score.getCurrentScore());
        graphics.setFont(graphics.getFont().deriveFont(20f));
        int stringWidth = graphics.getFontMetrics().stringWidth(powerLevelString);
        int stringHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(powerLevelString, x + width / 2 - stringWidth / 2, y + height / 2 + stringHeight / 3);
    }
}
